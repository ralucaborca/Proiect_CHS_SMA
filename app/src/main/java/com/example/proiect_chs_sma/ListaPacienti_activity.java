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
import java.util.List;

public class ListaPacienti_activity extends AppCompatActivity {
    private RecyclerView mrecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacienti);
        mrecyclerView = (RecyclerView) findViewById(R.id.recycleview_pacienti);
        new DatabaseHelper().readPacients(new DatabaseHelper.DataStatus() {
            @Override
            public void dateIncarcate(List<Pacients> pacients, List<String> keys) {
                new RecyclerView_Config().setConfig(mrecyclerView, ListaPacienti_activity.this, pacients, keys);
            }

            @Override
            public void dateIntroduse() {

            }

            @Override
            public void dateActualizate() {

            }

            @Override
            public void dateSterse() {

            }
        });
    }
}