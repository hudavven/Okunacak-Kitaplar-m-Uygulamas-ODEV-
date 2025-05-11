package com.example.mobil_programlama_odev.KitapDeğerlendirmeleri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_odev.R;

import java.util.List;

public class BitenKitapAdapter extends RecyclerView.Adapter<BitenKitapAdapter.ViewHolder> {
    private List<BitenKitap> kitaplar;

    public BitenKitapAdapter(List<BitenKitap> kitaplar) {
        this.kitaplar = kitaplar;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKitapAdi, tvKitapYazari, tvGunSayisi, tvYorum;

        public ViewHolder(View itemView) {
            super(itemView);
            tvKitapAdi = itemView.findViewById(R.id.tvKitapAdi);
            tvKitapYazari = itemView.findViewById(R.id.tvKitapYazari);
            tvGunSayisi = itemView.findViewById(R.id.tvGunSayisi);
            tvYorum = itemView.findViewById(R.id.tvYorum);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_biten_kitap, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BitenKitap kitap = kitaplar.get(position);
        holder.tvKitapAdi.setText(kitap.getKitap_adi());
        holder.tvKitapYazari.setText("Yazar: " + kitap.getKitap_yazari());
        holder.tvGunSayisi.setText("Gün: " + kitap.getBitirme_gunu());
        holder.tvYorum.setText("Yorum: " + kitap.getYorum());
    }

    @Override
    public int getItemCount() {
        return kitaplar.size();
    }
}

