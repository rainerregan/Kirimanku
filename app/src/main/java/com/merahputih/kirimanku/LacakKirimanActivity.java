package com.merahputih.kirimanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.merahputih.kirimanku.adapters.JenisKurirSpinnerAdapter;
import com.merahputih.kirimanku.oop_classes.JenisKurir;
import com.merahputih.kirimanku.rajaongkir.RajaOngkirFunctions;
import com.merahputih.kirimanku.retrofit_api.GetProvinceAPI;
import com.merahputih.kirimanku.retrofit_api.RetrofitClient;
import com.merahputih.kirimanku.retrofit_api.WaybillAPI;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LacakKirimanActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<JenisKurir> jenisKurirList = new ArrayList<>();
    Spinner jenisKurirSpinner;
    JenisKurirSpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lacak_kiriman);

        // Set Views
        jenisKurirSpinner = findViewById(R.id.jenisKurirSpinner);

        // Populate List dengan Data Jenis Kurir
        populateJenisKurirList();

        // Set Adapter
        spinnerAdapter = new JenisKurirSpinnerAdapter(this, jenisKurirList);
        jenisKurirSpinner.setAdapter(spinnerAdapter);
        jenisKurirSpinner.setOnItemSelectedListener(this);

        Log.i("RAJAONGKIR_CERT", RajaOngkirFunctions.getCertificate(this));

        getData();

    }

    private void getData() {
        Retrofit retrofit = RetrofitClient.getClient("https://pro.rajaongkir.com/api/");

        WaybillAPI waybillAPI = retrofit.create(WaybillAPI.class);

        Call<ResponseBody> call = waybillAPI.getWaybillData(
                "002707225497",
                "sicepat",
                BuildConfig.ANDROID_KEY,
                BuildConfig.RAJAONGKIR_API_KEY
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("JSON RESPONSE", response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LacakKirimanActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataProvinsi() {
        Retrofit retrofit = RetrofitClient.getClient("https://pro.rajaongkir.com/api/");

        GetProvinceAPI getProvinceAPI = retrofit.create(GetProvinceAPI.class);

        Call<ResponseBody> call = getProvinceAPI.getProvince(
                "6",
                "1fe1d701d3586534ca5e233f01c2b21159473532;com.merahputih.kirimanku",
                BuildConfig.RAJAONGKIR_API_KEY
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("JSON RESPONSE", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("JSON ERROR", t.getMessage());
            }
        });
    }

    private void populateJenisKurirList() {
        jenisKurirList.add(new JenisKurir("Pos Indonesia", R.drawable.pos_indonesia, "pos"));
        jenisKurirList.add(new JenisKurir("JNE", R.drawable.jne, "jne"));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Selected: " + jenisKurirList.get(position).getNama(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}