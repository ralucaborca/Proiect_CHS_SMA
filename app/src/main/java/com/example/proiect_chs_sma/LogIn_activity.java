package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn_activity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private TextView emailAddress, password;
    private TextView gotoregister, resetarParola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth =FirebaseAuth.getInstance();

        emailAddress = findViewById(R.id.textnume);
        password = findViewById(R.id.textparola);
        gotoregister = findViewById(R.id.register);
        resetarParola = findViewById(R.id.resetare_parola);
        Button button_login = findViewById(R.id.button_login);


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailCreate = emailAddress.getText().toString().trim();
                String passwordCreate = password.getText().toString().trim();
                final String verificareMail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if (emailCreate.isEmpty()) {
                    emailAddress.setError("Introduceti adresa de e-mail!");
                    emailAddress.requestFocus();
                    return;
                }else{
                    if(!emailCreate.matches(verificareMail)) {
                        emailAddress.setError("Mail-ul este invalid!");
                        return;
                    }
                }
                if(passwordCreate.isEmpty()){
                    password.setError("Introduceti parola!");
                    password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(emailCreate, passwordCreate).addOnSuccessListener(authResult -> {

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("Users").child(mAuth.getUid()).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String usertype = snapshot.getValue(String.class);
                            if(usertype.equals("doctor")){
                                Intent in = new Intent(LogIn_activity.this,Doctor_activity.class);
                                startActivity(in);
                                finish();
                            }
                            if(usertype.equals("pacient")){
                                Intent in1 = new Intent(LogIn_activity.this,Pacient_activity.class);
                                in1.putExtra("idpacient", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                startActivity(in1);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    Toast.makeText(LogIn_activity.this, "Conectare realizata cu succes!", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LogIn_activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        gotoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoReg= new Intent(LogIn_activity.this,Register_activity_home.class);
                startActivity(gotoReg);
                finish();
            }
        });

        resetarParola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adresa_email = emailAddress.getText().toString();

                if(!adresa_email.isEmpty()){
                    mAuth.sendPasswordResetEmail(adresa_email).addOnSuccessListener((OnSuccessListener<? super Void>) task ->
                            Toast.makeText(LogIn_activity.this, "Email-ul pentru resetarea parolei a fost trimis",
                                    Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LogIn_activity.this,"A aparut o eroare la trimiterea e-mail-ului.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }else {
                    emailAddress.setError("Introduceti adresa de email.");
                }
            }
        });

    }
}