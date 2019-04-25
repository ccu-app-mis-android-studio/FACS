package com.example.alramclock;


import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int start_id;
    boolean isRunning;

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



        if (!this.isRunning && start_id == 1) {
            Log.e("there's no music, ", "and you want start");
            media_song = MediaPlayer.create(this, R.raw.faded);
            media_song.start();

            this.isRunning = true;
            this.start_id = 0;

            NotificationManager notify_manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);

            PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this, 0, intent_main_activity,0);

            Notification notification_popup = new Notification.Builder(this)
                    .setContentTitle("An alarm is going off!")
                    .setContentText("Stop")
                    .setContentIntent(pending_intent_main_activity)
                    .setAutoCancel(true)
                    .build();

            notify_manager.notify(0,notification_popup);


        }
        else if (!this.isRunning && start_id == 0) {
            Log.e("there's music, ", "and you want end");
            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.start_id = 0;


        }
        else if (!this.isRunning && start_id == 0) {
            Log.e("there's no music, ", "and you want end");

            this.isRunning = false;
            this.start_id = 0;
        }
        else if (!this.isRunning && start_id == 1) {
            Log.e("there's music, ", "and you want start");

            this.isRunning = true;
            this.start_id = 1;
        }
        else {
            Log.e("else ", "somehow you reached this");
        }

        media_song = MediaPlayer.create(this, R.raw.faded);
        media_song.start();

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
