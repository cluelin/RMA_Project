import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class AdvancedReplacementPanel extends JPanel{
	
	private JTable _RMAitemTable;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JPanel panel_3;
	private JLabel label_17;
	private JLabel label_18;
	private JTextField textField_16;
	private JTextField textField_17;
	private JLabel label_19;
	private JTextField textField_18;
	private JLabel label_20;
	private JTextField textField_19;
	private JLabel label_21;
	private JTextField textField_20;
	private JLabel label_22;
	private JTextField textField_21;
	private JPanel advancedReplacementPanel;
	private JPanel panel_2;
	
	private JTextArea txtrBillTo;
	private JTextArea txtrShipTo;
	private JLabel lblBillTo;
	private JLabel lblShipTo;
	private JScrollPane _RMAitemInformationScrollPanel;
	
	private JPanel panel_9;
	private JPanel panel_10;
	private JLabel label_5;
	private JTextPane textPane;
	private JPanel _RMAcontentsPanel;
	private JPanel _RMAbillTo;
	private JPanel _RMAshipTo;
	
	private JScrollPane advancedReplacementScrollPane;
	
	private JPanel historyPanel;
	private JLabel historyLabel;
	private JList historyList;
	
	public AdvancedReplacementPanel(){
		
		setLayout(new BorderLayout(0, 0));
		
		advancedReplacementScrollPane = new JScrollPane(null);
		
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
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(6,2));
		
		JLabel label = new JLabel("RMA Number");
		panel_1.add(label);
		
		textField_11 = new JTextField();
		textField_11.setText("test");
		textField_11.setColumns(10);
		panel_1.add(textField_11);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		panel_1.add(lblCompanyName);
		
		textField_12 = new JTextField();
		textField_12.setText("test");
		textField_12.setColumns(10);
		panel_1.add(textField_12);
		
		JLabel label_2 = new JLabel("Address");
		panel_1.add(label_2);
		
		textField_13 = new JTextField();
		textField_13.setText("test");
		textField_13.setColumns(10);
		panel_1.add(textField_13);
		
		JLabel label_3 = new JLabel("Phone Number");
		panel_1.add(label_3);
		
		
		textField_14 = new JTextField();
		textField_14.setText("test");
		textField_14.setColumns(10);
		panel_1.add(textField_14);
		
		JLabel label_4 = new JLabel("Email");
		panel_1.add(label_4);
		
		textField_15 = new JTextField();
		textField_15.setText("test");
		textField_15.setColumns(10);
		panel_1.add(textField_15);
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(6,2));
		
		label_22 = new JLabel("Date");
		panel_3.add(label_22);
		
		textField_16 = new JTextField();
		textField_16.setText("test");
		textField_16.setColumns(10);
		panel_3.add(textField_16);
		
		label_17 = new JLabel("Site Name");
		panel_3.add(label_17);
		
		textField_17 = new JTextField();
		textField_17.setText("test");
		textField_17.setColumns(10);
		panel_3.add(textField_17);
		
		label_19 = new JLabel("Phone Number");
		panel_3.add(label_19);
		
		textField_18 = new JTextField();
		textField_18.setText("test");
		textField_18.setColumns(10);
		panel_3.add(textField_18);
		
		label_18 = new JLabel("Site Address");
		panel_3.add(label_18);
		
		textField_19 = new JTextField();
		textField_19.setText("test");
		textField_19.setColumns(10);
		panel_3.add(textField_19);
		
		label_20 = new JLabel("Email");
		panel_3.add(label_20);
		
		textField_20 = new JTextField();
		textField_20.setText("test");
		textField_20.setColumns(10);
		panel_3.add(textField_20);
		
		label_21 = new JLabel("Order Number");
		panel_3.add(label_21);
		
		textField_21 = new JTextField();
		textField_21.setText("test");
		textField_21.setColumns(10);
		panel_3.add(textField_21);
		
		panel_9 = new JPanel();
		advancedReplacementPanel.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		_RMAitemInformationScrollPanel = new JScrollPane();
		_RMAitemInformationScrollPanel.setPreferredSize(new Dimension(0, 150));
		_RMAitemInformationScrollPanel.setMinimumSize(new Dimension(100, 100));
		_RMAitemInformationScrollPanel.setMaximumSize(new Dimension(100, 100));
		panel_9.add(_RMAitemInformationScrollPanel, BorderLayout.NORTH);
		
		_RMAitemTable = new JTable(data, columnNames);
		_RMAitemTable.setPreferredSize(new Dimension(375, 100));
		_RMAitemInformationScrollPanel.setViewportView(_RMAitemTable);
		
		loadContentsPanel();
		
		add(advancedReplacementScrollPane, BorderLayout.CENTER);
		
		
		
		historyPanel = new JPanel();
		historyPanel.setPreferredSize(new Dimension(200, 10));
		add(historyPanel, BorderLayout.EAST);
		historyPanel.setLayout(new BorderLayout(0, 0));
		
		historyLabel = new JLabel("History");
		historyLabel.setPreferredSize(new Dimension(46, 50));
		historyPanel.add(historyLabel, BorderLayout.NORTH);
		historyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		historyList = new JList();
		historyList.setPreferredSize(new Dimension(200, 10));
		historyPanel.add(historyList, BorderLayout.CENTER);
	}
	
	private void loadContentsPanel(){
		panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.CENTER);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWeights = new double[]{2.0, 1.0, 1.0};
		panel_10.setLayout(gbl_panel_10);
		
		_RMAcontentsPanel = new JPanel();
		GridBagConstraints gbc__RMAcontentsPanel = new GridBagConstraints();
		gbc__RMAcontentsPanel.insets = new Insets(10, 10, 10, 10);
		gbc__RMAcontentsPanel.gridx = 0;
		gbc__RMAcontentsPanel.gridy = 0;
		gbc__RMAcontentsPanel.fill = GridBagConstraints.BOTH;
		gbc__RMAcontentsPanel.gridwidth = 1;
		gbc__RMAcontentsPanel.gridheight = 1;
		gbc__RMAcontentsPanel.weighty = 1.0;
		panel_10.add(_RMAcontentsPanel, gbc__RMAcontentsPanel);
		_RMAcontentsPanel.setLayout(new BorderLayout(0, 0));
		
		textPane = new JTextPane();
		_RMAcontentsPanel.add(textPane, BorderLayout.CENTER);
		textPane.setPreferredSize(new Dimension(10, 100));
		
		label_5 = new JLabel("Contents");
		_RMAcontentsPanel.add(label_5, BorderLayout.NORTH);
		
		
		
		_RMAbillTo = new JPanel();
		GridBagConstraints gbc__RMAbillTo = new GridBagConstraints();
		gbc__RMAbillTo.insets = new Insets(10, 10, 10, 10);
		gbc__RMAbillTo.gridx = 1;
		gbc__RMAbillTo.gridy = 0;
		gbc__RMAbillTo.fill=GridBagConstraints.BOTH;
		gbc__RMAbillTo.gridwidth = 1;
		gbc__RMAbillTo.gridheight = 1;
		
		panel_10.add(_RMAbillTo, gbc__RMAbillTo);
		_RMAbillTo.setLayout(new BorderLayout(0, 0));
		
		txtrBillTo = new JTextArea();
		_RMAbillTo.add(txtrBillTo, BorderLayout.CENTER);
		
		lblBillTo = new JLabel("Bill to");
		_RMAbillTo.add(lblBillTo, BorderLayout.NORTH);
		
		
		
		
		_RMAshipTo = new JPanel();
		GridBagConstraints gbc__RMAshipTo = new GridBagConstraints();
		gbc__RMAshipTo.insets = new Insets(10, 10, 10, 10);
		gbc__RMAshipTo.gridx = 2;
		gbc__RMAshipTo.gridy = 0;
		gbc__RMAshipTo.fill = GridBagConstraints.BOTH;
		gbc__RMAshipTo.gridwidth = 1;
		panel_10.add(_RMAshipTo, gbc__RMAshipTo);
		_RMAshipTo.setLayout(new BorderLayout(0, 0));
		
		lblShipTo = new JLabel("Ship to");
		_RMAshipTo.add(lblShipTo, BorderLayout.NORTH);
		
		txtrShipTo = new JTextArea();
		_RMAshipTo.add(txtrShipTo, BorderLayout.CENTER);
	}

}
