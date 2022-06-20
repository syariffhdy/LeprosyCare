package com.yarsi.leprosycare;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class DataDiriActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout lanjut;
    private EditText nm;
    private int GALLERY = 1, CAMERA = 2;
    private ImageView imgView;
    private Bitmap imgS;
    private static final String IMAGE_DIRECTORY = "/userimage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMultiplePermissions();
        setContentView(R.layout.activity_data_diri);
        imgView = findViewById(R.id.imgView);
        lanjut = findViewById(R.id.btnlanjut);
        lanjut.setOnClickListener(this);
        imgView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlanjut:
                nm = (EditText)findViewById(R.id.nama);
                if(!nm.getText().toString().equalsIgnoreCase("")){
                    DBUser user = new DBUser(this);
                    user.tambahData(nm.getText().toString(),setImage(imgS),"0");
                    //Intent i = new Intent(this,MainActivity.class);
                    Log.d("TAG", String.valueOf(imgS));
                    Log.d("TAG", String.valueOf(nm.getText()));
                    byte[] arrImg = user.getData().getBlob(user.getData().getColumnIndex("fotouser"));
                    Bitmap bitmap = BitmapFactory.decodeByteArray(arrImg, 0, arrImg.length);
                    ImageView imgview = findViewById(R.id.imgView2);
                    imgview.setImageBitmap(bitmap);
                    //startActivity(i);
                }else{
                    Toast.makeText(this,"Masukan Nama terlebih dahulu",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.imgView:
                showPictureDialog();
                break;
        }
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    imgS = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(imgS);
                    Toast.makeText(DataDiriActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imgView.setImageBitmap(imgS);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(DataDiriActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            try {
                Bitmap imgS = (Bitmap) data.getExtras().get("data");
                imgView.setImageBitmap(imgS);
                saveImage(imgS);
                Toast.makeText(DataDiriActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        setImage(myBitmap);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved" + f.getAbsolutePath());
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    public byte[] setImage(Bitmap image){
        if(image != null){
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            byte[] byteArray = bytes.toByteArray();
            return byteArray;
        }else{
            image = BitmapFactory.decodeResource(getResources(), R.mipmap.camera);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            byte[] byteArray = bytes.toByteArray();
            return byteArray;
        }
    }
}
