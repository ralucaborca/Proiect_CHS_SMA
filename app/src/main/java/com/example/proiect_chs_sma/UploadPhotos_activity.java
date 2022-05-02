package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Fotografii puls");
    StorageReference pozaRef;
    Uri imagineURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photos);

        home = findViewById(R.id.anulare);
        rasfoieste = findViewById(R.id.rasfoieste_btn);
        incarca = findViewById(R.id.uploadphoto_btn);
        imageView = findViewById(R.id.fotoView);

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
                            databaseRef.child(setNumePozaDb).setValue(data);

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