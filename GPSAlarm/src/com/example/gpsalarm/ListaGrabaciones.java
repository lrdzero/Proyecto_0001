package com.example.gpsalarm;



import java.io.File;


import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.TextView;

/*
 * public class ListaGrabaciones extends ListActivity {
 
	
	TextView footerView;
	audioListAdapter alAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		alAdapter = new audioListAdapter(this);
		getListView().setFooterDividersEnabled(true);
		//footerView =(TextView) getLayoutInflater().inflate(R.layout.activity_lista_grabaciones,null);
		getListView().setAdapter(alAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.lista_grabaciones, menu);
		return true;
	}
	
	public void onResume() {
		super.onResume();

		// Load saved ToDoItems, if necessary

		if (alAdapter.getCount() == 0){
			loadItems();
			
		}
		
	}
	
	public void loadItems(){
		 File sdCard = Environment.getExternalStorageDirectory();
		 File f= new File( sdCard.getAbsolutePath()+"/MisGrabaciones");
		 for(String i:f.list()){
			// alAdapter.addAudio(new audioItem(i));
		 }
		 
		
		
		
		
	
	}

}*/
