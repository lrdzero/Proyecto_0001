package com.example.gpsalarm;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BlockConfig extends Activity {
	
	private final int dialog_id=0;
	private final int dialog_id2=1;
	private TextView currentHour;
	private TextView currentDate;
	int hour, min, year, month,day;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block_config);
		
		
		
		
		ImageButton timeButton =(ImageButton)findViewById(R.id.imageButton1);
		
		timeButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				
				showDialog(dialog_id);
				
				
			}
			
		});
		
		ImageButton dateButton =(ImageButton) findViewById(R.id.imageButton2);
		
			dateButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				
				showDialog(dialog_id2);
				
				
			}
			
		});
	}

   protected Dialog onCreateDialog(int id){
	   
	   switch(id){
	   		case dialog_id:
	   			return new TimePickerDialog(this,mTimeSetListener,hour,min,false);
	   			
	   		case dialog_id2:
	   			return new DatePickerDialog(this,pickerListener,year,month,day);
	   			
	   }
	   return null;
	  
   }
   
   private DatePickerDialog.OnDateSetListener pickerListener = 
		   new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int Year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				year=Year;
				month=monthOfYear;
				day=dayOfMonth;
				
				 currentDate.setText(new StringBuilder().append(month + 1)
		                    .append("-").append(day).append("-").append(year)
		                    .append(" "));
		     
		           
				
			}
		};
   
   private TimePickerDialog.OnTimeSetListener mTimeSetListener =
		   new TimePickerDialog.OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				hour=hourOfDay;
				min=minute;
				currentHour.setText(new StringBuilder().append(padding_str(hour)).append(":").append(padding_str(min)));
				
				
				
			}
		};
	private static String padding_str(int c){
		if(c >=10){
			return String.valueOf(c);
		}
		else{
			return String.valueOf(c);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.block_config, menu);
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
