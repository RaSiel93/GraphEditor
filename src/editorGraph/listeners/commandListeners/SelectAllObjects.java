package listeners.commandListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Controller;

public class SelectAllObjects implements ActionListener {
	Controller controller;
	
	public SelectAllObjects(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.activateAll();
	}
}
