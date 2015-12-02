package com.patahouse;



import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class GPSLocation extends Service implements LocationListener{
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	protected Context context;
	TextView txtLat;
	String lat;
	String provider;
	protected String latitude,longitude; 
	protected boolean gps_enabled,network_enabled;
	public IBinder onStart(Intent arg0){
		
		
		//the proceesing
		//txtLat = (TextView) findViewById(R.id.textview1);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		SharedPreferences test=getSharedPreferences("test", MODE_PRIVATE);
		Editor editor=test.edit();
		editor.putString("name", "bethwel");
		return null;
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onLocationChanged(Location location) {
	//txtLat = (TextView) findViewById(R.id.textview1);
	txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
	SharedPreferences GPSLocation=getSharedPreferences("GPSLocation", MODE_PRIVATE);
	Editor editor=GPSLocation.edit();
	editor.putLong("Latitude", (long) location.getLatitude());
	editor.putLong("Longitude", (long) location.getLatitude());
	editor.commit();
	}

	@Override
	public void onProviderDisabled(String provider) {
	Log.d("Latitude","disable");
	}

	@Override
	public void onProviderEnabled(String provider) {
	Log.d("Latitude","enable");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	Log.d("Latitude","status");
	}

	   
	    
	}


