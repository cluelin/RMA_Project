package AdvancedReplacement;

import java.awt.Desktop;
import java.awt.TextArea;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import AdvancedReplacement.ItemTable.MyTableModel;
import Communication.Communication;
import SignInAndUp.UserInfo;

public class AdvancedReplacementOperation {

	static GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel.getInstance();

	Communication commnunication = Communication.getInstance();

	private static AdvancedReplacementOperation instance = null;

	JSONParser jsonParser = new JSONParser();

	private AdvancedReplacementOperation() {

		SaveBtnListener saveBtnListener = new SaveBtnListener();

		// GUI�� Action Listener �߰�.
		guiAdvancedRepalcementPanel.getSaveBtn().addActionListener(saveBtnListener);
		guiAdvancedRepalcementPanel.getAttachFileBtn().addActionListener(saveBtnListener);

		// Change Tab key function in GUI TextArea
		setTabAdaptor();

		setComboBoxListener();

		// Ŭ���̾�Ʈ�� ������ ������ �Ǹ� ���� ���� RMAnumber�� preserve�ؼ� ������ �´�.
		String rmaNumber = commnunication.getRMAnumberFromServer();
		setRMAnumber(rmaNumber);

	}

	public static AdvancedReplacementOperation getInstance() {

		if (instance == null) {
			instance = new AdvancedReplacementOperation();
		}

		return instance;
	}

	// ����Ǿ��ִ� ������ ��������.
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
		guiAdvancedRepalcementPanel.clearRMADetail();

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
		
		objectToServer.put("USER_ID", UserInfo.getInterface().getUserID());

		// company detail information.
		objectToServer.put("companyName",
				guiAdvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString());
		objectToServer.put("companyAddress", guiAdvancedRepalcementPanel.getTxtCompanyAddress().getText());
		objectToServer.put("companyCity", guiAdvancedRepalcementPanel.getTxtCompanyCity().getText());
		objectToServer.put("companyZipCode", guiAdvancedRepalcementPanel.getTxtCompanyZipCode().getText());
		objectToServer.put("companyPhone", guiAdvancedRepalcementPanel.getTxtCompanyPhone().getText());
		objectToServer.put("companyEmail", guiAdvancedRepalcementPanel.getTxtCompanyEmail().getText());

		// company�� ������. ������ �߰��ؾ���.
		objectToServer.put("siteName", guiAdvancedRepalcementPanel.getTxtSiteName().getEditor().getItem().toString());

		int itemCount = getValidItemRowCount();

		// Item information
		System.out.println("itemCount : " + itemCount);

		// RMA ITEM TABLE ����
		{
			objectToServer.put("itemCount", itemCount);

			for (int i = 0; i < itemCount; i++) {

				objectToServer.put("itemName" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0));
				objectToServer.put("itemSerialNumber" + i,
						guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1));

