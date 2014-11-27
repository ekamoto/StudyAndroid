package com.example.hisamoto.observer;

import java.util.Observable;

/**
 * Created by leandro on 27/11/14.
 */
public class ObserverTeste extends Observable {

    public static ObserverTeste ob=null;

    public ObserverTeste() {

    }

    public static ObserverTeste getInstance() {

        if(ob == null)
            ob = new ObserverTeste();

        return ob;
    }

    public void start() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                int cont = 0;

                while(true) {
                    cont++;
                    if(cont%2 == 0) {

                        numeroPar("Leandro");
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    public void numeroPar(Object data) {

        setChanged();
        notifyObservers(data);
    }

}
