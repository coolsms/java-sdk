import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleAddMessages
 * @brief This sample code demonstrate how to delete sms group through CoolSMS Rest API PHP
 */
public class ExampleDeleteGrouops {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";
    GroupMessage coolsms = new GroupMessage(api_key, api_secret);

    // group_ids are mandatory		
    String group_ids = "GID56FCE501593C8,GID56FB7C8A9A135"; // Group IDs

    try {
      JSONObject obj = (JSONObject) coolsms.deleteGroups(group_ids);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
