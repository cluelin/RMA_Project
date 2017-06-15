package Default;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import AdvancedReplacement.AdvancedReplacementOperation;

public class MainFrame extends JFrame {

	public static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static JPanel variablePanel;

	private static MainFrame instance = null;
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

	public static MainFrame getInstance() {

		if (instance == null) {
			instance = new MainFrame();
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

}
