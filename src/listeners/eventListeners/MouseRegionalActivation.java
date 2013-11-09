package listeners.eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseRegionalActivation implements MouseMotionListener {
	Controller controller;

	public MouseRegionalActivation(Controller controller) {
		this.controller = controller;
	}

	public void mouseDragged(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK
				&& !controller.isStatusDragged()
				&& !controller.getCurrentGraph().checkExistsTempEdge() || event.isControlDown()) {
			if (controller.checkPointIfEmpty(event.getPoint())
					&& !controller.isStatusSelection()) {
				controller.setStatusSelection(true);
				controller.setPointSelectionBegin(event.getPoint());
				controller.setPointSelectionEnd(event.getPoint());
			}
			if (controller.isStatusSelection()) {
				controller.setPointSelectionEnd(event.getPoint());
				controller.setSelectionObjects();
			}
		}
	}

	public void mouseMoved(MouseEvent event) {
	}

}