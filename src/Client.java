import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread{
	
	final public static String SERVER_IP = "127.0.0.1";
	final public static int SERVER_PORT = 9090;
	
	
	public Client() {

	}
	
	@Override
	public void run(){
		try{
			
			System.out.println("Client : connecting...");
			Socket client = new Socket(SERVER_IP, SERVER_PORT);
			System.out.println("Client : connected");
			
			OutputStream outputStream = client.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			
			printStream.println("클라이언트 접속");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
