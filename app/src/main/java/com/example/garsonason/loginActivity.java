package com.example.garsonason;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    private EditText userId_Edittext;
    private EditText userPassword_Edittext;
    private Button userLogin_Button;
    private FirebaseAuth mAuth;
    private TextView forgotPassword_TextView;
    private ProgressDialog progressDialog1;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userId_Edittext = (EditText) findViewById(R.id.userId_Edittext);
        userPassword_Edittext = (EditText) findViewById(R.id.userPassword_Edittext);
        forgotPassword_TextView = (TextView) findViewById(R.id.forgotPassword_Text);
        userLogin_Button = (Button) findViewById(R.id.userLogin_Button);
        progressDialog1= new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();

        userLogin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kullaniciAdi = userId_Edittext.getText().toString();
                String kullaniciSifre = userPassword_Edittext.getText().toString();
                if(!TextUtils.isEmpty(kullaniciAdi)||!TextUtils.isEmpty(kullaniciSifre)){

                    login_user(kullaniciAdi,kullaniciSifre);

                }
            }
        });

        forgotPassword_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, forgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login_user(String kullaniciAdi, String kullaniciSifre) {

        mAuth.signInWithEmailAndPassword(kullaniciAdi,kullaniciSifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog1.setTitle("Giriş Yapılıyor");
                    progressDialog1.setMessage("Lütfen bekleyin...");
                    progressDialog1.setCanceledOnTouchOutside(false);
                    progressDialog1.show();
                    Intent intent = new Intent(loginActivity.this, RedirectActivity.class);
                    progressDialog1.dismiss();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Eksik veya yanlış girdi. "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
