package net.nurigo.java_sdk.examples.Image;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleGetImageList
 * @brief This sample code demonstrate how to check image list through CoolSMS Rest API PHP
 */
public class ExampleGetImageList {
	public static void main(String[] args) {
		String api_key = "CS558104628ADED";
		String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
		Image coolsms = new Image(api_key, api_secret);		
		
		// Optional parameters for your own needs
		HashMap<String, String> params = new HashMap<String, String>();
		// params.put("offset", "0"); // default 0
		// params.put("limit", "20"); // default 20
			
		try {
			JSONObject obj = (JSONObject) coolsms.getImageList(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
