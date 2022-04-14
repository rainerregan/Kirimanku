package com.merahputih.kirimanku.retrofit_api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GetProvinceAPI {
    @GET("province")
    Call<ResponseBody> getProvince(
            @Query("province") String province,
            @Query("android-key") String android_key,
            @Header("key") String API_KEY
    );
}
