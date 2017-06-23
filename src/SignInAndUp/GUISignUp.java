package SignInAndUp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUISignUp extends JFrame {

	static public GUISignUp instance = null;

	private JTextField IDtextField;
	private JPasswordField passWordTextField;
	private JPasswordField passWordConfrimTextField;

	private JButton confirmBtn;
	private JButton cancelBtn;

	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	final int SIGNUP_FRAME_WIDTH = 400;
	final int SIGNUP_FRAME_HEIGHT = 600;

	JPanel signUpPanel;

	public static GUISignUp getInstance() {

		if (instance == null) {
			instance = new GUISignUp();
		}

		return instance;
	}

	private GUISignUp() {

		this.setSize(SIGNUP_FRAME_WIDTH, SIGNUP_FRAME_HEIGHT);
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setElement();
	}

	public void setElement() {

		addSignUpField();

		addSignUpBtn();

//		this.setVisible(true);

	}

	private void addSignUpField() {

		signUpPanel = new JPanel();
		getContentPane().add(signUpPanel, BorderLayout.CENTER);
		signUpPanel.setLayout(null);

		IDtextField = new JTextField();
		IDtextField.setBounds(200, 135, 86, 20);
		signUpPanel.add(IDtextField);
		IDtextField.setColumns(10);

		passWordTextField = new JPasswordField();
		passWordTextField.setBounds(200, 178, 86, 20);
		signUpPanel.add(passWordTextField);
		passWordTextField.setColumns(10);

		passWordConfrimTextField = new JPasswordField();
		passWordConfrimTextField.setBounds(200, 213, 86, 20);
		signUpPanel.add(passWordConfrimTextField);
		passWordConfrimTextField.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(96, 138, 46, 14);
		signUpPanel.add(lblId);

		JLabel lblPassword = new JLabel("PassWord");
		lblPassword.setBounds(96, 181, 72, 14);
		signUpPanel.add(lblPassword);

		JLabel lblPassword_1 = new JLabel("PassWord");
		lblPassword_1.setBounds(96, 216, 72, 14);
		signUpPanel.add(lblPassword_1);
	}

	private void addSignUpBtn() {

		confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		confirmBtn.setBounds(79, 348, 89, 23);
		confirmBtn.addActionListener(new BtnClickListener());
		
		this.getRootPane().setDefaultButton(confirmBtn);
		
		signUpPanel.add(confirmBtn);

		cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(208, 348, 89, 23);
		cancelBtn.addActionListener(new BtnClickListener());
		
		signUpPanel.add(cancelBtn);

	}

	public JTextField getIDtextField() {
		return IDtextField;
	}

	public JPasswordField getPassWordTextField() {
		return passWordTextField;
	}

	public JPasswordField getPassWordConfrimTextField() {
		return passWordConfrimTextField;
	}

	public JButton getConfirmBtn() {
		return confirmBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

}
