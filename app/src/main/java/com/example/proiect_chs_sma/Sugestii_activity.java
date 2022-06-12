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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Sugestii_activity extends AppCompatActivity {
    RecyclerView mrecyclerView;
    RecyclerVi_Config recyclerVi_config;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<Feedback> feedbackArrayList;
    Feedback data_ora;
    FloatingActionButton goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestii);
        data_ora = new Feedback();
        goback = findViewById(R.id.goback);

        mrecyclerView = findViewById(R.id.recycleview_sugestii);
        databaseReference = FirebaseDatabase.getInstance().getReference("Sugestii medic");
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        feedbackArrayList = new ArrayList<>();
        recyclerVi_config = new RecyclerVi_Config(this, feedbackArrayList);
        mrecyclerView.setAdapter(recyclerVi_config);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Sugestii_activity.this, Pacient_activity.class);
                startActivity(home);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                feedbackArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Feedback feedback = dataSnapshot.getValue(Feedback.class);
                    if (feedback.getIDPacient().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        feedbackArrayList.add(feedback);
                    }
                }
                recyclerVi_config.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Sugestii_activity.this,"Eroare! Va rugam reveniti!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}