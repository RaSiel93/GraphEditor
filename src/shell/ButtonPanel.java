package shell;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

class ButtonPanel extends JPanel {
	private EditionPanel editionPanel;

	public ButtonPanel(EditionPanel ep) {
		editionPanel = ep;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(80, 0));
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(80));
		//box.setPreferredSize(new Dimension(45, 0));
		box.setMaximumSize(new Dimension(80, 300));
		add(box);

		
		ImageIcon iconVertex = new ImageIcon("buttonVertex4.gif");
		//Image imgVertex = iconVertex.getImage();
	    //iconVertex = new ImageIcon(imgVertex.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH ));
		
	    ImageIcon iconEdge = new ImageIcon("buttonEdge4.gif");
	    //Image imgEdge = iconEdge.getImage();
        //iconEdge = new ImageIcon(imgEdge.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH ));
	    
		
		JButton buttonVertex = new JButton(iconVertex);
		buttonVertex.setMinimumSize(new Dimension(20, 40));
		buttonVertex.setMaximumSize(new Dimension(80, 80));
		buttonVertex.setPreferredSize(new Dimension(64, 64));
		//buttonVertex.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buttonVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editionPanel.enableVertexMode();
			}
		});

		JButton buttonEdge = new JButton(iconEdge);
		buttonEdge.setMinimumSize(new Dimension(20, 20));
		buttonEdge.setMaximumSize(new Dimension(80, 80));
		buttonEdge.setPreferredSize(new Dimension(64, 64));
		//buttonEdge.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buttonEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editionPanel.enableEdgeMode();
			}
		});

		JButton buttonRevers = new JButton("Πεβεπρ");
		buttonRevers.setMaximumSize(new Dimension(80, 30));
		buttonRevers.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		box.add(buttonVertex);
		box.add(Box.createVerticalStrut(2));
		box.add(buttonEdge);
		box.add(Box.createVerticalStrut(2));
		//box.add(buttonRevers);
	}
}
