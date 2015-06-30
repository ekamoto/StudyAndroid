package com.example.hisamoto;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.hisamoto.observer.ObserverTeste;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends Activity implements Observer, OnClickListener{

    private String SAMPLE_DB_NAME = "bancoLeandro2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Porcaria de Service feito errado
        // startService(new Intent(getApplicationContext(), ServiceTeste.class));
        // startService(new Intent("INICIAR_SERVICO"));

        Intent intent = new Intent("br.com.hisamoto.service.INICIAR_SERVICO_EXEMPLO");
        startService(intent);

        Log.i("service", "Prova que é thread");

        ObserverTeste.getInstance().start();
        ObserverTeste.getInstance().addObserver(this);

        // Context contexto = getApplicationContext();
        // Tanto faz utilizar getApplicationContext ou Usar uma classe para isso
        // Mas existe casos em que o contexto não vai ser acessível, por isso a classe
        ApplicationContextProvider.getContext().registerReceiver(broadcastTeste, new IntentFilter("HisamotoBroadCast"));

        findViewById(R.id.button1).setOnClickListener((android.view.View.OnClickListener)this);
        findViewById(R.id.button2).setOnClickListener((android.view.View.OnClickListener)this);
        findViewById(R.id.button3).setOnClickListener((android.view.View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button1:
                deleteDB();
                break;
            case R.id.button2:
                exportDB();
                break;
            case R.id.button3:
                createDB();
                break;
        }
    }

    private void deleteDB(){
        boolean result = this.deleteDatabase(SAMPLE_DB_NAME);
        if (result==true) {
            Toast.makeText(this, "DB Deleted!", Toast.LENGTH_LONG).show();
        }
    }

    private void createDB() {

    }

    private void exportDB(){
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "com.example.hisamoto" +"/databases/"+SAMPLE_DB_NAME;
        String backupDBPath = SAMPLE_DB_NAME + ".sqlite";
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(this, "DB Exported!", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private BroadcastReceiver broadcastTeste = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String nome = intent.getStringExtra("nome");
            String gmail = intent.getStringExtra("gmail");

            Log.i("broadcasthisamoto", "[BROADCAST] Mensagem recebido por broadcast: " + nome + " email:" + gmail);
            Toast.makeText(getApplicationContext(), "[BROADCAST] Mensagem recebido por broadcast: " + nome + " email:" + gmail, Toast.LENGTH_SHORT).show();
        }
    };

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
    	
    	if(item.getItemId()==R.id.botaoAdicionarContato) {

    		Log.i("agenda", "Botão adicionar");
    		abrirTelaAdicionarContato();

            //abrirTelaMonito();
    	} else if(item.getItemId()==R.id.botaoPesquisar){

    		Log.i("agenda", "Botão pesquisar");
    		iniciarPesquisa();
    	} else if(item.getItemId()==R.id.action_settings){

    		Log.i("agenda", "Clicou em opções");
            Toast.makeText(getApplicationContext(),"Clicou em opções",Toast.LENGTH_SHORT).show();
    	}

		return super.onOptionsItemSelected(item);
	}

    private void abrirTelaMonito() {
        Intent it = new Intent();
        it.setClass(this, ShakeActivity.class);
        it.putExtra("valor_nome", "Leandro Shindi Ekamoto");
        startActivity(it);
    }

    private void abrirTelaAdicionarContato() {
    	Intent it = new Intent();
    	it.setClass(ApplicationContextProvider.getContext(), FormActivity.class);
    	it.putExtra("valor_nome", "Leandro Shindi Ekamoto");
    	startActivity(it);
    }
    
    private void iniciarPesquisa() {
    	buscarDados();
    	Intent it = new Intent();
    	it.setClass(ApplicationContextProvider.getContext(), ShowActivity.class);
    	it.putExtra("valor_nome", "Leandro Shindi Ekamoto");
    	startActivity(it);
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i("observer", data.toString());
    }
}
