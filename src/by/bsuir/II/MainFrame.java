package by.bsuir.II;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

class MainFrame extends JFrame{
	public MainFrame(Controller controller){
		//JFrame frame = new JFrame("Editor Graph");
		setSize(DEFAUT_WIDTH, DEFAUT_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuPanel menuPanel = new MenuPanel(controller);
		EditionPanel editionPanel = new EditionPanel(controller);
		ButtonPanel buttonPanel = new ButtonPanel(editionPanel);
		
		//WbuttonEdge = false;
		
		Container contentPane = getContentPane();
		
		contentPane.add(menuPanel, BorderLayout.NORTH);
		contentPane.add(buttonPanel, BorderLayout.WEST);
		contentPane.add(editionPanel);
		
		//pack();
		//frame.setPreferredSize(new Dimension(270, 225));
		//frame.pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public boolean buttonEdge;
	public static final int DEFAUT_WIDTH = 800;
	public static final int DEFAUT_HEIGHT = 600;
}
