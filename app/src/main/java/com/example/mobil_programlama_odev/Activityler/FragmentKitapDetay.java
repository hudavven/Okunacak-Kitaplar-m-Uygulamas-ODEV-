package com.example.mobil_programlama_odev.Activityler;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobil_programlama_odev.KitapTakip.Kitap;
import com.example.mobil_programlama_odev.R;
import com.google.firebase.Timestamp; // Firestore için zaman damgası
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FragmentKitapDetay extends Fragment {
    TextView titleText, authorText, descriptionText, pageCountText, publishedDateText;
    Button buttonOkumayaBasla;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kitap_detay, container, false);
        TextView titleText = view.findViewById(R.id.detailTitle);
        TextView authorText = view.findViewById(R.id.detailAuthor);
        TextView descriptionText = view.findViewById(R.id.detailDescription);
        TextView pageCountText = view.findViewById(R.id.detailPageCount);
        TextView publishedDateText = view.findViewById(R.id.detailPublishedDate);

        buttonOkumayaBasla = view.findViewById(R.id.addToListButton);

        Bundle args = getArguments();
        if (args != null) {
            titleText.setText(args.getString("title"));
            authorText.setText(args.getString("authors"));
            descriptionText.setText(args.getString("description"));
            pageCountText.setText("Sayfa Sayısı: " + args.getInt("pageCount", 0));
            publishedDateText.setText("Yayın Tarihi: " + args.getString("publishedDate"));
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        buttonOkumayaBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    String userId = user.getUid();
                    String baslangicTarihi = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                    // Kitap nesnesi oluştur
                    Kitap kitap = new Kitap(
                            args.getString("title"),
                            args.getString("authors"),
                            baslangicTarihi,
                            null // kitap_bitis null
                    );

                    db.collection("okunan_kitaplar")
                            .document(userId)
                            .collection("kitaplarim")
                            .add(kitap)
                            .addOnSuccessListener(documentReference -> {
                                Toast.makeText(v.getContext(), "Kitap takibe alındı!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(v.getContext(), "Hata: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });




        return view;
    }

}