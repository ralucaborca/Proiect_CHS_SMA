package com.example.proiect_chs_sma;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaPacienti_activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private Lista_recycle_adapt listaRecycleAdapt;
    private ArrayList<Pacients> listaPacienti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacienti);

        recyclerView = findViewById(R.id.listaEvenimente);
        databaseReference = FirebaseDatabase.getInstance().getReference("Date pacienti");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaPacienti = new ArrayList<>();
        listaRecycleAdapt = new Lista_recycle_adapt(this, listaPacienti);

        recyclerView.setAdapter(listaRecycleAdapt);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaPacienti.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Pacients pacients = snapshot1.getValue(Pacients.class);
                    listaPacienti.add(pacients);
                }
                listaRecycleAdapt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListaPacienti_activity.this, "Eroare la procesarea datelor. Va rugam reveniti!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}