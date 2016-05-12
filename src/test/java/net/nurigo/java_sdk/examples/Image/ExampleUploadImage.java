package net.nurigo.java_sdk.examples.Image;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleUploadImage
 * @brief This sample code demonstrate how to upload image through CoolSMS Rest API PHP
 */
public class ExampleUploadImage {
	public static void main(String[] args) {
		String api_key = "CS558104628ADED";
		String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
		Image coolsms = new Image(api_key, api_secret);		
		
		// Optional parameters for your own needs
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("image", "images/test.jpg"); // image
		// params.put("encoding", "binary"); // image encoding type (base64, binary) default binary
		// params.put("limit", "20"); // default 20
		
		try {
			JSONObject obj = (JSONObject) coolsms.uploadImage(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
