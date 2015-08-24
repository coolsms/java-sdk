import java.io.*;
import java.net.URLEncoder;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import java.util.Properties;
import java.util.Random;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/*
 * Coolsms Class
 * RestApi JAVA 
 * v1.1 
 * POST?GET REQUEST
 */
public class Coolsms extends Https {
	final String URL = "https://api.coolsms.co.kr";
	private String sms_url = URL + "/sms/1.5/";
	private String senderid_url = URL + "/senderid/1.1/";
	private String api_key;
	private String api_secret;	
	private String timestamp;
	private Https https = new Https();
	Properties properties = System.getProperties();

	/*
	 * Set api_key, api_secret
	 */
	public Coolsms(String api_key, String api_secret) {
		this.api_key = api_key;
		this.api_secret = api_secret;
	}
	
	/*
	 * Send messages
	 * @param set : HashMap<String, String>
	 */
	public JSONObject send(HashMap<String, String> params) {
		JSONObject response = new JSONObject();
		try {
			// 기본정보 입력
			params = setBasicInfo(params);
			params.put("os_platform", properties.getProperty("os_name"));
			params.put("dev_lang", "JAVA " + properties.getProperty("java.version"));
			params.put("sdk_version", "JAVA SDK 1.1");

			// Send message 
			response = https.postRequest(sms_url + "send", params);	
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
			// 기본정보 입력
			params = setBasicInfo(params);
			
			response = https.request(sms_url + "sent", params); // GET방식 전송	
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
			// 기본정보 입력
			params = setBasicInfo(params);
			
			// Cancel reserve message 
			response = https.postRequest(sms_url + "cancel", params);	

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
			params = setBasicInfo(params);

			// GET방식 전송	
			response = https.request(sms_url + "balance", params); // GET방식 전송	
		} catch (Exception e) {
			response.put("status", false);
			response.put("message", e.toString());
		}
		return response;
	}

	/*
	 * Register sender number
	 * @param set : HashMap<String, String>
	 */
	public JSONObject register(HashMap<String, String> params) {
		JSONObject response = new JSONObject();
		try {
			// 기본정보 입력
			params = setBasicInfo(params);
			
			// Register sender number request
			response = https.postRequest(senderid_url + "register", params);	
		} catch (Exception e) {
			response.put("status", false);
			response.put("message", e.toString());
		}
		return response;
	}

	/*
	 * Verify sender number
	 * @param set : HashMap<String, String>
	 */
	public JSONObject verify(HashMap<String, String> params) {
		JSONObject response = new JSONObject();
		try {
			// 기본정보 입력
			params = setBasicInfo(params);
			
			// Register verify sender number 
			response = https.postRequest(senderid_url + "verify", params);	
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
	 * Delete sender number
	 * @param set : HashMap<String, String>
	 */
	public JSONObject delete(HashMap<String, String> params) {
		JSONObject response = new JSONObject();
		try {
			// 기본정보 입력
			params = setBasicInfo(params);
			
			// Register delete sender number 
			response = https.postRequest(senderid_url + "delete", params);	
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
	 * Set default sender number
	 * @param set : HashMap<String, String>
	 */
	public JSONObject setDefault(HashMap<String, String> params) {
		JSONObject response = new JSONObject();
		try {
			// 기본정보 입력
			params = setBasicInfo(params);
			
			// Register set default sender number 
			response = https.postRequest(senderid_url + "set_default", params);	
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
	 * Get sender number list
	 * @param set : HashMap<String, String>
	 */
	public JSONObject list() {
		JSONObject response = new JSONObject();
		try {
			// 기본정보 입력
			HashMap<String, String> params = new HashMap<String, String>();
			params = setBasicInfo(params);
			
			// Register sender number request
			response = https.request(senderid_url + "list", params);	
		} catch (Exception e) {
			response.put("status", false);
			response.put("message", e.toString());
		}
		return response;
	}

	/*
	 * Get default sender number
	 * @param set : HashMap<String, String>
	 */
	public JSONObject getDefault() {
		JSONObject response = new JSONObject();
		try {
			// 기본정보 입력
			HashMap<String, String> params = new HashMap<String, String>();
			params = setBasicInfo(params);
			
			// Get default sender number 
			response = https.request(senderid_url + "get_default", params);	
		} catch (Exception e) {
			response.put("status", false);
			response.put("message", e.toString());
		}
		return response;
	}

	/*
	 * Set api_key and api_secret.
	 * @param set : HashMap<String, String>
	 */
	private HashMap<String, String> setBasicInfo(HashMap<String, String> params) {
		params.put("api_secret", this.api_secret);
		params.put("api_key", this.api_key);
		return params;
	}
}
