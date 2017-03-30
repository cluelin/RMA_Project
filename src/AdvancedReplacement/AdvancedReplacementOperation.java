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

import Default.ServerInformation;

public class AdvancedReplacementOperation implements ActionListener {

	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel
			.getGUIadvancedReplecementPanel();

	PrintStream printStream;
	BufferedReader bufferedReader;
	Socket client;

	DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>();

	public AdvancedReplacementOperation() {

		guiAdvancedRepalcementPanel.getSaveBtn().addActionListener(this);
		guiAdvancedRepalcementPanel.getAttachFileBtn().addActionListener(this);

		setTabAdaptor();
		setComboBoxListener();

		// 클라이언트가 서버와 연결이 되면 제일 먼저 RMAnumber를 preserve해서 가지고 온다.
		try {
			connectServer();
			getRMAindexFromDataBase();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("서버 연결 에러");
			// System.exit(0);
		} finally {
			closeConnection();
		}

	}

	private void setComboBoxListener() {
		JComboBox siteName = guiAdvancedRepalcementPanel.getTxtSiteName();

		if (siteName.getEditor().getEditorComponent() instanceof JTextComponent) {
			JTextComponent siteNameComponent = (JTextComponent) siteName.getEditor().getEditorComponent();
			siteNameComponent.getDocument().putProperty("owner", "siteName");
			siteNameComponent.getDocument().addDocumentListener(new ComboBoxListener());

		}

		JComboBox companyName = guiAdvancedRepalcementPanel.getTxtCompanyName();

		if (companyName.getEditor().getEditorComponent() instanceof JTextComponent) {
			JTextComponent companyNameComponent = (JTextComponent) companyName.getEditor().getEditorComponent();
			companyNameComponent.getDocument().putProperty("owner", "companyName");
			companyNameComponent.getDocument().addDocumentListener(new ComboBoxListener());
			

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

		JSONObject obj = new JSONObject();
		obj.put("Action", "requestRMAindex");

		// send request to serever
		printStream.println(obj.toJSONString());

		JSONParser jsonParser = new JSONParser();

		try {

			// 받아온 RMA number 설정.
			String input = bufferedReader.readLine();

			if (input != null) {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(input);
				String RMAnumber = "DA" + jsonObject.get("RMAindex").toString();
				System.out.println("RMA index : " + RMAnumber);

				guiAdvancedRepalcementPanel.getTxtRMAnumber().setText(RMAnumber);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void connectServer() throws Exception {

		// 서버로 전송

		System.out.println("Client : connecting...");
		client = new Socket(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
		System.out.println("Client : connected");
		printStream = new PrintStream(client.getOutputStream());
		bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

	}

	private void closeConnection() {

		try {
			client.close();
			System.out.println("Client : close");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// save RMA Information To Database
	private void saveRMAInformationToDatabase() {

		JSONObject obj = new JSONObject();

		obj.put("Action", "requestSaveRMAData");

		// company detail informatil. update로 변경사항이있을시에 업데이트 해줘야한다.
		obj.put("companyName", guiAdvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString());
		obj.put("companyAddress", guiAdvancedRepalcementPanel.getTxtCompanyAddress().getText());
		obj.put("companyCity", guiAdvancedRepalcementPanel.getTxtCompanyCity().getText());
		obj.put("companyZipCode", guiAdvancedRepalcementPanel.getTxtCompanyZipCode().getText());
		obj.put("companyPhone", guiAdvancedRepalcementPanel.getTxtCompanyPhone().getText());
		obj.put("companyEmail", guiAdvancedRepalcementPanel.getTxtCompanyEmail().getText());

		// company에 종속적. 없으면 추가해야함.
		obj.put("siteName", guiAdvancedRepalcementPanel.getTxtSiteName().getEditor().getItem().toString());

		System.out.println("0,0 : " + guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(0, 0));

		obj.put("rmaNumber", guiAdvancedRepalcementPanel.getTxtRMAnumber().getText());
		obj.put("rmaDate", guiAdvancedRepalcementPanel.getTxtDate().getText());
		obj.put("rmaOrderNumber", guiAdvancedRepalcementPanel.getTxtOrderNumber().getText());
		obj.put("rmaContents", guiAdvancedRepalcementPanel.getTxtContents().getText());
		obj.put("rmaBillTo", guiAdvancedRepalcementPanel.getTxtBillTo().getText());
		obj.put("rmaShipTo", guiAdvancedRepalcementPanel.getTxtShipTo().getText());
		obj.put("rmaTrackingNumber", guiAdvancedRepalcementPanel.getTxtTrackingNumber().getText());

		// RMA ITEM TABLE 저장
		obj.put("itemCount", guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount());

		for (int i = 0; i < guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount(); i++) {
			obj.put("itemName" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0));
			obj.put("serialNumber" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1));
		}

		printStream.println(obj.toJSONString());
		System.out.println("Update RMA 수행");

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getSaveBtn()) {
			// save 버튼

			try {
				connectServer();
				saveRMAInformationToDatabase();
				closeConnection();
				connectServer();
				getRMAindexFromDataBase();
				closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("서버 연결 에러");
				System.exit(0);
			} finally {
				closeConnection();
			}

		} else if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getAttachFileBtn()) {

			// 파일 첨부 버튼
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
