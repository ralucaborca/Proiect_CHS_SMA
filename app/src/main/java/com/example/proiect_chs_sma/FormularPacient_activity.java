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
    private EditText  problemes, id_pacient;
    private Spinner varstaSpinner, inaltimeSpinner, greutateSpinner, pulsSpinner, fumatSpinner, sportSpinner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    Pacients pacients;
    private Button button_formular, button_puls, button_imagine;
    private FirebaseUser user;
    private String userId;

    Button home, rasfoieste, incarca;
    ImageView imageView;
    StorageReference pozaRef;
    DatabaseReference pozafirebaase;
    Uri imagineURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_pacient);

        button_formular = findViewById(R.id.button_formular);
        problemes = findViewById(R.id.editprobleme);
        varstaSpinner = (Spinner) findViewById(R.id.alegerevarsta);
        inaltimeSpinner = (Spinner) findViewById(R.id.alegereinaltime);
        greutateSpinner = (Spinner) findViewById(R.id.alegeregreutate);
        pulsSpinner = (Spinner) findViewById(R.id.alegerepuls);
        fumatSpinner = (Spinner) findViewById(R.id.alegerefumat);
        sportSpinner = (Spinner) findViewById(R.id.alegeresport);

        rasfoieste = findViewById(R.id.rasfoieste_btn);
        imageView = findViewById(R.id.fotoView);
        pacients = new Pacients();

        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference("Despre pacienti");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPhoto = new Intent();
                openPhoto.setAction(Intent.ACTION_GET_CONTENT);
                openPhoto.setType("image/*");
                startActivityForResult(openPhoto, 2);
            }
        });
        rasfoieste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPhoto = new Intent();
                openPhoto.setAction(Intent.ACTION_GET_CONTENT);
                openPhoto.setType("image/*");
                startActivityForResult(openPhoto, 2);
            }
        });


        button_formular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat datePoza  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
                Date dataCurenta = new Date();
                String numePoza = datePoza.format(dataCurenta);
                String setNumePozaDb = numePoza ;
                try{
                String varsta = varstaSpinner.getSelectedItem().toString();
                String inaltime = inaltimeSpinner.getSelectedItem().toString();
                String greutate = greutateSpinner.getSelectedItem().toString();
                String puls = pulsSpinner.getSelectedItem().toString();
                String fumat = fumatSpinner.getSelectedItem().toString();
                String sport = sportSpinner.getSelectedItem().toString();
                String probleme_sanatate = problemes.getText().toString().trim();
                String idpacient = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

                Uri uri = null;

                pacients.setGreutate(greutate);
                pacients.setInaltime(inaltime);
                pacients.setVarsta(varsta);
                pacients.setPuls(puls);
                pacients.setFumat(fumat);
                pacients.setSport(sport);
                pacients.setSanatate(probleme_sanatate);
                pacients.setIdPacient(idpacient);
                pacients.setLinkImagine(setNumePozaDb);


                    if(imagineURI != null){
                        uploadToFirebase(imagineURI);
                    }else{
                        Toast.makeText(FormularPacient_activity.this, "Va rugam incarcati imaginea cu pulsul dvs.",Toast.LENGTH_SHORT).show();
                    }
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
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            imagineURI = data.getData();
            imageView.setImageURI(imagineURI);
        }
    }
    private void uploadToFirebase(Uri uri){
        SimpleDateFormat datePoza  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
        Date dataCurenta = new Date();
        String numePoza = datePoza.format(dataCurenta);
        String setNumePozaDb = numePoza ;
        pozaRef = FirebaseStorage.getInstance().getReference("Fotografii puls/" + numePoza);

        pozaRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pozaRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        PhotoDatas data = new PhotoDatas();
                        data.setLinkImagine(uri.toString());
                        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child(numePoza).setValue(data);

                        Intent goToMenuP = new Intent(FormularPacient_activity.this, FormularPacient_activity.class);
                        startActivity(goToMenuP);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FormularPacient_activity.this, "Informatiile nu au fost trimise!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}