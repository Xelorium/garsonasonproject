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

public class businessRegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button businessRegister_Button;
    private EditText businessRegister_id_Edittext;
    private EditText businessRegister_mail_Edittext;
    private EditText businessRegister_password_Edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_register);
        businessRegister_id_Edittext=(EditText) findViewById(R.id.businessRegister_id_Edittext);
        businessRegister_mail_Edittext=(EditText) findViewById(R.id.businessRegister_mail_Edittext);
        businessRegister_password_Edittext=(EditText) findViewById(R.id.businessRegister_password_Edittext);
        businessRegister_Button=(Button) findViewById(R.id.businessRegister_Button);


        mAuth=FirebaseAuth.getInstance();
        businessRegister_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String posta = businessRegister_mail_Edittext.getText().toString();
             String sifre = businessRegister_password_Edittext.getText().toString();

                kayitOl(posta, sifre);


            }
        });


    }

    private void kayitOl(String posta, String sifre) {
        mAuth.createUserWithEmailAndPassword(posta,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
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

