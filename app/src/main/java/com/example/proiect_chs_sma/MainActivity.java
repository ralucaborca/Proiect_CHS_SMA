package com.example.proiect_chs_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView register;
    Button but_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but_register=findViewById(R.id.inregistrare);

        but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregister = new Intent(MainActivity.this,Register_activity.class);
                startActivity(gotoregister);
            }
        });
    }
    /*public void onClick(View v){
        switch (v.getId()){
            case R.id.signup:
                startActivity(new Intent(this,SignUp.class));
                break;
        }
    }*/
}