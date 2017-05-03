package AdvancedReplacement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.simple.JSONObject;

public class GUIadvancedRepalcementPanel extends JPanel {

	private static String FILE_NAME = "ReplacementInformation.txt";
	private static GUIadvancedRepalcementPanel instance = null;

	private JTextField txtRMAnumber;
	private JComboBox txtCompanyName;
	private JTextField txtCompanyAddress;
	private JTextField txtCompanyZipCode;
	private JTextField txtCompanyEmail;
	private JTextField txtDate;
	private JComboBox txtSiteName;
	private JTextField txtCompanyCity;
	private JTextField txtCompanyPhone;
	private JTextField txtOrderNumber;

	private JPanel rightCompanyInformationPanel;
	private JPanel advancedReplacementPanel;
	private JPanel companyInformationPanel;
	private JTable _RMAitemTable;
	private JLabel label_17;
	private JLabel lblPhone;
	private JLabel lblCity;
	private JLabel label_21;
	private JLabel label_22;
	private JLabel lblBillTo;
	private JLabel lblShipTo;
	private JLabel label_5;

	private JTextArea txtContents;
	private JTextArea txtBillTo;
	private JTextArea txtShipTo;

	private JScrollPane _RMAitemInformationScrollPanel;

	private JPanel itemAndOtherPanel;
	private JPanel rmaContentsAndShipToPanel;
	private JPanel _RMAcontentsPanel;
	private JPanel _RMAbillTo;
	private JPanel _RMAshipTo;

	private JScrollPane advancedReplacementScrollPane;

	private JPanel historyPanel;
	private JLabel historyLabel;
	private JPanel panel;
	private JPanel panel_4;
	private JButton attachFileBtn;
	private JButton SaveBtn;
	private JTextField txtTrackingNumber;
	private JPanel panel_5;

	PrintStream printStream;
	BufferedReader bufferedReader;
	Socket client;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JPanel previousRMAListPanel;
	private JPanel previousRMAitemPanel;
	private JScrollPane previousRMAListScrollPanel;
	private JPanel panel_3;

	private JComboBox itemComboBox;
	MyTableModel myTableModel;
	int initialTableRowCount = 10;

