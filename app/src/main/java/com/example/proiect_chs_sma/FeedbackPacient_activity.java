package com.example.proiect_chs_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackPacient_activity extends AppCompatActivity {
    private EditText id, nume, sugestii;
    private Spinner caz;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference database;
    Feedback feedback;
    private Button button_feedback;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_pacient);

        button_feedback = findViewById(R.id.button_feedback);
        id = findViewById(R.id.feedback_id);
        nume = findViewById(R.id.feedback_nume_prenume_medic);
        caz = (Spinner) findViewById(R.id.alegerecazpuls);
        sugestii = findViewById(R.id.sugestii);
        feedback = new Feedback();

        firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase.getReference().child("Sugestii medic");

        button_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String id_medic = id.getText().toString().trim();
                    String nume_medic = nume.getText().toString().trim();
                    String caz_puls = caz.getSelectedItem().toString();
                    String sugestii_medic = sugestii.getText().toString().trim();

                    if(id_medic.isEmpty()){
                        id.setError("Introduceti id!");
                        return;
                    }

                    feedback.setID(id_medic);
                    feedback.setNume(nume_medic);
                    feedback.setCaz(caz_puls);
                    feedback.setFeedback(sugestii_medic);

                    database.child(id_medic).setValue(feedback);
                    Toast.makeText(FeedbackPacient_activity.this, "Sugestiile medicului adaugate cu succes!", Toast.LENGTH_LONG).show();
                        Intent goBack = new Intent(FeedbackPacient_activity.this,Doctor_activity.class);
                        startActivity(goBack);

                }catch (Exception e){
                    Toast.makeText(FeedbackPacient_activity.this, "Informatii introduse gresit!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
