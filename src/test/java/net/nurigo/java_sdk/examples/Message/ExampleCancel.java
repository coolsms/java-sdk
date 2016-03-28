package net.nurigo.java_sdk.examples.Message;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleCancel
 * @brief This sample code demonstrate how to cancel reserved sms through CoolSMS Rest API PHP
 */
public class ExampleCancel {
	public static void main(String[] args) {
		String api_key = "CS558104628ADED";
		String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
		Message coolsms = new Message(api_key, api_secret);		
		
		// Either mid or gid is must be entered
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("mid", "M52CB443257C61"); // message id
		// params.put("gid", "G52CB4432576C8"); // group id
			
		try {
			JSONObject obj = (JSONObject) coolsms.cancel(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
