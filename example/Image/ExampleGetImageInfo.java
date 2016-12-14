import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleGetImageInfo
 * @brief This sample code demonstrate how to check image info through CoolSMS Rest API PHP
 */
public class ExampleGetImageInfo {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";
    Image coolsms = new Image(api_key, api_secret);

    // image_id are mandatory
    String image_id = "IMG5734504C13BFB"; // image id

    try {
      JSONObject obj = (JSONObject) coolsms.getImageInfo(image_id);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
