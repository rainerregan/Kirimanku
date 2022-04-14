package com.merahputih.kirimanku.retrofit_api;

import com.merahputih.kirimanku.waybill_json_output_classes.Rajaongkir;
import com.merahputih.kirimanku.waybill_json_output_classes.ResponseWaybill;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WaybillAPI {
    @FormUrlEncoded
    @POST("waybill")
    Call<ResponseWaybill> getWaybillData(
            @Field("waybill") String waybill,
            @Field("courier") String courier,
            @Field("android-key") String android_key,
            @Header("key") String apiKey
    );
}
