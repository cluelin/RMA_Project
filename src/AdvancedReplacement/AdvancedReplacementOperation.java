package AdvancedReplacement;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Communication.Communication;
import Communication.ConnectionSocket;

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

	//저장되어있는 모든것을 지워버림. 
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
		guiAdvancedRepalcementPanel.getTxtOrderNumber().setText("");

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

					if (companyNameComponent.getText().length() > 0) {

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

	public void setRMAnumber(String rmaNumber) {
		guiAdvancedRepalcementPanel.getTxtRMAnumber().setText(rmaNumber);
	}

	public void saveRMAdetailToServer() {
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
		objectToServer.put("siteName", guiAdvancedRepalcementPanel.getTxtSiteName().getEditor().getItem().toString());

		int itemCount = getValidItemRowCount();

		// Item information
		System.out.println("0,0 : " + guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(0, 0));
		System.out.println("itemCount : " + itemCount);

		// RMA ITEM TABLE 저장
		objectToServer.put("itemCount", itemCount);

		for (int i = 0; i < itemCount; i++) {

			objectToServer.put("itemName" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0));
			objectToServer.put("itemSerialNumber" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1));

			if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 2) == null) {
				objectToServer.put("itemDescription" + i, "");
			} else {
				objectToServer.put("itemDescription" + i,
						guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 2));
			}

			if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3) == null) {
				objectToServer.put("itemPrice" + i, 0);
			} else {
				objectToServer.put("itemPrice" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3));
			}

		}

		objectToServer.put("rmaNumber", guiAdvancedRepalcementPanel.getTxtRMAnumber().getText());
		objectToServer.put("rmaDate", guiAdvancedRepalcementPanel.getTxtDate().getText());
		objectToServer.put("rmaOrderNumber", guiAdvancedRepalcementPanel.getTxtOrderNumber().getText());
		objectToServer.put("rmaContents", guiAdvancedRepalcementPanel.getTxtContents().getText());
		objectToServer.put("rmaBillTo", guiAdvancedRepalcementPanel.getTxtBillTo().getText());
		objectToServer.put("rmaShipTo", guiAdvancedRepalcementPanel.getTxtShipTo().getText());
		objectToServer.put("rmaTrackingNumber", guiAdvancedRepalcementPanel.getTxtTrackingNumber().getText());

		Communication.getInstance().saveRMAInformationToServer(objectToServer);
	}

	public boolean validityCheck() {
		boolean pass = true;

		// Company Name 작성 여부 확인
		if (guiAdvancedRepalcementPanel.getTxtCompanyName().getSelectedItem() == null
				|| guiAdvancedRepalcementPanel.getTxtCompanyName().getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "컴퍼니 이름을 적어주세요");
			pass = false;
			return pass;

			// Site Name 작성 여부 확인
		} else if (guiAdvancedRepalcementPanel.getTxtSiteName().getSelectedItem() == null
				|| guiAdvancedRepalcementPanel.getTxtSiteName().getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "사이트 이름을 적어주세요");
			pass = false;
			return pass;
		}

		// item table 검증
		JSONObject itemTableObjectToServer = new JSONObject();
		int rowCount = getValidItemRowCount();

		itemTableObjectToServer.put("Action", "validate");
		itemTableObjectToServer.put("itemCount", rowCount);

		for (int i = 0; i < rowCount; i++) {

			if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1) == null
					|| guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1).toString().equals("")) {
				JOptionPane.showMessageDialog(null, "시리얼 넘버가 유효하지 않습니다. ");
				pass = false;
				return pass;
			}

			// item table price check.
			try {
				System.out.println(guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3).toString());
				Integer.parseInt(guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3).toString());
			} catch (NumberFormatException e) {

				if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3).toString() != null) {

					// null 일때는 0으로 설정.
					JOptionPane.showMessageDialog(null, "Price가 숫자가 아닙니다.");
					pass = false;
					return pass;
				}

			} catch (NullPointerException e) {
				// price의 값이 없을때는 0으로 기본 설정
				guiAdvancedRepalcementPanel.get_RMAitemTable().setValueAt(0, i, 3);
			}

			itemTableObjectToServer.put("itemName" + i,
					guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0));
			itemTableObjectToServer.put("itemSerialNumber" + i,
					guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1));

		}

		JSONObject itemTableValidObject = commnunication.getItemTableValidObject(itemTableObjectToServer);

		if (itemTableValidObject.get("itemNameValidation") == null) {

		} else if ((boolean) itemTableValidObject.get("itemNameValidation") == false) {
			JOptionPane.showMessageDialog(null, "item Name이 list에 존재하지않습니다.");
			pass = false;
			return pass;
		}

		if (itemTableValidObject.get("itemSerialValidation") == null) {

		} else if ((boolean) itemTableValidObject.get("itemSerialValidation") == false) {
			JOptionPane.showMessageDialog(null, "SerialNumber가 중복됩니다.");
			pass = false;
			return pass;
		}

		// item, serial validate check. 추가해야함.

		return pass;
	}

	// Item Name 이 채워져있는 행 수를 return
	public int getValidItemRowCount() {

		int rowCount = 0;

		for (int i = 0; i < guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount(); i++) {

			if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0) == null) {

				rowCount = i;
				break;
			}

		}

		System.out.println("행 수 : " + rowCount);

		return rowCount;

	}
}
