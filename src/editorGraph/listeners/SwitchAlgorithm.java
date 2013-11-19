package editorGraph.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorGraph.algoritm.EnumAlgorithms;
import editorGraph.controller.Controller;

public class SwitchAlgorithm implements ActionListener {
	Controller controller;
	EnumAlgorithms algorithm;

	public SwitchAlgorithm(Controller controller, EnumAlgorithms algorithm) {
		this.controller = controller;
		this.algorithm = algorithm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.switchAlgorithm(algorithm);
	}
}
