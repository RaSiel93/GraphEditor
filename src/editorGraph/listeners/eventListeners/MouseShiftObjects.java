package listeners.eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseShiftObjects implements MouseMotionListener {
	Controller controller;

	public MouseShiftObjects(Controller controller) {
		this.controller = controller;
	}

	public void mouseDragged(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK || event.isControlDown() || event.isShiftDown()) {
			if (controller.isPassibleDragged() && controller.isObject(event.getPoint())) {
				controller.setDragged(true);
			}
			if (controller.isDragged()) {
				controller.shiftObjects(event.getPoint());
			}
			controller.repaint();
		}
	}

	public void mouseMoved(MouseEvent event) {
		controller.setPointDragged(event.getPoint());
	}
}
