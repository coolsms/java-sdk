import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleVerify
 * @brief This sample code demonstrate how to verify sender number through CoolSMS Rest API PHP
 */
public class ExampleVerify {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";

    try {
      SenderID coolsms = new SenderID(api_key, api_secret);

      // handle_key are mandatory
      String handle_key = "SID56FA3A1266426"; // sender number handle key. check for 'ExampleList'

      JSONObject obj = (JSONObject) coolsms.verify(handle_key);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
