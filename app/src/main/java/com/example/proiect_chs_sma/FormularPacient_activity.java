package com.example.proiect_chs_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormularPacient_activity extends AppCompatActivity{
    private EditText  problemes, id_pacient;
    private Spinner varstaSpinner, inaltimeSpinner, greutateSpinner, pulsSpinner, fumatSpinner, sportSpinner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    Pacients pacients;
    private Button button_formular, button_puls, button_imagine;
    private FirebaseUser user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_pacient);

        button_formular = findViewById(R.id.button_formular);
        button_imagine = findViewById(R.id.button_imagine);
        problemes = findViewById(R.id.editprobleme);
        varstaSpinner = (Spinner) findViewById(R.id.alegerevarsta);
        inaltimeSpinner = (Spinner) findViewById(R.id.alegereinaltime);
        greutateSpinner = (Spinner) findViewById(R.id.alegeregreutate);
        pulsSpinner = (Spinner) findViewById(R.id.alegerepuls);
        fumatSpinner = (Spinner) findViewById(R.id.alegerefumat);
        sportSpinner = (Spinner) findViewById(R.id.alegeresport);
        pacients = new Pacients();

        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference("Despre pacienti");

        button_formular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                String varsta = varstaSpinner.getSelectedItem().toString();
                String inaltime = inaltimeSpinner.getSelectedItem().toString();
                String greutate = greutateSpinner.getSelectedItem().toString();
                String puls = pulsSpinner.getSelectedItem().toString();
                String fumat = fumatSpinner.getSelectedItem().toString();
                String sport = sportSpinner.getSelectedItem().toString();
                String probleme_sanatate = problemes.getText().toString().trim();
                String idpacient = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

                pacients.setGreutate(greutate);
                pacients.setInaltime(inaltime);
                pacients.setVarsta(varsta);
                pacients.setPuls(puls);
                pacients.setFumat(fumat);
                pacients.setSport(sport);
                pacients.setSanatate(probleme_sanatate);
                pacients.setIdPacient(idpacient);

                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(pacients);
                Toast.makeText(FormularPacient_activity.this, "Informatiile pacientului au fost adaugate cu succes!",
                        Toast.LENGTH_SHORT).show();
                    Intent goback = new Intent(FormularPacient_activity.this, Pacient_activity.class);
                    startActivity(goback);
            }catch(Exception e)

            {
                Toast.makeText(FormularPacient_activity.this, "Informatiile introduse sunt gresite!",
                        Toast.LENGTH_SHORT).show();
            }


          }
        });

        button_imagine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goImage = new Intent(FormularPacient_activity.this, UploadPhotos_activity.class);
                startActivity(goImage);
            }
        });

    }
}