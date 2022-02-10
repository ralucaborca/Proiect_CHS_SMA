package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn_activity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private TextView emailAddress, password;
    private TextView gotoregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth=FirebaseAuth.getInstance();

        emailAddress=findViewById(R.id.textnume);
        password=findViewById(R.id.textparola);
        gotoregister = findViewById(R.id.register);
        Button button_login_doctor = findViewById(R.id.button_login_doctor);
        Button button_login_pacient = findViewById(R.id.button_login_pacient);

        button_login_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailCreate = emailAddress.getText().toString().trim();
                String passwordCreate = password.getText().toString().trim();
                String verificareMail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



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

                mAuth.signInWithEmailAndPassword(emailCreate, passwordCreate).addOnSuccessListener(authResult -> {
                    startActivity(new Intent(LogIn_activity.this, Doctor_activity.class));
                    finish();
                    Toast.makeText(LogIn_activity.this, "Conectare realizata cu succes!", Toast.LENGTH_SHORT).show();

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LogIn_activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        button_login_pacient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailCreate = emailAddress.getText().toString().trim();
                String passwordCreate = password.getText().toString().trim();
                String verificareMail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


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

                mAuth.signInWithEmailAndPassword(emailCreate, passwordCreate).addOnSuccessListener(authResult -> {
                    startActivity(new Intent(LogIn_activity.this, Pacient_activity.class));
                    finish();
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
                Intent gotoReg= new Intent(LogIn_activity.this,Register_activity.class);
                startActivity(gotoReg);
                finish();
            }
        });

    }
}