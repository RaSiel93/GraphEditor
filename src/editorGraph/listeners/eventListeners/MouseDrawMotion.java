package listeners.eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseDrawMotion implements MouseMotionListener {
	Controller controller;

	public MouseDrawMotion(Controller controller) {
		this.controller = controller;
	}

	public void mouseDragged(MouseEvent event) {
		controller.repaint();
	}

	public void mouseMoved(MouseEvent event) {
		controller.repaint();
	}
}
