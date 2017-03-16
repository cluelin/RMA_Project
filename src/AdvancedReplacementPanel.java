import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AdvancedReplacementPanel extends JPanel implements ActionListener {

	private static String FILE_NAME = "ReplacementInformation.txt";

	private JTextField txtRMAnumber;
	private JTextField txtCompanyName;
	private JTextField txtCompanyAddress;
	private JTextField txtCompanyZipCode;
	private JTextField txtCompanyEmail;
	private JTextField txtDate;
	private JTextField txtSiteName;
	private JTextField txtCompanyCity;
	private JTextField txtCompanyPhone;
	private JTextField txtOrderNumber;

	private JPanel panel_3;
	private JPanel advancedReplacementPanel;
	private JPanel panel_2;
	private JTable _RMAitemTable;
	private JLabel label_17;
	private JLabel lblPhone;
	private JLabel lblCity;
	private JLabel label_21;
	private JLabel label_22;
	private JLabel lblBillTo;
	private JLabel lblShipTo;
	private JLabel label_5;

	private JTextPane txtContents;
	private JTextArea txtBillTo;
	private JTextArea txtShipTo;

	private JScrollPane _RMAitemInformationScrollPanel;

	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel _RMAcontentsPanel;
	private JPanel _RMAbillTo;
	private JPanel _RMAshipTo;

	private JScrollPane advancedReplacementScrollPane;

	private JPanel historyPanel;
	private JLabel historyLabel;
	private JList historyList;
	private JPanel panel;
	private JPanel panel_4;
	private JButton attachFileBtn;
	private JButton SaveBtn;
	private JTextField txtTrackingNumber;
	private JPanel panel_5;

	PrintStream printStream;
	BufferedReader bufferedReader;
	Socket client;

	public AdvancedReplacementPanel() {

		setLayout(new BorderLayout(0, 0));

		advancedReplacementScrollPane = new JScrollPane(null);

		String[] columnNames = { "Item Name", "Serial number", "Description", "Price" };

		Object[][] data = { { "Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20), new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

		advancedReplacementPanel = new JPanel();
		advancedReplacementPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

		advancedReplacementScrollPane.setViewportView(advancedReplacementPanel);
		advancedReplacementPanel.setPreferredSize(new Dimension(697, 619));
		advancedReplacementPanel.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 300));
		advancedReplacementPanel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 5, 5, 5));
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(6, 2));

		JLabel label = new JLabel("RMA Number");
		panel_1.add(label);

		txtRMAnumber = new JTextField();
		txtRMAnumber.setEditable(false);
		txtRMAnumber.setColumns(10);
		panel_1.add(txtRMAnumber);

		JLabel lblCompanyName = new JLabel("Company Name");
		panel_1.add(lblCompanyName);

		txtCompanyName = new JTextField();
		txtCompanyName.setColumns(10);
		panel_1.add(txtCompanyName);

		JLabel label_2 = new JLabel("Address");
		panel_1.add(label_2);

		txtCompanyAddress = new JTextField();
		txtCompanyAddress.setColumns(10);
		panel_1.add(txtCompanyAddress);

		JLabel lblZipCode = new JLabel("Zip Code");
		panel_1.add(lblZipCode);

		txtCompanyZipCode = new JTextField();
		txtCompanyZipCode.setColumns(10);
		panel_1.add(txtCompanyZipCode);

		JLabel label_4 = new JLabel("Email");
		panel_1.add(label_4);

		txtCompanyEmail = new JTextField();
		txtCompanyEmail.setColumns(10);
		panel_1.add(txtCompanyEmail);

		panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(0, 5, 5, 5));
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(6, 2));

		label_22 = new JLabel("Date");
		panel_3.add(label_22);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setText(new SimpleDateFormat("MM-dd-YYYY").format(Calendar.getInstance().getTime()));
		System.out.println(txtDate.getText().replace("-", ""));

		panel_3.add(txtDate);

		label_17 = new JLabel("Site Name");
		panel_3.add(label_17);

		txtSiteName = new JTextField();
		txtSiteName.setColumns(10);
		panel_3.add(txtSiteName);

		lblCity = new JLabel("City");
		panel_3.add(lblCity);

		txtCompanyCity = new JTextField();
		txtCompanyCity.setColumns(10);
		panel_3.add(txtCompanyCity);

		lblPhone = new JLabel("Phone");
		panel_3.add(lblPhone);

		txtCompanyPhone = new JTextField();
		txtCompanyPhone.setColumns(10);
		panel_3.add(txtCompanyPhone);

		label_21 = new JLabel("Order Number");
		panel_3.add(label_21);

		txtOrderNumber = new JTextField();
		txtOrderNumber.setColumns(10);
		panel_3.add(txtOrderNumber);

		panel_9 = new JPanel();
		advancedReplacementPanel.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));

		_RMAitemInformationScrollPanel = new JScrollPane();
		_RMAitemInformationScrollPanel.setPreferredSize(new Dimension(0, 150));
		_RMAitemInformationScrollPanel.setMinimumSize(new Dimension(100, 100));
		_RMAitemInformationScrollPanel.setMaximumSize(new Dimension(100, 100));
		panel_9.add(_RMAitemInformationScrollPanel, BorderLayout.NORTH);

		_RMAitemTable = new JTable(data, columnNames);
		_RMAitemTable.setRowHeight(50);
		_RMAitemInformationScrollPanel.setViewportView(_RMAitemTable);

		loadContentsPanel();

		add(advancedReplacementScrollPane, BorderLayout.CENTER);

		historyPanel = new JPanel();
		historyPanel.setPreferredSize(new Dimension(200, 10));
		add(historyPanel, BorderLayout.EAST);
		historyPanel.setLayout(new BorderLayout(0, 0));

		historyLabel = new JLabel("History", SwingConstants.CENTER);
		historyLabel.setPreferredSize(new Dimension(46, 50));
		historyPanel.add(historyLabel, BorderLayout.NORTH);
		historyPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		historyList = new JList();
		historyList.setPreferredSize(new Dimension(200, 10));
		historyPanel.add(historyList, BorderLayout.CENTER);

		// RMA number seting.
		connectServer();
		getRMANumberFromDataBase();
		closeConnection();

	}

	
	//rma number setting
	private void getRMANumberFromDataBase() {

		JSONObject obj = new JSONObject();
		obj.put("Action", "requestRMANumber");

		printStream.println(obj.toJSONString());

		JSONParser jsonParser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(bufferedReader.readLine());

			System.out.println("RMA number : " + jsonObject.get("RMANumber").toString());

			txtRMAnumber.setText(jsonObject.get("RMANumber").toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadContentsPanel() {

		panel = new JPanel();
		panel_9.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		panel_10 = new JPanel();
		panel.add(panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWeights = new double[] { 2.0, 1.0, 1.0 };
		panel_10.setLayout(gbl_panel_10);

		_RMAcontentsPanel = new JPanel();
		GridBagConstraints gbc__RMAcontentsPanel = new GridBagConstraints();
		gbc__RMAcontentsPanel.insets = new Insets(10, 0, 10, 10);
		gbc__RMAcontentsPanel.gridx = 0;
		gbc__RMAcontentsPanel.gridy = 0;
		gbc__RMAcontentsPanel.fill = GridBagConstraints.BOTH;
		gbc__RMAcontentsPanel.gridwidth = 1;
		gbc__RMAcontentsPanel.gridheight = 1;
		gbc__RMAcontentsPanel.weighty = 1.0;
		panel_10.add(_RMAcontentsPanel, gbc__RMAcontentsPanel);
		_RMAcontentsPanel.setLayout(new BorderLayout(0, 0));

		txtContents = new JTextPane();
		_RMAcontentsPanel.add(txtContents, BorderLayout.CENTER);
		txtContents.setPreferredSize(new Dimension(10, 100));

		label_5 = new JLabel("Contents");
		_RMAcontentsPanel.add(label_5, BorderLayout.NORTH);

		_RMAbillTo = new JPanel();
		GridBagConstraints gbc__RMAbillTo = new GridBagConstraints();
		gbc__RMAbillTo.insets = new Insets(10, 10, 10, 10);
		gbc__RMAbillTo.gridx = 1;
		gbc__RMAbillTo.gridy = 0;
		gbc__RMAbillTo.fill = GridBagConstraints.BOTH;
		gbc__RMAbillTo.gridwidth = 1;
		gbc__RMAbillTo.gridheight = 1;

		panel_10.add(_RMAbillTo, gbc__RMAbillTo);
		_RMAbillTo.setLayout(new BorderLayout(0, 0));

		txtBillTo = new JTextArea();
		_RMAbillTo.add(txtBillTo, BorderLayout.CENTER);

		lblBillTo = new JLabel("Bill to");
		_RMAbillTo.add(lblBillTo, BorderLayout.NORTH);

		_RMAshipTo = new JPanel();
		GridBagConstraints gbc__RMAshipTo = new GridBagConstraints();
		gbc__RMAshipTo.insets = new Insets(10, 10, 10, 0);
		gbc__RMAshipTo.gridx = 2;
		gbc__RMAshipTo.gridy = 0;
		gbc__RMAshipTo.fill = GridBagConstraints.BOTH;
		gbc__RMAshipTo.gridwidth = 1;
		panel_10.add(_RMAshipTo, gbc__RMAshipTo);
		_RMAshipTo.setLayout(new BorderLayout(0, 0));

		lblShipTo = new JLabel("Ship to");
		_RMAshipTo.add(lblShipTo, BorderLayout.NORTH);

		txtShipTo = new JTextArea();
		_RMAshipTo.add(txtShipTo, BorderLayout.CENTER);

		panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		attachFileBtn = new JButton("Attach File");
		attachFileBtn.addActionListener(this);
		panel_4.add(attachFileBtn, BorderLayout.WEST);

		SaveBtn = new JButton("save");
		SaveBtn.addActionListener(this);
		panel_4.add(SaveBtn, BorderLayout.EAST);

		panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT));

		txtTrackingNumber = new JTextField();
		panel_5.add(txtTrackingNumber);
		txtTrackingNumber.setColumns(20);
	}

	private void connectServer() {

		// 서버로 전송
		try {

			System.out.println("Client : connecting...");
			client = new Socket(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
			System.out.println("Client : connected");

			try {

				printStream = new PrintStream(client.getOutputStream());
				bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	private void closeConnection() {

		try {
			client.close();
			System.out.println("Client : close");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void saveFile() {

		try {
			File file = new File(FILE_NAME);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));

			// 모든필드 내보내기.
			fileWriter.write(txtTrackingNumber.getText());

			fileWriter.flush();
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// save RMA Information To Database
	private void saveRMAInformation() {

		JSONObject obj = new JSONObject();

		obj.put("Action", "requestSaveRMAData");

		obj.put("companyName", txtCompanyName.getText());
		obj.put("companyAddress", txtCompanyAddress.getText());
		obj.put("companyCity", txtCompanyCity.getText());
		obj.put("companyZipCode", txtCompanyZipCode.getText());
		obj.put("companyPhone", txtCompanyPhone.getText());
		obj.put("companyEmail", txtCompanyEmail.getText());
		obj.put("companySiteName", txtSiteName.getText());

		obj.put("rmaNumber", txtRMAnumber.getText());
		obj.put("rmaDate", txtDate.getText());
		obj.put("rmaOrderNumber", txtOrderNumber.getText());
		obj.put("rmaContents", txtContents.getText());
		obj.put("rmaBillTo", txtBillTo.getText());
		obj.put("rmaShipTo", txtShipTo.getText());
		obj.put("rmaTrackingNumber", txtTrackingNumber.getText());

		System.out.println("rmaContents " + txtContents.getText());
		System.out.println("rmaBillTo " + txtBillTo.getText());
		System.out.println("rmaShipTo " + txtShipTo.getText());
		System.out.println("rmaTrackingNumber " + txtTrackingNumber.getText());

		// obj의 크기가 커져서 그런듯?? 어찌해결할꼬.
		printStream.println(obj.toJSONString());

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == SaveBtn) {
			// save 버튼
			connectServer();
			saveRMAInformation();
			closeConnection();
			connectServer();
			getRMANumberFromDataBase();
			closeConnection();

		} else if (e.getSource() == attachFileBtn) {

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

	// get given File's extension
	private static String getFileExtension(File file) {

		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

}
