package com.example.jessl.alarm;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    ImageButton food;
    ImageButton alarm;
    ImageButton chat;
    ImageButton schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        schedule = (ImageButton) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, Schedule.class);
                startActivity(i);
            }
        });

        alarm = (ImageButton) findViewById(R.id.alarm);
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(HomeActivity.this, Alarm.class);
                startActivity(i2);
            }
        });

        chat = (ImageButton) findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(HomeActivity.this, Chat.class);
                startActivity(i3);
            }
        });

        food = (ImageButton) findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(HomeActivity.this, Food.class);
                startActivity(i4);
            }
        });
    }
}

