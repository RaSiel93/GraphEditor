package editorGraph.listeners.commandListeners.algoritm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorGraph.controller.Controller;

public class AlgoritmRun implements ActionListener {
	Controller controller;

	public AlgoritmRun(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.runAlgoritm();
		controller.repaint();
	}
}
