package com.example.mobil_programlama_odev.Activityler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobil_programlama_odev.Activityler.KayitOlActivity;
import com.example.mobil_programlama_odev.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvCreateAccount;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){
            Intent intent = new Intent(MainActivity.this, KitapUygulamaActivity.class);
            intent.putExtra("email", firebaseUser.getEmail());
            intent.putExtra("uid", firebaseUser.getUid());
            startActivity(intent);
            finish();
        }


        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Lütfen Tüm Alanları Doldurun", Toast.LENGTH_SHORT).show();
            } else {

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            mailOnaylamaKontrol();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Hesap Bulunamadı!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        tvCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, KayitOlActivity.class);
            startActivity(intent);
        });


    }

    public void mailOnaylamaKontrol(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser.isEmailVerified()==true){
            Intent intent = new Intent(MainActivity.this, KitapUygulamaActivity.class);
            intent.putExtra("email", firebaseUser.getEmail());
            intent.putExtra("uid", firebaseUser.getUid());
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Mail Hesabınızdan Doğrulama İşlemini Yapınız!", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
