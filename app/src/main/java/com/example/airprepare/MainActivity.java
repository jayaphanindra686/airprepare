package com.example.airprepare;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference1, databaseReference2;


    Button createaccountb, login;

    FirebaseAuth mAuth;

    EditText number, password;

    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;

    String num;

    String pas;

    ProgressDialog progressDialog;

    int repeat = 100;

    Intent intent;

    private FirebaseAuth.AuthStateListener authStateListener;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        intent = new Intent(this, Homescreen.class);


        databaseReference1 = FirebaseDatabase.getInstance().getReference("USERS");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        createaccountb = findViewById(R.id.createaccountb);

        number = findViewById(R.id.number);

        password = findViewById(R.id.password);

        login = findViewById(R.id.signinb);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Loading...");

        progressDialog.setCanceledOnTouchOutside(false);


        if (firebaseAuth.getCurrentUser() != null) {

            finish();

            startActivity(new Intent(this, Homescreen.class));

        }


    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("Data", MODE_APPEND);
        String mbno = sharedPreferences.getString("mbno", "");
        String pass = sharedPreferences.getString("pass", "");
        if (mbno != null && pass != null) {
            //  Intent i1 = new Intent(this, Homescreen.class);
            startActivity(intent);
        }

    }


    public void Opennunberactivity(View view) {

        Intent i = new Intent(this, numberauthentication.class);

        startActivity(i);

    }


    public void openHomescreen(View view) {
        // login.setClickable(false);

        if (number.getText().toString() == null || number.getText().toString().equals("") && password.getText().toString() == null || password.getText().toString().equals("")) {

            Toast.makeText(MainActivity.this, "Inavlid Inputs", Toast.LENGTH_SHORT).show();

        } else {

            //     progressDialog.show();

            final String num1, pass;

            num1 = number.getText().toString();
            pass = password.getText().toString();
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String passw = ds.child("pass").getValue(String.class);
                        String number = ds.child("phnumber").getValue(String.class);

                        //for(int i = 0; i < num.length() ; i++){
                            /*if(num.charAt(i) == '*'){
                                if(num.charAt(i+1)== '/'){
                                    if(num.charAt(i+2)=='/' ){
                                        if(num.charAt(i+3)=='*'){
                                           String substr1 =  num.substring(0,i);
                                           String substr2 = num.substring(i+4);
                                           if((num1 == substr1) && (pass == substr2)) {
                                               repeat = 0;
                                               Toast.makeText(MainActivity.this, substr1 + "pp" + substr2, Toast.LENGTH_SHORT).show();
                                               break;
                                           }
                                        }
                                    }
                                }
                            }*/
                        if (passw.equals(pass)) {
                            if (number.equals(num1)) {
                                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                break;
                            }
                        }

                    }

                   /*     if (num.equals(number)) {


                            String date = ds.child("DATE").getValue().toString();
                            String from = ds.child("FROM").getValue().toString();
                            String time = ds.child("TIME").getValue().toString();
                            String to = ds.child("TO").getValue().toString();
                            String day = ds.child("DAY").getValue().toString();

                        }*/

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}