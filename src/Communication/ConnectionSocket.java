package Communication;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Error.ConnectionFailed;

public class ConnectionSocket extends Thread {

	public static PrintStream printStream;
	public static BufferedReader bufferedReader;
	public static Socket client = null;

	static ConnectionSocket instance = null;

	public static ConnectionSocket getInstance() {

		if (instance == null) {
			instance = new ConnectionSocket();
		}

		return instance;
	}

	public static void connectServer() {

		if (client == null) {
			try {
				System.out.println("Client : connecting...");
				client = new Socket(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
				System.out.println("Client : connected");

				printStream = new PrintStream(client.getOutputStream());

				bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

			} catch (Exception e) {
				e.printStackTrace();
				// JOptionPane.showMessageDialog(null, "서버 연결 실패");
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

	public OutputStream getOutputStream() throws IOException {

		return client.getOutputStream();
	}
	
	public Socket getSocket(){
		return client;
	}

}
