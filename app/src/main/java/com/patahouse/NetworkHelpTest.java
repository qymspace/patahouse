package com.patahouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;

public class NetworkHelpTest extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //setContentView(R.layout.activity_network_help_test);
	    setContentView(R.layout.activity_registration_extras);
	    ConnectToNetworkTest network=new ConnectToNetworkTest(this);
	    network.execute("");
	    
	
	    // TODO Auto-generated method stub
	}

}
class ConnectToNetworkTest extends AsyncTask<String, Integer, String>{
	Activity mActivity;
	private CharSequence queryResult;
	
	public ConnectToNetworkTest(Activity activity){
		mActivity=activity;
	}
	@Override
	protected String doInBackground(String... arg0) {
		
		// TODO Auto-generated method stub
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
			/*try {
				//BufferedReader in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				//queryResult=in.readLine().toString();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			return null;
		
	
	}
	
	
	protected void onPostExecute(Long result){
		Button button=(Button)mActivity.findViewById(R.id.button2);
		button.setText("COMPLETE");
		
		//Intent intent=new Intent(mActivity, BuildingRegistration.class);
    	//intent.setAction("android.intent.action.BUILDINGREGISTRATIONACTIVITY");
    	
    	//mActivity.startActivity(intent);
	}
	
}
