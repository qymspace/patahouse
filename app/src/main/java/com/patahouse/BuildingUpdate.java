package com.patahouse;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

public class BuildingUpdate extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_building_update);
	    EditText editText=(EditText)findViewById(R.id.editText1);
	    DatabaseHelper databaseHelper=new DatabaseHelper(this);
	    SQLiteDatabase db=databaseHelper.getReadableDatabase();
	    
	    // TODO Auto-generated method stub
	}

}
