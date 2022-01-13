package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pacient_activity extends AppCompatActivity {
    private TextInputLayout nume1, nume2, feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacient);
        Button button_completati_formular = findViewById(R.id.button_completati_fomular);
        Button button_sugestii = findViewById(R.id.button_sugestii);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Sugestii");


        button_completati_formular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoformular = new Intent(Pacient_activity.this,FormularPacient_activity.class);
                startActivity(gotoformular);
            }
        });

        button_sugestii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosugestii = new Intent(Pacient_activity.this,Sugestii_activity.class);
                startActivity(gotosugestii);
            }
        });

    }
}