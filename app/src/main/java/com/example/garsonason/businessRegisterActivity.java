package com.example.garsonason;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class businessRegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button businessRegister_Button;
    private EditText businessRegister_id_Edittext;
    private EditText businessRegister_mail_Edittext;
    private EditText businessRegister_password_Edittext;
    private EditText businessRegister_passwordRepeat_Edittext;
    private EditText businessRegister_phoneNumber_Edittext;
    private DatabaseReference database_Ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_register);
        businessRegister_id_Edittext=(EditText) findViewById(R.id.businessRegister_id_Edittext);
        businessRegister_mail_Edittext=(EditText) findViewById(R.id.businessRegister_mail_Edittext);
        businessRegister_password_Edittext=(EditText) findViewById(R.id.businessRegister_password_Edittext);
        businessRegister_Button=(Button) findViewById(R.id.businessRegister_Button);
        businessRegister_passwordRepeat_Edittext=(EditText) findViewById(R.id.businessRegister_passwordRepeat_Edittext);
        businessRegister_phoneNumber_Edittext= (EditText) findViewById(R.id.businessRegister_phoneNumber_Edittext);


        mAuth=FirebaseAuth.getInstance();
        businessRegister_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             String posta = businessRegister_mail_Edittext.getText().toString();
             String sifre = businessRegister_password_Edittext.getText().toString();
             String kullaniciAdi = businessRegister_id_Edittext.getText().toString();
             String sifreTekrar = businessRegister_passwordRepeat_Edittext.getText().toString();
             String telNo = businessRegister_phoneNumber_Edittext.getText().toString();
             String puan="5";
             String adres="mah sokak levet kadıköy";






                kayitOl(posta, sifre,kullaniciAdi,telNo,puan,adres);


            }
        });


    }

    private void kayitOl(final String posta, final String sifre, final String kullaniciAdi, final String telNo, final String puan, final String adres) {
        mAuth.createUserWithEmailAndPassword(posta,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


              if (task.isSuccessful()){
                  String users_Id=mAuth.getCurrentUser().getUid();
                  database_Ref= FirebaseDatabase.getInstance().getReference().child("Isletme_Kullanicilari_Kayit").child(users_Id);
                  HashMap<String, String> isletmeKullaniciKayit = new HashMap<>();
                  isletmeKullaniciKayit.put("kullaniciAdi",kullaniciAdi);
                  isletmeKullaniciKayit.put("sifre",sifre);
                  isletmeKullaniciKayit.put("telNo",telNo);
                  isletmeKullaniciKayit.put("ePosta",posta);
                  isletmeKullaniciKayit.put("adres",adres);
                  isletmeKullaniciKayit.put("puan",puan);


                  database_Ref.setValue(isletmeKullaniciKayit).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if (task.isSuccessful()){
                              Intent kayitTamamlandi = new Intent(businessRegisterActivity.this, loginActivity.class);
                              startActivity(kayitTamamlandi);
                          }
                          else
                          {

                              Toast.makeText(getApplicationContext(),"hata"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                          }
                      }
                  });



              }

            }
        });
    }


}

