package com.example.gpsalarm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;





import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AlarmMapAdapter extends BaseAdapter {
	private final List<AlarmMap> mItems = new ArrayList<AlarmMap>();
	private Context context;

	public AlarmMapAdapter(Context c){
		context= c;
	
	}
	
	public void addAlarm(AlarmMap al){
		mItems.add(al);
	}
	
	
	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItems.size();
	}

	@Override
	public Object getItem(int arg) {
		// TODO Auto-generated method stub
		return mItems.get(arg);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub
		    
		   
		   
		  
			
		   

			// TODO - Inflate the View for this ToDoItem
			// from todo_item.xml
		    LayoutInflater inflater = null;
			if(convertView == null){
			    // rest of the code
				inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    convertView = inflater.inflate(R.layout.activity_lista_alarmas, null);
			    //convertView = inflater.inflate(R.layout.options, null);
			}
			
			final TextView hora= (TextView)convertView.findViewById(R.id.hora);
			final TextView ciudad= (TextView)convertView.findViewById(R.id.ciudades);
			final TextView distancia= (TextView)convertView.findViewById(R.id.distancia);
			
			hora.setText(mItems.get(arg).getDate());
			distancia.setText(String.valueOf(mItems.get(arg).getDistancia()));
			ciudad.setText(mItems.get(arg).getCiudad());
			
	 
			// Return the View you just created
			return convertView;
			
		
	}
	
	

	 

}
