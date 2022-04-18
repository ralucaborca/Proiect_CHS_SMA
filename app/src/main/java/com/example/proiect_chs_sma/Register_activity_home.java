package com.example.proiect_chs_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register_activity_home extends AppCompatActivity {
    Button pacient,medic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_home);

        medic = findViewById(R.id.register_medic);
        pacient = findViewById(R.id.register_pacient);

        medic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medic_reg = new Intent(Register_activity_home.this, Register_activity_doctor.class);
                startActivity(medic_reg);
            }
        });

        pacient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(Register_activity_home.this, Register_activity_pacient.class);
                startActivity(reg);
            }
        });
    }
}