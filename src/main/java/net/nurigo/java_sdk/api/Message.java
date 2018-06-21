package net.nurigo.java_sdk.api;

import java.util.HashMap;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.java_sdk.exceptions.CoolsmsSDKException;

import org.json.simple.JSONObject;

/**
 * @class Message
 * @brief management message, using Rest API
 */
public class Message extends Coolsms {
  /**
   * @brief constructor
   */
  public Message(String apiKey, String apiSecret) {
    super(apiKey, apiSecret);
  }

  /**
   * @brief send messages ( HTTP Method POST )
   * @param hashmap<string, string> params {
   * @param string to [required]
   * @param string from [required]
   * @param string text [required]
   * @param string type [optional]
   * @param mixed image [optional]
   * @param string image_encoding [optional]
   * @param string refname [optional]
   * @param mixed country [optional]
   * @param string datetime [optional]
   * @param string subject [optional]
   * @param string charset [optional]
   * @param string srk [optional]
   * @param string mode [optional]
   * @param string extension [optional]
   * @param integer delay [optional]
   * @param boolean force_sms [optional]
   * @param string app_version [optional] }
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject send(HashMap<String, String> params) throws CoolsmsException {
    return sendPostRequest("send", params);
  }

  /**
   * @brief sent messages ( HTTP Method GET )
   * @param hashmap<string, string> params {
   * @param integer offset [optional]
   * @param integer limit [optional]
   * @param string rcpt [optional]
   * @param string start [optional]
   * @param string end [optional]
   * @param string status [optional]
   * @param string status [optional]
   * @param string resultcode [optional]
   * @param string notin_resultcode [optional]
   * @param string message_id [optional]
   * @param string group_id [optional] }
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject sent(HashMap<String, String> params) throws CoolsmsException {
    return sendGetRequest("sent", params);
  }

  /**
   * @brief cancel reserve message. message_id or group_id either one must be entered. ( HTTP Method POST )
   * @param hashmap<string, string> params {
   * @param string message_id [optional]
   * @param string group_id [optional] }
   * @return None
   * @throws CoolsmsException
   */
  public JSONObject cancel(HashMap<String, String> params) throws CoolsmsException {
    if (!checkString(params.get("message_id")) && !checkString(params.get("group_id")))
      throw new CoolsmsSDKException("message_id or group_id either one must be entered", 202);

    return sendPostRequest("cancel", params);
  }

  /**
   * @brief get remaining balance ( HTTP Method GET )
   * @param None
   * @return JSONobject
   * @throws CoolsmsException
   */
  public JSONObject balance() throws CoolsmsException {
    // resource 'balance' does not required params so hand over empty params.
    HashMap<String, String> params = new HashMap<String, String>();
    return sendGetRequest("balance", params);
  }

  /**
   * @brief get status ( HTTP Method GET )
   * @param hashmap<string, string> params {
   * @param integer count [optional]
   * @param string unit [optional]
   * @param string date [optional]
   * @param integer channel [optional] }
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject getStatus(HashMap<String, String> params) throws CoolsmsException {
    return sendGetRequest("status", params);
  }
}
