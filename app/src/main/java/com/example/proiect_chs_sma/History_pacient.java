package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class History_pacient extends AppCompatActivity{
    RecyclerView hRecyclerView;
    HistoryAdapter historyAdapter;
    DatabaseReference databasereference;
    FirebaseUser firebaseUser;
    ArrayList<History> historyArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pacient);

        hRecyclerView = findViewById(R.id.recycleview_history);
        databasereference = FirebaseDatabase.getInstance().getReference("Istoric pacienti");
        hRecyclerView.setHasFixedSize(true);
        hRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyArrayList = new ArrayList<>();
        historyAdapter = new HistoryAdapter(this, historyArrayList);
        hRecyclerView.setAdapter(historyAdapter);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                historyArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    History history= dataSnapshot.getValue(History.class);
                    historyArrayList.add(history);
                }
                historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(History_pacient.this,"Eroare! Va rugam reveniti!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}