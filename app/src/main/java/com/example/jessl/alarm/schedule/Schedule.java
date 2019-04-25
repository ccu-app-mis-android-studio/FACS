package com.example.jessl.alarm.schedule;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;
import android.widget.CalendarView;
import android.content.Intent;
import android.view.View;

import java.util.Calendar;
import java.util.List;

import android.widget.Toast;

import com.example.jessl.alarm.R;
import com.example.jessl.alarm.schedule.adapter.ProductAdapter;
import com.example.jessl.alarm.schedule.database.SqliteDatabase;
import com.github.clans.fab.FloatingActionButton;
import com.example.jessl.alarm.schedule.AddTaskActivity;

public class Schedule extends AppCompatActivity {

    private FloatingActionButton api;
    private FloatingActionButton add;

    TextView myDate1;
    CalendarView calendarView;

    private SqliteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule2);

        RecyclerView productView = (RecyclerView)findViewById(R.id.task_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(linearLayoutManager);
        productView.setHasFixedSize(true);

        mDatabase = new SqliteDatabase(this);
        List<Product> allProducts = mDatabase.listProducts();


        if(allProducts.size() > 0){
            productView.setVisibility(View.VISIBLE);
            ProductAdapter mAdapter = new ProductAdapter(this, allProducts);
            productView.setAdapter(mAdapter);

        }else {
            productView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no task in the database. Start adding now", Toast.LENGTH_LONG).show();
        }

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
                Intent addTaskIntent = new Intent(Schedule.this, AddTaskActivity.class);
                startActivity(addTaskIntent);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //return super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.task_menu, menu);
//        return true;
//    }
}


