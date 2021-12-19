package com.example.proiect_chs_sma;

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
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth=FirebaseAuth.getInstance();

        emailAddress=findViewById(R.id.textnume);
        password=findViewById(R.id.textparola);
        radioGroup=findViewById(R.id.radioGroup);
        radioButton1=findViewById(R.id.radio_medic);
        radioButton2=findViewById(R.id.radio_pacient);
        Button button_login_doctor = findViewById(R.id.button_login_doctor);
        Button button_login_pacient = findViewById(R.id.button_login_pacient);

        button_login_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailCreate = emailAddress.getText().toString().trim();
                String passwordCreate = password.getText().toString().trim();
                String verificareMail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

               /*if(!emailCreate.matches(verificareMail)){
                    emailCreate.setError("Mail-ul este invalid!");
                    return;
                }*/

                mAuth.signInWithEmailAndPassword(emailCreate, passwordCreate).addOnSuccessListener(authResult -> {
                    startActivity(new Intent(LogIn_activity.this, Doctor_activity.class));
                    finish();
                    Toast.makeText(LogIn_activity.this, "Conectare realizata cu succes!", Toast.LENGTH_SHORT).show();
                    //public void onFailure(@NonNull Exception e){
                    // Toast.makeText(LogIn_activity.this, e.getMessage, Toast.LENGTH_SHORT).show();
                    //  }
                });
            }
        });

        button_login_pacient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailCreate = emailAddress.getText().toString().trim();
                String passwordCreate = password.getText().toString().trim();
                String verificareMail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

               /*if(!emailCreate.matches(verificareMail)){
                    emailCreate.setError("Mail-ul este invalid!");
                    return;
                }*/

                mAuth.signInWithEmailAndPassword(emailCreate, passwordCreate).addOnSuccessListener(authResult -> {
                    startActivity(new Intent(LogIn_activity.this, Pacient_activity.class));
                    finish();
                    Toast.makeText(LogIn_activity.this, "Conectare realizata cu succes!", Toast.LENGTH_SHORT).show();
                    //public void onFailure(@NonNull Exception e){
                    // Toast.makeText(LogIn_activity.this, e.getMessage, Toast.LENGTH_SHORT).show();
                    //  }
                });
            }
        });
        /*radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int u_select= radioGroup.getCheckedRadioButtonId();
                StringBuffer result = new StringBuffer();
                result.append("Rez ales!");
                if(radioButton1 != null){
                    startActivity(new Intent(LogIn_activity.this,Doctor_activity.class));
                    result.append("\nProfesie: \n").append(radioButton1.getText().toString());
                }
            }
        });*/

    }
}