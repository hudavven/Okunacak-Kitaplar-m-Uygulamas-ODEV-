package com.example.mobil_programlama_odev.Activityler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mobil_programlama_odev.Classlar.UserViewModel;
import com.example.mobil_programlama_odev.KitapTakip.Kitap;
import com.example.mobil_programlama_odev.KitapTakip.KitapAdapter;
import com.example.mobil_programlama_odev.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FragmentKitaplarim extends Fragment {
    private RecyclerView recyclerView;
    private KitapAdapter adapter;
    private List<Kitap> kitapList = new ArrayList<>();
    private FirebaseFirestore db;
    private FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kitaplarim, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewKitaplar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String userId = user.getUid();

            db.collection("okunan_kitaplar")
                    .document(userId)
                    .collection("kitaplarim")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        kitapList.clear();
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            Kitap kitap = doc.toObject(Kitap.class);
                            kitap.setDocumentId(doc.getId()); // 🔴 burada documentId'yi set ediyoruz
                            kitapList.add(kitap);
                        }
                        adapter = new KitapAdapter(getContext(), kitapList, userId);
                        recyclerView.setAdapter(adapter);
                    });
        }

        return view;
    }
}
