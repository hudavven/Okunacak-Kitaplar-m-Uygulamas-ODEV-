package com.example.mobil_programlama_odev.KitapTakip;

public class Kitap {
    private String documentId;
    private String kitap_adi;
    private String kitap_yazari;
    private String kitap_baslangic;
    private String kitap_bitis;

    public Kitap() {
    }
    public Kitap(String kitap_adi, String kitap_yazari, String kitap_baslangic, String kitap_bitis) {
        this.kitap_adi = kitap_adi;
        this.kitap_yazari = kitap_yazari;
        this.kitap_baslangic = kitap_baslangic;
        this.kitap_bitis = kitap_bitis;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public String getKitap_yazari() {
        return kitap_yazari;
    }

    public void setKitap_yazari(String kitap_yazari) {
        this.kitap_yazari = kitap_yazari;
    }

    public String getKitap_baslangic() {
        return kitap_baslangic;
    }

    public void setKitap_baslangic(String kitap_baslangic) {
        this.kitap_baslangic = kitap_baslangic;
    }

    public String getKitap_bitis() {
        return kitap_bitis;
    }

    public void setKitap_bitis(String kitap_bitis) {
        this.kitap_bitis = kitap_bitis;
    }
}

