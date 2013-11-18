package listeners.commandListeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Controller;

public class Create implements ActionListener {
	private Controller controller;
	
	public Create(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.create();
	}
}
