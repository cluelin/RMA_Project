package Default;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread{
	
	final public static String SERVER_IP = "127.0.0.1";
	final public static int SERVER_PORT = 9090;
	
	public static PrintStream printStream;
	public static BufferedReader bufferedReader;
	public static Socket client;
	
	
	
	public static void connectServer() throws Exception {

		// 서버로 전송

		System.out.println("Client : connecting...");
		client = new Socket(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
		System.out.println("Client : connected");
		printStream = new PrintStream(client.getOutputStream());
		bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

	}

	public static void closeConnection() {

		try {
			client.close();
			System.out.println("Client : close");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
