package SignInAndUp;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUISignIn extends JFrame {

	static private GUISignIn instance = null;
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	final int LOGIN_FRAME_WIDTH = 1000;
	final int LOGIN_FRAME_HEIGHT = 800;

	final int ID_FIELD_WIDTH = 150;
	final int ID_FILED_HEIGHT = 30;

	private JTextField IDTextField;
	private JPasswordField passWordTextField;

	private JButton btnSignIn;
	private JButton btnSignUp;

	JLabel lblSignIn;

	public static GUISignIn getInstance() {

		if (instance == null) {
			instance = new GUISignIn();
		}

		return instance;
	}

	private GUISignIn() {

		this.setSize(LOGIN_FRAME_WIDTH, LOGIN_FRAME_HEIGHT);
		
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

	}

	public void setElement() {
		addSignInLabel();
		addIDPassWordField();
		addSignInUpBtn();

		this.setVisible(true);
	}

	private void addSignInLabel() {

		lblSignIn = new JLabel("Sign In");
		lblSignIn.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSignIn.setPreferredSize(new Dimension(35, 14));
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignIn.setBounds(LOGIN_FRAME_WIDTH / 4, LOGIN_FRAME_HEIGHT / 5, LOGIN_FRAME_WIDTH / 2,
				LOGIN_FRAME_HEIGHT / 10);
		getContentPane().add(lblSignIn);
	}

	private void addIDPassWordField() {

		IDTextField = new JTextField();
		IDTextField.setBounds(LOGIN_FRAME_WIDTH / 2 - ID_FIELD_WIDTH / 2, lblSignIn.getY() + lblSignIn.getHeight(),
				ID_FIELD_WIDTH, ID_FILED_HEIGHT);
		getContentPane().add(IDTextField);
		IDTextField.setColumns(10);

		passWordTextField = new JPasswordField();
		passWordTextField.setBounds(LOGIN_FRAME_WIDTH / 2 - ID_FIELD_WIDTH / 2,
				lblSignIn.getY() + lblSignIn.getHeight() + 2 * ID_FILED_HEIGHT, ID_FIELD_WIDTH, ID_FILED_HEIGHT);
		getContentPane().add(passWordTextField);
		passWordTextField.setColumns(10);
	}

	private void addSignInUpBtn() {

		btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(LOGIN_FRAME_WIDTH / 2 - ID_FIELD_WIDTH / 2, LOGIN_FRAME_HEIGHT / 2, ID_FIELD_WIDTH,
				ID_FILED_HEIGHT);

		btnSignIn.addActionListener(new BtnClickListener());

		getContentPane().add(btnSignIn);

		btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(LOGIN_FRAME_WIDTH / 2 - ID_FIELD_WIDTH / 2, LOGIN_FRAME_HEIGHT / 2 + 2 * ID_FILED_HEIGHT,
				ID_FIELD_WIDTH, ID_FILED_HEIGHT);
		
		btnSignUp.addActionListener(new BtnClickListener());
		getContentPane().add(btnSignUp);

	}

	public String getStringID() {

		String stringID = null;

		if (getInputValidation(IDTextField.getText())) {
			stringID = IDTextField.getText();
		} else {
			JOptionPane.showMessageDialog(null, "영문자/숫자 만 허용됩니다.");
		}

		return stringID;
	}

	public char[] getStringPassWord() {
		return passWordTextField.getPassword();
	}

	public boolean getInputValidation(String inputText) {

		char testChar;

		for (int i = 0; i < inputText.length(); i++) {

			testChar = inputText.charAt(i);

			if (testChar >= 'a' && testChar <= 'z') {
				// 영어 소문자
			} else if (testChar >= 'A' && testChar <= 'Z') {
				// 영어 대문자
			} else if (testChar >= '0' && testChar <= '9') {
				// 숫자
			} else {

				return false;
			}

		}

		return true;

	}

	public JButton getBtnSignIn() {
		return btnSignIn;
	}

	public JButton getBtnSignUp() {
		return btnSignUp;
	}


}
