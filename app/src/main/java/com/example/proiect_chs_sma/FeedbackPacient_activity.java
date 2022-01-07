package com.example.proiect_chs_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackPacient_activity extends AppCompatActivity {
    private EditText nume_medic, nume_pacient, feedback;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = mDatabase.getReference().child("Sugestii:");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_pacient);

        Button button_sugestii = findViewById(R.id.button_feedback);
        nume_medic = findViewById(R.id.feedback_nume_prenume_medic);
        nume_pacient = findViewById(R.id.feedback_nume_prenume_pacient);
        feedback = findViewById(R.id.sugestii);

        button_sugestii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goback = new Intent(FeedbackPacient_activity.this, Doctor_activity.class);
                startActivity(goback);

                String nume1 = nume_medic.getText().toString();
                String nume2 = nume_pacient.getText().toString();
                String sugestiuni = feedback.getText().toString();
                databaseReference.child("s1").setValue(nume1);
                databaseReference.child("s2").setValue(nume2);
                databaseReference.child("s3").setValue(sugestiuni);
            }
        });
    }
}