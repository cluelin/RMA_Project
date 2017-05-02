package Communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Default.ServerInformation;
import Error.ConnectionFailed;

public class ConnectionSocket extends Thread {

	public static PrintStream printStream;
	public static BufferedReader bufferedReader;
	public static Socket client = null;

	public static void connectServer() {
		
		if(client == null){
			try{
				System.out.println("Client : connecting...");
				client = new Socket(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
				System.out.println("Client : connected");
				
				printStream = new PrintStream(client.getOutputStream());
				bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
			}catch(Exception e){
				e.printStackTrace();
//				JOptionPane.showMessageDialog(null, "서버 연결 실패");
				new ConnectionFailed();
				System.exit(1);
			}
		}

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
