package com.example.mobil_programlama_odev.Activityler;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobil_programlama_odev.KitapDeğerlendirmeleri.BitenKitap;
import com.example.mobil_programlama_odev.KitapDeğerlendirmeleri.BitenKitapAdapter;
import com.example.mobil_programlama_odev.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FragmentKisiDegerlendirme extends Fragment {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<BitenKitap> kitapList;
    private BitenKitapAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kisi_degerlendirme, container, false);

        toolbar = view.findViewById(R.id.toolbar5);
        recyclerView = view.findViewById(R.id.degerlendirmeRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kitapList = new ArrayList<>();
        adapter = new BitenKitapAdapter(kitapList);
        recyclerView.setAdapter(adapter);

        Bundle args = getArguments();
        if (args != null) {
            String uid = args.getString("uid");
            toolbar.setTitle(uid);
            Log.e("uid", uid);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("biten_kitaplar")
                    .document(uid)
                    .collection("kitaplar")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        kitapList.clear();
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            BitenKitap kitap = doc.toObject(BitenKitap.class);
                            kitapList.add(kitap);
                        }
                        adapter.notifyDataSetChanged();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Hata: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }

        return view;
    }
}
