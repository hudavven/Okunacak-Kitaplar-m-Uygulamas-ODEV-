package com.example.mobil_programlama_odev.KitapDeğerlendirmeleri;

public class BitenKitap {
    private String kitap_adi;
    private String kitap_yazari;
    private long bitirme_gunu;
    private String yorum;

    public BitenKitap() {} // Firestore için boş constructor

    public BitenKitap(String kitap_adi, String kitap_yazari, long bitirme_gunu, String yorum) {
        this.kitap_adi = kitap_adi;
        this.kitap_yazari = kitap_yazari;
        this.bitirme_gunu = bitirme_gunu;
        this.yorum = yorum;
    }

    // Getter ve setter'lar
    public String getKitap_adi() { return kitap_adi; }
    public void setKitap_adi(String kitap_adi) { this.kitap_adi = kitap_adi; }

    public String getKitap_yazari() { return kitap_yazari; }
    public void setKitap_yazari(String kitap_yazari) { this.kitap_yazari = kitap_yazari; }

    public long getBitirme_gunu() { return bitirme_gunu; }
    public void setBitirme_gunu(long bitirme_gunu) { this.bitirme_gunu = bitirme_gunu; }

    public String getYorum() { return yorum; }
    public void setYorum(String yorum) { this.yorum = yorum; }
}

