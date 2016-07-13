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
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";
    GroupMessage coolsms = new GroupMessage(api_key, api_secret);

    // group_id, message_ids are mandatory.
    String group_id = "GID56FA3B1BF0826"; // Group ID
    String message_ids = "MID56FA3B405A847, MIDFFFA3B405A847"; // Message Ids

    try {
      JSONObject obj = (JSONObject) coolsms.deleteMessages(group_id, message_ids);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}