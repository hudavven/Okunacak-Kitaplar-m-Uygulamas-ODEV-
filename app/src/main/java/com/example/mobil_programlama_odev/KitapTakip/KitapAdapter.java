package com.example.mobil_programlama_odev.KitapTakip;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_odev.KitapDeğerlendirmeleri.BitenKitap;
import com.example.mobil_programlama_odev.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class KitapAdapter extends RecyclerView.Adapter<KitapAdapter.KitapViewHolder> {
    private List<Kitap> kitapList;
    private Context context;
    private String userId;

    public KitapAdapter(Context context, List<Kitap> kitapList, String userId) {
        this.context = context;
        this.kitapList = kitapList;
        this.userId = userId;
    }

    @NonNull
    @Override
    public KitapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kitap, parent, false);
        return new KitapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KitapViewHolder holder, int position) {
        Kitap kitap = kitapList.get(position);
        holder.kitapAdi.setText(kitap.getKitap_adi());
        holder.kitapYazari.setText(kitap.getKitap_yazari());

        String baslangic = kitap.getKitap_baslangic();
        long gun = hesaplaGecenGunSayisi(baslangic);
        holder.gecenGun.setText(gun + " gündür okuyorsunuz.");

        holder.bitirButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Değerlendirmenizi Girin");

            final EditText input = new EditText(context);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            builder.setView(input);

            builder.setPositiveButton("Kaydet", (dialog, which) -> {
                String yorum = input.getText().toString();
                String bugun = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                long gecenGun = hesaplaGecenGunSayisi(baslangic);

                BitenKitap bitenKitap = new BitenKitap( kitap.getKitap_adi(),kitap.getKitap_yazari(),gecenGun,yorum);


                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("biten_kitaplar")
                        .document(userId)
                        .collection("kitaplar")
                        .add(bitenKitap)
                        .addOnSuccessListener(ref -> {
                            // 🔴 Başarıyla eklendiyse okunan_kitaplar'dan sil
                            db.collection("okunan_kitaplar")
                                    .document(userId)
                                    .collection("kitaplarim")
                                    .document(kitap.getDocumentId())
                                    .delete()
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(context, "Tebrikler! Kitap Değerlendirmeniz Kaydedildi.", Toast.LENGTH_SHORT).show();
                                        kitapList.remove(holder.getAdapterPosition());
                                        notifyItemRemoved(holder.getAdapterPosition());
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(context, "Silme hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(context, "Hata: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            });

            builder.setNegativeButton("İptal", (dialog, which) -> dialog.cancel());
            builder.show();
        });

    }

    @Override
    public int getItemCount() {
        return kitapList.size();
    }

    public static class KitapViewHolder extends RecyclerView.ViewHolder {
        TextView kitapAdi, kitapYazari, gecenGun;
        Button bitirButton;

        public KitapViewHolder(@NonNull View itemView) {
            super(itemView);
            kitapAdi = itemView.findViewById(R.id.tvKitapAdi);
            kitapYazari = itemView.findViewById(R.id.tvKitapYazari);
            gecenGun = itemView.findViewById(R.id.tvGecenGun);
            bitirButton = itemView.findViewById(R.id.btnBitir);
        }
    }

    private long hesaplaGecenGunSayisi(String tarihStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date baslangicTarihi = sdf.parse(tarihStr);
            long fark = new Date().getTime() - baslangicTarihi.getTime();
            return TimeUnit.MILLISECONDS.toDays(fark);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}


