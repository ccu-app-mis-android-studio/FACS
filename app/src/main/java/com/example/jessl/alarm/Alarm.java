package com.example.jessl.alarm;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.AlarmManager;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.PendingIntent;
import android.widget.TimePicker;

import com.example.jessl.alarm.schedule.Schedule;

import java.util.ArrayList;
import java.util.Calendar;

public class Alarm extends AppCompatActivity {


    AlarmManager alarm_manager;

    PendingIntent pending_intent;
    TextView update_text;
    Context context;

    private LinearLayout parentLinearLayout;
    private Button addbtn;
    ArrayList<TextView> txt = new ArrayList<>();
    String ap="AM";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        addbtn = findViewById(R.id.addbtn);
        this.context = this;
        final Intent intent = new Intent(this.context, AlarmReceiver.class);
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        parentLinearLayout = findViewById(R.id.parentlinearLayout);



        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater  = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.test1,null);
                final Button del =rowView.findViewById(R.id.delete_button);
                final TextView time =rowView.findViewById(R.id.time);
                final TextView apm =rowView.findViewById(R.id.ap);
                final Button stop =rowView.findViewById(R.id.stop_button);


                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        pending_intent = PendingIntent.getBroadcast(Alarm.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                        alarm_manager.cancel(pending_intent);

                        intent.putExtra("extra", "alarm off");

                        sendBroadcast(intent);

                        parentLinearLayout.removeView((View) v.getParent());

                    }

                });

                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        pending_intent = PendingIntent.getBroadcast(Alarm.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                        alarm_manager.cancel(pending_intent);

                        intent.putExtra("extra", "alarm off");

                        sendBroadcast(intent);


                    }

                });

                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount()-1);
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(Alarm.this, new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                        calendar.set(Calendar.SECOND,0);
                        calendar.set(Calendar.MILLISECOND,0);
                        //  Calendar currentTime = Calendar.getInstance();
                        String stringhours ;
                        String stringminute ;
                        intent.putExtra("extra", "alarm on");
                        pending_intent = PendingIntent.getBroadcast(Alarm.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

                        if(hourOfDay>12)
                        {
                            hourOfDay=hourOfDay-12;
                            ap="PM";

                        }
                        if(hourOfDay<10)
                        {
                            stringhours  = "0" + hourOfDay;
                        }
                        else{
                            stringhours  = String.valueOf(hourOfDay);
                        }
                        if (minute < 10)
                        {
                            stringminute  = "0" + String.valueOf(minute);
                        }
                        else
                        {
                            stringminute  = String.valueOf(minute);
                        }

                        apm.setText(ap);
                        time.setText(stringhours + ":" + stringminute);
                    }
                }, hour, minute, false).show();


            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Alarm.this,HomeActivity.class);// 記得put進去，不然資料不會帶過去
        startActivity(intent);
    }

}