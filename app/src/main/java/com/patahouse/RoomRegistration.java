package com.patahouse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


public class RoomRegistration extends Activity {
    public JSONObject json;
    String buildingName;
    ConnectToNetwork network;
    List<NameValuePair> dataList = new ArrayList<NameValuePair>();
    public EditText editText;
    public static List<String> rooms;

    public static void addRoom(String s) {
        rooms.add(s);
    }

    //String[] array;
    JSONArray roomsJsonArray;
    Button registerAllButton;
    public static Context mContext;
    GridView gridView;
    ArrayAdapter<String> adapter;

    static final String[] numbers = new String[]{
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_registration);
        mContext = getApplicationContext();
        rooms = new ArrayList<String>();

        registerAllButton = (Button) findViewById(R.id.registerAllButton);
        registerAllButton.setText("register");
        editText = (EditText) findViewById(R.id.queryEditText2);

        EditText roomQueryText = (EditText) findViewById(R.id.queryEditText1);
        RoomRegistration roomRegistration = new RoomRegistration();
        RoomRegistration.EditTextWatcher watcher = roomRegistration.new EditTextWatcher();

        roomQueryText.addTextChangedListener(watcher);
        gridView = (GridView) findViewById(R.id.roomGridview);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, rooms);
        gridView.setAdapter(adapter);


        // TODO Auto-generated method stub
    }

    public void addNewQuery(View v) {

    }

    public void registerRooms(View v) {
        buildingName = getIntent().getStringExtra("buildingName");
        String buildingId = getIntent().getStringExtra("buildingId");
        /**
         * *
         *
         *
         *
         * converting list to array(why??)
         *
         *
         * try{
         json=new JSONObject();
         int roomSize=rooms.size();
         String[] roomsArray=new String[roomSize];



         for(int i=0;i<rooms.size();i++){
         roomsArray[i]=rooms.get(i);
         }


         json.put("roomsArray", roomsArray);*/

        try {
            json = new JSONObject();
            JSONArray roomsJsonArray = new JSONArray(rooms);
            json.accumulate("buildingId", buildingId);
            json.put("buildingName", buildingName);
            json.put("roomsList", roomsJsonArray);


            dataList.add(new BasicNameValuePair("json", json.toString()));
            network = new ConnectToNetwork(this, "http://192.168.35.1/patahouse/register_room.php", "registerAllRooms");
            Button button = (Button) findViewById(R.id.registerAllButton);

            //roomsJsonArray=json.getJSONArray("roomsArray");


            if (new NetworkHelper(this).isConnectedToInternet()) {

                network.execute(dataList);
            } else {
                Toast.makeText(this, "Could not connect to internet", 3000).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    class EditTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub


            String s = arg0.toString();
            if (s.contains(";")) {

                //Toast.makeText(mContext, rooms.toString(), 1000).show();

                s = s.substring(0, s.length() - 1);
                String[] borders = s.split("-");

                if (borders[0].matches("[0-9]+")) {
                    for (int i = Integer.parseInt(borders[0]); i <= Integer.parseInt(borders[1]); i++) {
                        rooms.add(Integer.toString(i));

                    }


                }

					/*Button button=(Button)findViewById(R.id.button1);
					
					
					String[] borders=s.split("-");
					
					//**
					//
					//check if string is alphabet
					if(borders[0].matches("[a-zA-Z]+")){
						int x;
					}
					//check if string is alphanumeric
					else if(borders[0].matches("^[a-zA-Z0-9]*$")){
						int y;
					} else {
						//borders[0].matches("^[0-9]+$");
						//for(int i=Integer.parseInt(borders[0]);i<Integer.parseInt(borders[1]);i++){
						//rooms.add(Integer.toString(i));
						//editText.setVisibility(View.VISIBLE);
						button.setText(s);
						}******/

            }

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1,
                                      int arg2, int arg3) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
            // TODO Auto-generated method stub

        }
    }
}



			
			
		


