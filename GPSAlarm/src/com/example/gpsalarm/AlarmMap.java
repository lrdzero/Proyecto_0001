package com.example.gpsalarm;

import java.io.IOException;
import java.util.Date;

import android.media.MediaPlayer;
import android.os.Environment;
import android.text.Editable;
import android.util.Log;

public class AlarmMap{
	
	private double distancia;
	private String ciudad;
	
	
	AlarmMap(double dist, String city){
		
		distancia=dist;
		ciudad=city;
	}
	
	
	
	public double getDistancia(){		
		return distancia;
	}
	
	public String getCiudad(){	
		return ciudad;
	}

	


}
