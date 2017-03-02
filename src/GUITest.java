import java.awt.Dimension;
import java.awt.Toolkit;
import java.security.acl.Group;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GUITest extends JFrame{
	private JTextField textField;
	
	public GUITest(){
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{1.0, 0.0};
		panel.setLayout(gbl_panel);
		
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		gbc_btnNewButton.fill=GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 1;
		gbc_btnNewButton.gridheight = 1;
		gbc_btnNewButton.weighty = 1.0;
		gbc_btnNewButton.ipadx = 50;
		gbc_btnNewButton.ipady = 50;
		
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		gbc_btnNewButton_1.fill=GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridwidth = 1;
		gbc_btnNewButton_1.gridheight = 1;
		
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.gridwidth = 1;
		
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		
		
		
		
	}
	
	public static void main(String[] args){
		
		GUITest layout = new GUITest();
		layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout.setSize(1280, 800);
		layout.setResizable(false);
		layout.setVisible(true);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		layout.setLocation(dim.width /2 - layout.getSize().width /2, dim.height /2 -layout.getSize().height /2);
		
	}

}
