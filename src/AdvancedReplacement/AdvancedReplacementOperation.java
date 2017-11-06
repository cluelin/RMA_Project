package AdvancedReplacement;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.print.DocFlavor;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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

	static private GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel.getInstance();

	Communication commnunication = Communication.getInstance();

	private static AdvancedReplacementOperation instance = null;

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

	// 저장되어있는 모든것을 지워버림.
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

	public JSONObject getRMAdetailJSONobj() {

		// 조건이 안맞으면 종료.
		if (!rmaInformationValidityCheck()) {

			return null;
		}

		JSONObject RMADetailObj = new JSONObject();

		RMADetailObj.put("Action", "requestSaveRMAData");

		RMADetailObj.put("USER_ID", UserInfo.getInterface().getUserID());

		// company detail information.
		RMADetailObj.put("companyName",
				guiAdvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString());
		RMADetailObj.put("companyAddress", guiAdvancedRepalcementPanel.getTxtCompanyAddress().getText());
		RMADetailObj.put("companyCity", guiAdvancedRepalcementPanel.getTxtCompanyCity().getText());
		RMADetailObj.put("companyZipCode", guiAdvancedRepalcementPanel.getTxtCompanyZipCode().getText());
		RMADetailObj.put("companyPhone", guiAdvancedRepalcementPanel.getTxtCompanyPhone().getText());
		RMADetailObj.put("companyEmail", guiAdvancedRepalcementPanel.getTxtCompanyEmail().getText());

		// company에 종속적. 없으면 추가해야함.
		RMADetailObj.put("siteName", guiAdvancedRepalcementPanel.getTxtSiteName().getEditor().getItem().toString());

		int itemCount = getValidItemRowCount();

		// Item information
		System.out.println("itemCount : " + itemCount);

		// RMA ITEM TABLE 저장
		{
			RMADetailObj.put("itemCount", itemCount);

			for (int i = 0; i < itemCount; i++) {

				RMADetailObj.put("itemName" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0));
				RMADetailObj.put("itemSerialNumber" + i,
						guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1));

				if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 2) == null) {
					RMADetailObj.put("itemDescription" + i, "");
				} else {
					RMADetailObj.put("itemDescription" + i,
							guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 2));
				}

				if (guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3) == null) {
					RMADetailObj.put("itemPrice" + i, 0);
				} else {
					RMADetailObj.put("itemPrice" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 3));
				}

				if ((boolean) guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 4)) {
					RMADetailObj.put("itemReceive" + i, true);
				} else {
					RMADetailObj.put("itemReceive" + i, false);
				}

			}
		}

		RMADetailObj.put("rmaNumber", guiAdvancedRepalcementPanel.getTxtRMAnumber().getText());
		RMADetailObj.put("rmaDate", guiAdvancedRepalcementPanel.getTxtDate().getText());
		RMADetailObj.put("rmaOrderNumber", guiAdvancedRepalcementPanel.getTxtOrderNumber().getText());
		RMADetailObj.put("rmaContents", guiAdvancedRepalcementPanel.getTxtContents().getText());
		RMADetailObj.put("rmaBillTo", guiAdvancedRepalcementPanel.getTxtBillTo().getText());
		RMADetailObj.put("rmaShipTo", guiAdvancedRepalcementPanel.getTxtShipTo().getText());
		RMADetailObj.put("rmaTrackingNumber", guiAdvancedRepalcementPanel.getTxtTrackingNumber().getText());

		// String companyName =
		// guiAdvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString();
		// String companyAddress =
		// guiAdvancedRepalcementPanel.getTxtCompanyAddress().getText();
		// String companyCity =
		// guiAdvancedRepalcementPanel.getTxtCompanyCity().getText();
		// String companyZipCode =
		// guiAdvancedRepalcementPanel.getTxtCompanyZipCode().getText();
		// String companyPhone =
		// guiAdvancedRepalcementPanel.getTxtCompanyPhone().getText();
		// String companyEmail =
		// guiAdvancedRepalcementPanel.getTxtCompanyEmail().getText();
		// String siteName =
		// guiAdvancedRepalcementPanel.getTxtSiteName().getEditor().getItem().toString();
		//
		// String rmaNumber =
		// guiAdvancedRepalcementPanel.getTxtRMAnumber().getText();
		// String rmaDate = guiAdvancedRepalcementPanel.getTxtDate().getText();
		// String rmaOrderNumber =
		// guiAdvancedRepalcementPanel.getTxtOrderNumber().getText();
		// String rmaContents =
		// guiAdvancedRepalcementPanel.getTxtContents().getText();
		// String rmaBillTo =
		// guiAdvancedRepalcementPanel.getTxtBillTo().getText();
		// String rmaShipTo =
		// guiAdvancedRepalcementPanel.getTxtShipTo().getText();
		// String rmaTrackingNumber =
		// guiAdvancedRepalcementPanel.getTxtTrackingNumber().getText();

		return RMADetailObj;

	}

	public boolean rmaInformationValidityCheck() {
		// 현재 RMA가 저장될 수 있는 상태인지 확인.

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

		// if (itemTableValidObject.get("itemSerialValidation") == null) {
		//
		// } else if ((boolean) itemTableValidObject.get("itemSerialValidation")
		// == false) {
		// JOptionPane.showMessageDialog(null, "SerialNumber가 중복됩니다.");
		// pass = false;
		// return pass;
		// }

		// item, serial validate check. 추가해야함.

		return pass;
	}

	// Item Name 이 채워져있는 행 수를 return
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
		// System.out.println("행 수 : " + rowCount);
		//
		// return rowCount;

		return ((MyTableModel) guiAdvancedRepalcementPanel.get_RMAitemTable().getModel()).getValidRowCount();

	}

	public void printDocx() {

		if (!rmaInformationValidityCheck()) {
			return;
		}

		String fileName = EditDocx();

		try {
//
//			PrinterJob pj = PrinterJob.getPrinterJob();
//			if (pj.printDialog()) {
//				try {
//					pj.print();
//				} catch (PrinterException exc) {
//					System.out.println(exc);
//				}
//			}

			
			Desktop.getDesktop().print(new File(fileName));

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

									text = text.replace("#price#", "" + getTotalPrice());
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

	public JList<String> getAttachmentList() {
		return guiAdvancedRepalcementPanel.getAttachmentList();
	}

	public String getTxtRMAnumber() {
		return guiAdvancedRepalcementPanel.getTxtRMAnumber().getText();
	}

	public void clearCompanyDetail() {

		guiAdvancedRepalcementPanel.clearCompanyDetail();
	}

	public void setCompanyDetail(String address, String city, String zipCode, String phone, String email) {

		guiAdvancedRepalcementPanel.setCompanyDetail(address, city, zipCode, phone, email);

	}

	public void setBillToArea(String companyName, String address, String city, String zipCode, String phone) {

		guiAdvancedRepalcementPanel.setBillToArea(companyName, address, city, zipCode, phone);

	}

	public JComboBox getTxtCompanyName() {
		return guiAdvancedRepalcementPanel.getTxtCompanyName();
	}

	public String getSiteName() {

		JComboBox owner = guiAdvancedRepalcementPanel.getTxtSiteName();

		Component component = owner.getEditor().getEditorComponent();
		JTextComponent textComponent = (JTextComponent) component;

		String siteName = textComponent.getText();

		return siteName;

	}

	public JComboBox getComboSiteName() {

		return guiAdvancedRepalcementPanel.getTxtSiteName();

	}

	public JComboBox getItemComboBox() {

		return guiAdvancedRepalcementPanel.getItemComboBox();

	}

	public String getCompanyName() {

		return guiAdvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString();

	}

	public JTable get_RMAitemTable() {

		return guiAdvancedRepalcementPanel.get_RMAitemTable();

	}

}
