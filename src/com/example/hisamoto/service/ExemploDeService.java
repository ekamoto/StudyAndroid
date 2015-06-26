package com.example.hisamoto.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Leandro Shindi
 * @version 1.0 26/06/15.
 */
public class ExemploDeService extends Service {

    private Thread threadService;
    private boolean stopThread = false;
    private static int cont = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        cont++;
        // Tarefas a serem executadas quando o service é criado
        Log.i("service", "Criando Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(threadService != null && threadService.isAlive()) {

            stopThread = true;
            Log.i("service", "Forçando parada Service:"+cont);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        threadService = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 100 && !stopThread; i++) {

                    try {

                        Log.i("service", "incremento: " + i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                        Log.i("service", "erro " + e);
                    }
                }
                stopThread = false;
                stopSelf();
            }
        }, "Thread conexao");

        threadService.start();

        // Tarefas a serem executadas quando o service é iniciado
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("service", "Serviço destruido: ");
        // Tarefas a serem executadas quando o service é destruido

    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.i("service", "onBind");
        return null;
    }


}
