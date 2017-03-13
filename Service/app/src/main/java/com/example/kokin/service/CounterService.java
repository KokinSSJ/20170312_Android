package com.example.kokin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Kokin on 2017-03-12.
 */
public class CounterService extends Service {

    private final String APP_NAME = "Custom Service";
    @Override
    public void onCreate(){
        Log.v(APP_NAME, "Service created");

    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startId ){
        Log.v(APP_NAME, "Serivce started");
        for (int i=0; i<100; i++){ //wypisuje liczby
            Log.v(APP_NAME, "number : " + i);

        }

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(APP_NAME, "onBind has been executed");
        return null;
    }
    @Override
    public  void onDestroy(){
        Log.v(APP_NAME, "service has been destroyed");
    }
}
