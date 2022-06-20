package com.yarsi.leprosycare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DBPerawatan extends SQLiteOpenHelper {

    //Bikin inner class buatbuat mengatur nama table, nama kolom dan query
    static public class MyColums implements BaseColumns {
    //nama tabel dan kolom
        static public final String namaTabel = "Perawatan";
        static final String hari = "Hari";
        static final String jam = "Jam";
        static final String menit = "Menit";
        static final String jenis_Perawatan = "Jenis_perawatan";
        static final String namaObat = "NamaObat";
        static final String namaObat2 = "NamaObat2";
        static final String namaObat3 = "NamaObat3";
        static final String namaObat4 = "NamaObat4";
        static final String namaObat5 = "NamaObat5";
        static final String perawatanPenuh = "PerawatanPenuh";

    }

    private static final String namaDB = "MDTRegimens.db";
    private static final int versiDB = 14;

    //query untuk membuat tabel
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + MyColums.namaTabel+
            "("+ MyColums.jenis_Perawatan+" TEXT PRIMARY KEY, "+ MyColums.jam+" TEXT NOT NULL, "+
            MyColums.menit+" TEXT NOT NULL, "+ MyColums.hari+" TEXT NOT NULL, "+
            MyColums.namaObat+ " TEXT NOT NULL, "+ MyColums.namaObat2+" TEXT NOT NULL, "+
            MyColums.namaObat3+" TEXT, "+ MyColums.namaObat4+ " TEXT NOT NULL, "+
            MyColums.namaObat5+" TEXT, "+ MyColums.perawatanPenuh+" TEXT NOT NULL)";

    //Query yang digunakan untuk mengupgrade Tabel
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ MyColums.namaTabel;

    public DBPerawatan(Context context) {
        super(context, namaDB, null, versiDB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        Log.d(TAG,"Table dbuat");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void tambahData(DataAlarm dataAlarm){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBPerawatan.MyColums.hari, dataAlarm.getHari());
        values.put(DBPerawatan.MyColums.jam, dataAlarm.getJam());
        values.put(DBPerawatan.MyColums.menit, dataAlarm.getMenit());
        values.put(DBPerawatan.MyColums.jenis_Perawatan, dataAlarm.getJudul());
        values.put(DBPerawatan.MyColums.namaObat, dataAlarm.getNamaObat());
        values.put(DBPerawatan.MyColums.namaObat2, dataAlarm.getNamaObat2());
        values.put(DBPerawatan.MyColums.namaObat3, dataAlarm.getNamaObat3());
        values.put(DBPerawatan.MyColums.namaObat4, dataAlarm.getNamaObat4());
        values.put(DBPerawatan.MyColums.namaObat5, dataAlarm.getNamaObat5());
        values.put(DBPerawatan.MyColums.perawatanPenuh, dataAlarm.getPerawatanPenuh());

        db.insert(MyColums.namaTabel, null, values);
        db.close();
    }

    public int getPerawatanCount(){
        String countQuery = "SELECT * FROM " + MyColums.namaTabel;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public ArrayList<DataAlarm> getSemuaData(){
        ArrayList<DataAlarm> dataAlarms = new ArrayList<>();
        String query = "SELECT * FROM "+ MyColums.namaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                DataAlarm dataAlarm = new DataAlarm(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3)+"",cursor.getString(4),cursor.getString(5),
                        cursor.getString(6),cursor.getString(7),cursor.getString(8),
                        cursor.getString(9));
                dataAlarms.add(dataAlarm);
            }while (cursor.moveToNext());
        }
        return dataAlarms;

    }
}
