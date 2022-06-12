package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register_activity_pacient extends AppCompatActivity {
    private EditText name, email, password, password2;
    private FirebaseAuth mAuth;
    private TextView gotologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pacient);

        gotologin = findViewById(R.id.login_pacient);
        name = findViewById(R.id.editnume_pacient);
        email = findViewById(R.id.email_pacient);
        password = findViewById(R.id.Parola_pacient);
        password2 = findViewById(R.id.parola2_pacient);
        Button button_signup = findViewById(R.id.button_register_pacient);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorii, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names = name.getText().toString().trim();
                String emails = email.getText().toString().trim();
                String passwords = password.getText().toString().trim();
                String passwords2 = password2.getText().toString().trim();
                String verificareMail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (names.isEmpty()) {
                    name.setError("Introduceti un nume!");
                    name.requestFocus();
                    return;
                }

                if (emails.isEmpty()) {
                    email.setError("Introduceti adresa de e-mail!");
                    email.requestFocus();
                    return;
                } else {
                    if (!emails.matches(verificareMail)) {
                        email.setError("Ati introdus o adresa de e-mail gresita!");
                        email.requestFocus();
                        return;
                    }
                }
                if (passwords.isEmpty()) {
                    password.setError("Introduceti parola!");
                    password.requestFocus();
                    return;
                }
                if (passwords.length() < 6) {
                    password.setError("Prola trebuie sa contina minim 6 caractere!");
                    password.requestFocus();
                    return;
                }
                if (passwords2.isEmpty()) {
                    password2.setError("Reintroduceti parola!");
                    password2.requestFocus();
                    return;
                } else {
                    if (!passwords2.equals(passwords)) {
                        password2.setError("Ati introdus o parola diferita fata de cea initiala!");
                        password2.requestFocus();
                        return;
                    }
                }
                mAuth = FirebaseAuth.getInstance();


                mAuth.createUserWithEmailAndPassword(emails, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(names, emails, "pacient");

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Toast.makeText(Register_activity_pacient.this, "User creat cu succes!", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Register_activity_pacient.this, "A aparut o eroare!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(Register_activity_pacient.this, "A aparut o eroare!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });


        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLog = new Intent(Register_activity_pacient.this, LogIn_activity.class);
                startActivity(gotoLog);
                finish();
            }
        });
    }

}
