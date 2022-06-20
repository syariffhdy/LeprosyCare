package com.yarsi.leprosycare;

import java.io.Serializable;

public class Data implements Serializable {
    private String nama;
    private String[] isi;

    public Data(String nama, String[] isi){
        this.nama = nama;
        this.isi = isi;
    }

    public Data(){
        this("",new String[]{});
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String[] getIsi() {
        return isi;
    }

    public void setIsi(String[] isi) {
        this.isi = isi;
    }

    public int countIsi(){
        return isi.length;
    }
}
