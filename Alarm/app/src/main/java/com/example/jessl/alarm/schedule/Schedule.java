package com.example.jessl.alarm.schedule;

import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.CalendarView;
import android.content.Intent;
import android.view.View;
import java.util.Calendar;
import android.widget.Button;
import com.example.jessl.alarm.R;
import java.util.Locale;


public class Schedule extends AppCompatActivity {

    private Button api;
    private Button add;
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    TextView myDate1;
    CalendarView calendarView;

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
                myDate1.setText(dayOfMonth);
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
            public void onClick(View arg0) {
//                List<Date> dayValueInCells = new ArrayList<Date>();
//                mQuery = new DatabaseQuery(context);
//                List<EventObject> mEvents = mQuery.getAllFutureEvents();
//                Calendar mCal = (Calendar)cal.clone();
//                mCal.set(Calendar.DAY_OF_MONTH, 1);
//                int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
//                mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
////                while(dayValueInCells.size() < MAX_CALENDAR_COLUMN){
////                    dayValueInCells.add(mCal.getTime());
////                    mCal.add(Calendar.DAY_OF_MONTH, 1);
////                }
//                Log.d(TAG, "Number of date " + dayValueInCells.size());
//                String sDate = formatter.format(cal.getTime());
//                currentDate.setText(sDate);
//                mAdapter = new GridAdapter(context, dayValueInCells, cal, mEvents);
//                calendarGridView.setAdapter(mAdapter);


            }
        });
    }
}


