package com.merahputih.kirimanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.merahputih.kirimanku.callbacks.GetDataProvinsiCallback;
import com.merahputih.kirimanku.provinsi_json_output_classes.Rajaongkir;
import com.merahputih.kirimanku.provinsi_json_output_classes.Result;
import com.merahputih.kirimanku.rajaongkir.RajaOngkirFunctions;
import com.techiness.progressdialoglibrary.ProgressDialog;

import java.util.ArrayList;

public class CekOngkirActivity extends AppCompatActivity {

    Spinner provinsiAsalSpinner;

    ArrayList<String> provinsiList = new ArrayList<>();
    ArrayList<String> provinsiIdList = new ArrayList<>();

    ArrayAdapter<String> provinsiArrayAdapter;

    ProgressDialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_ongkir);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
        progressDialog.show();

        setViews();
        populateProvinsiSpinner();

        RajaOngkirFunctions.getDataProvinsi(new GetDataProvinsiCallback() {
            @Override
            public void onSuccess(Rajaongkir response) {
                int progress = 0;
                int size = response.getResults().size();
                for(Result result : response.getResults()){
                    provinsiList.add(result.getProvince());
                    provinsiIdList.add(result.getProvinceId());

                    progress++;
                    progressDialog.setProgress((progress/size) * 100);
                }
                populateProvinsiSpinner();
                progressDialog.dismiss();
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(CekOngkirActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateProvinsiSpinner() {
        provinsiArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinsiList);
        provinsiAsalSpinner.setAdapter(provinsiArrayAdapter);

//        progressDialog.dismiss();
    }

    private void setViews() {
        provinsiAsalSpinner = findViewById(R.id.provinsiAsalSpinner);
    }
}