package com.example.gpsalarm;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.support.v7.appcompat.*;
import android.support.v7.app.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	String fileName="Filename.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (!getFileStreamPath(fileName).exists()) {
			
			FileOutputStream fos=null;
			try {
				fos = openFileOutput(fileName, MODE_PRIVATE);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			PrintWriter pw = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(fos)));

			pw.println("Line 1: This is a test of the File Writing API");
			pw.println("Line 2: This is a test of the File Writing API");
			pw.println("Line 3: This is a test of the File Writing API");

			pw.close();

			
		}
		Button boton1 = (Button)findViewById(R.id.button1);
		
		boton1.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this,BlockConfig.class);
				startActivity(intent);
			}
		});
		
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
