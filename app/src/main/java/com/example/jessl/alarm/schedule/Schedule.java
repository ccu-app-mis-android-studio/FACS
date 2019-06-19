package com.example.jessl.alarm.schedule;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CalendarView;
import android.content.Intent;
import android.view.View;

import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

import android.widget.Toast;

import com.example.jessl.alarm.R;
import com.example.jessl.alarm.schedule.adapter.ProductAdapter;
import com.example.jessl.alarm.schedule.database.SqliteDatabase;
import com.github.clans.fab.FloatingActionButton;

public class Schedule extends AppCompatActivity {

    private static final String TAG = Schedule.class.getSimpleName();
    private FloatingActionButton api;
    private FloatingActionButton add;

    TextView myDate1;
    CalendarView calendarView;

    private static EditText taskdate;
    private static EditText tasktime;
    private static String selectedTime;
    private static String selectedDate;

    private SqliteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule2);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate1 = (TextView) findViewById(R.id.activity_main_text_day_of_month);
        String date_n = new SimpleDateFormat("yyyy - M - d", Locale.getDefault()).format(new Date());
        myDate1.setText(date_n);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            final Calendar ca = Calendar.getInstance();
            int year = ca.get(Calendar.YEAR);
            int month = ca.get(Calendar.MONTH);
            int day = ca.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                myDate1.setText("");
                String date = year + " - " + (month + 1) + " - " + dayOfMonth;
                myDate1.setText(date);
            }
        });

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
                // add new quick task
                addTaskDialog();
            }
        });
    }

    //after clicked add FAB
    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.activity_add_task, null);

        final EditText taskname = (EditText)subView.findViewById(R.id.add_task_name);
        final EditText taskdesc = (EditText)subView.findViewById(R.id.add_task_description);
        taskdate = (EditText)subView.findViewById(R.id.add_task_ending);
        tasktime = (EditText)subView.findViewById(R.id.add_task_ending_time);

        //add task date
        final ImageView addTaskDate = (ImageView) subView.findViewById(R.id.add_task_date);
        addTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerFragment().show(getSupportFragmentManager(), "Task Date");
            }
        });
        // delete task date
        final ImageView deleteTaskDate = (ImageView) subView.findViewById(R.id.delete_task_date);
        deleteTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskdate.setText("");
            }
        });
        final ImageView addTaskTime = (ImageView) subView.findViewById(R.id.add_task_time);
        addTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePicker().show(getSupportFragmentManager(), "Task Time");
            }
        });
        final ImageView deleteTaskTime = (ImageView) subView.findViewById(R.id.delete_task_time);
        deleteTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasktime.setText("");
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Task");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("ADD NEW TASK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = taskname.getText().toString();
                final String desc = taskdesc.getText().toString();
                final String date = taskdate.getText().toString();
                final String time = tasktime.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(desc)){
                    Toast.makeText(Schedule.this, R.string.invalid_input_values , Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty(date) || TextUtils.isEmpty(time)){
                    Toast.makeText(Schedule.this, R.string.task_date_and_time, Toast.LENGTH_LONG).show();
                }
                else{
                    Product newProduct = new Product(name, desc, date, time);
                    mDatabase.addProduct(newProduct);

                    //refresh the activity
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Schedule.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            selectedTime =  String.valueOf(hourOfDay) + " : " + String.valueOf(minute);
            tasktime.setText(String.valueOf(hourOfDay) + " : " + String.valueOf(minute));
        }
    }
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            selectedDate = String.valueOf(year) + " - " + String.valueOf(month+1) + " - " + String.valueOf(day);
            taskdate.setText(String.valueOf(year) + " - " + String.valueOf(month+1) + " - " + String.valueOf(day));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }
}



