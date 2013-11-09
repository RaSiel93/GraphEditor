package listeners.commandListeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Controller;

public class Open implements ActionListener {
	Controller controller;
	
	public Open(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.open();
	}
}
