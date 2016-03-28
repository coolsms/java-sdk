package net.nurigo.java_sdk.examples.GroupMessage;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleAddMessages
 * @brief This sample code demonstrate how to delete messages through CoolSMS Rest API PHP
 */
public class ExampleDeleteMessages {
	public static void main(String[] args) {
		String api_key = "CS558104628ADED";
		String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
		GroupMessage coolsms = new GroupMessage(api_key, api_secret);		
		
		// group_id, message_ids are mandatory.
		String group_id = "GID384DEIE1932"; // Group ID
		String message_ids = "MID392EODK234, MID23921982"; // Message Ids
		
		
		try {
			JSONObject obj = (JSONObject) coolsms.deleteMessages(group_id, message_ids);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}