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
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import AdvancedReplacement.AdvancedReplacementOperation;
import AdvancedReplacement.GUIadvancedRepalcementPanel;

import java.awt.Component;
import javax.swing.border.EmptyBorder;

public class ClientMain extends JFrame {

	public static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel variablePanel;

	private static ClientMain instance = null;
	private CommonPanel commonPanel;

	private ClientMain() {
		super("RMA");
		
		
		// setSize(new Dimension(1280, 800));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 크기 지정.
		this.setSize(1280, 800);

				
		
		// 공통 패널 소환.
		loadCommonPanel();
		// 첫번째 화면 출력.
		loadAdvancedReplacementPanel();
		
		
		this.setLocation(ScreenSize.width / 2 - this.getSize().width / 2,
				ScreenSize.height / 2 - this.getSize().height / 2);
		// 사이즈 변경하는건 나중에 하도록..
		this.setResizable(false);
		this.setVisible(true);

	}

	public static ClientMain getInstance() {
		
		if(instance == null){
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
	
	public JPanel getVariablePanel(){
		return variablePanel;
	}

	public void loadAdvancedReplacementPanel() {

		variablePanel = AdvancedReplacementOperation.getInstance().getGUIadvancedReplacementPanel();
//		variablePanel = GUIadvancedRepalcementPanel.getGUIadvancedReplecementPanel();
		commonPanel.addVariablePanel(variablePanel);

	}

	public void removeVariablePanel() {

		// commmonPanel의 하위에 있는 Jpanel에 Variable Panel이 올라가있는 상황이라서, CommonPanel
		// 내부에서 따로 제거해줘야한다.
		commonPanel.removeVariablePanel(variablePanel);

	}

	public static void main(String[] args) {
		
		ConnectionSocket.connectServer();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		    	System.out.println("종료 이벤트");
		    	ConnectionSocket.closeConnection();
		    }
		}));

		ClientMain layout = getInstance();
		

	}

}
