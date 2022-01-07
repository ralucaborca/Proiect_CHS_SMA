package com.example.proiect_chs_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormularPacient_activity extends AppCompatActivity {
    private EditText nume, inaltime, varsta, greutate, problemes, pulsul;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = mDatabase.getReference().child("Date pacienti");
    private DatabaseReference databaseReference1 = mDatabase.getReference().child("Date");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_pacient);

        Button button_formular = findViewById(R.id.button_formular);
        nume = findViewById(R.id.text_nume_prenume);
        varsta= findViewById(R.id.text_varsta);
        inaltime=findViewById(R.id.text_inaltime);
        greutate=findViewById(R.id.text_greutate);
        problemes = findViewById(R.id.text_probleme);
        pulsul = findViewById(R.id.text_puls);

        button_formular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goback = new Intent(FormularPacient_activity.this, Pacient_activity.class);
                startActivity(goback);
                String name = nume.getText().toString();
                String age = varsta.getText().toString();
                String weight = greutate.getText().toString();
                String height = inaltime.getText().toString();
                String probl = problemes.getText().toString();
                String puls = pulsul.getText().toString();
                databaseReference.child("01").setValue(name);
                databaseReference.child("02").setValue(age);
                databaseReference.child("03").setValue(weight);
                databaseReference.child("04").setValue(height);
                databaseReference.child("05").setValue(probl);
                databaseReference.child("06").setValue(puls);

                databaseReference1.child("01").setValue(name);
                databaseReference1.child("02").setValue(age);
                databaseReference1.child("03").setValue(weight);
                databaseReference1.child("04").setValue(height);
                databaseReference1.child("05").setValue(probl);
                databaseReference1.child("06").setValue(puls);
            }
        });
    }
}