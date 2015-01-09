package com.example.hisamoto;

import java.io.File;
import java.util.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.hisamoto.observer.ObserverTeste;

public class ShowActivity extends Activity implements Observer{

    private ListView listView;
    private ArrayList<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_show);

		listView = (ListView) findViewById(R.id.lista);
		
		list = new ArrayList<String>();
		
		Context contexto = getApplicationContext();
    	UsuarioDAO usuarioDAO= new UsuarioDAO(contexto);
    	
    	list = usuarioDAO.getUsuarios();

	    final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
	    
	    listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                String item = ((TextView)arg1).getText().toString();

                Context contexto = getApplicationContext();
                Utils.setMensagem(item, contexto);

                adapter.remove(item);
            }
        });

        ObserverTeste.getInstance().start();
        ObserverTeste.getInstance().addObserver(this);

        // Pegando valor de res/values/strings.xml
        String developer = getResources().getString(R.string.developer);

        Log.i("Hisamoto", "Retornando mensagem de string: " + developer);


        if(false) {
            // Abre o browser e acessa o link
            Uri uri = Uri.parse("http://www.android.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

            // Abre acesso a camera e salva imagem
            uri = Uri.fromFile(new File("/sdcard/LivroDeAndroid/hello_camera.jpg"));
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivity(intent);

            // Abrir tela de contatos
            uri = Uri.parse("content://contacts/people/");
            intent = new Intent(Intent.ACTION_VIEW, uri);

            startActivity(intent);
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i("observer", "ShowActivity: " + data.toString());
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
	      	super(context, textViewResourceId, objects);
	      	for (int i = 0; i < objects.size(); ++i) {
	      		mIdMap.put(objects.get(i), i);
	      	}
    	}

	    @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if(item.getItemId()==android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}