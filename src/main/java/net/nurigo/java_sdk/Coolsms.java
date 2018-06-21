package net.nurigo.java_sdk;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

import java.util.Properties;
import java.util.Random;
import java.util.HashMap;
import java.util.Map.Entry;

import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.java_sdk.exceptions.CoolsmsSDKException;
import net.nurigo.java_sdk.exceptions.CoolsmsServerException;
import net.nurigo.java_sdk.exceptions.CoolsmsSystemException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;

/**
 * Copyright (C) 2008-2018 NURIGO \n
 * http://www.coolsms.co.kr
 */

/**
 * @mainpage JAVA SDK
 * @section intro 소개
 *     - 소개 : Coolsms REST API 
 *     - 버전 : 2.2
 *     - 설명 : Coolsms REST API 를 이용 보다 빠르고 안전하게 문자메시지를 보낼 수 있는 JAVA로 만들어진 SDK 입니다.
 * @section CreateInfo 작성 정보
 *     - 작성자 : Nurigo
 *     - 작성일 : 2016/05/13 * 
 * @section common 기타 정보
 *     - 저작권 GPL v2
 */

/**
 * @class Coolsms
 * @brief Coolsms Rest API core class, using the Rest API
 */
public class Coolsms {
  /** base resource url & sdk_version */
  final String URL = "https://api.coolsms.co.kr";
  final String SDK_VERSION = "2.2";

  /** api name & api version */
  private String apiName = "sms";
  private String apiVersion = "2";

  /** need for authentication */
  private String salt;
  private String timestamp;
  private String signature;
  private String apiKey;
  private String apiSecret;

  // charset  
  String charset = "UTF-8";

  /**
   * @brief set apiKey, apiSecret
   * @param string apiKey [required]
   * @param string apiSecret [required]
   */
  public Coolsms(String apiKey, String apiSecret) {
    // disable SNI. Java 1.7 bug
    System.setProperty("jsse.enableSNIExtension", "false");
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
  }

