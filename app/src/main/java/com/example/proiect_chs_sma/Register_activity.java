package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText names, emails, passwords, passwords2;
    private FirebaseAuth mAuth;
    private TextView gotologin;
    private Spinner spinner;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = findViewById(R.id.alegerecategorie);
        gotologin = findViewById(R.id.login);
        names = findViewById(R.id.editnume);
        emails = findViewById(R.id.email);
        passwords = findViewById(R.id.Parola);
        passwords2 = findViewById(R.id.parola2);
        Button button_signup = findViewById(R.id.button_register);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorii, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namess = names.getText().toString().trim();
                String emailss = emails.getText().toString().trim();
                String passwordss = passwords.getText().toString().trim();
                String passwordss2 = passwords2.getText().toString().trim();
                String spinner1 = spinner.getSelectedItem().toString().trim();
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

                /*mAuth.createUserWithEmailAndPassword(emailss, passwordss)
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
                });*/

                mAuth.createUserWithEmailAndPassword(emailss, passwordss).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(namess, emailss);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register_activity.this, "User creat cu succes!",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(Register_activity.this, "A aparut o eroare!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Register_activity.this, "A aparut o eroare!",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });



        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLog= new Intent(Register_activity.this,LogIn_activity.class);
                startActivity(gotoLog);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String alegere = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),alegere,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}