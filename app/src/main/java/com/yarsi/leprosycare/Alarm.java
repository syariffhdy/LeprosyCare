package com.yarsi.leprosycare;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Alarm extends AppCompatActivity {

    private DateFormat jamFormat = new SimpleDateFormat("HH");
    private DateFormat menitFormat = new SimpleDateFormat("mm");
    private ArrayList<DataAlarm> dataSet;
    private TimePicker jam;
    private String[] perawatan = {"PB adult treatment", "PB child treatment", "MB adult treatment",
            "MB child treatment"};
    private Spinner spin;
    private Button simpan;
    private String setJenisPerawatan, setNamaObat,setNamaObat2,setNamaObat3,setNamaObat4,setNamaObat5, setPerawatanPenuh, setHariKe,setJam,setMenit;
    private Animation animFadeIn;

    //engatur text data perawatan
    private TextView obat;
    private TextView obat2;
    private TextView obat3;
    private TextView obat4;
    private TextView obat5;
    private TextView totalperawatan;
    private LinearLayout layout_data;

    //Variable Untuk Inisialisasi Database DBMahasiswa
    private DBPerawatan dbPerawatan;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        simpan = (Button) findViewById(R.id.btn_simpan);
        jam = (TimePicker) findViewById(R.id.jam);
        spin = (Spinner) findViewById(R.id.spinnerPerawatan);
        jam.setIs24HourView(true);

        setJam = jamFormat.format(new Date());
        setMenit = menitFormat.format(new Date());
        //menemukan id tampilan data obat
        obat = (TextView) findViewById(R.id.namaObat);
        obat2 = (TextView) findViewById(R.id.namaObat2);
        obat3 = (TextView) findViewById(R.id.namaObat3);
        obat4 = (TextView) findViewById(R.id.namaObat4);
        obat5 = (TextView) findViewById(R.id.namaObat5);
        totalperawatan = (TextView) findViewById(R.id.perawatanPenuh);
        layout_data = (LinearLayout) findViewById(R.id.data);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        //Inisialisasi dan Mendapatkan Konteks dari DBPerawatan
        dbPerawatan = new DBPerawatan(this);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item,
                perawatan);

        spin.setAdapter(adapter);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setJenisPerawatan = spin.getSelectedItem().toString();
                obat.setVisibility(View.VISIBLE);
                obat.startAnimation(animFadeIn);
                obat2.setVisibility(View.VISIBLE);
                obat2.startAnimation(animFadeIn);
                obat3.setVisibility(View.VISIBLE);
                obat3.startAnimation(animFadeIn);
                obat4.setVisibility(View.VISIBLE);
                obat4.startAnimation(animFadeIn);
                obat5.setVisibility(View.VISIBLE);
                obat5.startAnimation(animFadeIn);
                layout_data.setVisibility(View.VISIBLE);
                layout_data.startAnimation(animFadeIn);
                totalperawatan.setVisibility(View.VISIBLE);
                totalperawatan.startAnimation(animFadeIn);
                if (adapter.getItem(i).equalsIgnoreCase("PB adult treatment")){
                    //Set Data untuk di masukan ke database
                    setNamaObat = "2 capsul rifampicin (300 mg X 2)";
                    setNamaObat2 = "1 tablet dapsone (100mg)";
                    setNamaObat3 = " ";
                    setNamaObat4 = "1 tablet dapsone (100 mg)";
                    setNamaObat5 = " ";
                    setPerawatanPenuh = "6 bungkus blister";

                    //set Tampilan
                    obat.setText("- 2 capsul rifampicin (300 mg X 2)");
                    obat2.setText("- 1 tablet dapsone (100mg)");
                    obat3.setText(" ");
                    obat4.setText("- 1 tablet dapsone (100 mg)");
                    obat5.setText(" ");
                    totalperawatan.setText("6 bungkus blister");
                }else if (adapter.getItem(i).equalsIgnoreCase("PB child treatment")){
                    //Set Data untuk di masukan ke database
                    setNamaObat = "2 capsul rifampicin (300 mg + 150 mg)";
                    setNamaObat2 = "1 tablet dapsone (50mg)";
                    setNamaObat3 = " ";
                    setNamaObat4 = "1 tablet dapsone (50 mg)";
                    setNamaObat5 = " ";
                    setPerawatanPenuh = "6 bungkus blister";

                    //set Tampilan
                    obat.setText("- 2 capsul rifampicin (300 mg + 150mg)");
                    obat2.setText("- 1 tablet dapsone (50mg)");
                    obat3.setText(" ");
                    obat4.setText("- 1 tablet dapsone(50 mg)");
                    obat5.setText(" ");
                    totalperawatan.setText("6 bungkus blister");
                }else if (adapter.getItem(i).equalsIgnoreCase("MB adult treatment")){
                    //Set Data untuk di masukan ke database
                    setNamaObat = "2 capsul rifampicin (300 mg X 2)";
                    setNamaObat2 = "3 capsul clofazimine (100mg X 3)";
                    setNamaObat3 = "1 tablet dapsone (100mg)";
                    setNamaObat4 = "1 capsul clofazimine (50mg)";
                    setNamaObat5 = "1 tablet dapsone (100 mg)";
                    setPerawatanPenuh = "12 bungkus blister";

                    //set Tampilan
                    obat.setText("- 2 capsul rifampicin (300 mg X 2)");
                    obat2.setText("- 3 capsul clofazimine (100mg X 3)");
                    obat3.setText("- 1 tablet dapsone (100mg)");
                    obat4.setText("- 1 capsul clofazimine (50mg)");
                    obat5.setText("- 1 tablet dapson (100 mg)");
                    totalperawatan.setText("12 bungkus blister");
                }else if (adapter.getItem(i).equalsIgnoreCase("MB child treatment")){
                    //Set Data untuk di masukan ke database
                    setNamaObat = "2 capsul rifampicin (300 mg + 150mg)";
                    setNamaObat2 = "3 capsul clofazimine (50mg X 3)";
                    setNamaObat3 = "1 tablet dapsone (50mg)";
                    setNamaObat4 = "1 capsul clofazimine (50mg)";
                    setNamaObat5 = "1 tablet dapsone (50 mg)";
                    setPerawatanPenuh = "12 bungkus blister";

                    //set Tampilan
                    obat.setText("- 2 capsul rifampicin (300 mg + 150mg)");
                    obat2.setText("- 3 capsul clofazimine (50mg X 3)");
                    obat3.setText("- 1 tablet dapsone (50mg)");
                    obat4.setText("- 1 capsul clofazimine (50mg)");
                    obat5.setText("- 1 tablet dapson (50 mg)");
                    totalperawatan.setText("12 bungkus blister");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        jam.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int j, int menit) {
                //Toast.makeText(Alarm.this,"jam "+j+" : "+menit, Toast.LENGTH_LONG).show();
                if (j<10){
                    setJam = "0"+j;
                }
                if(menit<10){
                    setMenit = "0"+menit;
                }
                if (j>9){
                    setJam = ""+j;
                }if (menit>9){
                    setMenit = ""+menit;
                }

            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanData();
                startActivity(new Intent(Alarm.this, MainActivity.class));
            }
        });

    }

    //simpan data2 ke database
    private void simpanData(){
        String cek = "";

        SQLiteDatabase readData = dbPerawatan.getReadableDatabase();
        SQLiteDatabase create = dbPerawatan.getWritableDatabase();
        Cursor cursor = readData.rawQuery("SELECT * FROM "+ DBPerawatan.MyColums.namaTabel, null);
        cursor.moveToFirst();

        int i=0;
        do {
            if (cursor.getCount()==0){
                cek = "buat";
                break;
            }

            if (setJenisPerawatan.equalsIgnoreCase(cursor.getString(i))){
                cek = "ada"; break;
            }else { cek = "buat";
            }
        }while (cursor.moveToNext());


        if (cek.equalsIgnoreCase("ada")){
            Toast.makeText(this, "GAGAL ! Jenis Perawatan sudah dibuat !", Toast.LENGTH_SHORT).show();
        }else if(cek.equalsIgnoreCase("buat")){
            //membuat map baru yang berisi nama kolomdan data yang ingin dimasukan
            ContentValues values = new ContentValues();
            values.put(DBPerawatan.MyColums.hari, "1");
            values.put(DBPerawatan.MyColums.jam, setJam);
            values.put(DBPerawatan.MyColums.menit, setMenit);
            values.put(DBPerawatan.MyColums.jenis_Perawatan, setJenisPerawatan);
            values.put(DBPerawatan.MyColums.namaObat, setNamaObat);
            values.put(DBPerawatan.MyColums.namaObat2, setNamaObat2);
            values.put(DBPerawatan.MyColums.namaObat3, setNamaObat3);
            values.put(DBPerawatan.MyColums.namaObat4, setNamaObat4);
            values.put(DBPerawatan.MyColums.namaObat5, setNamaObat5);
            values.put(DBPerawatan.MyColums.perawatanPenuh, setPerawatanPenuh);

            //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
            create.insert(DBPerawatan.MyColums.namaTabel,null, values);
            create.close();
            Toast.makeText(this, "Berhasil Menyimpan Pengingat !", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Alarm.this, MainActivity.class));

    }
}
