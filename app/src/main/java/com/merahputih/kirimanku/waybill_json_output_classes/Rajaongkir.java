package com.merahputih.kirimanku.waybill_json_output_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rajaongkir {

    @SerializedName("query")
    @Expose
    private Query query;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("result")
    @Expose
    private Result result;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Rajaongkir{" +
                "query=" + query +
                ", status=" + status +
                ", result=" + result +
                '}';
    }
}