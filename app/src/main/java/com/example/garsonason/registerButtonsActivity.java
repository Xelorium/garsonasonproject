package com.example.garsonason;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class registerButtonsActivity extends AppCompatActivity {

    public Button musteriKaydiButonu;
    public Button isletmeKaydiButonu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_buttons);

        musteriKaydiButonu = (Button) findViewById(R.id.customerRegisterEntry_Button);
        musteriKaydiButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musteriYonlendir();
            }
        });

        isletmeKaydiButonu = (Button) findViewById(R.id.businessRegisterEntry_Button);
        isletmeKaydiButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isletmeYonlendir();
            }
        });
    }

    public void musteriYonlendir(){
        Intent musteriKayitEkraniYonlendir = new Intent(this, customerRegisterActivity.class);
        startActivity(musteriKayitEkraniYonlendir);
    }

    public void isletmeYonlendir(){
        Intent isletmeKayitEkaniYonlendir = new Intent(this, businessRegisterActivity.class);
        startActivity(isletmeKayitEkaniYonlendir);
    }
}
