package com.patahouse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

public class ConnectToNetwork extends AsyncTask<List<NameValuePair>, Integer, String>{
	Activity mActivity;
	String url;
	private CharSequence queryResult;
	private String data;
	private String tag;
	private static JSONObject jsonReturnData;
	
	public ConnectToNetwork(Activity activity,String url,String tag){
		this.tag=tag;
		mActivity=activity;
		this.url=url;
		jsonReturnData=null;
	}
	protected void onPreExecute (){
		
		Toast.makeText(mActivity.getApplicationContext(), "Registering... "  , Toast.LENGTH_LONG).show();
	}
	@Override
	protected String doInBackground(List<NameValuePair>... arg0) {
		List<NameValuePair> indexPair=arg0[0];
		
		
		
		HttpClient client=new DefaultHttpClient();
		//HttpGet request=new HttpGet();
		HttpPost request=new HttpPost();
		try {
			request.setURI(new URI(url));
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
			try {
				jsonReturnData=new JSONObject(data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 
		 * CURRENTLY NOT BEING USED
		 * 
		 * try {
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
			data = ;*
			
			
			*
			*
			*
			*
			*
			*
			*CURRENTLY NOT BEING USED
			*
			*
			*
			*/
		return null;
	}
	
	 protected void onPostExecute(String file_url) {


		 try {
			 Toast.makeText(mActivity, jsonReturnData.getString("result").toString(), Toast.LENGTH_LONG).show();
		 } catch (JSONException e) {
			 e.printStackTrace();
		 }


		 //if(this.tag=="completeBuildingRegistration"){
            	//Intent intent=new Intent(mActivity.getApplicationContext(),RoomRegistration.class);
            	//intent.putExtra("returnData", value)
            	
            	//mActivity.startActivity(intent);
           // }
           // else{
            	//Intent intent=new Intent(mActivity.getApplicationContext(),MainActivity.class);
            	//mActivity.startActivity(intent);
           // }
            
        }
	

	
}
