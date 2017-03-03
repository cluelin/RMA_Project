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

public class MainThread extends JFrame{
	
	
	
	
	
	private CommonPanel commonPanel;
	
	
	
	private JPanel panel_7;
	
	
	
	
	
	public MainThread(){
		super("RMA");
		setSize(new Dimension(1280, 800));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		loadCommonPanel();
		loadAdvancedReplacementPanel();
		
		
		
	}
	
	
	private void loadCommonPanel(){
		
		commonPanel = new CommonPanel();
		
		getContentPane().add(commonPanel, BorderLayout.CENTER);
		
		
		
	}
	
	private void loadAdvancedReplacementPanel(){
		
		
		panel_7 = new AdvancedReplacementPanel();
		commonPanel.addVariablePanel(panel_7);
		
		
	}
	
	
	public static void main(String[] args){
		MainThread layout = new MainThread();
		layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout.setSize(1280, 800);
//		layout.setResizable(false);
		layout.setVisible(true);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		layout.setLocation(dim.width /2 - layout.getSize().width /2, dim.height /2 -layout.getSize().height /2);
	}
}
