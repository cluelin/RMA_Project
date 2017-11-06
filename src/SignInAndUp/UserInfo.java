package SignInAndUp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

	
	private static MessageDigest messageDigest;
	
	private UserInfo() {

		try{
			messageDigest =  MessageDigest.getInstance("SHA-256");
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
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

		String pass = new String(passWord);
		messageDigest.update(pass.getBytes());

		byte byteData[] = messageDigest.digest();

		StringBuffer encryptedPassword = new StringBuffer();

		for (int i = 0; i < byteData.length; i++) {
			encryptedPassword.append(Integer.toString((byteData[i] & 0xff) + 0x100, 1).substring(1));

		}

		// 비밀번호 암호화
		System.out.println("비밀번호  : " + encryptedPassword.toString());

		

		JSONObject registerObj = new JSONObject();

		registerObj.put("Action", "SignUp");
		registerObj.put("stringID", stringID);
		registerObj.put("passWord", encryptedPassword.toString());

		System.out.println("passWord.toString() : " + new String(passWord));

		ConnectionSocket.printStream.println(registerObj);

		try {

			String input = ConnectionSocket.getInstance().readLineFromServer();

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
		
		String pass = new String(passWord);
		messageDigest.update(pass.getBytes());

		byte byteData[] = messageDigest.digest();

		StringBuffer encryptedPassword = new StringBuffer();

		for (int i = 0; i < byteData.length; i++) {
			encryptedPassword.append(Integer.toString((byteData[i] & 0xff) + 0x100, 1).substring(1));

		}

		// 비밀번호 암호화
		System.out.println("비밀번호  : " + encryptedPassword.toString());
		
		
		JSONObject signInObj = new JSONObject();

		signInObj.put("Action", "SignIn");
		signInObj.put("stringID", stringID);
		signInObj.put("passWord", encryptedPassword.toString());
		
				
		ConnectionSocket.printStream.println(signInObj);
		
		try {

			String input = ConnectionSocket.getInstance().readLineFromServer();

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
