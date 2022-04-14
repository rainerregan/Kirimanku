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

import java.util.ArrayList;

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