package AdvancedReplacement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Communication.Communication;
import Default.ConnectionSocket;

public class HistoryPanelClickListener implements MouseListener {

	String rmaNumber;

	public HistoryPanelClickListener(String rmaNumber) {
		// TODO Auto-generated constructor stub

		this.rmaNumber = rmaNumber;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("마우스 클릭됨 : " + rmaNumber);

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
