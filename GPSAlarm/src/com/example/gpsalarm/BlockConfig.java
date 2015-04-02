package com.example.gpsalarm;







import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BlockConfig extends Activity {
	
	private final int dialog_id=0;
	private final int dialog_id2=1;
	private TextView currentHour;
	private TextView currentDate;
	int hour, min, year, month,day;
	private GPSAlarmService service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block_config);
		
		
		
		
		
		service = new GPSAlarmService();
		
		Log.i("IEEEEEEE", "Entered onActivityResult()");
		
		//Botón de confirmación
		Button confirm =(Button)findViewById(R.id.confirm);
		final SeekBar sb = (SeekBar)findViewById(R.id.DistBar);
		//final TextView tv =(TextView)findViewById(R.id.km);
		 
		confirm.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				Intent intent = new Intent();
					//EditText mEditText = (EditText)findViewById(R.id.editText1);
				
				   // intent.putExtra("ciudad",mEditText.getText().toString());
			    intent.putExtra("distancia",String.valueOf(sb.getProgress()));
					// TODO - Set Activity's result with result code RESULT_OK
				    //Toast.makeText(BlockConfig.this, "siuu", Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK,intent);
				
				
				service.onStart(BlockConfig.this,5000);
					// TODO - Finish the Activity
				finish();
					
			}
		});
		
		
		
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
		
			public void onProgressChanged(    SeekBar seekBar,    int progress,    boolean fromUser){
			      
			    	  final TextView tc= (TextView)findViewById(R.id.km);
			      	  tc.setText(String.valueOf(sb.getProgress())+" km");
			     
			   
			      	
			      
			      //Toast.makeText(BlockConfig.this, String.valueOf(sb.getProgress()), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			
				
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
