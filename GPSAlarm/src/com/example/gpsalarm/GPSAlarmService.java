package com.example.gpsalarm;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;



public class GPSAlarmService extends Service {
	
	Alarm alarm = new Alarm();
	public void onCreate()
    {
        super.onCreate();       
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) 
{
         alarm.SetAlarm(GPSAlarmService.this,0);
     return START_STICKY;
}



    public void onStart(Context context, int startId)
    {
        alarm.SetAlarm(context,startId);
    }

    @Override
    public IBinder onBind(Intent intent) 
    {
        return null;
    }
}



