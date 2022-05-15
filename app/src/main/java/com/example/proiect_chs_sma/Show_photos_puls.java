package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos_puls);

        recyclerView = findViewById(R.id.recycleview_photos);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        photoDatasArrayList = new ArrayList<>();
        photosAdapter = new PhotosAdapter(this,photoDatasArrayList);
        recyclerView.setAdapter(photosAdapter);

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