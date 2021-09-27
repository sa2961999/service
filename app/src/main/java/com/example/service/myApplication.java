package com.example.service;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class myApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        createChannel();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("CHANEL_ID","MY CHANNEL",NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            if (manager!=null)
            { manager.createNotificationChannel(channel);}
        }
    }
}
