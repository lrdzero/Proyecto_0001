package com.example.gpsalarm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.os.PowerManager;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.AlarmManager;

@SuppressLint("ServiceCast")
public class Alarm extends BroadcastReceiver{
		
		private static final int ALARM_REQUEST_CODE = 1;
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			
			showScreem(context);
			
		}

		public void showScreem(Context context){
			Intent nuevo = new Intent(context,ControlAlarma.class);
			nuevo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
			nuevo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
			//Toast.makeText(context, "entrooo", Toast.LENGTH_LONG).show();
			context.startActivity(nuevo);
		}
		 public void SetAlarm(Context context,int tamanio)
	     {
				AlarmManager mg=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
				  Intent intent  = new Intent(context, Alarm.class);
		          PendingIntent pIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent,  PendingIntent.FLAG_CANCEL_CURRENT);

		          mg.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + tamanio, pIntent);  
	     }
		 
		 public void CancelAlarm(Context context)
	     {
	         Intent intent = new Intent(context, Alarm.class);
	         PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
	         AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	         alarmManager.cancel(sender);
	     }
	}

	
	



