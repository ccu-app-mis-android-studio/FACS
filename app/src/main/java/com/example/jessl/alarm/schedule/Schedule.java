package com.example.jessl.alarm.schedule;

import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.CalendarView;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import android.widget.Button;
import android.widget.Toast;

import com.example.jessl.alarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Schedule extends AppCompatActivity {

    private Button api;
    private Button add;
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

        api = (Button) findViewById(R.id.calendarAPI);
        api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        add = (Button) findViewById(R.id.Add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //working with data
        ourdoes = findViewById(R.id.ourdoes);
        ourdoes.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<SDoes>();

        // get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("FACS");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // set code to retrieve data and replace layout
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    SDoes p = dataSnapshot1.getValue(SDoes.class);
                    list.add(p);
                }
                sAdapter = new SAdapter(Schedule.this, list);
                ourdoes.setAdapter(sAdapter);
                sAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // set code to show an error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


