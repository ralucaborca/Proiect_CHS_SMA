package com.example.proiect_chs_sma;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Sugestii_activity extends AppCompatActivity {
    RecyclerView mrecyclerView;
    RecyclerVi_Config recyclerVi_config;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ArrayList<Feedback> feedbackArrayList;
    private String currentID;
    private String doctorasName;
    private int maxcount=0;
    Feedback data_ora;
    TextView textid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestii);
        data_ora = new Feedback();

        //String idpac = getIntent().getStringExtra("pacientID");
        //textid.setText(idpac);

        mrecyclerView = findViewById(R.id.recycleview_sugestii);
        databaseReference = FirebaseDatabase.getInstance().getReference("Sugestii medic");
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        feedbackArrayList = new ArrayList<>();
        recyclerVi_config = new RecyclerVi_Config(this, feedbackArrayList);
        mrecyclerView.setAdapter(recyclerVi_config);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        currentID = firebaseUser.getUid();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT
                | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                feedbackArrayList.remove(position);
                recyclerVi_config.notifyDataSetChanged();
                Toast.makeText(Sugestii_activity.this, "A fost sters cu succes!", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mrecyclerView);

        /*databaseReference.orderByChild("17 aprilie 2022").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                feedbackArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Feedback feedback = dataSnapshot.getValue(Feedback.class);
                        feedbackArrayList.add(feedback);
                }
                recyclerVi_config.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Sugestii_activity.this,"Eroare! Va rugam reveniti!", Toast.LENGTH_SHORT).show();
            }
        });*/
        databaseReference.orderByChild("Sugestii medic/idpacient").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                feedbackArrayList.clear();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Feedback feedback = dataSnapshot.getValue(Feedback.class);
                        feedbackArrayList.add(feedback);
                    }
                    recyclerVi_config.notifyDataSetChanged();
                }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}