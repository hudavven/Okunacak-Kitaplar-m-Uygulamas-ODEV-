package com.example.mobil_programlama_odev.Activityler;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobil_programlama_odev.R;


public class FragmentKitaplar extends Fragment {
    private Toolbar toolbar;
    private static final String ARG_KATEGORI_AD = "kategori_ad";

    public static FragmentKitaplar newInstance(String kategoriAd) {
        FragmentKitaplar fragment = new FragmentKitaplar();
        Bundle args = new Bundle();
        args.putString(ARG_KATEGORI_AD, kategoriAd);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kitaplar, container, false);

        toolbar = view.findViewById(R.id.toolbar4);
        String kategoriAd = getArguments().getString(ARG_KATEGORI_AD);

        toolbar.setTitle(kategoriAd);
        return view;
    }
}