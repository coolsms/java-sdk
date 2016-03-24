package net.nurigo.java_sdk.api;

import java.util.HashMap;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import org.json.simple.JSONObject;

public class Message extends Coolsms {	
	/**
	 * @brief constructor
	 */	
	public Message(String api_key, String api_secret) {
		super(api_key, api_secret);		
	}

	/**
	 * @brief send messages
	 * @param hashmap<string, string> set
	 * @return JSONobject
	 * @throws CoolsmsException
	 */
	public JSONObject send(HashMap<String, String> params)  throws CoolsmsException {
		return postRequest("send", params);		
	}

	/**
	 * @brief sent messages
	 * @param hashmap<string, string> set
	 * @return JSONobject
	 * @throws CoolsmsException
	 */
	public JSONObject sent(HashMap<String, String> params) throws CoolsmsException {		
		return request("sent", params);		
	}

	/**
	 * @brief reserve message cancel 
	 * @param hashmap<string, string> set
	 * @return JSONobject
	 * @throws CoolsmsException
	 */
	public JSONObject cancel(HashMap<String, String> params) throws CoolsmsException {
		return postRequest("cancel", params);		
	}

	/**
	 * @brief Balance info
	 * @param hashmap<string, string> set
	 * @return JSONobject
	 * @throws CoolsmsException 
	 */
	public JSONObject balance() throws CoolsmsException {		
		// resource 'balance' does not required params so hand over empty params.
		HashMap<String, String> params = new HashMap<String, String>();
		return request("balance", params);
	}
}
