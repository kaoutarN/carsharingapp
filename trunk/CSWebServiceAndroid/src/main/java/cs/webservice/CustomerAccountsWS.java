package cs.webservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import cs.model.CustomerAccount;
import cs.common.HttpClient;
import cs.define.Define;

public class CustomerAccountsWS
{
	private JSONObject jsonObjectSend;
	
	private static final String URL = Define.webServiceRootUrl + "CSAppWeb/CustomerAccountsWS";
	
	public CustomerAccountsWS()
	{
		// JSON object to hold the information, which is sent to the server
		jsonObjectSend = new JSONObject();
		
		// To construct the jsonObject header
		HttpClient.constructHeader(jsonObjectSend);
	}
	
	public CustomerAccount getCustomerAccount(Integer id)
	{ 
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("id", id.toString()));
		
		JSONObject jsonObjectReturn = HttpClient.SendHttpPost(URL, jsonObjectSend, nameValuePairs);

		CustomerAccount customerAccount = null;
        
	    try
	    {
			JSONObject jsonObjectCustomerAccount;
			
			if(!jsonObjectReturn.get("customerAccount").equals(null))
			{				
				jsonObjectCustomerAccount = jsonObjectReturn.getJSONObject("customerAccount");
	    	 
				customerAccount = new CustomerAccount(jsonObjectCustomerAccount);
			}
	    }
	    catch (JSONException e)
	    {
	    	e.printStackTrace();
	    }
	  
	    return customerAccount;
	}
	
	public CustomerAccount getCustomerAccount(String customerLogin, String customerPassword)
	{
		List<NameValuePair> paramsToPost = new ArrayList<NameValuePair>();
		paramsToPost.add(new BasicNameValuePair("customerLogin", customerLogin));
		paramsToPost.add(new BasicNameValuePair("customerPassword", customerPassword));
		
		// Contains the jsonObject return by the http request
		JSONObject jsonObjectReturn = HttpClient.SendHttpPost(URL, jsonObjectSend, paramsToPost);
		
		CustomerAccount customerAccount = null;
		
		try
	    {
			JSONObject jsonObjectCustomerAccount;
			
			if(!jsonObjectReturn.get("customerAccount").equals(null))
			{				
				jsonObjectCustomerAccount = jsonObjectReturn.getJSONObject("customerAccount");
	    	 
				customerAccount = new CustomerAccount(jsonObjectCustomerAccount);
			}
	    }
	    catch (JSONException e)
	    {
	    	e.printStackTrace();
	    }
	    
	    return customerAccount;
	}
	
	public void saveCustomerLocation(Integer idCustomerAccount, double geolocLongitude, double geolocLatitude)
	{
		List<NameValuePair> paramsToPost = new ArrayList<NameValuePair>();
		paramsToPost.add(new BasicNameValuePair("id", idCustomerAccount.toString()));
		paramsToPost.add(new BasicNameValuePair("geolocLongitude", Double.toString(geolocLongitude)));
		paramsToPost.add(new BasicNameValuePair("geolocLatitude", Double.toString(geolocLatitude)));
		
		// Send the http request
		HttpClient.SendHttpPost(URL, jsonObjectSend, paramsToPost);
	}
}