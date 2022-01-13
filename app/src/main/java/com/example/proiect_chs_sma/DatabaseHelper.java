package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Pacients> listaPacienti = new ArrayList<>();

    public interface DataStatus{
        void dateIncarcate(List<Pacients> pacients, List<String> keys);
        void dateIntroduse();
        void dateActualizate();
        void dateSterse();
    }

    public DatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Date pacienti");
    }

    public void readPacients( final DataStatus dataStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaPacienti.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    keys.add(snapshot1.getKey());
                    Pacients pacients = snapshot1.getValue(Pacients.class);
                    listaPacienti.add(pacients);
                }
                dataStatus.dateIncarcate(listaPacienti,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
