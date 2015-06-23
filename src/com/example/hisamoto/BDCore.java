package com.example.hisamoto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDCore extends SQLiteOpenHelper {
	
	private static final String NOME_BD = "bancoLeandro2";
	private static final int VERSAO_BD = 2;
    private static BDCore mInstance;

    private static final String TABELA_USUARIO = "create table usuario (id integer primary key autoincrement, nome text not null, email text not null)";

    public synchronized static BDCore getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new BDCore(context);
        }

        return mInstance;
    }

	public BDCore(Context ctx){
		super(ctx, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

        Log.i("HisamotoTeste", "Criou tabela");
        db.execSQL(TABELA_USUARIO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("HisamotoTeste", "Não Criou tabela!!!");
        onCreate(db);
	}

}


