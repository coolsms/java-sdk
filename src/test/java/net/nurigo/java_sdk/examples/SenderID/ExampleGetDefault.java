package net.nurigo.java_sdk.examples.SenderID;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleGetDefault
 * @brief This sample code demonstrate how to get default sender number through CoolSMS Rest API PHP
 */
public class ExampleGetDefault {
	public static void main(String[] args) {
		String api_key = "CS558104628ADED";
		String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
		
		try {
			SenderID coolsms = new SenderID(api_key, api_secret);		
			
			// Optional parameters for your own needs
			HashMap<String, String> params = new HashMap<String, String>();			
			// params.put("site_user", "admin"); // site user id. '__private__' is default
			
			JSONObject obj = (JSONObject) coolsms.getDefault(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
