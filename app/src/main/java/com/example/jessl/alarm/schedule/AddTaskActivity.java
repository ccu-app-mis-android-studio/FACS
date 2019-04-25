package com.example.jessl.alarm.schedule;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jessl.alarm.R;
import com.example.jessl.alarm.schedule.adapter.ProductAdapter;
import com.example.jessl.alarm.schedule.database.SqliteDatabase;

import java.util.Calendar;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

    private static final String TAG = AddTaskActivity.class.getSimpleName();
    private EditText taskName;
    private EditText taskDescription;
    private Spinner taskCategory;
    private static EditText taskDueDate;
    private static EditText taskDueTime;
    private CheckBox reminder;
    private boolean isAlarmSet = false;
    private String selectedCategory;
    private static String selectedTime;
    private static String selectedDate;

    Button btnSaveTask, btnCancel;

    private SqliteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        taskName = (EditText) findViewById(R.id.add_task_name);
        taskDescription = (EditText) findViewById(R.id.add_task_description);
        taskDueDate = (EditText) findViewById(R.id.add_task_ending);
        taskDueTime = (EditText) findViewById(R.id.add_task_ending_time);
        reminder = (CheckBox) findViewById(R.id.set_task_alarm);
        reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isAlarmSet = true;
                } else {
                    isAlarmSet = false;
                }
            }
        });

        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = taskName.getText().toString();
                final String desc = taskDescription.getText().toString();
                final String dueDate = taskDueDate.getText().toString();
                final String dueTime = taskDueTime.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc)) {
                    Toast.makeText(AddTaskActivity.this, R.string.invalid_input_values, Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(dueDate) || TextUtils.isEmpty(dueTime)) {
                    Toast.makeText(AddTaskActivity.this, R.string.task_date_and_time, Toast.LENGTH_LONG).show();
                } else {
                    Product newProduct = new Product(name, desc, dueDate, dueTime);
                    mDatabase.addProduct(newProduct);

                    //refresh the activity
                    finish();
                    Intent a = new Intent(AddTaskActivity.this,Schedule.class);
                    startActivity(a);
                }
            }});

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTaskActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
//                finish();
//                startActivity(getIntent());
                Intent a = new Intent(AddTaskActivity.this,Schedule.class);
                startActivity(a);
            }
        });

        //add task date
        ImageView addTaskDate = (ImageView) findViewById(R.id.add_task_date);
        addTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerFragment().show(getSupportFragmentManager(), "Task Date");
            }
        });
        // delete task date
        ImageView deleteTaskDate = (ImageView) findViewById(R.id.delete_task_date);
        deleteTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskDueDate.setText("");
            }
        });
        ImageView addTaskTime = (ImageView) findViewById(R.id.add_task_time);
        addTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePicker().show(getSupportFragmentManager(), "Task Time");
            }
        });
        ImageView deleteTaskTime = (ImageView) findViewById(R.id.delete_task_time);
        deleteTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskDueTime.setText("");
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.add_task_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.action_add_task){
//            //call method to save in database.
//            validateInputAndSave();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    private void validateInputAndSave() {
        String name = taskName.getText().toString();
        String desc = taskDescription.getText().toString();
        String dueDate = taskDueDate.getText().toString();
        String dueTime = taskDueTime.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc) || TextUtils.isEmpty(selectedCategory)) {
            Toast.makeText(AddTaskActivity.this, R.string.invalid_input_values, Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(dueDate) || TextUtils.isEmpty(dueTime)) {
            Toast.makeText(AddTaskActivity.this, R.string.task_date_and_time, Toast.LENGTH_LONG).show();
        } else {
            //add task to sqlite database
            Product newProduct = new Product(name, desc, dueDate, dueTime);
            mDatabase.addProduct(newProduct);

            //refresh the activity
            finish();
            startActivity(getIntent());

        }
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
            selectedTime =  String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
            taskDueTime.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
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
            selectedDate = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
            taskDueDate.setText(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day));
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
