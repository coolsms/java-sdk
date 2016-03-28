package net.nurigo.java_sdk.api;

import java.util.HashMap;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.java_sdk.exceptions.CoolsmsSDKException;

import org.json.simple.JSONObject;

/**
 * @class GroupMessage
 * @brief management group message, using Rest API
 */
public class GroupMessage extends Coolsms {	
	/**
	 * @brief constructor
	 */	
	public GroupMessage(String api_key, String api_secret) {
		super(api_key, api_secret);		
	}

	/**
	 * @brief create create group ( HTTP Method GET )
	 * @param hashmap<string, string> params {
	 *   @param string charset [optional]
	 *   @param string srk [optional]
	 *   @param string mode [optional]
	 *   @param string delay [optional]
	 *   @param boolean force_sms [optional]
	 *   @param string os_platform [optional]
	 *   @param string dev_lang [optional]
	 *   @param string sdk_version [optional]
	 *   @param string app_version [optional] }
	 * @return JSONObject
	 * @throws CoolsmsException
	 */
	public JSONObject createGroup(HashMap<String, String> params)  throws CoolsmsException {
		return request("new_group", params);		
	}

	/**
	 * @brief get group list ( HTTP Method GET )
	 * @param None
	 * @return JSONArray
	 * @throws CoolsmsException
	 */
	public JSONObject getGroupList() throws CoolsmsException {		
		// resource 'balance' does not required params so hand over empty params.
		HashMap<String, String> params = new HashMap<String, String>();		
		return request("group_list", params);		
	}

	/**
	 * @brief delete groups ( HTTP Method POST )
	 * @param string group_ids [required]
	 * @return JSONobject
	 * @throws CoolsmsException
	 */
	public JSONObject deleteGroups(String group_ids) throws CoolsmsException {
		if (!checkString(group_ids)) throw new CoolsmsSDKException("group_ids is required", 202);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("group_ids", group_ids);		

		return postRequest("delete_groups", params);		
	}

	/**
	 * @brief get group info ( HTTP Method GET )
	 * @param string group_id [required] 
	 * @return JSONObject
	 * @throws CoolsmsException 
	 */
	public JSONObject getGroupInfo(String group_id) throws CoolsmsException {		
		if (!checkString(group_id)) throw new CoolsmsSDKException("group_id is required", 202);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("group_id", group_id);
		String resource = "groups/" + group_id;        
		return request(resource, params);
	}

	/**
	 * @brief add message to group ( HTTP Method POST )
	 * @param hashmap<string, string> params {
	 *   @param string group_id [required]
	 *   @param string to [required]
	 *   @param string from [required]
	 *   @param string text [required]
	 *   @param string image_id [optional]
	 *   @param string refname [optional]
	 *   @param string country [optional]
	 *   @param string datetime [optional]
	 *   @param string subject [optional]
	 *   @param integer delay [optional] }
	 * @return JSONObject
	 * @throws CoolsmsException 
	 */
	public JSONObject addMessages(HashMap<String, String> params) throws CoolsmsException 
	{    	
		if (!checkString(params.get("group_id")) || !checkString(params.get("to")) || !checkString(params.get("from")) || !checkString(params.get("text"))) {
			throw new CoolsmsSDKException("group_id, to, text, from is required", 202);
		}

		String resource = "groups/" + params.get("group_id") + "/add_messages";        
		return postRequest(resource, params);
	}

	/**
	 * @brief get message list ( HTTP Method GET )
	 * @param hashmap<string, string> params {
	 *   @param string group_id [required]
	 *   @param integer offset [optional]
	 *   @param integer limit [optional] }
	 * @return JSONObject
	 * @throws CoolsmsException 
	 */
	public JSONObject getMessageList(HashMap<String, String> params) throws CoolsmsException
	{
		if (!checkString(params.get("group_id"))) throw new CoolsmsSDKException("group_id is required", 202);

		String resource = "groups/" + params.get("group_id") + "/message_list";        
		return request(resource, params);
	}

	/**
	 * @brief delete message from group ( HTTP Method POST )
	 * @param string group_id [required]
	 * @param string message_ids [required]
	 * @return JSONObject
	 * @throws CoolsmsException 
	 */
	public JSONObject deleteMessages(String group_id, String message_ids) throws CoolsmsException 
	{
		if (!checkString(group_id) || !checkString(message_ids)) {
			throw new CoolsmsSDKException("group_id and message_ids are required", 202);
		}

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("group_id", group_id);
		params.put("message_ids", message_ids);

		String resource = "groups/" + params.get("group_id") + "/delete_messages";        
		return postRequest(resource, params);
	}

	/**
	 * @brief send group message ( HTTP Method POST )
	 * @param string group_id [required]
	 * @return JSONObject
	 * @throws CoolsmsException 
	 */
	public JSONObject sendGroupMessage(String group_id) throws CoolsmsException 
	{
		if (!checkString(group_id)) throw new CoolsmsSDKException("group_id is required", 202);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("group_id", group_id);		

		String resource = "groups/" + params.get("group_id") + "/send";        
		return postRequest(resource, params);       
	}
}