  /**
   * @brief sendPostRequest (POST)
   * @param string resource [required]
   * @param hashmap <string, string> params [required]
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject sendPostRequest(String resource, HashMap<String, String> params) throws CoolsmsException {
    JSONObject obj = new JSONObject();

    // set base info
    params = setBaseInfo(params);

    // create delimiter
    String boundary = this.salt + this.timestamp;
    String delimiter = "\r\n--" + boundary + "\r\n";

    // create stringbuffer and append delimiter
    StringBuffer postDataBuilder = new StringBuffer();
    postDataBuilder.append(delimiter);

    // set contents
    for (Entry<String, String> entry : params.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();

      // if key is image. process below
      if (key == "image")
        continue;
      postDataBuilder = setPostData(postDataBuilder, key, value, delimiter);
    }

    try {
      // start https connection	
      URL url = new URL(getResourceUrl(resource));
      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
      connection.setDoInput(true); // Server에서 온 데이터를 입력 받을 수 있도록			
      connection.setDoOutput(true); // Server에서 온 데이터를 출력 할수 있도록
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Connection", "Keep-Alive");
      connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
      connection.setUseCaches(false);
      DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));

      // set image data 
      if (params.get("image") != null) {
        // set image file 
        postDataBuilder.append(setFile("image", params.get("image")));
        postDataBuilder.append("\r\n");

        FileInputStream fileStream = new FileInputStream(params.get("image"));
        outputStream.writeUTF(postDataBuilder.toString());

        // add an image file to the buffer
        int maxBufferSize = 1024;
        int bufferSize = Math.min(fileStream.available(), maxBufferSize);
        byte[] buffer = new byte[bufferSize];
        int byteRead = fileStream.read(buffer, 0, bufferSize);
        while (byteRead > 0) {
          outputStream.write(buffer);
          bufferSize = Math.min(fileStream.available(), maxBufferSize);
          byteRead = fileStream.read(buffer, 0, bufferSize);
        }
        fileStream.close();
      } else {
        outputStream.writeUTF(postDataBuilder.toString());
      }

      outputStream.writeBytes(delimiter);
      outputStream.flush();
      outputStream.close();

      // get response data
      String response = getHttpsResponse(connection);
      if (response == null)
        return obj;

      // casting JSONObject or JSONArray
      try {
        obj = (JSONObject) JSONValue.parse(response);
      } catch (ClassCastException e) {
        try {
          JSONArray response_array = (JSONArray) JSONValue.parse(response);
          obj.put("data", response_array);
        } catch (Exception ex) {
          throw new CoolsmsSystemException(ex.getMessage(), 302);
        }
      }
    } catch (IOException e) {
      throw new CoolsmsSystemException(e.getMessage(), 399);
    }

    return obj;
  }

  /**
   * @brief https sendGetRequest ( GET )
   * @param string resource [required]
   * @param hashmap <string, string> params [required]
   * @return JSONObject
   * @throws CoolsmsException
   */
  public JSONObject sendGetRequest(String resource, HashMap<String, String> params) throws CoolsmsException {
    JSONObject obj = new JSONObject();

    // set base info
    params = setBaseInfo(params);
    String data = getResourceUrl(resource) + "?";
    try {
      data = data + URLEncoder.encode("api_key", this.charset) + "=" + URLEncoder.encode(this.apiKey, this.charset);
    } catch (UnsupportedEncodingException e) {
      throw new CoolsmsSystemException(e.getMessage(), 399);
    }

    // remove api_secret
    params.remove("api_secret");

    // set contents
    for (Entry<String, String> entry : params.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();

      // api_key 는 위에서 넣어줬기 때문에 continue
      if (key == "api_key")
        continue;

      data = setGetData(data, key, value);
      if (data == null) {
        throw new CoolsmsSDKException("params is something wrong, key : " + key + " value : " + value, 201);
      }
    }

    try {
      URL url = new URL(data);
      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      String response = getHttpsResponse(connection);

      // casting JSONObject or JSONArray
      try {
        obj = (JSONObject) JSONValue.parse(response);
      } catch (ClassCastException e) {
        try {
          JSONArray response_array = (JSONArray) JSONValue.parse(response);
          obj.put("data", response_array);
        } catch (Exception ex) {
          throw new CoolsmsSystemException(ex.getMessage(), 302);
        }
      }
    } catch (IOException e) {
      throw new CoolsmsSystemException(e.getMessage(), 399);
    }

    return obj;
  }

  /**
   * @brief set api name and api version
   * @param string $api_name [required] 'sms', 'senderid', 'image'
   * @param integer $api_version [required]
   * @throws CoolsmsException
   */
  public void setApiConfig(String api_name, String api_version) throws CoolsmsException {
    if (!checkString(api_name) || !checkString(api_version))
      throw new CoolsmsSDKException("API name and version is requried", 201);
    this.apiName = api_name;
    this.apiVersion = api_version;
  }

  /**
   * @brief set base info
   * @param hashmap <string, string> params [required]
   * @return
   * @throws CoolsmsException
   */
  private HashMap<String, String> setBaseInfo(HashMap<String, String> params) throws CoolsmsException {
    Properties properties = System.getProperties();

    this.salt = getSalt();
    this.timestamp = getTimestamp();
    this.signature = getSignature(this.apiSecret, salt, timestamp); // getSignature

    params.put("api_key", this.apiKey);
    params.put("salt", this.salt);
    params.put("signature", this.signature);
    params.put("timestamp", this.timestamp);
    params.put("os_platform", properties.getProperty("os.name"));
    params.put("dev_lang", "JAVA " + properties.getProperty("java.version"));
    params.put("sdk_version", "JAVA SDK " + this.SDK_VERSION);
    return params;
  }

  /**
   * @brief 업로드할 파일에 대한 메타 데이터를 설정한다.
   * @param string key [required] 서버에서 사용할 파일 변수명
   * @param string fileName [required] 서버에서 저장될 파일명
   * @return string
   */
  public String setFile(String key, String fileName) {
    return "Content-Disposition: form-data; name=\"" + key + "\";filename=\"" + fileName + "\"\r\nContent-type: image/jpeg;\r\n";
  }

