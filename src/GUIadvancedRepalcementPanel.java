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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

public class GUIadvancedRepalcementPanel extends JPanel {

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
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JPanel previousRMApanel;
	private JPanel panel_2;
	private JPanel panel_3;

	public GUIadvancedRepalcementPanel() {

		setLayout(new BorderLayout(0, 0));

		// 확장성을 위한 Scroll Panel.
		advancedReplacementScrollPane = new JScrollPane(null);

		String[] columnNames = { "Item Name", "Serial number", "Description", "Price" };

		// Object[][] data = { { "PM-COS4-U", "1", "Snowboarding", new
		// Integer(5), new Boolean(false) },
		// { "PM-COS4-U", "2", "Rowing", new Integer(3), new Boolean(true) },
		// { "PM-HOS4-U", "3", "Knitting", new Integer(2), new Boolean(false) },
		// { "PM-COS4-U", "4", "Speed reading", new Integer(20), new
		// Boolean(true) },
		// { "PM-HOS4-U", "5", "Pool", new Integer(10), new Boolean(false) } };

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

		txtCompanyName = new JTextField();
		txtCompanyName.setColumns(10);
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

		txtSiteName = new JTextField();
		txtSiteName.setColumns(10);
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

		DefaultTableModel defaultTableModel = new DefaultTableModel(5, columnNames.length);
		defaultTableModel.setColumnIdentifiers(columnNames);

		_RMAitemTable = new JTable(defaultTableModel);
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

		previousRMApanel = new JPanel();
		historyPanel.add(previousRMApanel, BorderLayout.CENTER);
		previousRMApanel.setLayout(new BoxLayout(previousRMApanel, BoxLayout.PAGE_AXIS));

		// DefaultListModel model = new DefaultListModel();
		// model.addElement(new JPanel().add(new JLabel("라벨")));
		// model.addElement(new JLabel("라벨"));
		//
		// historyList = new JList();
		// historyList.setPreferredSize(new Dimension(200, 10));
		//
		//
		// historyList.setModel(model);
		//
		//
		// scrollPane = new JScrollPane();
		// scrollPane.setViewportView(historyList);
		// scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// historyPanel.add(scrollPane, BorderLayout.CENTER);

	}

	public void setRelatedRMAInformation(String rmaNumber, String rmaDate, String rmaContents) {

		System.out.println("history panel 세팅");
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setMaximumSize(new Dimension(32767, 50));

		panel_2.add(new JLabel(rmaNumber));
		panel_2.add(new JLabel(rmaDate));
		panel_2.add(new JLabel(rmaContents));

		System.out.println("rmaNumber : " + rmaNumber + " rmaContents : " + rmaContents);

		previousRMApanel.add(panel_2);

		previousRMApanel.validate();

	}
	
	public void clearHistoryPanel(){
		
		System.out.println("clearHistoryPanel");
		previousRMApanel.removeAll();
		
		previousRMApanel.revalidate();
		previousRMApanel.repaint();
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

	public JTable get_RMAitemTable() {
		return _RMAitemTable;
	}

	public void set_RMAitemTable(JTable _RMAitemTable) {
		this._RMAitemTable = _RMAitemTable;
	}

	public JButton getAttachFileBtn() {
		return attachFileBtn;
	}

	public void setAttachFileBtn(JButton attachFileBtn) {
		this.attachFileBtn = attachFileBtn;
	}

	public JButton getSaveBtn() {
		return SaveBtn;
	}

	public void setSaveBtn(JButton saveBtn) {
		SaveBtn = saveBtn;
	}

	public JTextField getTxtRMAnumber() {
		return txtRMAnumber;
	}

	public void setTxtRMAnumber(JTextField txtRMAnumber) {
		this.txtRMAnumber = txtRMAnumber;
	}

	public JTextField getTxtCompanyName() {
		return txtCompanyName;
	}

	public void setTxtCompanyName(JTextField txtCompanyName) {
		this.txtCompanyName = txtCompanyName;
	}

	public JTextField getTxtCompanyAddress() {
		return txtCompanyAddress;
	}

	public void setTxtCompanyAddress(JTextField txtCompanyAddress) {
		this.txtCompanyAddress = txtCompanyAddress;
	}

	public JTextField getTxtCompanyZipCode() {
		return txtCompanyZipCode;
	}

	public void setTxtCompanyZipCode(JTextField txtCompanyZipCode) {
		this.txtCompanyZipCode = txtCompanyZipCode;
	}

	public JTextField getTxtCompanyEmail() {
		return txtCompanyEmail;
	}

	public void setTxtCompanyEmail(JTextField txtCompanyEmail) {
		this.txtCompanyEmail = txtCompanyEmail;
	}

	public JTextField getTxtDate() {
		return txtDate;
	}

	public void setTxtDate(JTextField txtDate) {
		this.txtDate = txtDate;
	}

	public JTextField getTxtSiteName() {
		return txtSiteName;
	}

	public void setTxtSiteName(JTextField txtSiteName) {
		this.txtSiteName = txtSiteName;
	}

	public JTextField getTxtCompanyCity() {
		return txtCompanyCity;
	}

	public void setTxtCompanyCity(JTextField txtCompanyCity) {
		this.txtCompanyCity = txtCompanyCity;
	}

	public JTextField getTxtCompanyPhone() {
		return txtCompanyPhone;
	}

	public void setTxtCompanyPhone(JTextField txtCompanyPhone) {
		this.txtCompanyPhone = txtCompanyPhone;
	}

	public JTextField getTxtOrderNumber() {
		return txtOrderNumber;
	}

	public void setTxtOrderNumber(JTextField txtOrderNumber) {
		this.txtOrderNumber = txtOrderNumber;
	}

	public JTextArea getTxtContents() {
		return txtContents;
	}

	public void setTxtContents(JTextArea txtContents) {
		this.txtContents = txtContents;
	}

	public JTextArea getTxtBillTo() {
		return txtBillTo;
	}

	public void setTxtBillTo(JTextArea txtBillTo) {
		this.txtBillTo = txtBillTo;
	}

	public JTextArea getTxtShipTo() {
		return txtShipTo;
	}

	public void setTxtShipTo(JTextArea txtShipTo) {
		this.txtShipTo = txtShipTo;
	}

	public JTextField getTxtTrackingNumber() {
		return txtTrackingNumber;
	}

	public void setTxtTrackingNumber(JTextField txtTrackingNumber) {
		this.txtTrackingNumber = txtTrackingNumber;
	}

}
