package com.example.hisamoto.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by leandro on 27/11/14.
 */
public class ServiceTeste extends Service {

    public static final String CATEGORIA = "service";

    @Override
    public void onStart(Intent intent, int startId) {

        incremento(0);
        stopSelf();
    }

    private void incremento(int result) {

        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(1000);
                result++;
                Log.i(CATEGORIA, "incremento  " + result);
            } catch (InterruptedException e) {

                Log.i(CATEGORIA, "erro " + e);
            }
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
