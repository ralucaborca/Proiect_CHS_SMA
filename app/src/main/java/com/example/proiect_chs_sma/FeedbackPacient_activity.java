package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackPacient_activity extends AppCompatActivity {
    private EditText nume, sugestii;
    private Spinner caz;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference database;
    Feedback feedback;
    private Button button_feedback;
    private long maxid=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_pacient);

        button_feedback = findViewById(R.id.button_feedback);
        nume = findViewById(R.id.feedback_nume_prenume_medic);
        caz = (Spinner) findViewById(R.id.alegerecazpuls);
        sugestii = findViewById(R.id.sugestii);
        feedback = new Feedback();

        firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase.getReference("Sugestii medic");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String nume_medic = nume.getText().toString().trim();
                    String caz_puls = caz.getSelectedItem().toString();
                    String sugestii_medic = sugestii.getText().toString().trim();

                    feedback.setNume(nume_medic);
                    feedback.setCaz(caz_puls);
                    feedback.setFeedback(sugestii_medic);

                    database.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(String.valueOf(maxid+1)).setValue(feedback);
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
