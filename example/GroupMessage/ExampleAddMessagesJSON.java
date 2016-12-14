import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleAddMessages
 * @brief This sample code demonstrate how to add messages into group through CoolSMS Rest API PHP
 */
public class ExampleAddMessagesJSON {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";
    GroupMessage coolsms = new GroupMessage(api_key, api_secret);

    JSONObject msg = new JSONObject();
    JSONArray messages = new JSONArray();
    String group_id = "GID5733EFC59F79E"; // Group ID             

    // options(to, from, text) are mandatory. must be filled
    msg.put("type", "SMS");
    msg.put("to", "01000000000, 01000000001"); // 수신번호
    msg.put("text", "Test Message"); // 문자내용
    msg.put("from", "01000000000"); // 10월 16일 부터 발신번호 등록제 적용으로 인해 등록된 발신번호만 사용이 가능합니다

    // Optional parameters for your own needs
    // msg.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
    // msg.put("image_id", "image_id"); // image_id. type must be set as 'MMS'
    // msg.put("refname", ""); // Reference name
    // msg.put("country", "82"); // Korea(82) Japan(81) America(1) China(86) Default is Korea
    // msg.put("datetime", "20140106153000"); // Format must be(YYYYMMDDHHMISS) 2014 01 06 15 30 00 (2014 Jan 06th 3pm 30 00)
    // msg.put("subject", "Message Title"); // set msg title for LMS and MMS
    // msg.put("delay", "10"); // '0~20' delay messages
    // msg.put("sender_key", "5554025sa8e61072frrrd5d4cc2rrrr65e15bb64"); // 알림톡 사용을 위해 필요합니다. 신청방법 : http://www.coolsms.co.kr/AboutAlimTalk
    // msg.put("template_code", "C004"); // 알림톡 template code 입니다. 자세한 설명은 http://www.coolsms.co.kr/AboutAlimTalk을 참조해주세요.

    messages.add(msg); // 원하는 만큼 JSONObject를 넣어주시면 됩니다  

    try {
      JSONObject obj = (JSONObject) coolsms.addMessagesJSON(group_id, messages);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
