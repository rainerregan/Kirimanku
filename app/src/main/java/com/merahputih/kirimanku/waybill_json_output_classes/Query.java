package com.merahputih.kirimanku.waybill_json_output_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("waybill")
    @Expose
    private String waybill;
    @SerializedName("courier")
    @Expose
    private String courier;
    @SerializedName("android-key")
    @Expose
    private String androidKey;

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getAndroidKey() {
        return androidKey;
    }

    public void setAndroidKey(String androidKey) {
        this.androidKey = androidKey;
    }

}