	private GUIadvancedRepalcementPanel() {

		String[] columnNames = { "Item Name", "Serial number", "Description", "Price" };

		setLayout(new BorderLayout(0, 0));

		// 확장성을 위한 Scroll Panel.
		advancedReplacementScrollPane = new JScrollPane(null);

		// 실질적인 기능을 수행 하는 패널.
		advancedReplacementPanel = new JPanel();
		advancedReplacementPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

		advancedReplacementScrollPane.setViewportView(advancedReplacementPanel);
		// advancedReplacementPanel.setPreferredSize(new Dimension(697, 619));
		advancedReplacementPanel.setLayout(new BorderLayout(0, 0));

		companyInformationPanel = new JPanel();
		companyInformationPanel.setPreferredSize(new Dimension(10, 300));
		advancedReplacementPanel.add(companyInformationPanel, BorderLayout.NORTH);
		companyInformationPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel leftCompanyInformationPanel = new JPanel();
		leftCompanyInformationPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		companyInformationPanel.add(leftCompanyInformationPanel);
		leftCompanyInformationPanel.setLayout(new GridLayout(6, 2));

		JLabel label = new JLabel("RMA Number");
		leftCompanyInformationPanel.add(label);

		txtRMAnumber = new JTextField();
		txtRMAnumber.setEditable(false);
		txtRMAnumber.setColumns(10);
		leftCompanyInformationPanel.add(txtRMAnumber);

		JLabel lblCompanyName = new JLabel("Company Name");
		leftCompanyInformationPanel.add(lblCompanyName);

		txtCompanyName = new JComboBox();
		txtCompanyName.setEditable(true);

		leftCompanyInformationPanel.add(txtCompanyName);

		JLabel label_2 = new JLabel("Address");
		leftCompanyInformationPanel.add(label_2);

		txtCompanyAddress = new JTextField();
		txtCompanyAddress.setColumns(10);
		leftCompanyInformationPanel.add(txtCompanyAddress);

		JLabel lblZipCode = new JLabel("Zip Code");
		leftCompanyInformationPanel.add(lblZipCode);

		txtCompanyZipCode = new JTextField();
		txtCompanyZipCode.setColumns(10);
		leftCompanyInformationPanel.add(txtCompanyZipCode);

		JLabel label_4 = new JLabel("Email");
		leftCompanyInformationPanel.add(label_4);

		txtCompanyEmail = new JTextField();
		txtCompanyEmail.setColumns(10);
		leftCompanyInformationPanel.add(txtCompanyEmail);

		rightCompanyInformationPanel = new JPanel();
		rightCompanyInformationPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		companyInformationPanel.add(rightCompanyInformationPanel);
		rightCompanyInformationPanel.setLayout(new GridLayout(6, 2));

		label_22 = new JLabel("Date");
		rightCompanyInformationPanel.add(label_22);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setText(new SimpleDateFormat("MM-dd-YYYY").format(Calendar.getInstance().getTime()));
		System.out.println(txtDate.getText().replace("-", ""));

		rightCompanyInformationPanel.add(txtDate);

		label_17 = new JLabel("Site Name");
		rightCompanyInformationPanel.add(label_17);

		txtSiteName = new JComboBox<>();
		txtSiteName.setEditable(true);

		rightCompanyInformationPanel.add(txtSiteName);

		lblCity = new JLabel("City");
		rightCompanyInformationPanel.add(lblCity);

		txtCompanyCity = new JTextField();
		txtCompanyCity.setColumns(10);
		rightCompanyInformationPanel.add(txtCompanyCity);

		lblPhone = new JLabel("Phone");
		rightCompanyInformationPanel.add(lblPhone);

		txtCompanyPhone = new JTextField();
		txtCompanyPhone.setColumns(10);
		rightCompanyInformationPanel.add(txtCompanyPhone);

		label_21 = new JLabel("Order Number");
		rightCompanyInformationPanel.add(label_21);

		txtOrderNumber = new JTextField();
		txtOrderNumber.setColumns(10);
		rightCompanyInformationPanel.add(txtOrderNumber);

		itemAndOtherPanel = new JPanel();
		advancedReplacementPanel.add(itemAndOtherPanel, BorderLayout.CENTER);
		itemAndOtherPanel.setLayout(new BorderLayout(0, 0));

		_RMAitemInformationScrollPanel = new JScrollPane();
		_RMAitemInformationScrollPanel.setPreferredSize(new Dimension(0, 150));
		_RMAitemInformationScrollPanel.setMinimumSize(new Dimension(100, 100));
		_RMAitemInformationScrollPanel.setMaximumSize(new Dimension(100, 100));
		itemAndOtherPanel.add(_RMAitemInformationScrollPanel, BorderLayout.NORTH);

		// myTableModel = new MyTableModel();
		// _RMAitemTable = new JTable(myTableModel);

		DefaultTableModel myTableModel = new DefaultTableModel(initialTableRowCount, columnNames.length);
		myTableModel.setColumnIdentifiers(columnNames);
		_RMAitemTable = new JTable(myTableModel);

		_RMAitemTable.setRowHeight(50);
		_RMAitemTable.setFillsViewportHeight(true);

		TableColumn sportColumn = _RMAitemTable.getColumnModel().getColumn(0);

		// Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		sportColumn.setCellRenderer(renderer);

		itemComboBox = new JComboBox();
		itemComboBox.setEditable(true);
		sportColumn.setCellEditor(new DefaultCellEditor(itemComboBox));

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


		previousRMAListScrollPanel = new JScrollPane();

		previousRMAListScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		previousRMAListScrollPanel.setPreferredSize(new Dimension(50,50));
		
		previousRMAListPanel = new JPanel();
//		previousRMAListPanel.setPreferredSize(new Dimension(10,5));
		previousRMAListPanel.setLayout(new BoxLayout(previousRMAListPanel, BoxLayout.PAGE_AXIS));

		previousRMAListScrollPanel.add(previousRMAListPanel);
		previousRMAListScrollPanel.setViewportView(previousRMAListPanel);
		historyPanel.add(previousRMAListScrollPanel, BorderLayout.CENTER);

//		historyPanel.add(previousRMAListPanel, BorderLayout.CENTER);
		
	}

	public static GUIadvancedRepalcementPanel getInstance() {
		if (instance == null) {
			instance = new GUIadvancedRepalcementPanel();
		}
		return instance;

	}

