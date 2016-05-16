package net.nurigo.java_sdk.examples.Message;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */
public class ExampleSend {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";
    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", "01000000000"); // 수신번호
    params.put("from", "01000000000"); // 발신번호
    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
    params.put("text", "Test Message"); // 문자내용    
    params.put("app_version", "JAVA SDK v1.2"); // application name and version

    // Optional parameters for your own needs
    // params.put("image", "desert.jpg"); // image for MMS. type must be set as "MMS"
    // params.put("refname", ""); // Reference name
    // params.put("country", "KR"); // Korea(KR) Japan(JP) America(USA) China(CN) Default is Korea
    // params.put("sender_key", "55540253a3e61072f57ed5d4cc2ecf965e15bb64"); // 알림톡 사용을 위해 필요합니다. 신청방법 : http://www.coolsms.co.kr/AboutAlimTalk
    // params.put("template_code", "C004"); // 알림톡 template code 입니다. 자세한 설명은 http://www.coolsms.co.kr/AboutAlimTalk을 참조해주세요. 
    // params.put("datetime", "20140106153000"); // Format must be(YYYYMMDDHHMISS) 2014 01 06 15 30 00 (2014 Jan 06th 3pm 30 00)
    // params.put("mid", "mymsgid01"); // set message id. Server creates automatically if empty
    // params.put("gid", "mymsg_group_id01"); // set group id. Server creates automatically if empty
    // params.put("subject", "Message Title"); // set msg title for LMS and MMS
    // params.put("charset", "euckr"); // For Korean language, set euckr or utf-8

    try {
      JSONObject obj = (JSONObject) coolsms.send(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}