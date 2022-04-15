package com.merahputih.kirimanku.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.merahputih.kirimanku.R;
import com.merahputih.kirimanku.waybill_json_output_classes.Manifest;

import java.util.ArrayList;
import java.util.List;

public class DetailRiwayatKirimanRecyclerAdapter extends RecyclerView.Adapter<DetailRiwayatKirimanRecyclerAdapter.ManifestHolder> {
    private List<Manifest> manifestArrayList;
    private Context context;

    public DetailRiwayatKirimanRecyclerAdapter(List<Manifest> manifestArrayList, Context context) {
        this.manifestArrayList = manifestArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ManifestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.kiriman_detail_recycler_item, parent, false);
        return new ManifestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManifestHolder holder, int position) {
        final Manifest manifest = manifestArrayList.get(position);

        // Set the data to the views here
        holder.setItemManifestCode(manifest.getManifestCode());
        holder.setItemManifestDescription(manifest.getManifestDescription());
        holder.setItemManifestDate(manifest.getManifestDate());
        holder.setItemManifestTime(manifest.getManifestTime());
        holder.setItemManifestCityName(manifest.getCityName());
        holder.setBulletImageView(position);

        // You can set click listners to indvidual items in the viewholder here
        // make sure you pass down the listner or make the Data members of the viewHolder public
    }

    @Override
    public int getItemCount() {
        return manifestArrayList == null? 0: manifestArrayList.size();
    }

    public class ManifestHolder extends RecyclerView.ViewHolder{

        private TextView itemManifestCode;
        private TextView itemManifestDescription;
        private TextView itemManifestDate;
        private TextView itemManifestTime;
        private TextView itemManifestCityName;
        private ImageView bulletImageView;

        public ManifestHolder(@NonNull View itemView) {
            super(itemView);

            itemManifestCode = itemView.findViewById(R.id.itemManifestCode);
            itemManifestDescription = itemView.findViewById(R.id.itemManifestDescription);
            itemManifestDate = itemView.findViewById(R.id.itemManifestDate);
            itemManifestTime = itemView.findViewById(R.id.itemManifestTime);
            itemManifestCityName = itemView.findViewById(R.id.itemManifestCityName);
            bulletImageView = itemView.findViewById(R.id.bulletImageView);
        }

        public void setItemManifestCode(String itemManifestCode) {
            this.itemManifestCode.setText(itemManifestCode);
        }

        public void setItemManifestDescription(String itemManifestDescription) {
            this.itemManifestDescription.setText(itemManifestDescription);
        }

        public void setItemManifestDate(String itemManifestDate) {
            this.itemManifestDate.setText(itemManifestDate);
        }

        public void setItemManifestTime(String itemManifestTime) {
            this.itemManifestTime.setText(itemManifestTime);
        }

        public void setItemManifestCityName(String itemManifestCityName) {
            this.itemManifestCityName.setText("Kota: " + itemManifestCityName);
        }

        public void setBulletImageView(int pos){
            if(pos == 0){
                this.bulletImageView.setImageResource(R.drawable.top_bullet);
            } else if(pos == manifestArrayList.size() -1){
                this.bulletImageView.setImageResource(R.drawable.bottom_bullet);
            } else{
                this.bulletImageView.setImageResource(R.drawable.middle_bullet);
            }
        }
    }
}
