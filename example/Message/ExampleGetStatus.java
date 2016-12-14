import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleGetStatus
 * @brief This sample code demonstrate how to check sms result through CoolSMS Rest API PHP
 */
public class ExampleGetStatus {
  public static void main(String[] args) {
    String api_key = "#ENTER_YOUR_OWN#";
    String api_secret = "#ENTER_YOUR_OWN#";
    Message coolsms = new Message(api_key, api_secret);

    // Optional parameters for your own needs
    HashMap<String, String> params = new HashMap<String, String>();
    // params.put("count", "1"); // 기본값 1이며 1개의 최신 레코드를 받을 수 있음. 10입력시 10분동안의 레코드 목록을 리턴
    // params.put("unit", "minute"); // minute(default), hour, day 중 하나 해당 단위의 평균
    // params.put("date", "20161016230000"); // 데이터를 읽어오는 기준 시각 
    // params.put("channel", "1"); // 1 : 1건 발송채널(default), 2 : 대량 발송 채널

    try {
      JSONObject obj = (JSONObject) coolsms.getStatus(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}
