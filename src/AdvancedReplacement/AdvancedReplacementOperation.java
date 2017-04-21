package AdvancedReplacement;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Default.Client;
import Default.ServerInformation;

public class AdvancedReplacementOperation implements ActionListener {

	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel;

	public AdvancedReplacementOperation() {

		// GUI ����
		guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel.getGUIadvancedReplecementPanel();

		// GUI�� Action Listener �߰�.
		guiAdvancedRepalcementPanel.getSaveBtn().addActionListener(this);
		guiAdvancedRepalcementPanel.getAttachFileBtn().addActionListener(this);

		// Change Tab key function in GUI TextArea
		setTabAdaptor();

		setComboBoxListener();

		// Ŭ���̾�Ʈ�� ������ ������ �Ǹ� ���� ���� RMAnumber�� preserve�ؼ� ������ �´�.
		getRMAindexFromDataBase();

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

	// rma number setting
	private void getRMAindexFromDataBase() {

		Client.connectServer();

		System.out.println("RMA��ȣ");

		JSONObject obj = new JSONObject();
		obj.put("Action", "requestRMAindex");

		// send request to serever
		Client.printStream.println(obj.toJSONString());

		JSONParser jsonParser = new JSONParser();

		try {

			// �޾ƿ� RMA number ����.
			String input = Client.bufferedReader.readLine();

			if (input != null) {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(input);
				String RMAnumber = "DA" + jsonObject.get("RMAindex").toString();
				System.out.println("RMA index : " + RMAnumber);

				guiAdvancedRepalcementPanel.getTxtRMAnumber().setText(RMAnumber);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Client.closeConnection();

	}

	// save RMA Information To Database
	private void saveRMAInformationToDatabase() {

		try {
			// Client.connectServer();

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestSaveRMAData");

			// company detail informatil. update�� ��������������ÿ� ������Ʈ ������Ѵ�.
			obj.put("companyName", guiAdvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString());
			obj.put("companyAddress", guiAdvancedRepalcementPanel.getTxtCompanyAddress().getText());
			obj.put("companyCity", guiAdvancedRepalcementPanel.getTxtCompanyCity().getText());
			obj.put("companyZipCode", guiAdvancedRepalcementPanel.getTxtCompanyZipCode().getText());
			obj.put("companyPhone", guiAdvancedRepalcementPanel.getTxtCompanyPhone().getText());
			obj.put("companyEmail", guiAdvancedRepalcementPanel.getTxtCompanyEmail().getText());

			// company�� ������. ������ �߰��ؾ���.
			obj.put("siteName", guiAdvancedRepalcementPanel.getTxtSiteName().getEditor().getItem().toString());

			System.out.println("0,0 : " + guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(0, 0));

			obj.put("rmaNumber", guiAdvancedRepalcementPanel.getTxtRMAnumber().getText());
			obj.put("rmaDate", guiAdvancedRepalcementPanel.getTxtDate().getText());
			obj.put("rmaOrderNumber", guiAdvancedRepalcementPanel.getTxtOrderNumber().getText());
			obj.put("rmaContents", guiAdvancedRepalcementPanel.getTxtContents().getText());
			obj.put("rmaBillTo", guiAdvancedRepalcementPanel.getTxtBillTo().getText());
			obj.put("rmaShipTo", guiAdvancedRepalcementPanel.getTxtShipTo().getText());
			obj.put("rmaTrackingNumber", guiAdvancedRepalcementPanel.getTxtTrackingNumber().getText());

			// RMA ITEM TABLE ����
			obj.put("itemCount", guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount());

			for (int i = 0; i < guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount(); i++) {
				obj.put("itemName" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0));
				obj.put("serialNumber" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1));
			}

			Client.printStream.println(obj.toJSONString());
			System.out.println("Update RMA ����");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���� ���� ����");
			System.exit(0);
		} finally {
			// Client.closeConnection();
		}

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getSaveBtn()) {
			// save ��ư

			saveRMAInformationToDatabase();

			getRMAindexFromDataBase();

		} else if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getAttachFileBtn()) {

			// ���� ÷�� ��ư
			JFileChooser jFileChooser = new JFileChooser();
			int returnVal = jFileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getName());
				File selectedFile = jFileChooser.getSelectedFile();
				// selectedFile.renameTo(new File("copiedFIle." +
				// getFileExtension(selectedFile)));
				copyFile(selectedFile);

			}
		}

	}

	// Copy selected File to Default Location With Same File name.
	private void copyFile(File orignalFile) {

		try {
			FileInputStream fileInputStream = new FileInputStream(orignalFile);
			FileOutputStream fileOutputStream = new FileOutputStream(new File(orignalFile.getName()));

			// using FileChannel improve performance
			FileChannel fileChannelIn = fileInputStream.getChannel();
			FileChannel fileChannelOut = fileOutputStream.getChannel();

			long size = fileChannelIn.size();
			fileChannelIn.transferTo(0, size, fileChannelOut);

			fileChannelOut.close();
			fileChannelIn.close();

			fileOutputStream.close();
			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
