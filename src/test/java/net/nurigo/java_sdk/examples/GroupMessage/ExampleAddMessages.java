package net.nurigo.java_sdk.examples.GroupMessage;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleAddMessages
 * @brief This sample code demonstrate how to add messages into group through CoolSMS Rest API PHP
 */
public class ExampleAddMessages {
	public static void main(String[] args) {
		String api_key = "CS558104628ADED";
		String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
		GroupMessage coolsms = new GroupMessage(api_key, api_secret);		
		
		HashMap<String, String> params = new HashMap<String, String>();
		
		// options(to, from, text) are mandatory. must be filled
		params.put("to", "01000000000");
		params.put("from", "01000000000");
		params.put("text", "Coolsms Testing Message!");
		params.put("group_id", "GID384DEIE1932"); // Group ID	    

	    // Optional parameters for your own needs
		// params.put("type", "SMS"); // Message type ( SMS, LMS, MMs )
		// params.put("image_id", "image_id"); // image_id. type must be set as 'MMS'
		// params.put("refname", ""); // Reference name
		// params.put("country", "82"); // Korea(82) Japan(81) America(1) China(86) Default is Korea
		// params.put("datetime", "20140106153000"); // Format must be(YYYYMMDDHHMISS) 2014 01 06 15 30 00 (2014 Jan 06th 3pm 30 00)
		// params.put("subject", "Message Title"); // set msg title for LMS and MMS
		// params.put("delay", "10"); // '0~20' delay messages

		try {
			JSONObject obj = (JSONObject) coolsms.addMessages(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
