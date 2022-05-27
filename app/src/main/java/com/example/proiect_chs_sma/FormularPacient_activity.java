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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormularPacient_activity extends AppCompatActivity{
    private EditText  problemes, id_pacient, denumire1;
    private Spinner varstaSpinner, inaltimeSpinner, greutateSpinner, genSpinner, fumatSpinner, sportSpinner, afectiuneSpinner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference, databaseReference1;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    Pacients pacients = new Pacients();
    History history = new History();
    private Button button_formular, button_imagine;
    private FirebaseUser user;
    private String userId;
    private long maxid;
    TextView nume_pacieent;

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
        fumatSpinner = (Spinner) findViewById(R.id.alegerefumat);
        sportSpinner = (Spinner) findViewById(R.id.alegeresport);
        afectiuneSpinner = (Spinner) findViewById(R.id.alegereAfectiune);
        genSpinner = (Spinner) findViewById(R.id.alegeregen);
        denumire1 = findViewById(R.id.numeee_pozaaa);


        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference("Despre pacienti");
        databaseReference1 = mDatabase.getReference("Istoric pacienti");

        String nume = getIntent().getStringExtra("nume_p");
        nume_pacieent = findViewById(R.id.nume_pacient);
        nume_pacieent.setText(nume);

        SimpleDateFormat datePoza  = new SimpleDateFormat("yyyy_MM_dd, HH:mm", Locale.getDefault());
        Date dataCurenta = new Date();
        String numePozaPuls = nume + "_" + datePoza.format(dataCurenta);

        databaseReference.addValueEventListener(new ValueEventListener() {
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

        button_formular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                String varsta = varstaSpinner.getSelectedItem().toString();
                String inaltime = inaltimeSpinner.getSelectedItem().toString();
                String greutate = greutateSpinner.getSelectedItem().toString();
                String fumat = fumatSpinner.getSelectedItem().toString();
                String sport = sportSpinner.getSelectedItem().toString();
                String probleme_sanatate = problemes.getText().toString().trim();
                String genut = genSpinner.getSelectedItem().toString();
                String afectiunee = afectiuneSpinner.getSelectedItem().toString();
                String idpacient = FirebaseAuth.getInstance().getCurrentUser().getUid();

                if(probleme_sanatate.isEmpty()){
                    problemes.setError("Completati problema sau scrieti NU daca nu aveti.");
                    problemes.requestFocus();
                    return;
                }

                pacients.setGreutate(greutate);
                pacients.setInaltime(inaltime);
                pacients.setVarsta(varsta);
                pacients.setFumat(fumat);
                pacients.setSport(sport);
                pacients.setSanatate(probleme_sanatate);
                pacients.setIdPacient(idpacient);
                pacients.setAfectiune(afectiunee);
                pacients.setGen(genut);
                pacients.setNumePoza(numePozaPuls);
                pacients.setNumePacient(nume);

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
                gob.putExtra("nume", nume);
                startActivity(gob);
                finish();
            }
        });

        /*button_istoric.setOnClickListener(new View.OnClickListener() {
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

                if(probleme_sanatate.isEmpty()){
                    problemes.setError("Completati problema sau scrieti NU daca nu aveti.");
                    problemes.requestFocus();
                    return;
                }

                if(denumire_poza.isEmpty()){
                    denumire1.setError("Denumirea pozei trebuie sa contina numele dumneavoastra.");
                    denumire1.requestFocus();
                    return;
                }

                history.setGreutate1(greutate);
                history.setInaltime1(inaltime);
                history.setVarsta1(varsta);
                history.setPuls1(puls);
                history.setFumat1(fumat);
                history.setSport1(sport);
                history.setSanatate1(probleme_sanatate);
                history.setIdPacient1(idpacient);
                history.setNumePoza1(denumire_poza);

                databaseReference1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(history);
                Toast.makeText(FormularPacient_activity.this, "Informatiile pacientului au fost adaugate cu succes!",
                        Toast.LENGTH_SHORT).show();
                Intent goback = new Intent(FormularPacient_activity.this, FormularPacient_activity.class);
                startActivity(goback);

            }catch(Exception e){
                Toast.makeText(FormularPacient_activity.this, "Informatiile introduse sunt gresite!",
                        Toast.LENGTH_SHORT).show();
            }
            }
        });*/

    }


}