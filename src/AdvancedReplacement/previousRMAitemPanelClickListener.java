package AdvancedReplacement;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.json.simple.JSONObject;

import Communication.Communication;

public class previousRMAitemPanelClickListener implements MouseListener {

	String rmaNumber;
	static JPanel previousSelected = null;
	static Color previousBackground = null;

	public previousRMAitemPanelClickListener(String rmaNumber) {
		// TODO Auto-generated constructor stub

		this.rmaNumber = rmaNumber;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("마우스 클릭됨 : " + rmaNumber);
		
		System.out.println("클릭된 위치 :  " + e.getSource());
		
		
		
		if(previousSelected != null){
			previousSelected.setBackground(previousBackground);
		}
		
		previousSelected = (JPanel)e.getSource();
		previousBackground = previousSelected.getBackground();

		
		previousSelected.setBackground(new Color(205,205,205));
				

		previousSelected.revalidate();
		previousSelected.repaint();

		JSONObject RMADetailJSON = Communication.getInstance().getRMAdetailFromServer(rmaNumber);

		//set RMA information on GUI. 
		GUIadvancedRepalcementPanel.getInstance().setRMADetail(RMADetailJSON);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	

}
