import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TabPanel extends JPanel{
	
	private JButton advancedReplacementBtn;
	private JButton repairBtn;
	private JButton _POReturnBtn;
	private JButton venderBtn;
	private JButton caseNumberBtn;
	private JButton backToStockBtn;
	
	public TabPanel(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		advancedReplacementBtn = new JButton("<Html>Advanced <br/>Replacement<Html>");
		add(advancedReplacementBtn);
		
		repairBtn = new JButton("<Html>Repair<Html>");
		add(repairBtn);
		
		_POReturnBtn = new JButton("<Html>PO <br/>Return<Html>");
		add(_POReturnBtn);
		
		venderBtn = new JButton("<Html>Vender<Html>");
		add(venderBtn);
		
		caseNumberBtn = new JButton("<Html>Case <br/>number<Html>");
		add(caseNumberBtn);
		
		backToStockBtn = new JButton("<Html>back to <br/>Stock<Html>");
		backToStockBtn.setPreferredSize(new Dimension(40, 40));
		add(backToStockBtn);
	}

}
