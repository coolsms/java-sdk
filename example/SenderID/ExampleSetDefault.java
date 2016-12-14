import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSetDefault
 * @brief This sample code demonstrate how to set default sender number through CoolSMS Rest API PHP
 */
public class ExampleSetDefault {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";

    try {
      SenderID coolsms = new SenderID(api_key, api_secret);

      // handle_key are mandatory
      HashMap<String, String> params = new HashMap<String, String>();
      params.put("handle_key", "SID56FA3A1266426"); // sender number handle key. check for 'ExampleSenderIdList' 

      // Optional parameters for your own needs
      // params.put("site_user", "admin"); // site user id. '__private__' is default

      JSONObject obj = (JSONObject) coolsms.setDefault(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
