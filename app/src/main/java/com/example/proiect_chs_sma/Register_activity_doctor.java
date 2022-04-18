package com.example.proiect_chs_sma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Register_activity_doctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText names, prenumes, emails, passwords, passwords2, specializare, adresa;
    private FirebaseAuth mAuth;
    private TextView gotologin;
    private Spinner spinner;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doctor);
        gotologin = findViewById(R.id.login_doctor);
        names = findViewById(R.id.editnume_doctor);
        prenumes = findViewById(R.id.editprenume_doctor);
        specializare = findViewById(R.id.specializare_doctor);
        adresa = findViewById(R.id.adresa_doctor);
        emails = findViewById(R.id.email_doctor);
        passwords = findViewById(R.id.Parola_doctor);
        passwords2 = findViewById(R.id.parola2_doctor);

        Button button_signup = findViewById(R.id.button_register_doctor);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorii, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namess = names.getText().toString().trim();
                String prenumess = prenumes.getText().toString().trim();
                String specializares = specializare.getText().toString().trim();
                String adresas = adresa.getText().toString().trim();
                String emailss = emails.getText().toString().trim();
                String passwordss = passwords.getText().toString().trim();
                String passwordss2 = passwords2.getText().toString().trim();
                String verificareParola = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (namess.isEmpty()) {
                    names.setError("Introduceti numele dumneavoastra!");
                    names.requestFocus();
                    return;
                }
                if (prenumess.isEmpty()) {
                    prenumes.setError("Introduceti prenumele dumneavoastra!");
                    prenumes.requestFocus();
                    return;
                }
                if (specializares.isEmpty()) {
                    specializare.setError("Introduceti specializarea dumneavoastra!");
                    specializare.requestFocus();
                    return;
                }
                if (adresas.isEmpty()) {
                    adresa.setError("Introduceti adresa cabinetului!");
                    adresa.requestFocus();
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
                    passwords.setError("Introduceti parola!");
                    passwords.requestFocus();
                    return;
                }
                if (passwordss.length() < 6) {
                    passwords.setError("Prola trebuie sa contina minim 6 caractere!");
                    passwords.requestFocus();
                    return;
                }
                if (passwordss2.isEmpty()) {
                    passwords2.setError("Reintroduceti parola!");
                    passwords2.requestFocus();
                    return;
                } else {
                    if (!passwordss2.equals(passwordss)) {
                        passwords2.setError("Ati introdus o parola diferita fata de cea initiala!");
                        passwords2.requestFocus();
                        return;
                    }
                }
                mAuth = FirebaseAuth.getInstance();


                mAuth.createUserWithEmailAndPassword(emailss, passwordss).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Doctor_details doctor_details = new Doctor_details(namess, prenumess, specializares, adresas, emailss, "doctor");

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(doctor_details)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Toast.makeText(Register_activity_doctor.this, "User creat cu succes!", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Register_activity_doctor.this, "A aparut o eroare!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(Register_activity_doctor.this, "A aparut o eroare!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLog = new Intent(Register_activity_doctor.this, LogIn_activity.class);
                startActivity(gotoLog);
                finish();
            }
        });
        /*try{
            String result = new JsonTask().execute("https://regmed.cmr.ro/api/v1/public/cautare/"+names).get();
            String totalResults = result.split(",")[0].replaceAll("[^0-9]", "");
            int numberOfResults = Integer.parseInt(totalResults);
            if(numberOfResults == 0){
                names.requestFocus();
                names.setError("Doctorul cu acest nume nu exista.");
                names.setError("");
                return;
            }

            JSONObject doctorDetails = JsonParser(result).getAsJsonObject().get("results").getAsJosnArray().get(0).getAsJsonObject();
            String status = doctorDetails.get("status").toString().toLowerCase().replace("\"","");
            String specialitate = doctorDetails.get("specialitati").getAsJsonArray().get(0).getAsJsonObject().get("name").toString().toLowerCase().replace("\"","");
            if(!status.equals("activ")){
                names.requestFocus();
                names.setError("Doctorul nu mai profeseaza.");
                names.setError("");
                return;
            }
            if(! specialitate.equals("cardiologie")){
                names.requestFocus();
                names.setError("Doctorul nu este cardiolog.");
                names.setError("");
                return;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    */
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String alegere = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),alegere,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;
            try{
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = bufferedReader.readLine()) != null){
                    buffer.append(line).append("\n");
                }
                return buffer.toString();


            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if(connection != null){
                    connection.disconnect();
                }try{
                    if(bufferedReader != null){
                        bufferedReader.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

    }

}