package com.example.gpsalarm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;







import android.support.v7.appcompat.*;
import android.support.v7.app.*;
import android.text.format.DateFormat;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	static private final int GET_TEXT_REQUEST_CODE = 1;
	String fileName="AlarmMap.txt";
	private File f;
	private AlarmMapAdapter alAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		alAdapter = new AlarmMapAdapter(this);
		ListView la = (ListView)findViewById(R.id.listView1);
		TextView newalarm = (TextView)findViewById(R.id.nuevoalarm);
		
		newalarm.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent na = new Intent(MainActivity.this,BlockConfig.class);
				startActivityForResult(na,GET_TEXT_REQUEST_CODE);
			}
			
		});
		la.setFooterDividersEnabled(true);
		la.setAdapter(alAdapter);
		
		//if (!getFileStreamPath(fileName).exists()) {
			//escribo una alarma de prueba
			FileOutputStream fos=null;
			try {
				fos = openFileOutput(fileName, MODE_PRIVATE);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			PrintWriter pw = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(fos)));

			
			pw.println("4");
			pw.println("Castro del Río");
			pw.println("-");
	
			pw.println("3.4");
			pw.println("Baza");

			pw.close();
		//}

		//leo todas las alarmas para listarlas en el listView
			FileInputStream fis=null;
			try {
				fis = openFileInput(fileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			
			
			
			
			double dist;
			String ciudad;
			String line="";
			try {
				while (null != (line = br.readLine())) {
					
					if(!line.equals("-")){
						dist=Double.valueOf(line);
						
						
					}
					else
						dist=Double.valueOf(br.readLine());
						
						
						
				
					ciudad=br.readLine();
					alAdapter.addAlarm(new AlarmMap(dist,ciudad));
			
				}
				
					
					
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	 
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        
			Log.i("AlarmMap", "Entered onActivityResult()");
			Log.i("AlarmMapresult", String.valueOf(resultCode));
			
			// TODO - Process the result only if this method received both a
			// RESULT_OK result code and a recognized request code
			if (resultCode == Activity.RESULT_OK && requestCode == GET_TEXT_REQUEST_CODE) {
			// If so, update the Textview showing the user-entered text.
				Log.i("Ha entrado","");
				alAdapter.addAlarm(new AlarmMap(Double.valueOf(data.getStringExtra("distancia")),data.getStringExtra("ciudad")));
				alAdapter.notifyDataSetChanged();
			}
			else{
				Log.i("Nada","");
			}
		
	    
	    
	    
	    
	    }
}
