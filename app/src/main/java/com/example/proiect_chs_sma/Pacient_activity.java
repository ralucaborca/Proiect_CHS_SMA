package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pacient_activity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userId;
    private TextView id_pacientel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacient);
        Button button_completati_formular = findViewById(R.id.button_completati_fomular);
        Button button_sugestii = findViewById(R.id.button_sugestii);

        String idpac = getIntent().getStringExtra("idpacient");
        id_pacientel = findViewById(R.id.idpacientel);
        id_pacientel.setText(idpac);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

       TextView fullnameView = findViewById(R.id.nume_prenume);
       TextView emailView = findViewById(R.id.email);

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile = snapshot.getValue(User.class);

                if(userprofile != null){
                    String fullname = userprofile.fullname;
                    String email = userprofile.email;

                    fullnameView.setText(fullname);
                    emailView.setText(email);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Pacient_activity.this, "Ceva neasteptat s-a intamplat!", Toast.LENGTH_LONG).show();
            }
        });



        button_completati_formular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namefull = fullnameView.getText().toString();
                Intent gotoformular = new Intent(Pacient_activity.this,FormularPacient_activity.class);
                gotoformular.putExtra("nume_p", namefull);
                startActivity(gotoformular);
                finish();
            }
        });

        button_sugestii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosugestii = new Intent(Pacient_activity.this,Sugestii_activity.class);
                gotosugestii.putExtra("pacientID", idpac);
                startActivity(gotosugestii);
                finish();
            }
        });


    }
}