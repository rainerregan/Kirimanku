package com.merahputih.kirimanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    // Views
    LinearLayout lacakKirimanLayoutButton;
    LinearLayout cekOngkirLayoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Assign Views
        lacakKirimanLayoutButton = findViewById(R.id.lacakKirimanLayoutButton);
        cekOngkirLayoutButton = findViewById(R.id.cekOngkirLayoutButton);

        // Assign On Click Listener
        lacakKirimanLayoutButton.setOnClickListener(this);
        cekOngkirLayoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == cekOngkirLayoutButton){
//            Toast.makeText(this, "Layanan ini belum tersedia, tunggu update selanjutnya", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, CekOngkirActivity.class));
        } else if(v == lacakKirimanLayoutButton){
            startActivity(new Intent(HomeActivity.this, LacakKirimanActivity.class));
        }
    }
}