package net.nurigo.java_sdk.api;

import java.util.HashMap;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.java_sdk.exceptions.CoolsmsSDKException;

import org.json.simple.JSONObject;

/**
 * @class SenderID 
 * @brief management sender id, using Rest API
 */
public class SenderID extends Coolsms {	
	/**  
	 * @brief constructor
	 * @throws CoolsmsException
	 */	
	public SenderID(String api_key, String api_secret) throws CoolsmsException {
		super(api_key, api_secret);

		// set API and version
		setApiConfig("senderid", "1.1");
	}

	/**
	 * @brief sender id registration request ( HTTP Method POST )
	 * @param hashmap<string, string> params {
	 *   @param string phone [required]
	 *   @param string site_user [optional] }
	 * @return JSONObject
	 * @throws CoolsmsException
	 */
	public JSONObject register(HashMap<String, String> params)  throws CoolsmsException {
		if (!checkString(params.get("phone"))) throw new CoolsmsSDKException("phone number is required", 202);	

		return postRequest("register", params);		
	}

	/**
	 * @brief verify sender id ( HTTP Method POST )
	 * @param string handle_key [required]
	 * @return None
	 * @throws CoolsmsException
	 */
	public JSONObject verify(String handle_key) throws CoolsmsException {		
		if (!checkString(handle_key)) throw new CoolsmsSDKException("handle_key is required", 202);	

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("handle_key", handle_key);				

		return postRequest("verify", params);
	}

	/**
	 * @brief delete sender id ( HTTP Method POST )
	 * @param string handle_key [required]
	 * @return None
	 * @throws CoolsmsException
	 */
	public JSONObject delete(String handle_key) throws CoolsmsException {
		if (!checkString(handle_key)) throw new CoolsmsSDKException("handle_key is required", 202);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("handle_key", handle_key);		

		return postRequest("delete", params);
	}

	/**
	 * @brief get sender id list ( HTTP Method GET )
	 * @param hashmap<string, string> params {
	 *   @param string site_user [optional]
	 * @return JSONObject
	 * @throws CoolsmsException 
	 */
	public JSONObject getSenderidList(HashMap<String, String> params) throws CoolsmsException {		
		return request("list", params);
	}

	/**
	 * @brief set default sender id ( HTTP Method POST )
	 * @param hashmap<string, string> params {
	 *   @param string handle_key [required]
	 *   @param string site_user [optional] }
	 * @return None
	 * @throws CoolsmsException 
	 */
	public JSONObject setDefault(HashMap<String, String> params) throws CoolsmsException 
	{    	
		if (!checkString(params.get("handle_key"))) throw new CoolsmsSDKException("handle_key is required", 202);    

		return postRequest("set_default", params);
	}

	/**
	 * @brief get default sender id ( HTTP Method GET )
	 * @param hashmap<string, string> params {
	 *   @param string site_user [optional]
	 * @return JSONObject
	 * @throws CoolsmsException 
	 */
	public JSONObject getDefault(HashMap<String, String> params) throws CoolsmsException
	{		
		return request("get_default", params);
	}
}
