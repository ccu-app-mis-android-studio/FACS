package com.example.jessl.alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlarmManager;
import android.content.Context;
import android.widget.TextView;
import android.app.PendingIntent;
import android.widget.TimePicker;

import java.util.Calendar;

public class Alarm extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

    }

    public void onBackPressed(){
        Intent intent = new Intent(Alarm.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
