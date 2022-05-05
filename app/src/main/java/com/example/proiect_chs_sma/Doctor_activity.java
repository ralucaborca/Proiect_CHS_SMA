package com.example.proiect_chs_sma;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.ArrayList;

public class Doctor_activity extends AppCompatActivity {
    private ListView listView;
    private Doctor_details doctor_details;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

       Button lista = findViewById(R.id.lista_pacienti);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        final TextView nameView = findViewById(R.id.nume);
        final TextView prenumeView = findViewById(R.id.prenume);
        final TextView specializareView = findViewById(R.id.specializare);
        final TextView adresaView = findViewById(R.id.adresa);

        Button show_puls = findViewById(R.id.photos_puls);


        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Doctor_details userprofile = snapshot.getValue(Doctor_details.class);

                if(userprofile != null){
                    String name = userprofile.name;
                    String prenume = userprofile.prenume;
                    String specializare = userprofile.specializare;
                    String adress = userprofile.adresa_cabinet;

                    nameView.setText(name);
                    prenumeView.setText(prenume);
                    specializareView.setText(specializare);
                    adresaView.setText(adress);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Doctor_activity.this, "Ceva neasteptat s-a intamplat!", Toast.LENGTH_LONG).show();
            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotolista = new Intent(Doctor_activity.this,ListaPacienti_activity.class);
                startActivity(gotolista);
                finish();
            }
        });

        show_puls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLog = new Intent(Doctor_activity.this, Show_photos_puls.class);
                startActivity(gotoLog);
                finish();
            }
        });

    }
}