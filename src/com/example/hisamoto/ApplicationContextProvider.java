package com.example.hisamoto;

import android.app.Application;
import android.content.Context;

/**
 * @author Leandro Shindi
 * @version 1.0 30/06/15.
 */
public class ApplicationContextProvider extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }

    // Retorna o contexto da aplicação
    public static Context getContext() {

        return context;
    }
}
