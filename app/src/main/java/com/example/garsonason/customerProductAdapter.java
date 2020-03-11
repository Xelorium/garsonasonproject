package com.example.garsonason;

public class customerProductAdapter {
    private String date;
    private String urunAdi;
    private String urunFiyat;
    private String urunTipi;
    public customerProductAdapter(){

    }
    public  customerProductAdapter(String date, String urunAdi, String urunFiyat, String urunTipi){
        this.date=date;
        this.urunAdi=urunAdi;
        this.urunFiyat=urunFiyat;
        this.urunTipi=urunTipi;
    }
    public String getkayitTarihi() {
        return date;
    }

    public String geturunAdi() {
        return urunAdi;
    }

    public String geturunFiyat() {
        return urunFiyat;
    }
    public String geturunTipi() {
        return urunTipi;
    }

}
