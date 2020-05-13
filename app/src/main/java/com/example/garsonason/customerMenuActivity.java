package com.example.garsonason;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class customerMenuActivity extends AppCompatActivity {

    private ListView urunleriListele_Musteri_ListView;
    private ListView custom1_listview;
    private FirebaseAuth mAuth;
    private DatabaseReference database_Ref;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList2;
    private ArrayList<customerProductAdapter> orders;
    private ArrayList<urunModel> sepet;
    private urunModel urunmodel;
    private Button siparisVer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        siparisVer=findViewById(R.id.siparisVer);
      //  sepet = new ArrayList<urunModel>();



        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        final String musteriId = getIntent().getExtras().getString("musId");
        urunleriListele_Musteri_ListView = (ListView)findViewById(R.id.urunleriListele_Musteri_ListView);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String isletmeId = getIntent().getExtras().getString("isId");
        System.out.println(isletmeId);
        System.out.println(musteriId);


        final DatabaseReference myRef = database.getReference().child("Isletme_Urunler_Bilgi").child(isletmeId);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String a = ds.getKey();
                    customerProductAdapter model = ds.getValue(customerProductAdapter.class);

                    arrayList.add("Ürün Adı: " + model.geturunAdi() + "\n" + "Ürün Türü: " + model.geturunTipi() + "\n" + "Ürün Fiyatı: " + model.geturunFiyat() + "TL");
                    arrayList2.add(a);

                    urunleriListele_Musteri_ListView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final HashMap<String, String> siparisListesi = new HashMap<>();
        urunleriListele_Musteri_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                System.out.println(arrayList2.get(position));

                database_Ref = FirebaseDatabase.getInstance().getReference().child("Isletme_Siparisler").child(isletmeId).child(musteriId).child("sepet").child(arrayList2.get(position));
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String a = ds.getKey();
                            customerProductAdapter model = ds.getValue(customerProductAdapter.class);
                            String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(new Date());

                            if(a.equals(arrayList2.get(position))){

                                System.out.println(arrayList2.get(position));
                                System.out.println(a);

                                siparisListesi.put("UrunAdi", model.geturunAdi());
                                siparisListesi.put("Adet", "5");
                                siparisListesi.put("Durum", "beklemede");
                                siparisListesi.put("Tarih", date);
                                database_Ref.setValue(siparisListesi);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                /*String deneme=arrayList.get(position);
                Toast.makeText(getApplicationContext(), deneme, Toast.LENGTH_SHORT).show();*/
            }
        });
       siparisVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sepet.clear();

            }
        });
    }
}
