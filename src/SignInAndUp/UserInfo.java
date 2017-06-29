package SignInAndUp;
import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Communication.Communication;
import Communication.ConnectionSocket;

public class UserInfo {

	private static UserInfo instance = null;
	private static String USER_ID = null;

	JSONParser jsonParser = new JSONParser();
	JSONObject resultObj = new JSONObject();

	private UserInfo() {

	}

	public static UserInfo getInterface() {

		if (instance == null) {
			instance = new UserInfo();
		}

		return instance;
	}
	
	
	public void setUserID(String ID){
		
		USER_ID = ID;
		
	}

	public boolean registerUser(String stringID, char[] passWord) {
		boolean result = false;

		// UserInfo.getInterface().registerUser(stringID, passWord);

		JSONObject registerObj = new JSONObject();

		registerObj.put("Action", "SignUp");
		registerObj.put("stringID", stringID);
		registerObj.put("passWord", new String(passWord));

		System.out.println("passWord.toString() : " + new String(passWord));

		ConnectionSocket.printStream.println(registerObj);

		try {

			String input = ConnectionSocket.bufferedReader.readLine();

			resultObj = (JSONObject) jsonParser.parse(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (resultObj.get("result").equals("OK")) {
			result = true;
			
			USER_ID = stringID;
		}

		return result;

	}

	public boolean signInUser(String stringID, char[] passWord) {

		boolean result = false;
		
		JSONObject signInObj = new JSONObject();

		signInObj.put("Action", "SignIn");
		signInObj.put("stringID", stringID);
		signInObj.put("passWord", new String(passWord));
		
		ConnectionSocket.printStream.println(signInObj);
		
		try {

			String input = ConnectionSocket.bufferedReader.readLine();

			resultObj = (JSONObject) jsonParser.parse(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if(resultObj.get("result").equals("SUCESS")){
			System.out.println("로그인 성공");
			
			result = true;
		}else{
			System.out.println("로그인  실패");
			JOptionPane.showMessageDialog(null, "아이디/ 비밀번호가 일치하지않습니다.");
		}
		if (resultObj.get("signInID") != null) {
			String signInID = resultObj.get("signInID").toString();
			
			System.out.println("로그인 id : " + signInID);
		}
		
		return result;
	}
	
	public String getUserID(){
		
		return USER_ID;
	}
}
