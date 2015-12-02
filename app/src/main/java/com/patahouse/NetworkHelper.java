/**
 * 
 */
package com.patahouse;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



/**
 * @author reuben
 *
 */
public class NetworkHelper {

	/**
	 * 
	 */
	public static Context context;
	public NetworkHelper(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	 public boolean isConnectedToInternet(){
	    ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	      if (connectivity != null) 
	      {
	          NetworkInfo[] info = connectivity.getAllNetworkInfo();
	          if (info != null) 
	              for (int i = 0; i < info.length; i++) 
	                  if (info[i].getState() == NetworkInfo.State.CONNECTED)
	                  {
	                      return true;
	                  }

	      }
	      return false;
	}
	

}
