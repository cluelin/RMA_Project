package Default;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DocumentControlPanel extends JPanel{
	
	private JButton docNewBtn;
	private JButton docSaveBtn;
	private JButton docClearBtn;
	private JButton docPrintBtn;
	private JButton docOpenCloseBtn;
	private JButton docSearchBtn;
	
	public DocumentControlPanel(){
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

}
