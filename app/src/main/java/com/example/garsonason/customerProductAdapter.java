package com.example.garsonason;

public class customerProductAdapter {
    private String date;
    private String urunAdi;
    private String urunFiyat;
    private String urunTipi;
    private int mQuantity;



    public customerProductAdapter() {

    }

    public customerProductAdapter(String date, String urunAdi, String urunFiyat, String urunTipi, int mQuantity) {
        this.date = date;
        this.urunAdi = urunAdi;
        this.urunFiyat = urunFiyat;
        this.urunTipi = urunTipi;
        this.mQuantity = mQuantity;
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

    public int getmQuantity(){
        return mQuantity;
    }

    public void addToQuantity(){
        this.mQuantity += 1;
    }

    public void removeFromQuantity(){
        if(this.mQuantity > 1){
            this.mQuantity -= 1;
        }
    }

}
