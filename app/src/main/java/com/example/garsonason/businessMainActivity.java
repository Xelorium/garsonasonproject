package com.example.garsonason;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class businessMainActivity extends AppCompatActivity {

    private Button businessMain_addProduct_Button;
    private EditText businessAddProduct_Name_Edittext;
    private EditText businessAddProduct_Type_Edittext;
    private EditText businessAddProduct_Cost_Edittext;
    private Button businessAddProduct_Add_Button;
    private FirebaseAuth mAuth;
    private DatabaseReference database_Ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_main);

        businessMain_addProduct_Button = (Button) findViewById(R.id.businessMain_addProduct_Button);
        businessAddProduct_Name_Edittext = (EditText) findViewById(R.id.businessAddProduct_Name_Edittext);
        businessAddProduct_Type_Edittext = (EditText) findViewById(R.id.businessAddProduct_Type_Edittext);
        businessAddProduct_Cost_Edittext = (EditText) findViewById(R.id.businessAddProduct_Cost_Edittext);
        businessAddProduct_Add_Button = (Button) findViewById(R.id.businessAddProduct_Add_Button);
        mAuth = FirebaseAuth.getInstance();




        businessMain_addProduct_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                businessAddProductActivity businessAddProduct = new businessAddProductActivity();
                transaction.replace(R.id.businessMain_Frame, businessAddProduct);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        businessAddProduct_Add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urunAdi = businessAddProduct_Name_Edittext.getText().toString();
                String urunTipi = businessAddProduct_Type_Edittext.getText().toString();
                String urunFiyati = businessAddProduct_Cost_Edittext.getText().toString();

                if (!TextUtils.isEmpty(urunAdi) && !TextUtils.isEmpty(urunTipi)&& !TextUtils.isEmpty(urunFiyati)){
                    urunEkle(urunAdi, urunTipi, urunFiyati);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Tüm alanları doldurmanız gerekiyor.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void urunEkle(String urunAdi, String urunTipi, String urunFiyati){

        String items_Id=mAuth.getUid();
        database_Ref= FirebaseDatabase.getInstance().getReference().child("Isletme_Urunler_Bilgi").child(items_Id);
        HashMap<String, String> isletmeUrunKayit = new HashMap<>();
        isletmeUrunKayit.put("urunAdi",urunAdi);
        isletmeUrunKayit.put("urunTipi",urunTipi);
        isletmeUrunKayit.put("urunFiyat",urunFiyati);

        database_Ref.setValue(isletmeUrunKayit).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Intent intent = new Intent(businessMainActivity.this, businessMainActivity.class);
                startActivity(intent);

            }
        });


    }
}
