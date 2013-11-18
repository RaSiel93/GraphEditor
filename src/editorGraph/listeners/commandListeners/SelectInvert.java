package editorGraph.listeners.commandListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorGraph.controller.Controller;

public class SelectInvert implements ActionListener {
	Controller controller;
	
	public SelectInvert(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.invertSelection();
		controller.repaint();
	}
}
