package com.example.airprepare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class flight_details extends AppCompatActivity {
    EditText b, c, d, e, f, g;
    Button btn;
    String number;

    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);
        b = findViewById(R.id.text2);
        c = findViewById(R.id.text3);
        d = findViewById(R.id.text4);
        databaseReference1 = FirebaseDatabase.getInstance().getReference("FLIGHTS");
        e = findViewById(R.id.text5);
        f = findViewById(R.id.text6);
        g = findViewById(R.id.text7);
        btn = findViewById(R.id.btn1);

    }

    public void getFlights(View view) {
        number = b.getText().toString();
        if (number.substring(0, 2).equals("18")) {
            databaseReference1.child("AIRINDIA").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String num = ds.child("NUMBER").getValue(String.class);

                        if (num.equals(number)) {


                            String date = ds.child("DATE").getValue().toString();
                            String from = ds.child("FROM").getValue().toString();
                            String time = ds.child("TIME").getValue().toString();
                            String to = ds.child("TO").getValue().toString();
                            String day = ds.child("DAY").getValue().toString();

                            d.setText(from);
                            e.setText(time);
                            f.setText(to);
                            g.setText(day);
                            c.setText(date);
                            break;
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (number.substring(0, 2).equals("91")) {
            databaseReference1.child("INDIGO").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String num = ds.child("NUMBER").getValue(String.class);

                        if (num.equals(number)) {


                            String date = ds.child("DATE").getValue().toString();
                            String from = ds.child("FROM").getValue().toString();
                            String time = ds.child("TIME").getValue().toString();
                            String to = ds.child("TO").getValue().toString();
                            String day = ds.child("DAY").getValue().toString();

                            d.setText(from);
                            e.setText(time);
                            f.setText(to);
                            g.setText(day);
                            c.setText(date);
                            break;
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if (number.substring(0, 2).equals("81")) {
            databaseReference1.child("KINGFISHER").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String num = ds.child("NUMBER").getValue(String.class);

                        if (num.equals(number)) {


                            String date = ds.child("DATE").getValue().toString();
                            String from = ds.child("FROM").getValue().toString();
                            String time = ds.child("TIME").getValue().toString();
                            String to = ds.child("TO").getValue().toString();
                            String day = ds.child("DAY").getValue().toString();

                            d.setText(from);
                            e.setText(time);
                            f.setText(to);
                            g.setText(day);
                            c.setText(date);
                            break;
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}