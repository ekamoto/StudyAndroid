package com.example.hisamoto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
	
	private static final String NOME_BD = "bancoLeandro2";
	private static final int VERSAO_BD = 2;
	
	public BDCore(Context ctx){
		super(ctx, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table usuario (id integer primary key autoincrement, nome text not null, email text not null);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}
	
}