	public void setRelatedRMAInformation(String rmaNumber, String rmaDate, String rmaContents) {

		System.out.println("history panel 세팅");
		previousRMAitemPanel = new JPanel();
		previousRMAitemPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		previousRMAitemPanel.setPreferredSize(new Dimension(10, 50));
		previousRMAitemPanel.setSize(50, 50);
		previousRMAitemPanel.setBounds(0, 0, 50, 50);
		previousRMAitemPanel.setMaximumSize(new Dimension(32767, 50));

		JLabel rmaNumberLabel = new JLabel(rmaNumber);
		JLabel rmaDateLabel = new JLabel(rmaDate);
		JLabel rmaContentsLabel = new JLabel(rmaContents);


		previousRMAitemPanel.add(rmaNumberLabel);
		previousRMAitemPanel.add(rmaDateLabel);
		previousRMAitemPanel.add(rmaContentsLabel);
		previousRMAitemPanel.addMouseListener(new previousRMAitemPanelClickListener(rmaNumber));

		System.out.println("rmaNumber : " + rmaNumber + " rmaContents : " + rmaContents);

		previousRMAListPanel.add(previousRMAitemPanel);
		previousRMAListPanel.validate();
		previousRMAListPanel.invalidate();
		previousRMAListPanel.repaint();
		
		previousRMAListScrollPanel.validate();
		previousRMAListScrollPanel.invalidate();
		previousRMAListScrollPanel.repaint();
		
		historyPanel.validate();
		historyPanel.invalidate();
		historyPanel.repaint();
		

	}

	public void setRMADetail(JSONObject RMADetailJSON) {

		String rmaNumber = RMADetailJSON.get("rmaNumber").toString();
		String rmaDate = RMADetailJSON.get("rmaDate").toString();
		String rmaOrderNumber = RMADetailJSON.get("rmaOrderNumber").toString();
		String rmaContents = RMADetailJSON.get("rmaContents").toString();
		String rmaBillTo = RMADetailJSON.get("rmaBillTo").toString();
		String rmaShipTo = RMADetailJSON.get("rmaShipTo").toString();
		String rmaTrackingNumber = RMADetailJSON.get("rmaTrackingNumber").toString();

		clearItemTable();
		
		
		// 받아온 item 항목을 각각의 위치에 삽입하는 과정 필요
		int rmaItemCount = Integer.parseInt(RMADetailJSON.get("itemCount").toString());
		
		System.out.println("rmaItemCount : " + rmaItemCount);

		for (int i = 0; i < rmaItemCount; i++) {
			String itemName = RMADetailJSON.get("itemName" + i).toString();
			String serialNumber = RMADetailJSON.get("serialNumber" + i).toString();
			String itemDescription = RMADetailJSON.get("itemDescription" + i).toString();
			Integer itemPrice = Integer.parseInt(RMADetailJSON.get("itemPrice" + i).toString());

			_RMAitemTable.setValueAt(itemName, i, 0);
			_RMAitemTable.setValueAt(serialNumber, i, 1);
			_RMAitemTable.setValueAt(itemDescription, i, 2);
			_RMAitemTable.setValueAt(itemPrice, i, 3);
		}

		txtRMAnumber.setText(rmaNumber);
		txtDate.setText(rmaDate);
		txtOrderNumber.setText(rmaOrderNumber);
		txtContents.setText(rmaContents);
		txtBillTo.setText(rmaBillTo);
		txtShipTo.setText(rmaShipTo);
		txtTrackingNumber.setText(rmaTrackingNumber);

	}

	public void clearHistoryPanel() {

		System.out.println("clearHistoryPanel");
		previousRMAListPanel.removeAll();

		previousRMAListPanel.revalidate();
		previousRMAListPanel.repaint();
	}

	public void clearItemTable() {

		System.out.println("clearItemTable");

		((DefaultTableModel) _RMAitemTable.getModel()).setRowCount(0);
		((DefaultTableModel) _RMAitemTable.getModel()).setRowCount(initialTableRowCount);

	}

