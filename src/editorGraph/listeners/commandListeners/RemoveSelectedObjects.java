package editorGraph.listeners.commandListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorGraph.controller.Controller;

public class RemoveSelectedObjects implements ActionListener {
	Controller controller;
	
	public RemoveSelectedObjects(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.removeSelectedObjects();
		controller.repaint();
	}
}
