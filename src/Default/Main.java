package Default;

import Communication.ConnectionSocket;
import JavaSignInAndUpTemplate.GUISignIn;

public class Main {

	public static void main(String[] args) {

		ConnectionSocket.connectServer();

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("종료 이벤트");
				ConnectionSocket.closeConnection();
			}
		}));

		// 로그인 패널.
		GUISignIn guiSignIn = GUISignIn.getInstance();

		guiSignIn.setElement();

	}

}
