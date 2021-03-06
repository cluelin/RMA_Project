package Default;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import AdvancedReplacement.AdvancedReplacementOperation;
import AdvancedReplacement.GUIadvancedRepalcementPanel;
import AdvancedReplacement.function.Function;

public class DocumentControlPanel extends JPanel implements ActionListener {

	private JButton docNewBtn;
	private JButton docSaveBtn;
	private JButton docClearBtn;
	private JButton docPrintBtn;
	private JButton docOpenCloseBtn;
	private JButton docSearchBtn;

	private static DocumentControlPanel instance = null;

	private DocumentControlPanel() {
		addBtnOnPanel();
		addBtnListener();
	}

	public static DocumentControlPanel getInstance() {

		if (instance == null) {
			instance = new DocumentControlPanel();
		}

		return instance;
	}

	private void addBtnOnPanel() {

		setPreferredSize(new Dimension(10, 50));
		setLayout(new GridLayout(1, 0, 0, 0));

		docNewBtn = new JButton("New");
		add(docNewBtn);

		docSaveBtn = new JButton("Save");
		add(docSaveBtn);

		docClearBtn = new JButton("clear");
		add(docClearBtn);

		docSearchBtn = new JButton("Search");
		add(docSearchBtn);

		docPrintBtn = new JButton("Print");
		add(docPrintBtn);

		docOpenCloseBtn = new JButton("Open/Close");
		add(docOpenCloseBtn);
	}

	private void addBtnListener() {

		docClearBtn.addActionListener(this);
		docPrintBtn.addActionListener(this);
		docSaveBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		AdvancedReplacementOperation advancedReplacementOperation = AdvancedReplacementOperation.getInstance();
		

		if (MainFrame.getInstance().getVariablePanel() instanceof GUIadvancedRepalcementPanel) {

			if (e.getSource() == docClearBtn) {
				advancedReplacementOperation.clearField();
			}else if(e.getSource() == docPrintBtn){
				
				advancedReplacementOperation.printDocx();
			}else if(e.getSource() == docSaveBtn){
				
				Function.getInstance().saveRMADetailDataToServer();
			}
		}

	}

}
