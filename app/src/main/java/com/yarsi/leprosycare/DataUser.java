package com.yarsi.leprosycare;

import android.graphics.Bitmap;

import java.io.Serializable;

public class DataUser implements Serializable {
    private String nama;
    private String poin;
    private Bitmap bitmap;

    public DataUser(String nama, Bitmap bitmap, String poin){
        this.nama = nama;
        this.bitmap = bitmap;
        this.poin = poin;
    }

    public DataUser(){
        this("",null,"");
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
