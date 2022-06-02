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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FeedbackPacient_activity extends AppCompatActivity {
    private EditText nume, sugestii;
    private Spinner caz;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference database;
    Feedback feedback;
    private Button button_feedback;
    private long maxid;
    private TextView id_p;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_pacient);

        String id = getIntent().getStringExtra("pacient");
        id_p = findViewById(R.id.id_pacient);
        id_p.setText(id);

        button_feedback = findViewById(R.id.button_feedback);
        nume = findViewById(R.id.feedback_nume_prenume_medic);
        caz = (Spinner) findViewById(R.id.alegerecazpuls);
        sugestii = findViewById(R.id.sugestii);
        feedback = new Feedback();

        firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase.getReference("Sugestii medic");

        SimpleDateFormat datePoza  = new SimpleDateFormat("yyyy_MM_dd, HH:mm", Locale.getDefault());
        Date dataCurenta = new Date();
       // String numePozaPuls = id_p + "_" + datePoza.format(dataCurenta);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = snapshot.getChildrenCount()+1;
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
                    String id_pacient = id_p.getText().toString().trim();

                    if(nume_medic.isEmpty()){
                        nume.setError("Completati numele dumneavoastra.");
                        nume.requestFocus();
                        return;
                    }
                    if(sugestii_medic.isEmpty()){
                        sugestii.setError("Completati o sugestie pentru pacient.");
                        sugestii.requestFocus();
                        return;
                    }

                    feedback.setNume(nume_medic);
                    feedback.setCaz(caz_puls);
                    feedback.setFeedback(sugestii_medic);
                    feedback.setIDPacient(id_pacient);

                    String numePozaPuls = id_pacient + "_" + datePoza.format(dataCurenta);
                    database.child(numePozaPuls).setValue(feedback);
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
