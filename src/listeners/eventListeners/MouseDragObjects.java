package listeners.eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseDragObjects implements MouseMotionListener {
	Controller controller;
	
	public MouseDragObjects(Controller controller) {
		this.controller = controller;
	}

	public void mouseDragged(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK || event.isControlDown()
				|| event.isShiftDown()) {
			if (!controller.checkObject(event.getPoint())
					&& !controller.isStatusSelection()) {
				controller.setStatusDragged(true);
			}
			if (controller.isStatusDragged()) {
				controller.dragObjects(event.getPoint());
				if (controller.getCurrentGraph().checkExistsTempEdge()) {
					controller.getCurrentGraph().setEndTempEdge(event.getPoint());
				}
			}
		}
	}

	public void mouseMoved(MouseEvent event) {
		controller.setPointDragged(event.getPoint());
	}
}
