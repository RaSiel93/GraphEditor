package listeners.commandListeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Controller;

public class Open implements ActionListener {
	int idGraph;
	Controller controller;
	
	public Open(int idGraph, Controller controller) {
		this.idGraph = idGraph;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.open(idGraph);
	}
}
