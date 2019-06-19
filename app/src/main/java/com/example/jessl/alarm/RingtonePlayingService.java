package com.example.jessl.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int start_id;
    boolean isRunning;
    NotificationManager manager;



    public IBinder onBind(Intent intent) {

        return null;
    }




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state: extra's", state);


        assert state != null;
        switch (state) {
            case "alarm on":

                start_id = 1;
                break;
            case "alarm off":
                start_id = 0;
                break;
            default:
                start_id = 0;
                break;
        }



        if (!this.isRunning && 1 == start_id) {
            Log.e("there's no music, ", "and you want start?");
            media_song = MediaPlayer.create(this, R.raw.faded);
            media_song.start();

            this.isRunning = true;
            this.start_id = 0;


            Intent intentx = new Intent(this, Alarm.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intentx,0);

            manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

            if(Build.VERSION.SDK_INT >= 26)
            {
                //当sdk版本大于26
                String id = "channel_1";
                String description = "143";
                int importance = NotificationManager.IMPORTANCE_LOW;
                NotificationChannel channel = new NotificationChannel(id, description, importance);
//                     channel.enableLights(true);
//                     channel.enableVibration(true);//
                manager.createNotificationChannel(channel);
                Notification notification = new Notification.Builder(this, id)
                        .setCategory(Notification.CATEGORY_MESSAGE)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("An alarm is going off!")
                        .setContentText("Stop")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();
                manager.notify(1, notification);
            }
            else
            {
                //当sdk版本小于26
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("An alarm is going off!")
                        .setContentText("Stop")
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();
                manager.notify(1,notification);
            }





        }
        else if (!this.isRunning && start_id==0) {
            Log.e("there's no music, ", "and you want end");

            this.isRunning = false;
            this.start_id = 0;
        }
        else if (this.isRunning && start_id == 1) {
            Log.e("there's music, ", "and you want start");
            media_song = MediaPlayer.create(this, R.raw.faded);
            media_song.start();

            this.isRunning = true;
            this.start_id = 0;
        }
        else {
            Log.e("else ", "somehow you reached this");
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.start_id = 0;
            manager.cancelAll();

        }



        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.

        Log.e("on Destroy called", "LALA");
        super.onDestroy();
        this.isRunning = false;
        Toast.makeText(this,"on Destroy called", Toast.LENGTH_SHORT).show();
    }


}