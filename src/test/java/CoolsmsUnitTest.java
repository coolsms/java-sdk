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
	String api_key = "CS558104628ADED";
	String api_secret = "983C21FB95000DCBD2A1C4FE25F14883";
	
	Message Message = new Message(api_key, api_secret);
	GroupMessage GroupMessage = new GroupMessage(api_key, api_secret);
	Image Image = new Image(api_key, api_secret);
	SenderID SenderID;
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
		    result = Message.send(params);		    
		    assertEquals(result.get("result_message"), "Success");		    
		    
			// balance
			result = Message.balance();
			assertNotNull(result.get("cash"));			
			
			// sent
			params.clear();
			try {
				result = Message.sent(params);				
				assertNotNull(result.get("data"));			
			} catch(Exception e) {
				result = (JSONObject) JSONValue.parse(e.getMessage());				
				assertEquals(result.get("code"), "NoSuchMessage");
			}		
			
			// status
			result = Message.getStatus(params);
			assertNotNull(result.get("data"));
						
			// cancel
			params.put("mid", "MIDTEST");
			result = Message.cancel(params);			
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
			result = GroupMessage.createGroup(params);
			assertNotNull(result.get("group_id"));
			group_id = (String) result.get("group_id");
			
			// group info			
			result = GroupMessage.getGroupInfo(group_id);
			assertNotNull(result.get("message_count"));
			
			// group list
			result = GroupMessage.getGroupList();
			assertNotNull(result.get("list"));
						
			// add messages
			params.clear();
			params.put("to", "01000000000");
			params.put("from", "01000000000");
			params.put("text", "Coolsms Testing Message!");
			params.put("group_id", group_id); // Group ID
			result = GroupMessage.addMessages(params);
			success_count = Integer.parseInt(result.get("success_count").toString());
			assertNotEquals(success_count, 0);

			// message list
			params.clear();
			params.put("group_id", group_id);
			result = GroupMessage.getMessageList(params);
			assertNotNull(result.get("list"));
			result_array = (JSONArray) result.get("list");
			result_array.get(0);
			message_id = (String) result_array.get(0);
			
			// send
			result = GroupMessage.sendGroupMessage(group_id);
			assertNotNull(result.get("group_id"));
			
			// delete messages
			result = GroupMessage.deleteMessages(group_id, message_id);
			success_count = Integer.parseInt(result.get("success_count").toString());
			assertNotEquals(success_count, 0);
			
			// delete group
			result = GroupMessage.deleteGroups(group_id);
			success_count = Integer.parseInt(result.get("success_count").toString());
			assertNotEquals(success_count, 0);			
		} catch (Exception e) {			
			fail(e.toString());
		}
	}
	
	@Test
	public void ImageTest() {
		try {
			String image_id;
			int total_count;
			int success_count;
			
			// upload image
			params.put("image", "C:\\test.jpg"); // image
			result = Image.uploadImage(params);					
			assertNotNull(result.get("image_id"));
			image_id = (String) result.get("image_id");
			
			// get image list
			params.clear();
			result = Image.getImageList(params);
			total_count = Integer.parseInt(result.get("total_count").toString());
			assertNotEquals(total_count, 0);			
			
			// get image info			
			params.put("image_id", image_id);			
			result = Image.getImageInfo(image_id);			
			assertNotNull(result.get("image_id"));
			
			// delete image
			params.clear();
			result = Image.deleteImages(image_id);
			success_count = Integer.parseInt(result.get("success_count").toString());
			assertNotEquals(success_count, 0);
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void SenderIDTest() {
		 try {
			this.SenderID = new SenderID(api_key, api_secret);
			String handle_key;
			
			// register
			params.put("phone", "01000000000");
			result = SenderID.register(params);
			handle_key = (String) result.get("handle_key");
			assertNotNull(result.get("handle_key"));
						
			// verify
			try {
				SenderID.verify(handle_key);
			} catch(CoolsmsException e) {
				result = (JSONObject) JSONValue.parse(e.getMessage());
				assertEquals(result.get("code"), "Busy");				
			}
			
			// get senderid list			
			result = SenderID.getSenderidList(params);			
			assertNotNull(result.get("data"));
			
			// get default			
			try {
				result = SenderID.getDefault(params);
				assertNotNull(result.get("handle_key"));				
			} catch(CoolsmsException e) {
				result = (JSONObject) JSONValue.parse(e.getMessage());				
				assertEquals(result.get("code"), "NoDefaultSenderID");				
			}			
			
			// set default
			params.put("handle_key", "testHandleKey");
			result = SenderID.setDefault(params);
			assertTrue(result.isEmpty());
		} catch (CoolsmsException e) {			
			fail(e.toString());
		}
	}

}
