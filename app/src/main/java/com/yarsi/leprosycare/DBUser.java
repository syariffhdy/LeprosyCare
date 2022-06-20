package com.yarsi.leprosycare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DBUser extends SQLiteOpenHelper {
    private static final String namaDB = "DBUser.db";
    private static final int versiDB = 14;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " +MyColums.namaTabel+
            "("+MyColums.namauser+" TEXT PRIMARY KEY, " +MyColums.fotouser+" BLOB, "+MyColums.poin+" TEXT)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ MyColums.namaTabel;

    static public class MyColums implements BaseColumns {
        //nama tabel dan kolom
        static public final String namaTabel = "user";
        static public final String namauser = "namauser";
        static public final String fotouser = "fotouser";
        static public final String poin = "poin";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d(TAG,"Table dbuat");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public DBUser(Context context){
        super(context, namaDB, null, versiDB);
    }

    public boolean tambahData(String name, byte[] img, String poin){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyColums.namauser,name);
        values.put(MyColums.fotouser,img);
        values.put(MyColums.poin,poin);
        long result = db.insert(MyColums.namaTabel,null,values);
        if(result == -1){
            return false;
        }else{
            Log.d(TAG,"Table dbuat");
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from "+MyColums.namaTabel;
        Cursor data = db.rawQuery(query,null);
        return data;
    }
}
