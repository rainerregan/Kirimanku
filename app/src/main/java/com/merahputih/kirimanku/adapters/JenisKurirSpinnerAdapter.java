package com.merahputih.kirimanku.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.merahputih.kirimanku.R;
import com.merahputih.kirimanku.oop_classes.JenisKurir;

import java.util.ArrayList;

public class JenisKurirSpinnerAdapter extends ArrayAdapter<JenisKurir> {

    Context context;
    ArrayList<JenisKurir> jenisKurirList;

    public JenisKurirSpinnerAdapter(@NonNull Context context, ArrayList<JenisKurir> jenisKurirList) {
        super(context, R.layout.jenis_kurir_spinner_item);
        this.context = context;
        this.jenisKurirList = jenisKurirList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater.from(context));
        View row = inflater.inflate(R.layout.jenis_kurir_spinner_item, null);
        TextView nama = (TextView) row.findViewById(R.id.jenisKurirSpinnerItemTextView);
        ImageView image = (ImageView) row.findViewById(R.id.jenisKurirSpinnerItemImageView);

        // Set Data
        nama.setText(jenisKurirList.get(position).getNama());
        image.setImageResource(jenisKurirList.get(position).getImage());

        return row;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater.from(context));
        View row = inflater.inflate(R.layout.jenis_kurir_spinner_item, null);
        TextView nama = (TextView) row.findViewById(R.id.jenisKurirSpinnerItemTextView);
        ImageView image = (ImageView) row.findViewById(R.id.jenisKurirSpinnerItemImageView);

        // Set Data
        nama.setText(jenisKurirList.get(position).getNama());
        image.setImageResource(jenisKurirList.get(position).getImage());

        return row;
    }

    @Nullable
    @Override
    public JenisKurir getItem(int position) {
        return jenisKurirList.get(position);
    }

    @Override
    public int getCount() {
        return jenisKurirList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
