package com.merahputih.kirimanku.retrofit_api;

import com.merahputih.kirimanku.provinsi_json_output_classes.Rajaongkir;
import com.merahputih.kirimanku.provinsi_json_output_classes.ResponseProvinsi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GetProvinceAPI {
    @GET("province")
    Call<ResponseProvinsi> getProvince(
            @Query("android-key") String android_key,
            @Header("key") String API_KEY
    );
}
