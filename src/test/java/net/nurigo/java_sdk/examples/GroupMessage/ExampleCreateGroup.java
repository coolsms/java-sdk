package net.nurigo.java_sdk.examples.GroupMessage;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleCreateGroup
 * @brief This sample code demonstrate how to create sms group through CoolSMS Rest API PHP
 */
public class ExampleCreateGroup {
	public static void main(String[] args) {
		String api_key = "CS558104628ADED";
		String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
		GroupMessage coolsms = new GroupMessage(api_key, api_secret);		
		
		// Optional parameters for your own needs
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("charset", "utf8"); // utf8, euckr default value is utf8
		// params.put("srk", "293DIWNEK103"); // Solution key
		// params.put("mode", "test"); // If 'test' value, refund cash to point
		// params.put("delay", '10'); // '0~20' delay messages
		// params.put("force_sms", true); // true is always send sms ( default true )
		// params.put("app_version", ""); 	// App version

		try {
			JSONObject obj = (JSONObject) coolsms.createGroup(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
