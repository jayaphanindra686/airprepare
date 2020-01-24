package com.example.airprepare;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Alarm extends AppCompatActivity {
    Spinner spinner, spinner2;
    EditText et;
    int flighttime = 530;
    private int alarmtime;

    //EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        //editText=findViewById(R.id.edittext);
        //editText.setText(date);
        et = findViewById(R.id.et);

    }

    public void setAlarm(View view) {
        int i, t = 0, u = 0, o;
        String alarmh = spinner.getSelectedItem().toString();
        String alarmm = spinner2.getSelectedItem().toString();
        //if(alarmm=="00")
        //  t=1;
        String alarm = alarmh + alarmm;
        alarmtime = Integer.valueOf(alarm);
        alarmtime = (flighttime) - (alarmtime);
        if (alarmtime < 0)
            alarmtime = alarmtime + 2400;
        i = alarmtime / 100;
        if ((alarmtime - (i * 100)) > 60)
            alarmtime = alarmtime - 40;
        u = (alarmtime / 100);
        et.setText(alarmtime + "     " + u);
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, i);
        o = alarmtime - u;//alarmtime/100);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, o);//(alarmtime-(alarmtime/100)));
        startActivity(intent);
    }
}
