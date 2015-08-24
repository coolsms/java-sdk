import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * List(발신번호 리스트 가져오기) 예제입니다.
 */
public class ListExample {
	public static void main(String[] args) {
		/*
		 * 서버에서 받은 API_KEY, API_SECRET를 입력해주세요.
		 */
		String api_key = "1CS5588FB7DE511A";
		String api_secret = "4FB5FF8B9AB7D0E0AEB840D404DE0F4";
		Coolsms coolsms = new Coolsms(api_key, api_secret);

		/*
		 * Parameters
		 * 관련정보 : http://www.coolsms.co.kr/SenderID_API#GETlist
		 */
		JSONObject result = coolsms.list(); // 발신번호 등록요청
		if ((Boolean) result.get("status") == true) {
			// 성공 및 전송결과 출력
			System.out.println("성공");			
			JSONArray data = (JSONArray) result.get("data");
			for (int i = 0; i < data.size(); i++) {
				JSONObject obj = (JSONObject) data.get(i);
				System.out.println("\n=======================================\n");
				System.out.println(obj.get("phone_number")); // 발신번호
				System.out.println(obj.get("idno")); // Handle Key
				System.out.println(obj.get("flag_default")); // 삭제여부
				System.out.println(obj.get("regdate")); // 생성시간
				System.out.println(obj.get("updatetime")); // 업데이트시간
			}
		} else {
			// 실패
			System.out.println("실패");
			System.out.println(result.get("code")); // REST API 에러코드
			System.out.println(result.get("message")); // 에러메시지
		}		
	}	
}
