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
import java.util.Date;





import android.support.v7.appcompat.*;
import android.support.v7.app.*;
import android.text.format.DateFormat;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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
	
	String fileName="AlarmMap.txt";
	private File f;
	private AlarmMapAdapter alAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		alAdapter = new AlarmMapAdapter(this);
		ListView la = (ListView)findViewById(R.id.listView1);
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

			pw.println("6:00");
			pw.println("4");
			pw.println("Castro del Río");
			pw.println("-");
			pw.println("18:00");
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

			
			
			
			String d;
			double dist;
			String ciudad;
			String line="";
			try {
				while (null != (line = br.readLine())) {
					
					if(!line.equals("-")){
						d=line;
						
						
					}
					else
						d=br.readLine();
						
						
						
					dist=Double.valueOf(br.readLine());
					ciudad=br.readLine();
					alAdapter.addAlarm(new AlarmMap(d,dist,ciudad));
			
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
}
