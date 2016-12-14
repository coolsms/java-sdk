import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleDeleteImage
 * @brief This sample code demonstrate how to delete images through CoolSMS Rest API PHP
 */
public class ExampleDeleteImage {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";
    Image coolsms = new Image(api_key, api_secret);

    // image_ids are mandatory
    String image_ids = "IMG5734504C13BFB,IMG1232504C13BFB"; // image ids

    try {
      JSONObject obj = (JSONObject) coolsms.deleteImages(image_ids);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
