package listeners.eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseTemporarySelection implements MouseMotionListener {
	int idGraph;
	Controller controller;

	public MouseTemporarySelection(int idGraph, Controller controller) {
		this.idGraph = idGraph;
		this.controller = controller;
	}

	public void mouseDragged(MouseEvent event) {
	}

	public void mouseMoved(MouseEvent event) {
		if (event.getModifiers() != event.BUTTON1_MASK) {
			if (!controller.checkPointIfEmpty(event.getPoint())) {
				controller.getGraph(idGraph).actualOff();
				controller.getGraph(idGraph).selectActualObject(
						event.getPoint());
				controller.getGraph(idGraph).actualOn();
			} else {
				controller.getGraph(idGraph).actualOff();
			}
		}
	}
}