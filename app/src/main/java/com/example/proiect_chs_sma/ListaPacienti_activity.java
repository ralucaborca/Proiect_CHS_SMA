package com.example.proiect_chs_sma;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaPacienti_activity extends AppCompatActivity implements RecyclerInterface {
    RecyclerView mrecyclerView;
    RecyclerView_Config recyclerView_config;
    DatabaseReference databaseReference;
    ArrayList<Pacients> pacientsArrayList;
    TextView nume_medic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacienti);

        mrecyclerView = findViewById(R.id.recycleview_pacienti);
        databaseReference = FirebaseDatabase.getInstance().getReference("Despre pacienti");
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        pacientsArrayList = new ArrayList<>();
        recyclerView_config = new RecyclerView_Config(this, pacientsArrayList,this);
        mrecyclerView.setAdapter(recyclerView_config);

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
                pacientsArrayList.remove(position);
                recyclerView_config.notifyDataSetChanged();
                Toast.makeText(ListaPacienti_activity.this, "A fost sters cu succes!", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mrecyclerView);

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
            Intent aaa =  new Intent(ListaPacienti_activity.this,FeedbackPacient_activity.class);
            Pacients pacient = pacientsArrayList.get(position);
            aaa.putExtra("pacient", pacient.getIdPacient());
            startActivity(aaa);
            finish();

}
}