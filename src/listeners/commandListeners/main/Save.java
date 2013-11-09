package listeners.commandListeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Controller;

public class Save implements ActionListener {
	int idGraph;
	Controller controller;
	
	public Save(int idGraph, Controller controller) {
		this.idGraph = idGraph;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.save(idGraph);
	}
}
