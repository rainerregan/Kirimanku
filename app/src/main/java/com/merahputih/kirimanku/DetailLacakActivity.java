package com.merahputih.kirimanku;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.merahputih.kirimanku.adapters.DetailRiwayatKirimanRecyclerAdapter;
import com.merahputih.kirimanku.callbacks.GetDataLacakCallback;
import com.merahputih.kirimanku.oop_classes.DataLacak;
import com.merahputih.kirimanku.oop_classes.JenisKurir;
import com.merahputih.kirimanku.rajaongkir.RajaOngkirFunctions;
import com.merahputih.kirimanku.waybill_json_output_classes.Manifest;
import com.merahputih.kirimanku.waybill_json_output_classes.Rajaongkir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aglibs.loading.skeleton.layout.SkeletonLinearLayout;
import aglibs.loading.skeleton.layout.SkeletonRecyclerView;

public class DetailLacakActivity extends AppCompatActivity implements View.OnClickListener {
    // Recycler
    private DetailRiwayatKirimanRecyclerAdapter adapter;
    private List<Manifest> manifests;

    // Views
    TextView judulLacak;
    ImageView backButton;
    TextView detailLacakNamaKurirTextView, detailLacakKodeKurirTextView, detailLacakNomorResiTextView,
            jenisLayananKurirTextView, detailKirimanStatus, detailKirimanNamaPengirim, detailKirimanAlamatPengirim, detailKirimanNamaPenerima,
            detailKirimanAlamatPenerima;
    ImageView detailLacakFotoKurirImageView;

    // Skeleton
    RecyclerView recyclerViewDetail;
    SkeletonLinearLayout skeletonParentLayout;

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
        jenisLayananKurirTextView = findViewById(R.id.jenisLayananKurirTextView);
        skeletonParentLayout = findViewById(R.id.skeletonParentLayout);
        recyclerViewDetail = findViewById(R.id.recyclerViewDetail);
        detailKirimanStatus = findViewById(R.id.detailKirimanStatus);
        detailKirimanNamaPengirim = findViewById(R.id.detailKirimanNamaPengirim);
        detailKirimanAlamatPengirim = findViewById(R.id.detailKirimanAlamatPengirim);
        detailKirimanNamaPenerima = findViewById(R.id.detailKirimanNamaPenerima);
        detailKirimanAlamatPenerima = findViewById(R.id.detailKirimanAlamatPenerima);

        // Set Views Properties
        backButton.setOnClickListener(this);
        recyclerViewDetail.setNestedScrollingEnabled(false);

        // Set RecyclerView
        setRecyclerView();

        // Get PutExtra
        getDetailData();
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDetail.setLayoutManager(layoutManager);
        adapter = new DetailRiwayatKirimanRecyclerAdapter(manifests, this);
        recyclerViewDetail.setAdapter(adapter);
    }

    /**
     * Mendapatkan data kurir dan waybill dan dimasukkan dalam class di global
     */
    private void getDetailData() {
        Intent intent = getIntent();
        String waybill = intent.getStringExtra("waybill");
        String kode = intent.getStringExtra("kode");
        String kurir = intent.getStringExtra("kurir");
        Integer imageKurir = intent.getIntExtra("kurir_image", 0);

        JenisKurir dataKurir = new JenisKurir(kurir, imageKurir, kode);
        
        RajaOngkirFunctions.getWaybillData(waybill, kode, new GetDataLacakCallback() {
            @Override
            public void onSuccess(Rajaongkir response) {
                dataLacak = new DataLacak(waybill, dataKurir, response);
                if(response.getStatus().getCode() == 200) {
                    manifests = response.getResult().getManifest();
                    Collections.reverse(manifests);
                    setData();
                } else {
                    Toast.makeText(DetailLacakActivity.this, response.getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(DetailLacakActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                openMessagePopUp(judulLacak, "Data tidak ditemukan");
            }
        });

    }

    private void openMessagePopUp(View view, String message){
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.message_pop_up_layout, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        TextView messageTextView = popupView.findViewById(R.id.popUpMessageTextView);
        Button tutupButton = popupView.findViewById(R.id.closePopUpButton);
        messageTextView.setText(message);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        tutupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                DetailLacakActivity.this.finish();
            }
        });

    }

    /**
     * Set data untuk halaman detail Lacak
     */
    private void setData() {
        if(dataLacak != null) {
            // Set Header Info
            detailLacakNomorResiTextView.setText(dataLacak.getWaybill());
            detailLacakNamaKurirTextView.setText(dataLacak.getDetailLacak().getResult().getSummary().getCourierName());
            detailLacakKodeKurirTextView.setText(dataLacak.getDetailLacak().getResult().getSummary().getCourierCode());
            detailLacakFotoKurirImageView.setImageResource(dataLacak.getKurirData().getImage());
            jenisLayananKurirTextView.setText(dataLacak.getDetailLacak().getResult().getSummary().getServiceCode());
            detailKirimanStatus.setText(dataLacak.getDetailLacak().getResult().getSummary().getStatus());
            detailKirimanNamaPengirim.setText(dataLacak.getDetailLacak().getResult().getSummary().getShipperName());
            detailKirimanAlamatPengirim.setText(dataLacak.getDetailLacak().getResult().getSummary().getOrigin());
            detailKirimanNamaPenerima.setText(dataLacak.getDetailLacak().getResult().getSummary().getReceiverName());
            detailKirimanAlamatPenerima.setText(dataLacak.getDetailLacak().getResult().getSummary().getDestination());

            setRecyclerView();

            skeletonParentLayout.stopLoading();
        } else{
            Toast.makeText(this, "Terjadi kesalahan sistem", Toast.LENGTH_SHORT).show();
        }
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