package com.yarsi.leprosycare;

public class DataAlarm {


    private String hari;
    private String jam;
    private String menit;
    private String judul;
    private String namaObat;
    private String namaObat2;
    private String namaObat3;
    private String namaObat4;
    private String namaObat5;
    private String perawatanPenuh;
    private boolean selected;

    public DataAlarm() {
        this.hari = "";
        this.jam = "";
        this.menit = "";
        this.judul = "";
        this.namaObat = "";
        this.namaObat2 = "";
        this.namaObat3 = "";
        this.namaObat4 = "";
        this.namaObat5 = "";
        this.perawatanPenuh = "";
        this.selected = false;
    }

    public DataAlarm(String judul, String jam, String menit, String hari, String namaObat, String namaObat2,
                     String namaObat3, String namaObat4, String namaObat5, String perawatanPenuh) {
        this.hari = hari;
        this.jam = jam;
        this.menit = menit;
        this.judul = judul;
        this.namaObat = namaObat;
        this.namaObat2 = namaObat2;
        this.namaObat3 = namaObat3;
        this.namaObat4 = namaObat4;
        this.namaObat5 = namaObat5;
        this.perawatanPenuh = perawatanPenuh;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }


    public String getMenit() {
        return menit;
    }

    public void setMenit(String menit) {
        this.menit = menit;
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getNamaObat2() {
        return namaObat2;
    }

    public void setNamaObat2(String namaObat2) {
        this.namaObat2 = namaObat2;
    }

    public String getNamaObat3() {
        return namaObat3;
    }

    public void setNamaObat3(String namaObat3) {
        this.namaObat3 = namaObat3;
    }

    public String getNamaObat4() {
        return namaObat4;
    }

    public void setNamaObat4(String namaObat4) {
        this.namaObat4 = namaObat4;
    }

    public String getNamaObat5() {
        return namaObat5;
    }

    public void setNamaObat5(String namaObat5) {
        this.namaObat5 = namaObat5;
    }

    public String getPerawatanPenuh() {
        return perawatanPenuh;
    }

    public void setPerawatanPenuh(String perawatanPenuh) {
        this.perawatanPenuh = perawatanPenuh;
    }
}
