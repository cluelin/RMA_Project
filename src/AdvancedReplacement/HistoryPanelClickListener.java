package AdvancedReplacement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Default.Client;

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

		JSONObject RMADetailJSON = getRMAdetailFromServer();

		GUIadvancedRepalcementPanel.getGUIadvancedReplecementPanel().setRMADetail(RMADetailJSON);
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

	private JSONObject getRMAdetailFromServer() {

		try {
//			Client.connectServer();

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestRMADetail");

			obj.put("rmaNumber", rmaNumber);

			Client.printStream.println(obj.toJSONString());

			JSONParser jsonParser = new JSONParser();

			String input;

			try {

				input = Client.bufferedReader.readLine();

				JSONObject RMADetailObject = (JSONObject) jsonParser.parse(input);

				return RMADetailObject;

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			Client.closeConnection();
		}

		return null;
	}

}
