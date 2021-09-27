package com.example.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

import androidx.core.app.NotificationCompat;

public class MyService extends Service implements mediaPLayer {
    Notification notification;

    MediaPlayer  mediaPLayer;
    private IBinder binder= new initClass() ;


    public boolean notPlaying(){
      return  !mediaPLayer.isPlaying();
    }

    public boolean isPlaying() {
        return mediaPLayer.isPlaying();
    }

    public static  class initClass extends Binder {
        MyService getService() {
            return new MyService();
        }


    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPLayer= MediaPlayer.create(this,R.raw.song);
    }@Override
    public IBinder onBind(Intent intent) {
        return binder;
    }@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotification();
        return START_NOT_STICKY;
    }private void createNotification() {
        notification=new NotificationCompat.Builder(this,"CHANEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("my title")
                .build();
        startForeground(1,notification);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
    @Override
    public void play() {
     mediaPLayer.start();
    }
    @Override
    public void error() {
mediaPLayer.stop();
    }
    @Override
    public void pause() {
mediaPLayer.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPLayer.stop();
        mediaPLayer.release();
    }
}