package com.example.hisamoto.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author Leandro Shindi
 * @version 1.0 01/12/14.
 */
public class BroadCastTeste extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String nome = intent.getStringExtra("nome");
        Log.i("broadcasthisamoto", "[BROADCAST] Mensagem recebido por broadcast: " + nome);
    }
}