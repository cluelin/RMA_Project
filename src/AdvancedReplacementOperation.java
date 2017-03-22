import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class AdvancedReplacementOperation implements ActionListener, DocumentListener {

	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel;

	PrintStream printStream;
	BufferedReader bufferedReader;
	Socket client;

	public AdvancedReplacementOperation() {

		guiAdvancedRepalcementPanel = new GUIadvancedRepalcementPanel();

		guiAdvancedRepalcementPanel.getSaveBtn().addActionListener(this);
		guiAdvancedRepalcementPanel.getAttachFileBtn().addActionListener(this);
		guiAdvancedRepalcementPanel.getTxtCompanyName().getDocument().addDocumentListener(this);

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
	private void saveRMAInformation() {

		JSONObject obj = new JSONObject();

		obj.put("Action", "requestSaveRMAData");

		obj.put("companyName", guiAdvancedRepalcementPanel.getTxtCompanyName().getText());
		obj.put("companyAddress", guiAdvancedRepalcementPanel.getTxtCompanyAddress().getText());
		obj.put("companyCity", guiAdvancedRepalcementPanel.getTxtCompanyCity().getText());
		obj.put("companyZipCode", guiAdvancedRepalcementPanel.getTxtCompanyZipCode().getText());
		obj.put("companyPhone", guiAdvancedRepalcementPanel.getTxtCompanyPhone().getText());
		obj.put("companyEmail", guiAdvancedRepalcementPanel.getTxtCompanyEmail().getText());
		obj.put("companySiteName", guiAdvancedRepalcementPanel.getTxtSiteName().getText());

		System.out.println("0,0 : " + guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(0, 0));

		obj.put("rmaNumber", guiAdvancedRepalcementPanel.getTxtRMAnumber().getText());
		obj.put("rmaDate", guiAdvancedRepalcementPanel.getTxtDate().getText());
		obj.put("rmaOrderNumber", guiAdvancedRepalcementPanel.getTxtOrderNumber().getText());
		obj.put("rmaContents", guiAdvancedRepalcementPanel.getTxtContents().getText());
		obj.put("rmaBillTo", guiAdvancedRepalcementPanel.getTxtBillTo().getText());
		obj.put("rmaShipTo", guiAdvancedRepalcementPanel.getTxtShipTo().getText());
		obj.put("rmaTrackingNumber", guiAdvancedRepalcementPanel.getTxtTrackingNumber().getText());

		System.out.println("rmaContents " + guiAdvancedRepalcementPanel.getTxtContents().getText());
		System.out.println("rmaBillTo " + guiAdvancedRepalcementPanel.getTxtBillTo().getText());
		System.out.println("rmaShipTo " + guiAdvancedRepalcementPanel.getTxtShipTo().getText());
		System.out.println("rmaTrackingNumber " + guiAdvancedRepalcementPanel.getTxtTrackingNumber().getText());

		obj.put("itemCount", guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount());

		for (int i = 0; i < guiAdvancedRepalcementPanel.get_RMAitemTable().getRowCount(); i++) {
			obj.put("itemName" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 0));
			obj.put("serialNumber" + i, guiAdvancedRepalcementPanel.get_RMAitemTable().getValueAt(i, 1));
		}

		printStream.println(obj.toJSONString());
		System.out.println("Update RMA 수행");

	}

	private void readRelatedRMAfromDataBase() {
		
		System.out.println("readRelatedRMAfromDataBase");
		
		guiAdvancedRepalcementPanel.clearHistoryPanel();

		JSONObject obj = new JSONObject();

		obj.put("Action", "requestSearchRelatedRMA");
		obj.put("companyName", guiAdvancedRepalcementPanel.getTxtCompanyName().getText());
		
		System.out.println(guiAdvancedRepalcementPanel.getTxtCompanyName().getText());

		printStream.println(obj.toJSONString());

		while (true) {

			try {
				String input = bufferedReader.readLine();

				if (input == null) {
					break;
				}

				JSONParser jsonParser = new JSONParser();

				JSONObject jsonObject = (JSONObject) jsonParser.parse(input);

				String rmaNumber = jsonObject.get("RMAnumber").toString();
				String rmaDate = jsonObject.get("RMAdate").toString();
				String rmaContents = jsonObject.get("RMAcontents").toString();

				guiAdvancedRepalcementPanel.setRelatedRMAInformation(rmaNumber, rmaDate, rmaContents);

				System.out.println("rmaNumber : " + rmaNumber + " rmaContents : " + rmaContents);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getSaveBtn()) {
			// save 버튼

			try {
				connectServer();
				saveRMAInformation();
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

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

		System.out.println("insertUpdate?");
		if (e.getDocument() == guiAdvancedRepalcementPanel.getTxtCompanyName().getDocument()) {
			try {
				connectServer();
				readRelatedRMAfromDataBase();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				closeConnection();
			}

		}

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("removeUpdate?");
		if (e.getDocument() == guiAdvancedRepalcementPanel.getTxtCompanyName().getDocument()) {
			try {
				connectServer();
				readRelatedRMAfromDataBase();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				closeConnection();
			}

		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("changedUpdate?");

	}

}
