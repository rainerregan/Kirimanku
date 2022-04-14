package com.merahputih.kirimanku.oop_classes;

public class JenisKurir {

    private String nama;
    private Integer image;
    private String kode;

    public JenisKurir(String nama, Integer image, String kode) {
        this.nama = nama;
        this.image = image;
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
