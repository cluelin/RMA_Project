package Default;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CommonPanel extends JPanel{
	
	private JPanel documentControlPanel;
	private JPanel tabPanel;
	private JPanel panel_6;

	public CommonPanel(){
		setPreferredSize(new Dimension(1280, 800));
		setLayout(new BorderLayout(0, 0));
		
		
		tabPanel = new LeftTabPanel();
		
		
		add(tabPanel, BorderLayout.WEST);
		
		
		
		
		panel_6 = new JPanel();
		add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		documentControlPanel = DocumentControlPanel.getInstance();
		panel_6.add(documentControlPanel, BorderLayout.NORTH);
	}
	
	public void addVariablePanel(JPanel variablePanel){
		
		panel_6.add(variablePanel, BorderLayout.CENTER);
		
	}
	
	public void removeVariablePanel(JPanel variablePanel){
		panel_6.remove(variablePanel);
		panel_6.invalidate();
		panel_6.validate();
		panel_6.repaint();
	}
}
