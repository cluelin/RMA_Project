package AdvancedReplacement;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Communication.Communication;
import Default.ConnectionSocket;

public class AdvancedReplacementOperation {

	static GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel.getInstance();

	Communication commnunication = Communication.getInstance();

	public static AdvancedReplacementOperation instance = null;

	JSONParser jsonParser = new JSONParser();

	private AdvancedReplacementOperation() {

		SaveBtnListener saveBtnListener = new SaveBtnListener();

		// GUI에 Action Listener 추가.
		guiAdvancedRepalcementPanel.getSaveBtn().addActionListener(saveBtnListener);
		guiAdvancedRepalcementPanel.getAttachFileBtn().addActionListener(saveBtnListener);

		// Change Tab key function in GUI TextArea
		setTabAdaptor();

		setComboBoxListener();

		// 클라이언트가 서버와 연결이 되면 제일 먼저 RMAnumber를 preserve해서 가지고 온다.
		String rmaNumber = commnunication.getRMAnumberFromServer();
		setRMAnumber(rmaNumber);

	}

	public static AdvancedReplacementOperation getInstance() {

		if (instance == null) {
			instance = new AdvancedReplacementOperation();
		}

		return instance;
	}

	public void clearField() {

		String rmaNumber = guiAdvancedRepalcementPanel.getTxtRMAnumber().getText().toString();

		boolean rmaNumberAlreadyUsed = Communication.getInstance().rmaNumberAlreadyUsed(rmaNumber);

		if (rmaNumberAlreadyUsed) {
			rmaNumber = commnunication.getRMAnumberFromServer();
			guiAdvancedRepalcementPanel.getTxtRMAnumber().setText(rmaNumber);
		}

		guiAdvancedRepalcementPanel.getTxtCompanyName().setSelectedItem("");
		guiAdvancedRepalcementPanel.getTxtSiteName().setSelectedItem("");
		guiAdvancedRepalcementPanel.clearCompanyDetail();
		guiAdvancedRepalcementPanel.clearHistoryPanel();
		guiAdvancedRepalcementPanel.clearItemTable();

		guiAdvancedRepalcementPanel.getTxtContents().setText("");
		guiAdvancedRepalcementPanel.getTxtBillTo().setText("");
		guiAdvancedRepalcementPanel.getTxtShipTo().setText("");
		guiAdvancedRepalcementPanel.getTxtTrackingNumber().setText("");

	}

	private void setComboBoxListener() {

		ComboBoxListener comboBoxListener = new ComboBoxListener();

		JComboBox companyName = guiAdvancedRepalcementPanel.getTxtCompanyName();

		if (companyName.getEditor().getEditorComponent() instanceof JTextComponent) {
			JTextComponent companyNameComponent = (JTextComponent) companyName.getEditor().getEditorComponent();
			companyNameComponent.getDocument().putProperty("owner", "companyName");
			companyNameComponent.getDocument().addDocumentListener(comboBoxListener);

			companyNameComponent.addFocusListener(new FocusListener() {

				@Override

				public void focusGained(FocusEvent arg0) {

					if (companyNameComponent.getText().length() >= 0) {

						companyName.setPopupVisible(true);

					}

				}

				@Override

				public void focusLost(FocusEvent arg0) {

				}

			});

		}

		JComboBox siteName = guiAdvancedRepalcementPanel.getTxtSiteName();

		if (siteName.getEditor().getEditorComponent() instanceof JTextComponent) {
			JTextComponent siteNameComponent = (JTextComponent) siteName.getEditor().getEditorComponent();
			siteNameComponent.getDocument().putProperty("owner", "siteName");
			siteNameComponent.getDocument().addDocumentListener(comboBoxListener);

			siteNameComponent.addFocusListener(new FocusListener() {

				@Override

				public void focusGained(FocusEvent arg0) {

					if (siteNameComponent.getText().length() >= 0) {

						siteName.setPopupVisible(true);

					}

				}

				@Override

				public void focusLost(FocusEvent arg0) {

				}

			});

		}

		JComboBox itemComboBox = guiAdvancedRepalcementPanel.getItemComboBox();

		if (itemComboBox.getEditor().getEditorComponent() instanceof JTextComponent) {
			JTextComponent itemNameComponent = (JTextComponent) itemComboBox.getEditor().getEditorComponent();
			itemNameComponent.getDocument().putProperty("owner", "itemName");
			itemNameComponent.getDocument().addDocumentListener(comboBoxListener);

			itemNameComponent.addFocusListener(new FocusListener() {

				@Override

				public void focusGained(FocusEvent arg0) {

					if (itemNameComponent.getText().length() > 0) {

						itemComboBox.setPopupVisible(true);

					}

				}

				@Override

				public void focusLost(FocusEvent arg0) {

				}

			});

		}

	}

