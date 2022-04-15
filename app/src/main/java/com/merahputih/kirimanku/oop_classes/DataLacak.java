package com.merahputih.kirimanku.oop_classes;

import com.merahputih.kirimanku.waybill_json_output_classes.Rajaongkir;

public class DataLacak {
    private String waybill;
    private JenisKurir kurirData;
    private Rajaongkir detailLacak;

    public DataLacak(String waybill, JenisKurir kurirData, Rajaongkir detailLacak) {
        this.waybill = waybill;
        this.kurirData = kurirData;
        this.detailLacak = detailLacak;
    }

    public DataLacak(String waybill, JenisKurir kurirData) {
        this.waybill = waybill;
        this.kurirData = kurirData;
    }

    public DataLacak() {
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public JenisKurir getKurirData() {
        return kurirData;
    }

    public void setKurirData(JenisKurir kurirData) {
        this.kurirData = kurirData;
    }

    public Rajaongkir getDetailLacak() {
        return detailLacak;
    }

    public void setDetailLacak(Rajaongkir detailLacak) {
        this.detailLacak = detailLacak;
    }
}
