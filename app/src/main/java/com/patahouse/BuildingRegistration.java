package com.patahouse;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi") public class BuildingRegistration extends Activity {
	public int locationId;
	SQLiteDatabase readableDatabase;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_registration);
	    DatabaseHelper databaseHelper = new DatabaseHelper(this);
	    readableDatabase=databaseHelper.getReadableDatabase();
	    
	    
	    Thread thread = new Thread() {
	         @Override
	         public void run() {
	             try {
	                 synchronized (this) {
	                     wait(3000);
	                 }
	             } catch (InterruptedException ex) {
	         }

	         // TODO
	         //THESE TWO LINES ARE THE ONES I WANT TO RUN AFTER 3 SECONDS
	             
	             
	         }
	         };

	         thread.start();
	    
	    // TODO Auto-generated method stub
	         View c = findViewById(R.id.progressBar1);
	         //c.setVisibility(View.VISIBLE);
            //c.setVisibility(View.INVISIBLE);
             TextView locationText=new TextView(this);
             locationText.setText("loading");
	     	    locationId=View.generateViewId();
	     	    locationText.setId(locationId);
	     	    ViewGroup parent = (ViewGroup) c.getParent();
	     	    int index = parent.indexOfChild(c);
	     	   // parent.removeView(c);
	     	   //sc = getLayoutInflater().inflate( locationId, parent, false);
	     	   // parent.addView(c, index);
	}
	
	public void manualLocation(View v){
		ManualLocationDialog dialog=new ManualLocationDialog();
		android.app.FragmentManager manager=getFragmentManager();
		dialog.show(manager, null);
	}
	
	public void registerBuilding(View v){
		//collect details
		EditText buildingNameEditText=(EditText)findViewById(R.id.buildingName);
		String buildingName = buildingNameEditText.getText().toString();
		
		EditText buildingOwnerEditText=(EditText)findViewById(R.id.fullName);
		String buildingOwner=buildingOwnerEditText.getText().toString();
		
		EditText phoneNumberEditText=(EditText)findViewById(R.id.phoneNumber);
		String phoneNumber=phoneNumberEditText.getText().toString();
		
		EditText idNumberEditText=(EditText)findViewById(R.id.IDNumber);
		String idNumber=idNumberEditText.getText().toString();
		
		//open a new aactivity
		
		Intent intent=new Intent(v.getContext(), RegistrationExtras.class);
		intent.putExtra("buildingName",buildingName );
		intent.putExtra("buildingOwner", buildingOwner);
		intent.putExtra("phoneNumber", phoneNumber);
		intent.putExtra("idNumber", idNumber);
		startActivity(intent);
	}

}
class ManualLocationDialog extends DialogFragment{
	String[] mode={"GPS Location","Google Maps"};
	public Dialog onCreateDialog(Bundle SavedInstanceState){
		
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 //sating custom layout for a dialog
		 	//builder.setView(getActivity().getLayoutInflater().inflate(R.layout.location_dialog, null));
		 
	        builder.setPositiveButton("Fire", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // FIRE ZE MISSILES!
	                   }
	               })
	               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // User cancelled the dialog
	                   }
	               }).setTitle("Select Location Mode").setItems(mode, new DialogInterface.OnClickListener() {
	            	   //process events 
	               
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	

	}
	

