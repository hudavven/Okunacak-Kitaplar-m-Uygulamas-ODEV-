package com.example.mobil_programlama_odev.Activityler;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobil_programlama_odev.Classlar.Kategori;
import com.example.mobil_programlama_odev.Classlar.KategoriAdapter;
import com.example.mobil_programlama_odev.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentKatalog extends Fragment {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private KategoriAdapter adapter;
    private List<Kategori> kategoriList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_katalog, container, false);
        toolbar= view.findViewById(R.id.toolbar2);
        setHasOptionsMenu(true);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        recyclerView = view.findViewById(R.id.recyclerViewKategori);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        kategoriList = new ArrayList<>();
        kategoriList.add(new Kategori("Roman", R.drawable.roman));
        kategoriList.add(new Kategori("Bilim Kurgu", R.drawable.bilimkurgu));
        kategoriList.add(new Kategori("Kişisel Gelişim", R.drawable.kisiselgelisim));
        kategoriList.add(new Kategori("Tarih", R.drawable.tarih));

        adapter = new KategoriAdapter(kategoriList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_arama, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
