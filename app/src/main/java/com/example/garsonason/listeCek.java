package com.example.garsonason;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listeCek extends AppCompatActivity {

    private ListView urunListele_ListView;
    private FirebaseAuth mAuth;

    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        arrayList  =new ArrayList<>();
        urunListele_ListView = (ListView) findViewById(R.id.urunListele_ListView);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Isletme_Urunler_Bilgi");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    dataSnapshot.getKey();
                    customerProductAdapter model = ds.getValue(customerProductAdapter.class);
                    System.out.println(model.getkayitTarihi());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
