package SignInAndUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Default.MainFrame;

public class BtnClickListener implements ActionListener {

	static GUISignIn guiSignIn = GUISignIn.getInstance();
	static GUISignUp guiSignUp = GUISignUp.getInstance();

	String stringID;

	private static MessageDigest messageDigest;

	public BtnClickListener() {
		
		try{
			messageDigest =  MessageDigest.getInstance("SHA-256");
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		 

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (guiSignIn.getBtnSignIn() == (JButton) e.getSource()) {

			// DB�� �����ؼ� Ȯ�� �޾ƿ��� ���� ���μ��� ����.

			boolean result = false;
			
			if(guiSignIn.getStringID() != null){
				result = UserInfo.getInterface().signInUser(guiSignIn.getStringID(), guiSignIn.getStringPassWord());
			}
			

			if (result) {
				MainFrame clientMain = MainFrame.getInstance();

				UserInfo.getInterface().setUserID(guiSignIn.getStringID());
				clientMain.setMainFrame();

				guiSignIn.dispose();
			}

		} else if (guiSignIn.getBtnSignUp() == (JButton) e.getSource()) {

			showSignUpPanel();

		} else if (guiSignUp.getCancelBtn() == (JButton) e.getSource()) {

			guiSignUp.dispose();

			guiSignIn.setVisible(true);

		} else if (guiSignUp.getConfirmBtn() == (JButton) e.getSource()) {

			if (checkPassWord(guiSignUp.getPassWordTextField().getPassword(),
					guiSignUp.getPassWordConfrimTextField().getPassword())) {

				// ID. ��й�ȣ ������. ȸ�����Կ�.

				// DB�� �����ؼ� �ش� ID, Password�� ��Ͻ�Ű�� ������ �ʿ���.

				boolean result = UserInfo.getInterface().registerUser(guiSignUp.getIDtextField().getText(),
						guiSignUp.getPassWordTextField().getPassword());

				
				if (!result) {
					JOptionPane.showMessageDialog(null, "ID�� �̹� �����մϴ�.");
				}

			}

		}

	}

	public boolean checkPassWord(char[] passWord, char[] passWordConfirm) {

		boolean result = false;

		if (passWord.length != passWordConfirm.length) {

			JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");

		} else if (passWord.length < 5) {

			JOptionPane.showMessageDialog(null, "��й�ȣ�� 5�� �̻��̾�� �մϴ�.");

		} else {
			result = Arrays.equals(passWord, passWordConfirm);

		}

		return result;

	}

	public void showSignUpPanel() {
		guiSignIn.dispose();

		guiSignUp.setVisible(true);
	}

}
