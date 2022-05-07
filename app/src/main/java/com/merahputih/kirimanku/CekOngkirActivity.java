package com.merahputih.kirimanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.merahputih.kirimanku.callbacks.GetDataProvinsiCallback;
import com.merahputih.kirimanku.provinsi_json_output_classes.Rajaongkir;
import com.merahputih.kirimanku.provinsi_json_output_classes.Result;
import com.merahputih.kirimanku.rajaongkir.RajaOngkirFunctions;

public class CekOngkirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_ongkir);

        RajaOngkirFunctions.getDataProvinsi(new GetDataProvinsiCallback() {
            @Override
            public void onSuccess(Rajaongkir response) {
//                Log.i("Provinsi", response.toString());
//                String province = "";
//                for(Result result : response.getResults()){
//                    province += result.getProvince() + ",";
//                }
//                Toast.makeText(CekOngkirActivity.this, province, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(Exception e) {
//                Log.e("Get Provinsi", e.getMessage());
            }
        });
    }
}