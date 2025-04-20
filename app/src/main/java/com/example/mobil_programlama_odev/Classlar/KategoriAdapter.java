package com.example.mobil_programlama_odev.Classlar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_odev.R;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder> {

    private List<Kategori> kategoriList;

    public KategoriAdapter(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
    }

    @NonNull
    @Override
    public KategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori_item, parent, false);
        return new KategoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriViewHolder holder, int position) {
        Kategori kategori = kategoriList.get(position);
        holder.kategoriAd.setText(kategori.getAd());
        holder.kategoriResim.setImageResource(kategori.getResimId());

        holder.itemView.setOnClickListener(v -> {
            // Kategoriye tıklandığında KitaplarFragment'e geçiş yap
            NavController navController = Navigation.findNavController(v);
            Bundle bundle = new Bundle();
            bundle.putString("kategori_ad", kategori.getAd());

            navController.navigate(R.id.action_fragmentKatalog_to_fragmentKitaplar, bundle);
        });
    }


    @Override
    public int getItemCount() {
        return kategoriList.size();
    }

    public static class KategoriViewHolder extends RecyclerView.ViewHolder {
        TextView kategoriAd;
        ImageView kategoriResim;

        public KategoriViewHolder(@NonNull View itemView) {
            super(itemView);
            kategoriAd = itemView.findViewById(R.id.kategoriAd);
            kategoriResim = itemView.findViewById(R.id.kategoriResim);
        }
    }
}

