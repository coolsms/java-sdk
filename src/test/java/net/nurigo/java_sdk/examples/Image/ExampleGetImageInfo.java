package net.nurigo.java_sdk.examples.Image;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleGetImageInfo
 * @brief This sample code demonstrate how to check image info through CoolSMS Rest API PHP
 */
public class ExampleGetImageInfo {
	public static void main(String[] args) {
		String api_key = "CS558104628ADED";
		String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
		Image coolsms = new Image(api_key, api_secret);
		
		// image_id are mandatory
		String image_id = "";  // image id
		
		try {
			JSONObject obj = (JSONObject) coolsms.getImageInfo(image_id);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
