package net.nurigo.java_sdk.examples.Image;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleDeleteImage
 * @brief This sample code demonstrate how to delete images through CoolSMS Rest API PHP
 */
public class ExampleDeleteImage {
	public static void main(String[] args) {
		String api_key = "CS558104628ADED";
		String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
		Image coolsms = new Image(api_key, api_secret);
		
		// image_ids are mandatory
		String image_ids = "";  // image ids. ex)'IM34BWIDJ12','IMG2559GBB'
		
		try {
			JSONObject obj = (JSONObject) coolsms.deleteImages(image_ids);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
