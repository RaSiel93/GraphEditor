package by.bsuir.II;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	private EditionPanel editionPanel;

	public ButtonPanel(EditionPanel ep) {
		editionPanel = ep;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(90, 0));
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(60));
		box.setPreferredSize(new Dimension(90, 0));
		add(box);

		JButton buttonVertex = new JButton("Узел");
		buttonVertex.setMaximumSize(new Dimension(80, 30));
		buttonVertex.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buttonVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editionPanel.enableVertexMode();
			}
		});

		JButton buttonEdge = new JButton("Дуга");
		buttonEdge.setMaximumSize(new Dimension(80, 30));
		buttonEdge.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buttonEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editionPanel.enableEdgeMode();
			}
		});

		JButton buttonRevers = new JButton("Реверс");
		buttonRevers.setMaximumSize(new Dimension(80, 30));
		buttonRevers.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		box.add(buttonVertex);
		box.add(Box.createVerticalStrut(2));
		box.add(buttonEdge);
		box.add(Box.createVerticalStrut(2));
		//box.add(buttonRevers);
	}
}
