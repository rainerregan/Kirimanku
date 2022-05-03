package com.merahputih.kirimanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.merahputih.kirimanku.adapters.JenisKurirSpinnerAdapter;
import com.merahputih.kirimanku.callbacks.GetDataLacakCallback;
import com.merahputih.kirimanku.oop_classes.JenisKurir;
import com.merahputih.kirimanku.rajaongkir.RajaOngkirFunctions;
import com.merahputih.kirimanku.retrofit_api.GetProvinceAPI;
import com.merahputih.kirimanku.retrofit_api.RetrofitClient;
import com.merahputih.kirimanku.retrofit_api.WaybillAPI;
import com.merahputih.kirimanku.waybill_json_output_classes.Rajaongkir;
import com.merahputih.kirimanku.waybill_json_output_classes.ResponseWaybill;

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

    // Views
    EditText nomorResiEditText;
    Button lacakButton;
    ImageView backButton;

    // Spinner Selected
    JenisKurir selectedJenisKurir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lacak_kiriman);

        // Set Views
        jenisKurirSpinner = findViewById(R.id.jenisKurirSpinner);
        nomorResiEditText = findViewById(R.id.nomorResiEditText);
        lacakButton = findViewById(R.id.buttonLacak);
        backButton = findViewById(R.id.backButton);

        // Populate List dengan Data Jenis Kurir
        populateJenisKurirList();

        // Set Adapter
        spinnerAdapter = new JenisKurirSpinnerAdapter(this, jenisKurirList);
        jenisKurirSpinner.setAdapter(spinnerAdapter);
        jenisKurirSpinner.setOnItemSelectedListener(this);

        // Set Click Listener untuk Button Lacak
        lacakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validasiInput()){
                    openWaybillDetailActivity();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LacakKirimanActivity.this.finish();
            }
        });

    }

    private boolean validasiInput() {
        boolean status = true;

        if(nomorResiEditText.getText().toString().trim().equalsIgnoreCase("")){
            nomorResiEditText.setError("Mohon masukkan nomor resi");
            status = false;
        }else if(!nomorResiEditText.getText().toString().matches("^[a-zA-Z0-9]*$")){
            nomorResiEditText.setError("Input hanya menerima angka dan huruf");
            status = false;
        }

        return status;
    }

    private void openWaybillDetailActivity() {
        Intent detailLacakIntent = new Intent(LacakKirimanActivity.this, DetailLacakActivity.class);
        detailLacakIntent.putExtra("waybill", nomorResiEditText.getText().toString());
        detailLacakIntent.putExtra("kode", selectedJenisKurir.getKode());
        detailLacakIntent.putExtra("kurir", selectedJenisKurir.getNama());
        detailLacakIntent.putExtra("kurir_image", selectedJenisKurir.getImage());
        startActivity(detailLacakIntent);
    }

    private void populateJenisKurirList() {
        jenisKurirList.add(new JenisKurir("Pos Indonesia", R.drawable.pos_indonesia, "pos"));
        jenisKurirList.add(new JenisKurir("JNE", R.drawable.jne, "jne"));
        jenisKurirList.add(new JenisKurir("SiCepat Express", R.drawable.sicepat, "sicepat"));
        jenisKurirList.add(new JenisKurir("JNT", R.drawable.jnt, "jnt"));
        jenisKurirList.add(new JenisKurir("AnterAja", R.drawable.anteraja, "anteraja"));
        jenisKurirList.add(new JenisKurir("Ninja", R.drawable.ninja, "ninja"));
        jenisKurirList.add(new JenisKurir("Wahana", R.drawable.wahana, "wahana"));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedJenisKurir = jenisKurirList.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}