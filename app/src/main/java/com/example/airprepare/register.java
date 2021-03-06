package com.example.airprepare;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity {

    EditText cpassword, epassword;

    DatabaseReference databasemember;

    FirebaseAuth firebaseAuth;

    FirebaseUser user;

    String userId;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        databasemember = FirebaseDatabase.getInstance().getReference("USERS");

        // eemail=findViewById(R.id.emailr);

        epassword=findViewById(R.id.passwordr);
        cpassword = findViewById(R.id.cpasswordr);
        firebaseAuth=FirebaseAuth.getInstance();
    }



    public void Openloginpage(View view) {
        addmember();
        Intent intent = new Intent(register.this,Homescreen.class);
        startActivity(intent);

    }

    private void addmember(){

        // String email=eemail.getText().toString().trim();

        String password=epassword.getText().toString().trim();

        String number=getIntent().getStringExtra("number");


        if (!TextUtils.isEmpty(password)) {
            String id = databasemember.push().getKey();

            databasemember.child(id).child("phnumber").setValue(number);
            databasemember.child(id).child("pass").setValue(password);
            Toast.makeText(this, "REGISTRATION SUCCESS", Toast.LENGTH_SHORT).show();

        }

        else {

            Toast.makeText(this, "REGISTRATION NOT SUCCESS", Toast.LENGTH_SHORT).show();

        }

    }



}