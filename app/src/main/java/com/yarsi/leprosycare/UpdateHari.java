package com.yarsi.leprosycare;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UpdateHari extends AppCompatActivity {
    private DBPerawatan myDb;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hari);

        myDb = new DBPerawatan(getBaseContext());
        btnUpdate = (Button) findViewById(R.id.update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpdate();
                startActivity(new Intent(UpdateHari.this, MainActivity.class));
                finish();
            }
        });
    }


    private void setUpdate(){
        String getHari = getIntent().getExtras().getString("SendHARI");
        String getJUDUL = getIntent().getExtras().getString("sendJUDUL");
        int setNewHari = Integer.parseInt(getHari);
        if (setNewHari == 28){
            setNewHari = 1;
        }else {
            setNewHari = setNewHari+1;
        }

        SQLiteDatabase database = myDb.getReadableDatabase();

        //memasukan data baru pada kolom hari
        ContentValues v = new ContentValues();
        v.put(DBPerawatan.MyColums.hari, setNewHari+"");

        //menentukan data item yg diubah berdasarkan hari
        String selection = DBPerawatan.MyColums.jenis_Perawatan + " LIKE ?";
        String[] selectionArgs = {getJUDUL};
        database.update(DBPerawatan.MyColums.namaTabel, v, selection, selectionArgs);
        Toast.makeText(getApplicationContext(), "berhasil update", Toast.LENGTH_SHORT).show();
    }
}
