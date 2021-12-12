package com.example.proiect_chs_sma;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

public class SignUp extends AppCompatActivity{
    private TextView signup;
    private EditText names, emails, passwords, passwords2;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        names = findViewById(R.id.edittextnume);
        emails = findViewById(R.id.edittextemail);
        passwords = findViewById(R.id.edittextpass);
        passwords2 = findViewById(R.id.edittextpass2);
        signup=findViewById(R.id.signup);
        Button button_signup = findViewById(R.id.button);

        mAuth=FirebaseAuth.getInstance();
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference databaseReference = database.getReference();



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

                mAuth.createUserWithEmailAndPassword(emailss, passwordss)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignUp.this, "Cont creat cu success!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }
   // public void registerUser() {
       /* String email = emails.getText().toString().trim();
        String password = passwords.getText().toString().trim();
        String name = names.getText().toString().trim();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if(email.isEmpty()){
                emails.setError("Introduceti adresa de e-mail!");
                emails.requestFocus();
                return;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emails.setError("Introduceti adresa de e-mail corecta!");
                emails.requestFocus();
                return;
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if(password.isEmpty()){
                passwords.setError("Introducti parola corecta!");
                passwords.requestFocus();
                return;
            }
        }
        if(password.length() < 6){
            passwords.setError("Prola trebuie sa contina minim 6 caractere!");
            passwords.requestFocus();
            return;
        }


        if(name.isEmpty()){
            names.setError("Introduceti numele complet!");
            names.requestFocus();
            return;
        }*/


        //button_signup.setVisibility(View.VISIBLE);
       /* mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "User-ul a fost inregistrat cu succes!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(SignUp.this, "Inregistrarea nu a avut loc! Incercati din nou!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }


                    }else

                    {
                        Toast.makeText(SignUp.this, "Inregistrarea nu a avut loc! Incercati din nou!", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
                });
    }*/


}