				if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 2) == null) {
					objectToServer.put("itemDescription" + i, "");
				} else {
					objectToServer.put("itemDescription" + i,
							guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 2));
				}

				if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3) == null) {
					objectToServer.put("itemPrice" + i, 0);
				} else {
					objectToServer.put("itemPrice" + i,
							guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3));
				}

				if ((boolean) guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 4)) {
					objectToServer.put("itemReceive" + i, true);
				} else {
					objectToServer.put("itemReceive" + i, false);
				}

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

		// Company Name �ۼ� ���� Ȯ��
		if (guiAdvancedRepalcementPanel.getTxtCompanyName().getSelectedItem() == null
				|| guiAdvancedRepalcementPanel.getTxtCompanyName().getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "���۴� �̸��� �����ּ���");
			pass = false;
			return pass;

			// Site Name �ۼ� ���� Ȯ��
		} else if (guiAdvancedRepalcementPanel.getTxtSiteName().getSelectedItem() == null
				|| guiAdvancedRepalcementPanel.getTxtSiteName().getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "����Ʈ �̸��� �����ּ���");
			pass = false;
			return pass;
		}

		// item table ����
		JSONObject itemTableObjectToServer = new JSONObject();
		int rowCount = getValidItemRowCount();

		itemTableObjectToServer.put("Action", "validate");
		itemTableObjectToServer.put("itemCount", rowCount);

		for (int i = 0; i < rowCount; i++) {

			if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1) == null
					|| guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1).toString().equals("")) {
				JOptionPane.showMessageDialog(null, "�ø��� �ѹ��� ��ȿ���� �ʽ��ϴ�. ");
				pass = false;
				return pass;
			}

			// item table price check.
			try {
				Integer.parseInt(guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3).toString());
			} catch (NumberFormatException e) {

				if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3).toString() != null) {

					// null �϶��� 0���� ����.
					JOptionPane.showMessageDialog(null, "Price�� ���ڰ� �ƴմϴ�.");
					pass = false;
					return pass;
				}

			} catch (NullPointerException e) {
				// price�� ���� �������� 0���� �⺻ ����
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
			JOptionPane.showMessageDialog(null, "item Name�� list�� ���������ʽ��ϴ�.");
			pass = false;
			return pass;
		}

		// if (itemTableValidObject.get("itemSerialValidation") == null) {
		//
		// } else if ((boolean) itemTableValidObject.get("itemSerialValidation")
		// == false) {
		// JOptionPane.showMessageDialog(null, "SerialNumber�� �ߺ��˴ϴ�.");
		// pass = false;
		// return pass;
		// }

		// item, serial validate check. �߰��ؾ���.

		return pass;
	}

	// Item Name �� ä�����ִ� �� ���� return
	public int getValidItemRowCount() {
		//
		// int rowCount = 0;
		//
		// for (int i = 0; i <
		// guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount(); i++) {
		//
		// if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0)
		// == null) {
		//
		// rowCount = i;
		// break;
		// }
		//
		// }
		//
		// System.out.println("�� �� : " + rowCount);
		//
		// return rowCount;

		return ((MyTableModel) guiAdvancedRepalcementPanel.get_RMAitemTable().getModel()).getValidRowCount();

	}

	public void printDocx() {

		String fileName = EditDocx();

		try {
			// Desktop.getDesktop().print(new File(fileName));
		} catch (Exception e) {

		}
	}

	public String EditDocx() {

		String fileName = null;

		try {

			String templetFileName = "COMMERCIAL INVOICE - BLANK.docx";

			XWPFDocument docx = new XWPFDocument(new FileInputStream(templetFileName));

			for (XWPFTable tbl : docx.getTables()) {
				for (XWPFTableRow row : tbl.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							for (XWPFRun r : p.getRuns()) {
								String text = r.getText(0);

								System.out.println(text);

								if (text == null)
									continue;

								if (text.contains("#DATE#")) {
									text = text.replace("#DATE#", guiAdvancedRepalcementPanel.getTxtDate().getText());
									r.setText(text, 0);
								} else if (text.contains("#RMA_NUMBER#")) {
									text = text.replace("#RMA_NUMBER#",
											guiAdvancedRepalcementPanel.getTxtRMAnumber().getText());
									r.setText(text, 0);
								} else if (text.contains("#Biling#")) {

									text = text.replace("#Biling#", "");
									r.setText(text, 0);

									for (String trimText : guiAdvancedRepalcementPanel.getTxtBillTo().getText()
											.toString().split("\n")) {

										r.setText(trimText.trim());
										r.addBreak();

									}

								} else if (text.contains("#Shipping#")) {
									text = text.replace("#Shipping#", "");
									r.setText(text, 0);

									for (String trimText : guiAdvancedRepalcementPanel.getTxtShipTo().getText()
											.toString().split("\n")) {

										r.setText(trimText.trim());
										r.addBreak();

									}

								} else if (text.contains("#price#")) {

									text = text.replace("#price#", "" +getTotalPrice());
									r.setText(text, 0);

								}
							}
						}
					}
				}
			}

			File dir = new File("Attached");

			if (!dir.exists()) {
				try {
					dir.mkdirs();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}

			fileName = dir + File.separator + guiAdvancedRepalcementPanel.getTxtRMAnumber().getText() + templetFileName;

			docx.write(new FileOutputStream(fileName));

			System.out.println("total price : " + getTotalPrice());

		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
		} catch (IOException ioE) {
			ioE.printStackTrace();
		}

		return fileName;

	}

	public double getTotalPrice() {

		double totalPrice = 0.00;

		System.out.println("total price : " + totalPrice);

		for (int i = 0; i < getValidItemRowCount(); i++) {

			totalPrice += (int) guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3);
		}

		return totalPrice;

	}

}