	private void loadContentsPanel() {

		panel = new JPanel();
		itemAndOtherPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		rmaContentsAndShipToPanel = new JPanel();
		panel.add(rmaContentsAndShipToPanel);
		GridBagLayout gbl_rmaContentsAndShipToPanel = new GridBagLayout();
		gbl_rmaContentsAndShipToPanel.columnWeights = new double[] { 2.0, 1.0, 1.0 };
		rmaContentsAndShipToPanel.setLayout(gbl_rmaContentsAndShipToPanel);

		_RMAcontentsPanel = new JPanel();
		GridBagConstraints gbc__RMAcontentsPanel = new GridBagConstraints();
		gbc__RMAcontentsPanel.insets = new Insets(10, 0, 10, 10);
		gbc__RMAcontentsPanel.gridx = 0;
		gbc__RMAcontentsPanel.gridy = 0;
		gbc__RMAcontentsPanel.fill = GridBagConstraints.BOTH;
		gbc__RMAcontentsPanel.gridwidth = 1;
		gbc__RMAcontentsPanel.gridheight = 1;
		gbc__RMAcontentsPanel.weighty = 1.0;
		rmaContentsAndShipToPanel.add(_RMAcontentsPanel, gbc__RMAcontentsPanel);
		_RMAcontentsPanel.setLayout(new BorderLayout(0, 0));

		txtContents = new JTextArea();
		_RMAcontentsPanel.add(txtContents, BorderLayout.CENTER);
		// txtContents.setPreferredSize(new Dimension(10, 100));
		txtContents.setLineWrap(true);

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

		rmaContentsAndShipToPanel.add(_RMAbillTo, gbc__RMAbillTo);
		_RMAbillTo.setLayout(new BorderLayout(0, 0));

		txtBillTo = new JTextArea();
		txtBillTo.setLineWrap(true);
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
		rmaContentsAndShipToPanel.add(_RMAshipTo, gbc__RMAshipTo);
		_RMAshipTo.setLayout(new BorderLayout(0, 0));

		lblShipTo = new JLabel("Ship to");
		_RMAshipTo.add(lblShipTo, BorderLayout.NORTH);

		txtShipTo = new JTextArea();
		_RMAshipTo.add(txtShipTo, BorderLayout.CENTER);
		txtShipTo.setLineWrap(true);

		panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		attachFileBtn = new JButton("Attach File");

		panel_4.add(attachFileBtn, BorderLayout.WEST);

		SaveBtn = new JButton("save");

		panel_4.add(SaveBtn, BorderLayout.EAST);

		panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT));

		lblNewLabel = new JLabel("Tracking Number");
		panel_5.add(lblNewLabel);

		txtTrackingNumber = new JTextField();
		panel_5.add(txtTrackingNumber);
		txtTrackingNumber.setColumns(20);
	}

	public void setCompanyDetail(String address, String city, String zipCode, String phone, String email) {

		txtCompanyAddress.setText(address);
		txtCompanyCity.setText(city);
		txtCompanyZipCode.setText(zipCode);
		txtCompanyPhone.setText(phone);
		txtCompanyEmail.setText(email);
	}

	public void clearCompanyDetail() {

		txtCompanyAddress.setText("");
		txtCompanyCity.setText("");
		txtCompanyZipCode.setText("");
		txtCompanyPhone.setText("");
		txtCompanyEmail.setText("");

	}

	public JTable get_RMAitemTable() {
		return _RMAitemTable;
	}

	public JButton getAttachFileBtn() {
		return attachFileBtn;
	}

	public JButton getSaveBtn() {
		return SaveBtn;
	}

	public JTextField getTxtRMAnumber() {
		return txtRMAnumber;
	}

	public JComboBox getTxtCompanyName() {
		return txtCompanyName;
	}

	public JTextField getTxtCompanyAddress() {
		return txtCompanyAddress;
	}

	public JTextField getTxtCompanyZipCode() {
		return txtCompanyZipCode;
	}

	public JTextField getTxtCompanyEmail() {
		return txtCompanyEmail;
	}

	public JTextField getTxtDate() {
		return txtDate;
	}

	public JComboBox getTxtSiteName() {
		return txtSiteName;
	}

	public JTextField getTxtCompanyCity() {
		return txtCompanyCity;
	}

	public JTextField getTxtCompanyPhone() {
		return txtCompanyPhone;
	}

	public JTextField getTxtOrderNumber() {
		return txtOrderNumber;
	}

	public JTextArea getTxtContents() {
		return txtContents;
	}

	public JTextArea getTxtBillTo() {
		return txtBillTo;
	}

	public JTextArea getTxtShipTo() {
		return txtShipTo;
	}

	public JTextField getTxtTrackingNumber() {
		return txtTrackingNumber;
	}

	public JComboBox getItemComboBox() {
		return itemComboBox;
	}

	public MyTableModel getTableModel() {
		return myTableModel;
	}

}
