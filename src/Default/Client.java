package Default;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread {

	final public static String SERVER_IP = "127.0.0.1";
	final public static int SERVER_PORT = 9090;

	public static PrintStream printStream;
	public static BufferedReader bufferedReader;
	public static Socket client = null;

	public static void connectServer() {

		// 서버로 전송

//		try {
//			System.out.println("Client : connecting...");
//			client = new Socket(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
//			System.out.println("Client : connected");
//			printStream = new PrintStream(client.getOutputStream());
//			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//		} catch (Exception e) {
//			e.printStackTrace();
//
//			System.out.println("서버 연결 에러");
//		}
		
		if(client == null){
			try{
				client = new Socket(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
				printStream = new PrintStream(client.getOutputStream());
				bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}

	public static void closeConnection() {

//		try {
//			client.close();
//			System.out.println("Client : close");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
