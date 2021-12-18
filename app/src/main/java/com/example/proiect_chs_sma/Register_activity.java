package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_activity extends AppCompatActivity {
    private EditText names, emails, passwords, passwords2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        names = findViewById(R.id.editnume);
        emails = findViewById(R.id.email);
        passwords = findViewById(R.id.Parola);
        passwords2 = findViewById(R.id.parola2);
        Button button_signup = findViewById(R.id.button_register);

        //mAuth=FirebaseAuth.getInstance();
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference databaseReference = database.getReference();


        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namess = names.getText().toString().trim();
                String emailss = emails.getText().toString().trim();
                String passwordss = passwords.getText().toString().trim();
                String passwordss2 = passwords2.getText().toString().trim();
                String verificareParola = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (namess.isEmpty()) {
                    emails.setError("Introduceti un nume!");
                    emails.requestFocus();
                    return;
                }
                if (emailss.isEmpty()) {
                    emails.setError("Introduceti adresa de e-mail!");
                    emails.requestFocus();
                    return;
                } else {
                    if (!emailss.matches(verificareParola)) {
                        emails.setError("Ati introdus o adresa de e-mail gresita!");
                        emails.requestFocus();
                        return;
                    }
                }
                if (passwordss.isEmpty()) {
                    emails.setError("Introduceti parola!");
                    emails.requestFocus();
                    return;
                }
                if (passwordss.length() < 6) {
                    passwords.setError("Prola trebuie sa contina minim 6 caractere!");
                    passwords.requestFocus();
                    return;
                }
                if (passwordss2.isEmpty()) {
                    emails.setError("Reintroduceti parola!");
                    emails.requestFocus();
                    return;
                } else {
                    if (!passwordss2.equals(passwordss)) {
                        emails.setError("Ati introdus o parola diferita fata de cea initiala!");
                        emails.requestFocus();
                        return;
                    }
                }
                mAuth=FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(emailss, passwordss)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Register_activity.this, "Cont creat cu success!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register_activity.this, LogIn_activity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register_activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}