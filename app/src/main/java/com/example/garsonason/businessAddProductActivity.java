package com.example.garsonason;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class businessAddProductActivity extends Fragment {


private FirebaseAuth mAuth;
private EditText businessAddProduct_Cost_Edittext;
private EditText businessAddProduct_Type_Edittext;
private EditText businessAddProduct_Name_Edittext;
private DatabaseReference database_Ref;







    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_business_add_product, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button businessAddProduct_Add_Button = (Button) getView().findViewById(R.id.businessAddProduct_Add_Button);



        businessAddProduct_Add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText businessAddProduct_Name_Edittext = (EditText) getView().findViewById(R.id.businessAddProduct_Name_Edittext);
                EditText businessAddProduct_Type_Edittext = (EditText) getView().findViewById(R.id.businessAddProduct_Type_Edittext);
                EditText businessAddProduct_Cost_Edittext = (EditText) getView().findViewById(R.id.businessAddProduct_Cost_Edittext);
                String urunAdi = businessAddProduct_Name_Edittext.getText().toString();
                String urunTipi = businessAddProduct_Type_Edittext.getText().toString();
                String urunFiyati = businessAddProduct_Cost_Edittext.getText().toString();
                String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(new Date());


                if (!TextUtils.isEmpty(urunAdi) && !TextUtils.isEmpty(urunTipi)&& !TextUtils.isEmpty(urunFiyati)){
                    urunEkle(urunAdi, urunTipi, urunFiyati, date);

                }
                else{
                    Toast.makeText(getContext(),"Tüm alanları doldurmanız gerekiyor.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void urunEkle(String urunAdi, String urunTipi, String urunFiyati, String date){

        String items_Id=mAuth.getUid();
        database_Ref= FirebaseDatabase.getInstance().getReference().child("Isletme_Urunler_Bilgi").child(items_Id);
        HashMap<String, String> isletmeUrunKayit = new HashMap<>();
        isletmeUrunKayit.put("urunAdi",urunAdi);
        isletmeUrunKayit.put("urunTipi",urunTipi);
        isletmeUrunKayit.put("urunFiyat",urunFiyati);
        isletmeUrunKayit.put("kayitTarihi", date);

        database_Ref.setValue(isletmeUrunKayit).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Intent intent = new Intent(businessMainActivity.this, businessAddProductActivity.class);
                startActivity(intent);

            }
        });


    }
}
