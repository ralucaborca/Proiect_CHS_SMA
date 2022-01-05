package com.example.proiect_chs_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Doctor_activity extends AppCompatActivity {
    Button lista_pacienti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        lista_pacienti.findViewById(R.id.lista_pacienti);
        lista_pacienti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotolista = new Intent(Doctor_activity.this,ListaPacienti_activity.class);
                startActivity(gotolista);
            }
        });
    }
}