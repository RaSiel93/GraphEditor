package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.TreeCalculation;

public class FrameMain extends JFrame {
	//private static int currentId = -1;

	//private FrameMain mainFrame;
	private static boolean useSystemLookAndFeel = false;
    
	//private JPanel panelTable;

	//private Map<String, ActionListener> listeners;
	private FrameMain frameMain;
	private JPanel panelTree;

	private JLabel inputDisplay;
	private PanelCalculation panelCalculation;
	
	public FrameMain(){//final Controller controller) {
		this.frameMain = this;
		if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout());

		//inputDisplay.setPreferredSize(new Dimension(200, 20));
		


		
		inputDisplay = new JLabel();
		setLayout(new BorderLayout());
		this.panelCalculation = new PanelCalculation(this);
		
		add(panelCalculation, BorderLayout.CENTER);
		
		panelTree = new JPanel();
		panelTree.setLayout(new BorderLayout());
		//panelTree.add(input, BorderLayout.NORTH);
		panelTree.add(inputDisplay, BorderLayout.SOUTH);
		
		getContentPane().add(panelTree, BorderLayout.WEST);
		getContentPane().add(panelCalculation, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void inDisplay(String expression){
		panelCalculation.setDisplay(expression);
	}
	
	void generationTreeCalculation(String expression){
		panelTree.removeAll();
		panelTree.add(new TreeCalculation(expression, frameMain));
		this.inputDisplay.setText(expression);
		revalidate();
	}   
}
