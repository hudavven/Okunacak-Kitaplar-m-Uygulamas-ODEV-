package com.example.mobil_programlama_odev.Activityler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobil_programlama_odev.Classlar.UserViewModel;
import com.example.mobil_programlama_odev.R;


public class FragmentProfil extends Fragment {
    private UserViewModel userViewModel;

    private TextView emaill,uidd;
    private Toolbar toolbar;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Menü kullanacağız
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        emaill = view.findViewById(R.id.email);
        uidd = view.findViewById(R.id.uid);

        toolbar = view.findViewById(R.id.toolbar);
        setHasOptionsMenu(true);
        toolbar.setTitle("Kullanıcı Ara...");
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);



        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        // Email ve UID'yi ayrı ayrı gözlemle, geldiğinde TextView'e yaz
        userViewModel.getEmail().observe(getViewLifecycleOwner(), email -> {
            userViewModel.getUid().observe(getViewLifecycleOwner(), uid -> {
                String mail = email;
                String id = uid;

                emaill.setText("Mail : "+mail);
                uidd.setText("İD : "+uid);

            });
        });




        return view;
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_arama, menu); // Menü dosyanı bağla
        super.onCreateOptionsMenu(menu, inflater);
    }

}