package com.merahputih.kirimanku.rajaongkir;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import com.merahputih.kirimanku.BuildConfig;
import com.merahputih.kirimanku.callbacks.GetDataLacakCallback;
import com.merahputih.kirimanku.callbacks.GetDataProvinsiCallback;
import com.merahputih.kirimanku.provinsi_json_output_classes.Rajaongkir;
import com.merahputih.kirimanku.provinsi_json_output_classes.ResponseProvinsi;
import com.merahputih.kirimanku.retrofit_api.GetProvinceAPI;
import com.merahputih.kirimanku.retrofit_api.RetrofitClient;
import com.merahputih.kirimanku.retrofit_api.WaybillAPI;
import com.merahputih.kirimanku.waybill_json_output_classes.ResponseWaybill;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RajaOngkirFunctions {

    /**
     * Mendapatkan data certificate dari aplikasi android
     * @param context
     * @return
     */
    public static String getCertificate(Context context) {
        String certificate = null;
        PackageManager pm = context.getPackageManager();
        String packageName = context.getPackageName();
        int flags = PackageManager.GET_SIGNATURES;
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
        }
        Signature[] signatures = packageInfo.signatures;
        byte[] cert = signatures[0].toByteArray();
        InputStream input = new ByteArrayInputStream(cert);
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        X509Certificate c = null;
        try {
            c = (X509Certificate) cf.generateCertificate(input);
        } catch (CertificateException e) {
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(c.getPublicKey().getEncoded());
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i]);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
            }
            certificate = hexString.toString();
        } catch (NoSuchAlgorithmException e1) {
        }
        return certificate + ";" + packageName;
    }


    /**
     *
     * @param nomorResi
     * @param kodeKurir
     * @param callback
     */
    public static void getWaybillData(String nomorResi, String kodeKurir, GetDataLacakCallback callback) {
        Retrofit retrofit = RetrofitClient.getClient("https://pro.rajaongkir.com/api/");

        WaybillAPI waybillAPI = retrofit.create(WaybillAPI.class);

        Call<ResponseWaybill> call = waybillAPI.getWaybillData(
                nomorResi,
                kodeKurir,
                BuildConfig.ANDROID_KEY,
                BuildConfig.RAJAONGKIR_API_KEY
        );

        call.enqueue(new Callback<ResponseWaybill>() {

            @Override
            public void onResponse(Call<ResponseWaybill> call, Response<ResponseWaybill> response) {
                try {
                    callback.onSuccess(response.body().rajaongkir);
                } catch (Exception e) {
                    callback.onFailed(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseWaybill> call, Throwable t) {
//                Toast.makeText(LacakKirimanActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                callback.onFailed(new Exception(t));
            }

        });
    }

    /**
     * Get Data Provinsi List
     */
    public static void getDataProvinsi(GetDataProvinsiCallback callback) {
        Retrofit retrofit = RetrofitClient.getClient("https://pro.rajaongkir.com/api/");

        GetProvinceAPI getProvinceAPI = retrofit.create(GetProvinceAPI.class);

        Call<ResponseProvinsi> call = getProvinceAPI.getProvince(
                BuildConfig.ANDROID_KEY,
                BuildConfig.RAJAONGKIR_API_KEY
        );

        call.enqueue(new Callback<ResponseProvinsi>() {
            @Override
            public void onResponse(Call<ResponseProvinsi> call, Response<ResponseProvinsi> response) {
                try {
//                    Log.i("JSON RESPONSE", response.body().getRajaongkir().toString());
                    callback.onSuccess(response.body().getRajaongkir());
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFailed(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseProvinsi> call, Throwable t) {
                Log.i("JSON ERROR", t.getMessage() + ": " + call.request().toString());
                callback.onFailed(new Exception(t));
            }
        });
    }

}
