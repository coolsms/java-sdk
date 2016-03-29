import static org.junit.Assert.*;

import java.io.Reader;
import java.util.HashMap;

import net.nurigo.java_sdk.api.GroupMessage;
import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;


public class CoolsmsUnitTest {
	String api_key = "NCS558104628ADED";
	String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
	
	Message message = new Message(api_key, api_secret);
	GroupMessage groupMessage = new GroupMessage(api_key, api_secret);
	Image image = new Image(api_key, api_secret);
	SenderID senderID;
	JSONObject result;
	JSONArray result_array;
	HashMap<String, String> params = new HashMap<String, String>();
	
	
	@Test
	public void MessageTest() {		
		try {			
			// send					
			params.put("to", "01000000000");
		    params.put("from", "01000000000");
		    params.put("type", "SMS");
		    params.put("text", "Coolsms Testing Message!");
		    params.put("mode", "test");     
		    
		    result = message.send(params);		    
		    assertEquals(result.get("result_message"), "Success");
		    
			// balance
			result = message.balance();
			assertNotNull(result.get("cash"));			
			
			// sent
			params.clear();
			result = message.sent(params);
			assertNotNull(result.get("data"));
			
			// status
			result = message.getStatus(params);
			assertNotNull(result.get("data"));
						
			// cancel
			params.put("mid", "MIDTEST");
			result = message.cancel(params);			
			assertTrue(result.isEmpty());				
		}catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void GroupMessageTest() {
		try {
			String group_id;
			String message_id;
			int success_count;
			params.clear();
			
			// create group
			params.put("mode", "test");
			result = groupMessage.createGroup(params);
			assertNotNull(result.get("group_id"));
			group_id = (String) result.get("group_id");		
			
			// group info			
			result = groupMessage.getGroupInfo(group_id);
			assertNotNull(result.get("message_count"));
			
			// group list
			result = groupMessage.getGroupList();
			assertNotNull(result.get("list"));
			
			// add messages
			params.clear();
			params.put("to", "01000000000");
			params.put("from", "01000000000");
			params.put("text", "Coolsms Testing Message!");
			params.put("group_id", group_id); // Group ID	    
			result = groupMessage.addMessages(params);		
			message_id = (String) result.get("message_id");
			success_count = Integer.parseInt(result.get("success_count").toString());
			assertNotEquals(success_count, 0);
						
			// message list
			params.clear();
			params.put("group_id", group_id);
			result = groupMessage.getMessageList(params);
			assertNotNull(result.get("list"));
			result_array = (JSONArray) result.get("list");
			result_array.get(0);
			message_id = (String) result_array.get(0);			
			
			// send
			result = groupMessage.sendGroupMessage(group_id);
			assertNotNull(result.get("group_id"));
			
			// delete messages
			result = groupMessage.deleteMessages(group_id, message_id);
			// 이 부분은 테스트 하면서 고쳐야 할듯 null값으로 넘어옴 
			
			// delete group
			result = groupMessage.deleteGroups(group_id);
			success_count = Integer.parseInt(result.get("success_count").toString());
			assertNotEquals(success_count, 0);			
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void ImageTest() {
		try {

		} catch (Exception e) {

		}
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
