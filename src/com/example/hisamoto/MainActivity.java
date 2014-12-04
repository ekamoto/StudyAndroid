package com.example.hisamoto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.hisamoto.broadcast.BroadCastTeste;
import com.example.hisamoto.observer.ObserverTeste;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends Activity implements Observer{
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // startService(new Intent(getApplicationContext(), ServiceTeste.class));
        startService(new Intent("INICIAR_SERVICO"));

        Log.i("servico", "Prova que é thread");

        ObserverTeste.getInstance().start();
        ObserverTeste.getInstance().addObserver(this);

        Context contexto = getApplicationContext();

        contexto.registerReceiver(new BroadCastTeste(), new IntentFilter("HisamotoBroadCast"));
        // MainActivity
    }
    
    public void setMensagem(String titulo, String mensagem) {
    	AlertDialog.Builder caixaDialog = new AlertDialog.Builder(MainActivity.this);
		caixaDialog.setMessage(mensagem);
		caixaDialog.setTitle(titulo);
		caixaDialog.setNeutralButton("OK", null);
		caixaDialog.show();
    }
    
    private void buscarDados()  {
    	Context contexto = getApplicationContext();
    	UsuarioDAO usuarioDAO= new UsuarioDAO(contexto);
    	usuarioDAO.listarUsuarios();
    } 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	
    	if(item.getItemId()==R.action_activity_principal.botaoAdicionarContato) {
    		Log.i("agenda", "Botão adicionar");
    		abrirTelaAdicionarContato();
    	} else if(item.getItemId()==R.action_activity_principal.botaoPesquisar){ 
    		Log.i("agenda", "Botão pesquisar");
    		iniciarPesquisa();
    	} else {
    		Log.i("agenda", "Seila");
    	}
    	
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
    private void abrirTelaAdicionarContato() {
    	Intent it = new Intent();
    	it.setClass(this, FormActivity.class);
    	it.putExtra("valor_nome", "Leandro Shindi Ekamoto");
    	startActivity(it);
    }
    
    private void iniciarPesquisa() {
    	buscarDados();
    	Intent it = new Intent();
    	it.setClass(this, ShowActivity.class);
    	it.putExtra("valor_nome", "Leandro Shindi Ekamoto");
    	startActivity(it);
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i("observer", data.toString());
    }
}
