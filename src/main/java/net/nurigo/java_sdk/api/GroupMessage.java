package net.nurigo.java_sdk.api;

import java.util.HashMap;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.java_sdk.exceptions.CoolsmsSDKException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @class GroupMessage
 * @brief management group message, using Rest API
 */
public class GroupMessage extends Coolsms {
  /**
   * @brief constructor
   */
  public GroupMessage(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }

  /**
   * @brief create create group ( HTTP Method GET )
   * @param hashmap <string, string> params {
   * @param string charset [optional]
   * @param string srk [optional]
   * @param string mode [optional]
   * @param string delay [optional]
   * @param boolean force_sms [optional]
   * @param string os_platform [optional]
   * @param string dev_lang [optional]
   * @param string sdk_version [optional]
   * @param string app_version [optional] }
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject createGroup(HashMap<String, String> params) throws CoolsmsException {
    return sendGetRequest("new_group", params);
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
    return sendGetRequest("group_list", params);
  }

  /**
   * @brief delete groups ( HTTP Method POST )
   * @param string groupIDs [required]
   * @return JSONobject
   * @throws CoolsmsException
   */
  public JSONObject deleteGroups(String groupIDs) throws CoolsmsException {
    if (!checkString(groupIDs))
      throw new CoolsmsSDKException("group_ids is required", 202);

    HashMap<String, String> params = new HashMap<String, String>();
    params.put("group_ids", groupIDs);

    return sendPostRequest("delete_groups", params);
  }

  /**
   * @brief get group info ( HTTP Method GET )
   * @param string groupID [required]
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject getGroupInfo(String groupID) throws CoolsmsException {
    if (!checkString(groupID))
      throw new CoolsmsSDKException("group_id is required", 202);

    HashMap<String, String> params = new HashMap<String, String>();
    params.put("group_id", groupID);
    String resource = "groups/" + groupID;
    return sendGetRequest(resource, params);
  }

  /**
   * @brief add message to group ( HTTP Method POST )
   * @param hashmap <string, string> params {
   * @param string group_id [required]
   * @param string to [required]
   * @param string from [required]
   * @param string text [required]
   * @param string image_id [optional]
   * @param string refname [optional]
   * @param string country [optional]
   * @param string datetime [optional]
   * @param string subject [optional]
   * @param integer delay [optional] }
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject addMessages(HashMap<String, String> params) throws CoolsmsException {
    if (!checkString(params.get("group_id")) || !checkString(params.get("to")) || !checkString(params.get("from")) || !checkString(params.get("text"))) {
      throw new CoolsmsSDKException("group_id, to, text, from is required", 202);
    }

    String resource = "groups/" + params.get("group_id") + "/add_messages";
    return sendPostRequest(resource, params);
  }

  /**
   * @brief add json type message to group ( HTTP Method POST )
   * @param string group_id [required]
   * @param JSONArray messages [required] [{
   * @param string to [required]
   * @param string from [required]
   * @param string text [required]
   * @param string image_id [optional]
   * @param string refname [optional]
   * @param string country [optional]
   * @param string datetime [optional]
   * @param string subject [optional]
   * @param integer delay [optional] }]
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject addMessagesJSON(String group_id, JSONArray messages) throws CoolsmsException {
    if (!checkString(group_id) || messages.size() < 1) {
      throw new CoolsmsSDKException("group_id, messages is required", 202);
    }

    JSONObject item = null;
    for (int i = 0; i < messages.size(); i++) {
      item = (JSONObject) messages.get(i);
      if (!checkString(item.get("to").toString()) || !checkString(item.get("text").toString()) || !checkString(item.get("from").toString()))
        throw new CoolsmsSDKException("to, from, text is required", 202);
    }

    HashMap<String, String> params = new HashMap<String, String>();
    params.put("messages", messages.toString());

    String resource = "groups/" + group_id + "/add_messages.json";
    return sendPostRequest(resource, params);
  }

  /**
   * @brief get message list ( HTTP Method GET )
   * @param hashmap <string, string> params {
   * @param string group_id [required]
   * @param integer offset [optional]
   * @param integer limit [optional] }
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject getMessageList(HashMap<String, String> params) throws CoolsmsException {
    if (!checkString(params.get("group_id")))
      throw new CoolsmsSDKException("group_id is required", 202);

    String resource = "groups/" + params.get("group_id") + "/message_list";
    return sendGetRequest(resource, params);
  }

  /**
   * @brief delete message from group ( HTTP Method POST )
   * @param string groupID [required]
   * @param string messageIDs [required]
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject deleteMessages(String groupID, String messageIDs) throws CoolsmsException {
    if (!checkString(groupID) || !checkString(messageIDs)) {
      throw new CoolsmsSDKException("group_id and message_ids are required", 202);
    }

    HashMap<String, String> params = new HashMap<String, String>();
    params.put("group_id", groupID);
    params.put("message_ids", messageIDs);

    String resource = "groups/" + params.get("group_id") + "/delete_messages";
    return sendPostRequest(resource, params);
  }

  /**
   * @brief send group message ( HTTP Method POST )
   * @param string groupID [required]
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject sendGroupMessage(String groupID) throws CoolsmsException {
    if (!checkString(groupID))
      throw new CoolsmsSDKException("group_id is required", 202);

    HashMap<String, String> params = new HashMap<String, String>();
    params.put("group_id", groupID);

    String resource = "groups/" + params.get("group_id") + "/send";
    return sendPostRequest(resource, params);
  }
}
