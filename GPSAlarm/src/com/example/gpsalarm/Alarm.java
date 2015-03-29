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
		Uri notification =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		Ringtone r;
		private static final int ALARM_REQUEST_CODE = 1;
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			PowerManager pm =(PowerManager) context.getSystemService(Context.POWER_SERVICE);
			PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"");
			Vibrator vibra = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			r= RingtoneManager.getRingtone(context, notification);
			wl.acquire();
			Toast.makeText(context,"alarmaaaa", Toast.LENGTH_LONG).show();
			vibra.vibrate(2000);
			r.play();
			wl.release();
			
			
		}

		
		 public void SetAlarm(Context context,int tamanio)
	     {
				AlarmManager mg=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
				  Intent intent  = new Intent(context, Alarm.class);
		          PendingIntent pIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent,  PendingIntent.FLAG_CANCEL_CURRENT);

		          mg.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2*1000, pIntent);  
	     }
		 
		 public void CancelAlarm(Context context)
	     {
	         Intent intent = new Intent(context, Alarm.class);
	         PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
	         AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	         alarmManager.cancel(sender);
	     }
	}


	
	



