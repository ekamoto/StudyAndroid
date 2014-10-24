package com.example.hisamoto;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioDAO {
	
	private SQLiteDatabase db;
	private Cursor cursor;
	
	public UsuarioDAO(Context context) {
		BDCore auxBD = new BDCore(context);
		db = auxBD.getWritableDatabase();
	}
	
	public SQLiteDatabase getDB() {
		return db;
	}
	
	public void inserir(Usuario usuario) {
		
		Log.i("agenda", "entrou na função inserir");
		
		ContentValues valores = new ContentValues();
		valores.put("nome", usuario.getNome());
		valores.put("email", usuario.getEmail());

		db.insert("usuario", null, valores);
	}

    public void deletar(int id) {

        ContentValues valores = new ContentValues();
        valores.put("id", id);

        db.delete("usuario", "_id=" + id, new String[] {});
    }

	public void listarUsuarios() {
		try {
    		String[] colunas = {"nome", "email"};
    		cursor = db.query(
    						false, 
    						"usuario", 
    						colunas, 
    						null, 
    						null, 
    						null, 
    						null, 
    						null, 
    						null);
    		if(cursor.getCount() != 0) {

    			cursor.moveToFirst();
    			
    			String nome = "";
    			String email = "";
    			
    			while (cursor.moveToNext()) {
    				nome = cursor.getString(cursor.getColumnIndex("nome"));
        			Log.i("agenda", nome);
        			
        			email = cursor.getString(cursor.getColumnIndex("email"));
        			Log.i("agenda", email);
    			}

    		} else {
    			Log.i("agenda", "Não existem registros");
    		}

		} catch (Exception e) {
			Log.i("agenda", "Erro ao buscar dados");
		}
	}
	
	public ArrayList<String> getUsuarios() {
		
		final ArrayList<String> list = new ArrayList<String>();
		
		try {
    		String[] colunas = {"id", "nome", "email"};
    		cursor = db.query(
    						false, 
    						"usuario", 
    						colunas, 
    						null, 
    						null, 
    						null, 
    						null, 
    						null, 
    						null);
    		if(cursor.getCount() != 0) {

    			cursor.moveToFirst();
    			
    			String nome = "";
    			String email = "";
    			
    			while (cursor.moveToNext()) {
    				
    				nome = cursor.getString(cursor.getColumnIndex("nome"));
    				Log.i("agenda", nome);
    				
    				list.add(nome);
    				
    				
        			
        			
        			email = cursor.getString(cursor.getColumnIndex("email"));
        			Log.i("agenda", email);
    			}

    		} else {
    			Log.i("agenda", "Não existem registros");
    		}

		} catch (Exception e) {
			Log.i("agenda", "Erro ao buscar dados");
		}
		return list;
	}
}
