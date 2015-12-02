package com.patahouse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void landingStartNewActivity(View v){
    	int id=v.getId();
    	//Intent intent=new Intent(v.getContext(), NetworkHelpTest.class);
    	
    	//intent.setAction("android.intent.action.BUILDINGREGISTRATIONACTIVITY");
    	
    	
    	switch(id){
    	case R.id.button1:
    		Intent intent=new Intent(v.getContext(),BuildingRegistration.class);
    		startActivity(intent);
    		break;
    	case R.id.button2:
    		intent=new Intent("android.intent.action.BUILDINGUPDATEACTIVITY");
    		startActivity(intent);
    		break;
    	case R.id.button3:
    		intent=new Intent(v.getContext(), RoomRegistration.class);
    		startActivity(intent);
    		break;
    	case R.id.button4:
    		intent=new Intent("android.intent.action.ROOMUPDATEACTIVITY");
    		startActivity(intent);
    		break;
    	case R.id.button5:
    		intent=new Intent("android.intent.action.REGISTEREDHOUSESACTIVITY");
    		startActivity(intent);
    		break;
    	case R.id.button6:
    		intent=new Intent("android.intent.action.AREACOVERAGEACTIVITY");
    		startActivity(intent);
    		break;
    	
    	}
    	
    	//startActivity(intent);
    }
}

