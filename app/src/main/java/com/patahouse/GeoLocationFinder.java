package com.patahouse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;

import android.util.Log;
import com.patahouse.*;
public class GeoLocationFinder extends Activity implements LocationListener{
protected LocationManager locationManager;
protected LocationListener locationListener;
protected Context context;
TextView txtLat;
String lat;
String provider;
protected String latitude,longitude; 
protected boolean gps_enabled,network_enabled;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
txtLat = (TextView) findViewById(0);
txtLat=new TextView(context);

locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
}
@Override
public void onLocationChanged(Location location) {
txtLat = (TextView) findViewById(0);
txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
