package com.example.garsonason;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class businessMainActivity extends AppCompatActivity {

    private Button businessMain_addProduct_Button;
    private EditText businessAddProduct_Name_Edittext;
    private EditText businessAddProduct_Type_Edittext;
    private EditText businessAddProduct_Cost_Edittext;
    private Button businessAddProduct_Add_Button;
    private Button listeleButton;
    private FirebaseAuth mAuth;
    private DatabaseReference database_Ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_main);

        businessMain_addProduct_Button = findViewById(R.id.businessMain_addProduct_Button);
        listeleButton = findViewById(R.id.intent_Button);

        mAuth = FirebaseAuth.getInstance();

        listeleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(businessMainActivity.this, listeCek.class);
                startActivity(intent);
            }
        });


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

    }
}
