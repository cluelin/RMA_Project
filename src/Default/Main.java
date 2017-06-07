package Default;

import Communication.ConnectionSocket;
import JavaSignInAndUpTemplate.GUISignIn;

public class Main {

	public static void main(String[] args) {

		ConnectionSocket.connectServer();

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("���� �̺�Ʈ");
				ConnectionSocket.closeConnection();
			}
		}));

		// �α��� �г�.
		GUISignIn guiSignIn = GUISignIn.getInstance();

		guiSignIn.setElement();

	}

}
