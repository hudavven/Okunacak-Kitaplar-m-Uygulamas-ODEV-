package com.example.mobil_programlama_odev.Activityler;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.mobil_programlama_odev.Classlar.UserViewModel;
import com.example.mobil_programlama_odev.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class KitapUygulamaActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kitap_uygulama);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarRengi));
        }

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // MainActivity'den gelen kullanıcı bilgileri
        String email = getIntent().getStringExtra("email");
        String uid = getIntent().getStringExtra("uid");

        userViewModel.setEmail(email);
        userViewModel.setUid(uid);

        bottomNav = findViewById(R.id.bottomNav);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);

        NavigationUI.setupWithNavController(bottomNav,navHostFragment.getNavController());



    }
}