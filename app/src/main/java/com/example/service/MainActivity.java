package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
ImageButton pause,next,previous;
SeekBar seekBar;
boolean bounded=false;
MyService mservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
      Intent intent=  new Intent(this,MyService.class );
     bindService(intent,connection,BIND_AUTO_CREATE);
     pause.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
          playpause();
         }
     });
    }

    private void initView() {
        pause=findViewById(R.id.pause);
        next=findViewById(R.id.next);
       previous=findViewById(R.id.previous);
       seekBar=findViewById(R.id.seekBar);
    }

    public void playpause(){
        if(mservice.isPlaying()){
           pause.setImageResource(R.drawable.pause);}
        else{  pause.setImageResource(R.drawable.play);
        mservice.pause();}
    } ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
          bounded=true;
          MyService.initClass binder=( MyService.initClass)iBinder ;
          mservice=binder.getService();
//          mservice.play();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
bounded=false;
        }
    };
}