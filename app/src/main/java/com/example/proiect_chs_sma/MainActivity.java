package com.example.proiect_chs_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button but_register,but_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but_register = findViewById(R.id.inregistrare);
        but_login = findViewById(R.id.button2);

        but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregister = new Intent(MainActivity.this,Register_activity_home.class);
                startActivity(gotoregister);

            }
        });

        but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologin = new Intent(MainActivity.this,LogIn_activity.class);
                startActivity(gotologin);
            }
        });

    }

}