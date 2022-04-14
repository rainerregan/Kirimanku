package com.merahputih.kirimanku.waybill_json_output_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WaybillOutput {

    @SerializedName("rajaongkir")
    @Expose
    private Rajaongkir rajaongkir;

    public Rajaongkir getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(Rajaongkir rajaongkir) {
        this.rajaongkir = rajaongkir;
    }

}