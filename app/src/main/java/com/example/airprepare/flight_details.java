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
    EditText c, d, e, f, g, et1;
    Button btn;
    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("FLIGHTS");
        et1 = findViewById(R.id.et1);
        setContentView(R.layout.activity_flight_details);
        c = findViewById(R.id.text3);
        d = findViewById(R.id.text4);
        e = findViewById(R.id.text5);
        f = findViewById(R.id.text6);
        g = findViewById(R.id.text7);
        btn = findViewById(R.id.show);

    }

    public void getFlights(View view) {
        final String number = "THURSDAY";
        databaseReference1.child("AIRINDIA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               /* Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while (items.hasNext()){
                    DataSnapshot item = items.next();
                    if(item.child("AIRINDIA").getValue().toString()=="17501A0529"){
                        Toast.makeText(Homescreen.this, "Hii", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }*/
                /*
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String value = snapshot.getValue(String.class);
                    if (value == "Samples") {
                        Toast.makeText(Homescreen.this, "ZZZZ..", Toast.LENGTH_SHORT).show();
                        break;

                    }

                 */
                // String Date1 = "";
                /*for (DataSnapshot ds: dataSnapshot.getChildren()){
                    HashMap<String,String> map= (HashMap<String, String>) ds.getValue();
                    String Date = map.get("Date").toString();
                    Date1 = Date1 + Date;
                }*/
                //  ArrayList<String> names = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //                String name = ds.child("DATE").getValue(String.class);
                    //                  names.add(name);
//                    String number = ds.child("DAY").getValue(String.class);
                    //Toast.makeText(Homescreen.this, name+number, Toast.LENGTH_SHORT).show();*/
                    String Date = ds.child("DAY").getValue(String.class);
                    // Date1 = Date1 + Date;
                    if (Date.equals(number)) {
                        //                  Toast.makeText(flight_details.this, "Hii", Toast.LENGTH_SHORT).show();

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
                    //Toast.makeText(Homescreen.this, Date1, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }

}