	private void setTabAdaptor() {
		// overriding TextArea's Tab Key function.
		KeyAdapter tabAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					if (e.getModifiers() > 0) {
						((JTextArea) e.getSource()).transferFocusBackward();
					} else {
						((JTextArea) e.getSource()).transferFocus();
					}
					e.consume();
				}
			}
		};

		guiAdvancedRepalcementPanel.getTxtContents().addKeyListener(tabAdapter);
		guiAdvancedRepalcementPanel.getTxtBillTo().addKeyListener(tabAdapter);
		guiAdvancedRepalcementPanel.getTxtShipTo().addKeyListener(tabAdapter);
	}

	public GUIadvancedRepalcementPanel getGUIadvancedReplacementPanel() {
		return guiAdvancedRepalcementPanel;
	}

	// get RMA number from Server
	// public String getRMAnumberFromServer() {
	//
	// String rmaNumber = null;
	//
	// JSONObject obj = new JSONObject();
	// obj.put("Action", "requestRMAindex");
	//
	// // send request to serever
	// ConnectionSocket.printStream.println(obj.toJSONString());
	//
	// try {
	//
	// // 받아온 RMA number 설정.
	// String input = ConnectionSocket.bufferedReader.readLine();
	//
	// if (input != null) {
	// JSONObject jsonObject = (JSONObject) jsonParser.parse(input);
	// rmaNumber = "DA" + jsonObject.get("RMAindex").toString();
	// System.out.println("RMA index : " + rmaNumber);
	//
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return rmaNumber;
	//
	// }

	public void setRMAnumber(String rmaNumber) {
		guiAdvancedRepalcementPanel.getTxtRMAnumber().setText(rmaNumber);
	}

	

	public void saveRMAdetailToServer(){
		JSONObject objectToServer = new JSONObject();

		objectToServer.put("Action", "requestSaveRMAData");

		// company detail information.
		objectToServer.put("companyName",
				guiAdvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString());
		objectToServer.put("companyAddress", guiAdvancedRepalcementPanel.getTxtCompanyAddress().getText());
		objectToServer.put("companyCity", guiAdvancedRepalcementPanel.getTxtCompanyCity().getText());
		objectToServer.put("companyZipCode", guiAdvancedRepalcementPanel.getTxtCompanyZipCode().getText());
		objectToServer.put("companyPhone", guiAdvancedRepalcementPanel.getTxtCompanyPhone().getText());
		objectToServer.put("companyEmail", guiAdvancedRepalcementPanel.getTxtCompanyEmail().getText());

		// company에 종속적. 없으면 추가해야함.
		objectToServer.put("siteName",
				guiAdvancedRepalcementPanel.getTxtSiteName().getEditor().getItem().toString());

		// Item information
		System.out.println("0,0 : " + guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(0, 0));
		System.out.println("itemCount : " + guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount());
		// RMA ITEM TABLE 저장
		objectToServer.put("itemCount", guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount());

		for (int i = 0; i < guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount(); i++) {

			System.out.println("guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3) : "
					+ guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3));
			objectToServer.put("itemName" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0));
			objectToServer.put("itemSerialNumber" + i,
					guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1));
			objectToServer.put("itemDescription" + i,
					guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 2));
			objectToServer.put("itemPrice" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3));
		}

		objectToServer.put("rmaNumber", guiAdvancedRepalcementPanel.getTxtRMAnumber().getText());
		objectToServer.put("rmaDate", guiAdvancedRepalcementPanel.getTxtDate().getText());
		objectToServer.put("rmaOrderNumber", guiAdvancedRepalcementPanel.getTxtOrderNumber().getText());
		objectToServer.put("rmaContents", guiAdvancedRepalcementPanel.getTxtContents().getText());
		objectToServer.put("rmaBillTo", guiAdvancedRepalcementPanel.getTxtBillTo().getText());
		objectToServer.put("rmaShipTo", guiAdvancedRepalcementPanel.getTxtShipTo().getText());
		objectToServer.put("rmaTrackingNumber", guiAdvancedRepalcementPanel.getTxtTrackingNumber().getText());
		
		Communication.getInstance().saveRMAInformationToDatabase(objectToServer);
	}
}
