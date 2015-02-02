package com.example.hisamoto.commandline;

import android.content.Context;
import android.util.Log;
import com.example.hisamoto.commandline.enums.EnumCommandLine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Robson Oliveira
 * @version 1.0 27/11/14
 */
public class Root {

    private final String TAG = "Root";
    private Context context;
    private static Root root;

    public static Root getInstance(){
        if(root == null){
            root = new Root();
        }

        return root;
    }

    public boolean run(String command) {
        try {
            Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", command});
            proc.waitFor();

            return true;
        } catch (Exception e) {
            Log.i(TAG, "Problemas ao executar comando como administrador: " + e.getMessage());
            return false;
        }
    }

    public List<String> runOut(String command){
        try {
            Process proc = Runtime.getRuntime().exec(new String[] {"su", "-c", command} );
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;
            List<String> list = new ArrayList<String>();
            while ((line = in.readLine()) != null){
                list.add(line);
            }

            in.close();
            return list;
        } catch (Exception e) {
            Log.i(TAG, "Problemas ao executar comando como administrador: " + e.getMessage());
        }

        return null;
    }
}
