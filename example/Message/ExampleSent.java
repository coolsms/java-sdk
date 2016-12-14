import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSent
 * @brief This sample code demonstrate how to check sms result through CoolSMS Rest API PHP
 */
public class ExampleSent {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";
    Message coolsms = new Message(api_key, api_secret);

    // Optional parameters for your own needs
    HashMap<String, String> params = new HashMap<String, String>();
    // 4 params(to, from, type, text) are mandatory. must be filled
    // params.put("mid", "M52CB443257C61"); // message id
    // params.put("gid", "G52CB4432576C8"); // group id
    // params.put("offset", "0"); // default 0
    // params.put("limit", "20"); // default 20
    // params.put("rcpt", "01000000000"); // search sent result by recipient number 
    // params.put("start", "201601070915"); // set search start date 
    // params.put("end", "201601071230"); // set search end date

    try {
      JSONObject obj = (JSONObject) coolsms.sent(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
