package Default;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LeftTabPanel extends JPanel implements ActionListener {

	private JButton advancedReplacementBtn;
	private JButton repairBtn;
	private JButton _POReturnBtn;
	private JButton venderBtn;
	private JButton caseNumberBtn;
	private JButton backToStockBtn;

	public LeftTabPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new GridLayout(0, 1, 0, 0));

		advancedReplacementBtn = new JButton("<Html>Advanced <br/>Replacement<Html>");
		advancedReplacementBtn.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if( e.getSource() == advancedReplacementBtn){
			
			ClientMain.getInstance().loadAdvancedReplacementPanel();
		}else if( e.getSource() == repairBtn){
			//open repair Panel.
		}else if( e.getSource() == _POReturnBtn){
			//open PO Return Panel
		}else if( e.getSource() == venderBtn){
			//open venderPanel
		}else if(e.getSource() == caseNumberBtn){
			//open caseNumber Panel
		}else if(e.getSource() == backToStockBtn){
			//open back to stock Panel
		}
		

	}

}
