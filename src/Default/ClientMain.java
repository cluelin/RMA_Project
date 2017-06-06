package Default;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import AdvancedReplacement.AdvancedReplacementOperation;
import AdvancedReplacement.GUIadvancedRepalcementPanel;
import Communication.ConnectionSocket;
import JavaSignInAndUpTemplate.GUISignIn;

import java.awt.Component;
import javax.swing.border.EmptyBorder;

public class ClientMain extends JFrame {

	public static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel variablePanel;

	private static ClientMain instance = null;
	private CommonPanel commonPanel;

	public void setMainFrame() {

		getContentPane().setLayout(new BorderLayout(0, 0));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ũ�� ����.
		this.setSize(1280, 800);

		// ���� �г� ��ȯ.
		loadCommonPanel();
		// ù��° ȭ�� ���.
		loadAdvancedReplacementPanel();

		this.setLocation(ScreenSize.width / 2 - this.getSize().width / 2,
				ScreenSize.height / 2 - this.getSize().height / 2);
		// ������ �����ϴ°� ���߿� �ϵ���..
		this.setResizable(false);
		this.setVisible(true);

	}

	public static ClientMain getInstance() {

		if (instance == null) {
			instance = new ClientMain();
		}

		return instance;
	}

	public CommonPanel getCommonPanel() {
		return commonPanel;
	}

	private void loadCommonPanel() {

		commonPanel = new CommonPanel();

		getContentPane().add(commonPanel, BorderLayout.CENTER);

	}

	public JPanel getVariablePanel() {
		return variablePanel;
	}

	public void loadAdvancedReplacementPanel() {

		variablePanel = AdvancedReplacementOperation.getInstance().getGUIadvancedReplacementPanel();
		// variablePanel =
		// GUIadvancedRepalcementPanel.getGUIadvancedReplecementPanel();
		commonPanel.addVariablePanel(variablePanel);

	}

	public void removeVariablePanel() {

		// commmonPanel�� ������ �ִ� Jpanel�� Variable Panel�� �ö��ִ� ��Ȳ�̶�, CommonPanel
		// ���ο��� ���� ����������Ѵ�.
		commonPanel.removeVariablePanel(variablePanel);

	}

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

	public boolean trySignIn(String stringID, char[] password) {

		boolean result = false;
		
		
		
		
		if(result){
			ClientMain clientMain = new ClientMain();

			clientMain.setMainFrame();
		}else{
			
			JOptionPane.showMessageDialog(null, "ID / Password ����ġ");
			
		}

		return result;
	}

}
