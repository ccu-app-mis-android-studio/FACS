package com.example.jessl.alarm.schedule;

import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.system.ErrnoException;
import android.widget.TextView;
import android.widget.CalendarView;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import android.widget.Toast;

import com.example.jessl.alarm.MainActivity;
import com.example.jessl.alarm.MainActivity.*;
import com.example.jessl.alarm.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Schedule extends AppCompatActivity {

    private FloatingActionButton api;
    private FloatingActionButton add;

    TextView myDate1;
    CalendarView calendarView;

    DatabaseReference reference;
    RecyclerView ourdoes;
    ArrayList<SDoes> list;
    SAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule2);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate1 = (TextView) findViewById(R.id.activity_main_text_day_of_month);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + " - " + (month + 1) + " - " + dayOfMonth;
                myDate1.setText(date);
            }
        });

        //clicked google calendar api button
        api = (FloatingActionButton) findViewById(R.id.menuitem_1);
        api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Schedule.this, "Clicked", Toast.LENGTH_SHORT).show();
                Calendar calendarEvent = Calendar.getInstance();
                Intent i = new Intent(Intent.ACTION_EDIT);
                i.setType("vnd.android.cursor.item/event");
                i.putExtra("beginTime", calendarEvent.getTimeInMillis());
                i.putExtra("allDay", true);
                i.putExtra("rule", "FREQ=YEARLY");
                i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
                i.putExtra("title", "Add Event");
                startActivity(i);

            }
        });

        //clicked add new event button
        add = (FloatingActionButton) findViewById(R.id.menuitem_2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Schedule.this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Schedule.this, SEvent.class);
                startActivity(i);
            }
        });

        //working with data
        ourdoes = findViewById(R.id.ourdoes);
        ourdoes.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<SDoes>();
    }
}