  /**
   * @brief string data를 http method POST 방식 data로 변경
   * @param stringbuffer builder [required]
   * @param string key [required]
   * @param string value [required]
   * @param string delimiter [required]
   * @return stringbuffer
   * @throws CoolsmsException
   */
  public StringBuffer setPostData(StringBuffer builder, String key, String value, String delimiter) throws CoolsmsException {
    try {
      String data = "Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n" + value;
      builder.append(data);
      builder.append(delimiter);
    } catch (Exception e) {
      throw new CoolsmsSystemException(e.getMessage(), 302);
    }

    return builder;
  }

  /**
   * @brief string data를 http method GET 방식 data로 변경
   * @param string data [required]
   * @param string key [required]
   * @param string value [required]
   * @param string charSet [required]
   * @return string
   * @throws CoolsmsException
   */
  public String setGetData(String data, String key, String value) throws CoolsmsException {
    try {
      data += "&" + URLEncoder.encode(key, this.charset) + "=" + URLEncoder.encode(value, this.charset);
    } catch (Exception e) {
      throw new CoolsmsSystemException(e.getMessage(), 302);
    }

    return data;
  }

  /**
   * @brief return resource url from api.coolsms.co.kr
   * @param string resource [required]
   * @return string
   */
  private String getResourceUrl(String resource) {
    String resourceUrl = String.format("%s/%s/%s/%s", this.URL, this.apiName, this.apiVersion, resource);
    return resourceUrl;
  }

  /**
   * @brief get signature
   * @param string api_secret [required]
   * @param string timestamp [required]
   * @return string
   * @throws CoolsmsException
   */
  public String getSignature(String apiSecret, String salt, String timestamp) throws CoolsmsException {
    String signature = "";

    try {
      String temp = timestamp + salt;
      SecretKeySpec keySpec = new SecretKeySpec(apiSecret.getBytes(), "HmacMD5");
      Mac mac = Mac.getInstance("HmacMD5");
      mac.init(keySpec);

      // 바이너리를 hex로 변환
      byte[] result = mac.doFinal(temp.getBytes());
      char[] hexArray = "0123456789ABCDEF".toCharArray();
      char[] hexChars = new char[result.length * 2];

      for (int i = 0; i < result.length; i++) {
        int positive = result[i] & 0xff;
        hexChars[i * 2] = hexArray[positive >>> 4];
        hexChars[i * 2 + 1] = hexArray[positive & 0x0F];
      }
      signature = new String(hexChars);
    } catch (Exception e) {
      throw new CoolsmsSystemException(e.getMessage(), 302);
    }

    return signature;
  }

  /**
   * @brief get timestamp
   * @return string
   */
  public String getTimestamp() {
    long timestamp_long = System.currentTimeMillis() / 1000;
    String timestamp = Long.toString(timestamp_long);
    return timestamp;
  }

  /**
   * @brief get https response
   * @param httpurlconnection api_key [required]
   * @return string
   * @throws CoolsmsException
   */
  public String getHttpsResponse(HttpURLConnection connection) throws CoolsmsException {
    int responseCode = 0;
    String responseBody = null;
    String inputLine = null;
    BufferedReader in = null;
    JSONObject obj = new JSONObject();

    try {
      responseCode = connection.getResponseCode();
      if (responseCode != 200) {
        in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      } else {
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      }

      while ((inputLine = in.readLine()) != null) {
        responseBody = inputLine;
      }

      // response code is not 200, throw CoolsmsServerException
      if (responseCode != 200)
        throw new CoolsmsServerException(responseBody, responseCode);
    } catch (Exception e) {
      throw new CoolsmsSystemException(e.getMessage(), 302);
    }

    return responseBody;
  }

  /**
   * @brief get salt data
   * @return string
   */
  public String getSalt() {
    String uniqId = "";
    Random randomGenerator = new Random();

    // length - set the unique Id length
    for (int length = 1; length <= 10; ++length) {
      int randomInt = randomGenerator.nextInt(10); // digit range from 0 - 9
      uniqId += randomInt + "";
    }

    return uniqId;
  }

  /**
   * @brief check string is empty or null
   * @return boolean
   */
  public boolean checkString(String str) {
    if (str == null)
      return false;
    str = str.trim();
    if (str == null || str.isEmpty())
      return false;
    return true;
  }
}
