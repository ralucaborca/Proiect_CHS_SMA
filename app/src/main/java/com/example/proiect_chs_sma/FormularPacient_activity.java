package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormularPacient_activity extends AppCompatActivity{
    private EditText  problemes, id_pacient, denumire1;
    private Spinner varstaSpinner, inaltimeSpinner, greutateSpinner, pulsSpinner, fumatSpinner, sportSpinner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    Pacients pacients = new Pacients();
    private Button button_formular, button_puls, button_imagine;
    private FirebaseUser user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_pacient);
        button_imagine = findViewById(R.id.xxxxxxx);
        button_formular = findViewById(R.id.button_formular);
        problemes = findViewById(R.id.editprobleme);
        varstaSpinner = (Spinner) findViewById(R.id.alegerevarsta);
        inaltimeSpinner = (Spinner) findViewById(R.id.alegereinaltime);
        greutateSpinner = (Spinner) findViewById(R.id.alegeregreutate);
        pulsSpinner = (Spinner) findViewById(R.id.alegerepuls);
        fumatSpinner = (Spinner) findViewById(R.id.alegerefumat);
        sportSpinner = (Spinner) findViewById(R.id.alegeresport);
        denumire1 = findViewById(R.id.numeee_pozaaa);

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
                String idpacient = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String denumire_poza = denumire1.getText().toString().trim();

                pacients.setGreutate(greutate);
                pacients.setInaltime(inaltime);
                pacients.setVarsta(varsta);
                pacients.setPuls(puls);
                pacients.setFumat(fumat);
                pacients.setSport(sport);
                pacients.setSanatate(probleme_sanatate);
                pacients.setIdPacient(idpacient);
                pacients.setNumePoza(denumire_poza);

                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(pacients);
                Toast.makeText(FormularPacient_activity.this, "Informatiile pacientului au fost adaugate cu succes!",
                        Toast.LENGTH_SHORT).show();
                    Intent goback = new Intent(FormularPacient_activity.this, Pacient_activity.class);
                    startActivity(goback);
            }catch(Exception e){
                Toast.makeText(FormularPacient_activity.this, "Informatiile introduse sunt gresite!",
                        Toast.LENGTH_SHORT).show();
            }

          }
        });
        button_imagine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gob = new Intent(FormularPacient_activity.this, UploadPhotos_activity.class);
                startActivity(gob);
            }
        });
    }


}