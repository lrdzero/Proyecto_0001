package com.example.gpsalarm;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ControlAlarma extends Activity {
	Uri notification =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
	Ringtone r;
	Vibrator vibra;
	
	PowerManager pm; 
	PowerManager.WakeLock wl; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control_alarma);
		
		Button stop = (Button) findViewById(R.id.button1);
		
		long []pattern ={0,500,500};
		
		
		pm=(PowerManager) this.getSystemService(Context.POWER_SERVICE);
		wl= pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"");
		r= RingtoneManager.getRingtone(this, notification);
		vibra= (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		wl.acquire();
		
		vibra.vibrate(pattern, 0);
		r.play();
		wl.release();
		
		stop.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				wl.acquire();
					vibra.cancel();
					r.stop();
				wl.release();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.control_alarma, menu);
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
