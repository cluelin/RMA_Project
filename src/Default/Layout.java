package Default;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class Layout extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTable table;
	private JTextField textField_10;
	
	public Layout(){
		super("RMA");
		setSize(new Dimension(1280, 1800));
		
		
		
		getContentPane().setLayout(null);
		
		
		
		
		
		
		
		loadAdvancedReplacementPanel();
		
		loadHistoryPanel();
		
		
		loadTypePanel();
		
	}
	
	private void loadAdvancedReplacementPanel(){
		
		JScrollPane advancedReplacementScrollPane = new JScrollPane(null);
		advancedReplacementScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		advancedReplacementScrollPane.setBounds(181, 87, 881, 674);
		
		getContentPane().add(advancedReplacementScrollPane);
		
		JPanel advancedReplacementPanel = new JPanel();
		advancedReplacementScrollPane.setViewportView(advancedReplacementPanel);
		advancedReplacementPanel.setPreferredSize(new Dimension(0, 1000));
		advancedReplacementPanel.setLayout(null);
		
		JLabel label = new JLabel("RMA Number");
		label.setBounds(14, 12, 92, 18);
		advancedReplacementPanel.add(label);
		
		JLabel label_1 = new JLabel("Company Nmae");
		label_1.setBounds(14, 61, 103, 18);
		advancedReplacementPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Address");
		label_2.setBounds(14, 159, 62, 18);
		advancedReplacementPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Contents");
		label_3.setBounds(17, 470, 62, 18);
		advancedReplacementPanel.add(label_3);
		
		textField = new JTextField();
		textField.setText("test");
		textField.setColumns(10);
		textField.setBounds(131, 12, 116, 18);
		advancedReplacementPanel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("test");
		textField_1.setColumns(10);
		textField_1.setBounds(131, 61, 116, 18);
		advancedReplacementPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("test");
		textField_2.setColumns(10);
		textField_2.setBounds(131, 159, 116, 18);
		advancedReplacementPanel.add(textField_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(17, 502, 528, 156);
		advancedReplacementPanel.add(textPane);
		
		JLabel lblSiteName = new JLabel("Site Name");
		lblSiteName.setBounds(319, 57, 92, 18);
		advancedReplacementPanel.add(lblSiteName);
		
		JLabel lblSiteAddress = new JLabel("Site Address");
		lblSiteAddress.setBounds(319, 159, 92, 18);
		advancedReplacementPanel.add(lblSiteAddress);
		
		textField_3 = new JTextField();
		textField_3.setText("test");
		textField_3.setColumns(10);
		textField_3.setBounds(426, 57, 116, 18);
		advancedReplacementPanel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText("test");
		textField_4.setColumns(10);
		textField_4.setBounds(426, 161, 116, 18);
		advancedReplacementPanel.add(textField_4);
		
		JLabel lblPhone = new JLabel("Phone Number");
		lblPhone.setBounds(14, 110, 103, 18);
		advancedReplacementPanel.add(lblPhone);
		
		textField_5 = new JTextField();
		textField_5.setText("test");
		textField_5.setColumns(10);
		textField_5.setBounds(131, 110, 116, 18);
		advancedReplacementPanel.add(textField_5);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(319, 108, 103, 18);
		advancedReplacementPanel.add(lblPhoneNumber);
		
		textField_6 = new JTextField();
		textField_6.setText("test");
		textField_6.setColumns(10);
		textField_6.setBounds(426, 109, 116, 18);
		advancedReplacementPanel.add(textField_6);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(14, 206, 62, 18);
		advancedReplacementPanel.add(lblEmail);
		
		textField_7 = new JTextField();
		textField_7.setText("test");
		textField_7.setColumns(10);
		textField_7.setBounds(131, 206, 116, 18);
		advancedReplacementPanel.add(textField_7);
		
		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setBounds(319, 206, 92, 18);
		advancedReplacementPanel.add(lblEmail_1);
		
		textField_8 = new JTextField();
		textField_8.setText("test");
		textField_8.setColumns(10);
		textField_8.setBounds(426, 208, 116, 18);
		advancedReplacementPanel.add(textField_8);
		
		JLabel lblOrderNumber = new JLabel("Order Number");
		lblOrderNumber.setBounds(319, 252, 92, 18);
		advancedReplacementPanel.add(lblOrderNumber);
		
		textField_9 = new JTextField();
		textField_9.setText("test");
		textField_9.setColumns(10);
		textField_9.setBounds(426, 254, 116, 18);
		advancedReplacementPanel.add(textField_9);
		
		JTextArea txtrBillTo = new JTextArea();
		txtrBillTo.setBounds(14, 744, 362, 190);
		advancedReplacementPanel.add(txtrBillTo);
		
		JTextArea txtrShipTo = new JTextArea();
		txtrShipTo.setBounds(390, 744, 362, 190);
		advancedReplacementPanel.add(txtrShipTo);
		
		JLabel lblBillTo = new JLabel("Bill to");
		lblBillTo.setBounds(14, 714, 62, 18);
		advancedReplacementPanel.add(lblBillTo);
		
		JLabel lblShipTo = new JLabel("Ship to");
		lblShipTo.setBounds(390, 714, 62, 18);
		advancedReplacementPanel.add(lblShipTo);
		
		String[] columnNames = {"Item Name",
                "Serial number",
                "Description",
                "# of Years",
                "Price"};
		
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 318, 689, 126);
		advancedReplacementPanel.add(scrollPane);
		
		table = new JTable(data, columnNames);
		scrollPane.setViewportView(table);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(312, 12, 92, 18);
		advancedReplacementPanel.add(lblDate);
		
		textField_10 = new JTextField();
		textField_10.setText("test");
		textField_10.setColumns(10);
		textField_10.setBounds(429, 12, 116, 18);
		advancedReplacementPanel.add(textField_10);
		
		advancedReplacementPanel.updateUI();
		
	}
	
	private void loadTypePanel(){
		
		JPanel typePanel = new JPanel();
		typePanel.setBounds(0, 0, 146, 761);
		getContentPane().add(typePanel);
		typePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		typePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton advancedReplacementBtn = new JButton("<Html>Advanced <br/>Replacement<Html>");
		typePanel.add(advancedReplacementBtn);
		
		JButton repairBtn = new JButton("<Html>Repair<Html>");
		typePanel.add(repairBtn);
		
		JButton _POReturnBtn = new JButton("<Html>PO <br/>Return<Html>");
		typePanel.add(_POReturnBtn);
		
		JButton venderBtn = new JButton("<Html>Vender<Html>");
		typePanel.add(venderBtn);
		
		JButton caseNumberBtn = new JButton("<Html>Case <br/>number<Html>");
		typePanel.add(caseNumberBtn);
		
		JButton backToStockBtn = new JButton("<Html>back to <br/>Stock<Html>");
		backToStockBtn.setPreferredSize(new Dimension(40, 40));
		typePanel.add(backToStockBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(181, 0, 1083, 79);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNew = new JButton("New");
		panel.add(btnNew);
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JButton btnClear = new JButton("clear");
		panel.add(btnClear);
		
		JButton btnPrint = new JButton("Print");
		panel.add(btnPrint);
		
		JButton btnNewButton = new JButton("Open/Close");
		panel.add(btnNewButton);
		
	}
	
	private void loadHistoryPanel(){
		
		JPanel historyPanel = new JPanel();
		historyPanel.setBounds(1075, 87, 187, 674);
		getContentPane().add(historyPanel);
		historyPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel historyLabel = new JLabel("History");
		historyLabel.setPreferredSize(new Dimension(46, 50));
		historyPanel.add(historyLabel, BorderLayout.NORTH);
		historyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JList historyList = new JList();
		historyPanel.add(historyList, BorderLayout.CENTER);
		
	}
	public static void main(String[] args){
		Layout layout = new Layout();
		layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout.setSize(1280, 800);
		layout.setResizable(false);
		layout.setVisible(true);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		layout.setLocation(dim.width /2 - layout.getSize().width /2, dim.height /2 -layout.getSize().height /2);
	}
}
