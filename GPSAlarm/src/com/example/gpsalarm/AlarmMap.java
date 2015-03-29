package com.example.gpsalarm;

import java.io.IOException;
import java.util.Date;

import android.media.MediaPlayer;
import android.os.Environment;
import android.text.Editable;
import android.util.Log;

public class AlarmMap{
	private String date;
	private double distancia;
	private String ciudad;
	
	
	AlarmMap(String d, double dist, String city){
		date=d;
		distancia=dist;
		ciudad=city;
	}
	
	public String getDate(){		
		return date;
	}
	
	public double getDistancia(){		
		return distancia;
	}
	
	public String getCiudad(){	
		return ciudad;
	}

	


}
