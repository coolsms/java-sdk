import static org.junit.Assert.*;

import java.util.HashMap;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import org.json.simple.JSONObject;
import org.junit.Test;


public class CoolsmsUnitTest {
	String api_key = "NCS558104628ADED";
	String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
	
	Message message = new Message(api_key, api_secret);
	GroupMessage groupMessage = new GroupMessage(api_key, api_secret);
	Image image = new Image(api_key, api_secret);
	SenderID senderID;
	JSONObject result;
	
	
	@Test
	public void MessageTest() {		
		try {			
			// Send
			HashMap<String, String> params = new HashMap<String, String>();		
			params.put("to", "01000000000");
		    params.put("from", "01000000000");
		    params.put("type", "SMS");
		    params.put("text", "Coolsms Testing Message!");
		    params.put("mode", "test");     
		    
		    result = message.send(params);		    
		    assertEquals(result.get("result_message"), "Success");
		    
			// Balance
			result = message.balance();
			assertNotNull(result.get("cash"));			
			
			// Sent
			params.clear();
			result = message.sent(params);
			assertNotNull(result.get("data"));
			
			// Status
			result = message.getStatus(params);
			assertNotNull(result.get("data"));
						
			// Cancel
			params.put("mid", "MIDTEST");
			result = message.cancel(params);			
			assertTrue(result.isEmpty());				
		}catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void GroupMessageTest() {
		
	}
	
	@Test
	public void ImageTest() {
		
	}
	
	@Test
	public void SenderIDTest() {
		 try {
			this.senderID = new SenderID(api_key, api_secret);
		} catch (CoolsmsException e) {			
			fail(e.toString());
		}
	}

}
