package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sugestii_activity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("Sugestii:");
    private ListView listView;
    private TextView nume_medic, nume_pacient, sugestii_medic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestii);

       nume_medic = findViewById(R.id.nume1);
       nume_pacient = findViewById(R.id.nume2);
       sugestii_medic = findViewById(R.id.sugestii);

       Intent intent = getIntent();
       String nume_m = intent.getStringExtra("Nume medic");
        String nume_p = intent.getStringExtra("Nume pacient");
        String sugestii_m = intent.getStringExtra("Sugestii");

        nume_medic.setText(nume_m);
        nume_pacient.setText(nume_p);
        sugestii_medic.setText(sugestii_m);

       /* listView = findViewById(R.id.list_view2);
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter arrayAdapter = new ArrayAdapter<String >(Sugestii_activity.this, R.layout.lista_pacienti, arrayList);
        listView.setAdapter(arrayAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    arrayList.add(snapshot1.getValue().toString());
                    Log.d("Lista sugestiilor este:", snapshot1.getValue().toString());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w( "Nu se pot vedea sugestiile medicului!", error.toException());
            }
        });*/
    }
}