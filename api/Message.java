package net.nurigo.java_sdk.api;

import java.util.HashMap;

import net.nurigo.java_sdk.Coolsms;

import org.json.simple.JSONObject;

public class Message extends Coolsms {	
	public Message(String api_key, String api_secret) {
		super(api_key, api_secret);		
	}

	/*
	 * Send messages
	 * @param set : HashMap<String, String>
	 */
	public JSONObject send(HashMap<String, String> params) {
		JSONObject response = new JSONObject();
		try {
			// send messages 
			response = postRequest("send", params);	
		} catch (Exception e) {
			response.put("status", false);
			response.put("message", e.toString());
		}
		return response;
	}
	
	/*
	 * Sent messages
	 * @param set : HashMap<String, String>
	 */
	public JSONObject sent(HashMap<String, String> params) {
		JSONObject response = new JSONObject();
		try {
			// sent messages
			response = request("sent", params); // GET방식 전송	
		} catch (Exception e) {
			response.put("status", false);
			response.put("message", e.toString());
		}
		return response;
	}

	/*
	 * Reserve message cancel 
	 * @param set : HashMap<String, String>
	 */
	public JSONObject cancel(HashMap<String, String> params) {
		JSONObject response = new JSONObject();
		try {
			// cancel reserve message 
			response = postRequest("cancel", params);	

			// Cancel은 response 가 empty 면 성공
			if (response.get("message") == "response is empty") {
				response.put("status", true);
				response.put("message", null);
			}
		} catch (Exception e) {
			response.put("status", false);
			response.put("message", e.toString());
		}
		return response;
	}

	/*
	 * Balance info
	 */
	public JSONObject balance() {
		JSONObject response = new JSONObject();
		try {
			// 기본정보 입력
			HashMap<String, String> params = new HashMap<String, String>();
			
			// GET방식 전송	
			response = request("balance", params); // GET방식 전송	
		} catch (Exception e) {
			response.put("status", false);
			response.put("message", e.toString());
		}
		return response;
	}
}
