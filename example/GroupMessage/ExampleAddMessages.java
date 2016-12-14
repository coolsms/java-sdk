import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleAddMessages
 * @brief This sample code demonstrate how to add messages into group through CoolSMS Rest API PHP
 */
public class ExampleAddMessages {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";
    GroupMessage coolsms = new GroupMessage(api_key, api_secret);

    HashMap<String, String> params = new HashMap<String, String>();

    // options(to, from, text) are mandatory. must be filled
    params.put("to", "01000000000");
    params.put("from", "01000000000");
    params.put("text", "Coolsms Testing Message!");
    params.put("group_id", "GID56FA3B1BF0826"); // Group ID	    

    // Optional parameters for your own needs
    // params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
    // params.put("image_id", "image_id"); // image_id. type must be set as 'MMS'
    // params.put("refname", ""); // Reference name
    // params.put("country", "82"); // Korea(82) Japan(81) America(1) China(86) Default is Korea
    // params.put("datetime", "20140106153000"); // Format must be(YYYYMMDDHHMISS) 2014 01 06 15 30 00 (2014 Jan 06th 3pm 30 00)
    // params.put("subject", "Message Title"); // set msg title for LMS and MMS
    // params.put("delay", "10"); // '0~20' delay messages
    // params.put("sender_key", "5554025sa8e61072frrrd5d4cc2rrrr65e15bb64"); // 알림톡 사용을 위해 필요합니다. 신청방법 : http://www.coolsms.co.kr/AboutAlimTalk
    // params.put("template_code", "C004"); // 알림톡 template code 입니다. 자세한 설명은 http://www.coolsms.co.kr/AboutAlimTalk을 참조해주세요.

    try {
      JSONObject obj = (JSONObject) coolsms.addMessages(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
