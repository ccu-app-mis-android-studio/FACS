package com.example.alramclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    AlarmManager alarm_manager;
    TimePicker alarm_timePicker;
    PendingIntent pending_intent;
    TextView update_text;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;


        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm_timePicker = (TimePicker) findViewById(R.id.timePicker);
        update_text = (TextView) findViewById(R.id.update_text);
        final Intent intent = new Intent(this.context, AlarmReceiver.class);
        final Calendar calendar = Calendar.getInstance();
        Button alarm_on = (Button) findViewById(R.id.alarm_on);


        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.set(Calendar.HOUR_OF_DAY, alarm_timePicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timePicker.getMinute());
                int hour = alarm_timePicker.getHour();
                int minute = alarm_timePicker.getMinute();

                String hour_String = String.valueOf(hour);
                String minute_String = String.valueOf(minute);

                if (hour < 12) {

                    hour_String = String.valueOf(hour - 12);

                }

                if (minute < 10) {

                    minute_String = "0" + String.valueOf(minute);

                }

                set_alarm_text("alarm set to :" + hour_String + ":" + minute_String);

                intent.putExtra("extra", "alarm on");
                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

                Intent intent_jump = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent_jump);
                finish();
            }
        });


        final Button alarm_off = (Button) findViewById(R.id.alarm_off);

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_alarm_text("Alarm off!");

                alarm_manager.cancel(pending_intent);

                intent.putExtra("extra", "alarm off");

                sendBroadcast(intent);
            }
        });
    }


    public void set_alarm_text(String output) {
        update_text.setText(output);
    }
}






