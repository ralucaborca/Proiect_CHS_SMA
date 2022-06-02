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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UploadPhotos_activity extends AppCompatActivity {
    Button home, rasfoieste, incarca;
    ImageView imageView;
    EditText nume_poza;
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Fotografii puls");
    StorageReference pozaRef;
    DatabaseReference pozafirebaase;
    Uri imagineURI;
    TextView den;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photos);

        nume_poza = findViewById(R.id.denumiree);
        home = findViewById(R.id.anulare);
        rasfoieste = findViewById(R.id.rasfoieste_btn);
        incarca = findViewById(R.id.uploadphoto_btn);
        imageView = findViewById(R.id.fotoView);

        String denumire_om = getIntent().getStringExtra("nume");
        den = findViewById(R.id.numeee);
        den.setText(denumire_om);

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
        incarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagineURI != null){
                    uploadToFirebase(imagineURI);
                }else{
                    Toast.makeText(UploadPhotos_activity.this, "Va rugam incarcati imaginea cu pulsul dvs.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(UploadPhotos_activity.this, FormularPacient_activity.class);
                startActivity(goHome);
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
        public void uploadToFirebase(Uri uri){
            String denumire_om = getIntent().getStringExtra("nume");
            den = findViewById(R.id.numeee);
            den.setText(denumire_om);
            SimpleDateFormat dataPoza  = new SimpleDateFormat("yyyy_MM_dd, HH:mm", Locale.getDefault());
            Date dataCurenta = new Date();
            String numePuls = denumire_om + "_" + dataPoza.format(dataCurenta);

           pozaRef = FirebaseStorage.getInstance().getReference("Fotografii puls/" + nume_poza);
            pozaRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pozaRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            PhotoDatas data = new PhotoDatas();
                            data.setNumePoza(numePuls);
                            data.setLinkImagine(uri.toString());
                            databaseRef.child(numePuls).setValue(data);

                            Intent goToMenuP = new Intent(UploadPhotos_activity.this, FormularPacient_activity.class);
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
                    Toast.makeText(UploadPhotos_activity.this, "Informatiile nu au fost trimise!", Toast.LENGTH_SHORT).show();
                }
            });
        }

}