package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;


public class PanelCalculation extends JPanel {
	private FrameMain frameMain;
	private JEditorPane display;
	private JButton buttonGenTree;
	PanelCalculation(FrameMain frameMain){
		this.frameMain = frameMain;
		setBackground(Color.GRAY);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 300));
		JPanel panelDigital = new PanelDigital(frameMain);
		add(panelDigital, BorderLayout.SOUTH);
		
		display = new JEditorPane();
		display.setPreferredSize(new Dimension(150, 20));
		
		buttonGenTree = new JButton("GenerateTree");
		buttonGenTree.addActionListener(new GenerateTree());		
		
		JPanel input = new JPanel();
		input.add(display);
		input.add(buttonGenTree);
		
		add(input, BorderLayout.NORTH);
	}
	
	class GenerateTree implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			frameMain.generationTreeCalculation(display.getText());
		}
	}

	public void setDisplay(String expression) {
		display.setText(expression);
	}
}
