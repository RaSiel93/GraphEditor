package listeners.eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseTemporarySelection implements MouseMotionListener {
	Controller controller;

	public MouseTemporarySelection(Controller controller) {
		this.controller = controller;
	}

	public void mouseDragged(MouseEvent event) {
	}

	public void mouseMoved(MouseEvent event) {
		controller.getCurrentGraph().setActualObject(event.getPoint());
		controller.repaint();
	}
}