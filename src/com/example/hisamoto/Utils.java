package com.example.hisamoto;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by leandroshindiekamoto on 24/10/14.
 */
public class Utils {

    public static void setMensagem(String msg, Context contexto) {

        Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}
