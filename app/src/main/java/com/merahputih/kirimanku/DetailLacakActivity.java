package com.merahputih.kirimanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.merahputih.kirimanku.oop_classes.DataLacak;
import com.merahputih.kirimanku.oop_classes.JenisKurir;

public class DetailLacakActivity extends AppCompatActivity implements View.OnClickListener {
    // Views
    TextView judulLacak;
    ImageView backButton;
    TextView detailLacakNamaKurirTextView, detailLacakKodeKurirTextView, detailLacakNomorResiTextView;
    ImageView detailLacakFotoKurirImageView;

    // Kurir Data
    DataLacak dataLacak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lacak);

        // Instantiate Views
        judulLacak = findViewById(R.id.judulLacak);
        backButton = findViewById(R.id.backButton);
        detailLacakNamaKurirTextView = findViewById(R.id.detailLacakNamaKurirTextView);
        detailLacakKodeKurirTextView = findViewById(R.id.detailLacakKodeKurirTextView);
        detailLacakNomorResiTextView = findViewById(R.id.detailLacakNomorResiTextView);
        detailLacakFotoKurirImageView = findViewById(R.id.detailLacakFotoKurirImageView);

        // Set Views Properties
        backButton.setOnClickListener(this);

        // Get PutExtra
        getKurirDataFromPutExtra();

        // Set Data
        setData();

    }

    /**
     * Mendapatkan data kurir dan waybill dan dimasukkan dalam class di global
     */
    private void getKurirDataFromPutExtra() {
        Intent intent = getIntent();
        String waybill = intent.getStringExtra("waybill");
        String kode = intent.getStringExtra("kode");
        String kurir = intent.getStringExtra("kurir");
        Integer imageKurir = intent.getIntExtra("kurir_image", 0);

        JenisKurir dataKurir = new JenisKurir(kurir, imageKurir, kode);

        dataLacak = new DataLacak(waybill, dataKurir);
    }

    /**
     * Set data untuk halaman detail Lacak
     */
    private void setData() {
        // Set Header Info
        detailLacakNomorResiTextView.setText(dataLacak.getWaybill());
        detailLacakNamaKurirTextView.setText("Nama Kurir: " + dataLacak.getKurirData().getNama());
        detailLacakKodeKurirTextView.setText("Kode Kurir: " + dataLacak.getKurirData().getKode());
        detailLacakFotoKurirImageView.setImageResource(dataLacak.getKurirData().getImage());

    }

    public void back(){
        this.finish();
    }

    @Override
    public void onClick(View v) {
        if (v == backButton){
            back();
        }
    }
}