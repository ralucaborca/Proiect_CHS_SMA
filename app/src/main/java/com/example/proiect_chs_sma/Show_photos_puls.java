package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_photos_puls extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<PhotoDatas> photoDatasArrayList;
    private PhotosAdapter photosAdapter;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Fotografii puls");
    FloatingActionButton goback3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos_puls);
        goback3 = findViewById(R.id.goback3);

        recyclerView = findViewById(R.id.recycleview_photos);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        photoDatasArrayList = new ArrayList<>();
        photosAdapter = new PhotosAdapter(this,photoDatasArrayList);
        recyclerView.setAdapter(photosAdapter);

       goback3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home3 = new Intent(Show_photos_puls.this, Doctor_activity.class);
                startActivity(home3);
            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PhotoDatas photoDatas = dataSnapshot.getValue(PhotoDatas.class);
                    photoDatasArrayList.add(photoDatas);
                }
                photosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}