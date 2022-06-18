package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaPacienti_activity extends AppCompatActivity implements RecyclerInterface {
    RecyclerView mrecyclerView;
    RecyclerView_Config recyclerView_config;
    DatabaseReference databaseReference;
    ArrayList<Pacients> pacientsArrayList;
    FloatingActionButton goback2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacienti);
        goback2 = findViewById(R.id.goback2);

        mrecyclerView = findViewById(R.id.recycleview_pacienti);
        databaseReference = FirebaseDatabase.getInstance().getReference("Despre pacienti");
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        pacientsArrayList = new ArrayList<>();
        recyclerView_config = new RecyclerView_Config(this, pacientsArrayList,this);
        mrecyclerView.setAdapter(recyclerView_config);

        goback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home2 = new Intent(ListaPacienti_activity.this, Doctor_activity.class);
                startActivity(home2);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pacientsArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Pacients pacients= dataSnapshot.getValue(Pacients.class);
                    pacientsArrayList.add(pacients);
                }
                recyclerView_config.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListaPacienti_activity.this,"Eroare! Va rugam reveniti!", Toast.LENGTH_SHORT).show();
            }
        });

        mrecyclerView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent xxx = new Intent(ListaPacienti_activity.this, FeedbackPacient_activity.class);
            startActivity(xxx);
            finish();
        }
    });

    }

    @Override
    public void onItemClick(int position) {
            Intent aaa =  new Intent(this,FeedbackPacient_activity.class);
            Pacients pacient = pacientsArrayList.get(position);
            aaa.putExtra("pacient", pacient.getIdPacient());
        startActivity(aaa);
        finish();

}
}