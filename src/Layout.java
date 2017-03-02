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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.border.EmptyBorder;

public class Layout extends JFrame{
	private JTable table;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JScrollPane advancedReplacementScrollPane;
	private JTextArea txtrBillTo;
	private JTextArea txtrShipTo;
	private JLabel lblBillTo;
	private JLabel lblShipTo;
	private JScrollPane scrollPane;
	private JPanel historyPanel;
	private JLabel historyLabel;
	private JList historyList;
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
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JPanel panel_6;
	private JPanel panel;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_2;
	private JPanel panel_9;
	private JPanel panel_10;
	private JLabel label_5;
	private JTextPane textPane;
	private JPanel panel_11;
	private JPanel panel_13;
	private JPanel panel_14;
	private JButton btnSearch;
	
	public Layout(){
		super("RMA");
		setSize(new Dimension(1280, 800));
		
		
		
		
		
		
		
		loadAdvancedReplacementPanel();
		
		loadHistoryPanel();
		
		
		loadTypePanel();
		
	}
	
	private void loadAdvancedReplacementPanel(){
		
		
		
	}
	
	private void loadTypePanel(){
		
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
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(1280, 800));
		getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		panel_5 = new JPanel();
		panel_5.setBorder(BorderFactory.createLineBorder(Color.black));
		panel_4.add(panel_5, BorderLayout.WEST);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		button = new JButton("<Html>Advanced <br/>Replacement<Html>");
		panel_5.add(button);
		
		button_1 = new JButton("<Html>Repair<Html>");
		panel_5.add(button_1);
		
		button_2 = new JButton("<Html>PO <br/>Return<Html>");
		panel_5.add(button_2);
		
		button_3 = new JButton("<Html>Vender<Html>");
		panel_5.add(button_3);
		
		button_4 = new JButton("<Html>Case <br/>number<Html>");
		panel_5.add(button_4);
		
		button_5 = new JButton("<Html>back to <br/>Stock<Html>");
		button_5.setPreferredSize(new Dimension(40, 40));
		panel_5.add(button_5);
		
		panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel_6.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		button_6 = new JButton("New");
		panel.add(button_6);
		
		button_7 = new JButton("Save");
		panel.add(button_7);
		
		button_8 = new JButton("clear");
		panel.add(button_8);
		
		btnSearch = new JButton("Search");
		panel.add(btnSearch);
		
		button_9 = new JButton("Print");
		panel.add(button_9);
		
		button_10 = new JButton("Open/Close");
		panel.add(button_10);
		
		panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		historyPanel = new JPanel();
		historyPanel.setPreferredSize(new Dimension(this.getSize().width/6, 10));
		panel_7.add(historyPanel, BorderLayout.EAST);
		historyPanel.setLayout(new BorderLayout(0, 0));
		
		historyLabel = new JLabel("History");
		historyLabel.setPreferredSize(new Dimension(46, 50));
		historyPanel.add(historyLabel, BorderLayout.NORTH);
		historyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		historyList = new JList();
		historyList.setPreferredSize(new Dimension(200, 10));
		historyPanel.add(historyList, BorderLayout.CENTER);
		
		advancedReplacementScrollPane = new JScrollPane(null);
		panel_7.add(advancedReplacementScrollPane, BorderLayout.CENTER);
		advancedReplacementScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(15, 15, 15, 15));
		advancedReplacementScrollPane.setViewportView(panel_8);
		panel_8.setPreferredSize(new Dimension(50, 50));
		panel_8.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 300));
		panel_8.add(panel_2, BorderLayout.NORTH);
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
		panel_8.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0, 150));
		scrollPane.setMinimumSize(new Dimension(100, 100));
		scrollPane.setMaximumSize(new Dimension(100, 100));
		panel_9.add(scrollPane, BorderLayout.NORTH);
		
		table = new JTable(data, columnNames);
		table.setPreferredSize(new Dimension(375, 100));
		scrollPane.setViewportView(table);
		
		loadContentsPanel();
		
	}
	
	private void loadContentsPanel(){
		panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.CENTER);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWeights = new double[]{2.0, 1.0, 1.0};
		panel_10.setLayout(gbl_panel_10);
		
		panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.insets = new Insets(10, 10, 10, 10);
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 0;
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridwidth = 1;
		gbc_panel_11.gridheight = 1;
		gbc_panel_11.weighty = 1.0;
		panel_10.add(panel_11, gbc_panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		textPane = new JTextPane();
		panel_11.add(textPane, BorderLayout.CENTER);
		textPane.setPreferredSize(new Dimension(10, 100));
		
		label_5 = new JLabel("Contents");
		panel_11.add(label_5, BorderLayout.NORTH);
		
		
		
		panel_13 = new JPanel();
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.insets = new Insets(10, 10, 10, 10);
		gbc_panel_13.gridx = 1;
		gbc_panel_13.gridy = 0;
		gbc_panel_13.fill=GridBagConstraints.BOTH;
		gbc_panel_13.gridwidth = 1;
		gbc_panel_13.gridheight = 1;
		
		panel_10.add(panel_13, gbc_panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		txtrBillTo = new JTextArea();
		panel_13.add(txtrBillTo, BorderLayout.CENTER);
		
		lblBillTo = new JLabel("Bill to");
		panel_13.add(lblBillTo, BorderLayout.NORTH);
		
		
		
		
		panel_14 = new JPanel();
		GridBagConstraints gbc_panel_14 = new GridBagConstraints();
		gbc_panel_14.insets = new Insets(10, 10, 10, 10);
		gbc_panel_14.gridx = 2;
		gbc_panel_14.gridy = 0;
		gbc_panel_14.fill = GridBagConstraints.BOTH;
		gbc_panel_14.gridwidth = 1;
		panel_10.add(panel_14, gbc_panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		lblShipTo = new JLabel("Ship to");
		panel_14.add(lblShipTo, BorderLayout.NORTH);
		
		txtrShipTo = new JTextArea();
		panel_14.add(txtrShipTo, BorderLayout.CENTER);
	}
	
	private void loadHistoryPanel(){
		
	}
	public static void main(String[] args){
		Layout layout = new Layout();
		layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout.setSize(1280, 800);
//		layout.setResizable(false);
		layout.setVisible(true);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		layout.setLocation(dim.width /2 - layout.getSize().width /2, dim.height /2 -layout.getSize().height /2);
	}
}
