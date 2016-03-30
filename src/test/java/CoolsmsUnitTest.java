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
		    System.out.println(result);
		    assertEquals(result.get("result_message"), "Success");		    
		    
			// balance
			result = message.balance();
			assertNotNull(result.get("cash"));			
			
			// sent
			params.clear();
			try {
				result = message.sent(params);				
				assertNotNull(result.get("data"));			
			} catch(Exception e) {
				result = (JSONObject) JSONValue.parse(e.getMessage());				
				assertEquals(result.get("code"), "NoSuchMessage");
			}		
			
			// status
			result = message.getStatus(params);
			assertNotNull(result.get("data"));
						
			// cancel
			params.put("mid", "MIDTEST");
			result = message.cancel(params);			
			assertTrue(!result.isEmpty());				
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
			success_count = Integer.parseInt(result.get("success_count").toString());
			assertNotEquals(success_count, 0);
			
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
			String handle_key;
			
			// register
			params.put("phone", "01000000000");
			result = senderID.register(params);
			handle_key = (String) result.get("handle_key");
			assertNotNull(result.get("handle_key"));
						
			// verify
			try {
				senderID.verify(handle_key);
			} catch(CoolsmsException e) {
				result = (JSONObject) JSONValue.parse(e.getMessage());
				assertEquals(result.get("code"), "Busy");				
			}
			
			// get senderid list			
			result = senderID.getSenderidList(params);			
			assertNotNull(result.get("data"));
			
			// get default			
			try {
				result = senderID.getDefault(params);
				assertNotNull(result.get("handle_key"));				
			} catch(CoolsmsException e) {
				result = (JSONObject) JSONValue.parse(e.getMessage());				
				assertEquals(result.get("code"), "NoDefaultSenderID");				
			}			
			
			// set default
			params.put("handle_key", "testHandleKey");
			result = senderID.setDefault(params);
			assertTrue(result.isEmpty());
		} catch (CoolsmsException e) {			
			fail(e.toString());
		}
	}

}
