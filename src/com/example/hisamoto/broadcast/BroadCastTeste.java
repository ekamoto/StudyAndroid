package com.example.hisamoto.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Set;

/**
 * @author Leandro Shindi
 * @version 1.0 01/12/14.
 */
public class BroadCastTeste extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();

//        String newString = "";
//
//        if(extras == null){
//            return;
//        } else {
//
//            newString = extras.getString("STRING_I_NEED");
//        }
//
//        Log.i("BroadCast", "chamando Release o loco: " + extras);

        Log.i("BroadCast", "chamando Release o loco: " + extras);

//        Set<String> sets = extras.keySet();
//        if(sets == null && sets.size() < 1) {
//            return;
//        }
//
//        String value = sets.iterator().next();
//        if(value == null) {
//            return;
//        }
//
//        Log.i("BroadCast", "chamando Release o loco: " + value);
    }
}