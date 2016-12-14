import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleRegister
 * @brief This sample code demonstrate how to request sender number register through CoolSMS Rest API PHP
 */
public class ExampleRegister {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";

    try {
      SenderID coolsms = new SenderID(api_key, api_secret);

      // phone are mandatory. must be filled
      HashMap<String, String> params = new HashMap<String, String>();
      params.put("phone", "01000000000"); // sender number to register 

      // Optional parameters for your own needs
      // params.put("site_user", "admin"); // site user id. '__private__' is default

      JSONObject obj = (JSONObject) coolsms.register(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
