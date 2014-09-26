package com.example.hisamoto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_show);
		
		String[] listaDeNomesASeremListados = new String[] { "Android", "iPhone", "WindowsMobile",
			        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
			        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
			        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
			        "Android", "iPhone", "WindowsMobile" };
		
		ListView listView = (ListView) findViewById(R.id.lista);
		
		ArrayList<String> list = new ArrayList<String>();
		
		Context contexto = getApplicationContext();
    	UsuarioDAO usuarioDAO= new UsuarioDAO(contexto);
    	
    	list = usuarioDAO.getUsuarios();
		
    	
	    //for (int i = 0; i < listaDeNomesASeremListados.length; ++i) {
    	//  list.add(listaDeNomesASeremListados[i]);
    	//}
	    
	    
	    final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
	    
	    listView.setAdapter(adapter);
	    
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
