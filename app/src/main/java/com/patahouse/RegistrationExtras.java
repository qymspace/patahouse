package com.patahouse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegistrationExtras extends Activity {
	public Button button;
	private static final int MEDIA_TYPE_IMAGE =1;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 11;
	private static final int MEDIA_TYPE_VIDEO = 0;
	public  JSONObject json;
	String buildingName;
	ConnectToNetwork network;
	List<NameValuePair> dataList=new ArrayList<NameValuePair>();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_registration_extras);
	     buildingName=getIntent().getStringExtra("buildingName");
	    String buildingOwner=getIntent().getStringExtra("buildingOwner");
	    String phoneNumber=getIntent().getStringExtra("phoneNumber");
	    String idNumber=getIntent().getStringExtra("idNumber");
	    try{ 
	    	json=new JSONObject();
	    	json.put("buildingName",buildingName );
	    json.put("buildingOwner", buildingOwner);
	    json.put("phoneNumber",phoneNumber );
	    json.put("idNumber",idNumber );
	    dataList.add(new BasicNameValuePair("json",json.toString()));
	     network=new ConnectToNetwork(this,"http://192.168.43.85/patahouse/register_building.php","completeBuildingRegistration");
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    
	    
	   
		 /*  button.setText("test passed");
		   try{
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost("http://www.vipasho.com/patahouse/index.php");
	            List<? extends NameValuePair> nameValuePairs = null;
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            InputStream is = entity.getContent();
	            byte[] buffer = null;
	            buffer.toString();
				int kim = is.read(buffer);
				button.setText(buffer.toString());
	        }catch(Exception e){
	            Log.e("log_tag", "Error in http connection"+e.toString());
	        }*/
	   
	    // TODO Auto-generated method stub
	   	}
	public void captureImage(View v){
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
	   //
	    // start the image capture Intent
	    startActivityForResult(intent, MEDIA_TYPE_IMAGE);

	   
	    
	}
	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Image saved to:\n" +
                         data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }
	 
	 /** Create a file Uri for saving an image or video */
	 private static Uri getOutputMediaFileUri(int type){
	       return Uri.fromFile(getOutputMediaFile(type));
	 }

	 /** Create a File for saving an image or video */
	 private static File getOutputMediaFile(int type){
	     // To be safe, you should check that the SDCard is mounted
	     // using Environment.getExternalStorageState() before doing this.

	     File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	               Environment.DIRECTORY_PICTURES), "MyCameraApp");
	     // This location works best if you want the created images to be shared
	     // between applications and persist after your app has been uninstalled.

	     // Create the storage directory if it does not exist
	     if (! mediaStorageDir.exists()){
	         if (! mediaStorageDir.mkdirs()){
	             Log.d("MyCameraApp", "failed to create directory");
	             return null;
	         }
	     }

	     // Create a media file name
	     String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	     File mediaFile;
	     if (type == MEDIA_TYPE_IMAGE){
	         mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	         "IMG_"+ timeStamp + ".jpg");
	     } else if(type == MEDIA_TYPE_VIDEO) {
	         mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	         "VID_"+ timeStamp + ".mp4");
	     } else {
	         return null;
	     }

	     return mediaFile;
	 }
	 
	 public void completeRegistration(View v){
		 
		
		    
		    
		    
		   if(new NetworkHelper(this).isConnectedToInternet()){
		
		 network.execute(dataList); 
		  }
		   else
		   {
		    Toast.makeText(this, "Could not connect to internet", 3000).show();
		    
		    }
		
		 
	 }
	 /*public void connectToNetwork(){
		 String link="http://www.vipasho.com/patahouse/index.php";
		 try {
			URL url=new URL(link);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 HttpClient client=new DefaultHttpClient();
		 HttpGet request=new HttpGet();
		 try {
			request.setURI(new URI(link));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			BufferedReader in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }*/

}

/*class ConnectToNetwork extends AsyncTask<List<NameValuePair>, Integer, String>{
	Activity mActivity;
	private CharSequence queryResult;
	private String data;
	
	public ConnectToNetwork(Activity activity){
		mActivity=activity;
	}
	protected void onPreExecute (){
		
		Toast.makeText(mActivity.getApplicationContext(), "Registering... "  , Toast.LENGTH_LONG).show();
	}
	@Override
	protected String doInBackground(List<NameValuePair>... arg0) {
		List<NameValuePair> indexPair=arg0[0];
		
		try {
			URL url=new URL("http://www.vipasho.com/patahouse/building_registration.php");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpClient client=new DefaultHttpClient();
		//HttpGet request=new HttpGet();
		HttpPost request=new HttpPost();
		try {
			request.setURI(new URI("http://192.168.43.85/patahouse/register_building.php"));
			try {
				request.setEntity(new UrlEncodedFormEntity(indexPair));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpResponse response = null;
		try {
			response = client.execute(request);
			HttpEntity httpEntity=response.getEntity();
			data=EntityUtils.toString(httpEntity);
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try {
			//data=EntityUtils.toString(response.getEntity());
			BufferedReader in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			char[] buffer = null;
			
			String line="";
            while((line = in.readLine()) != null){
                data = data + line;
            }
        } catch(Exception e){
            data = "error2";
        }*/
			
			
			/*URL url = new URL("http://www.itbstudios.tk/test.php");
			HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
			mUrlConnection.setDoInput(true);

			InputStream is = new BufferedInputStream(mUrlConnection.getInputStream());
			data = ;*/
	/*	return null;
	}
	
	 protected void onPostExecute(String file_url) {
            Toast.makeText(mActivity, data, Toast.LENGTH_LONG).show();
            Button button=(Button)mActivity.findViewById(R.id.button2);
            button.setText(data);
            
        }
	

	
}*/