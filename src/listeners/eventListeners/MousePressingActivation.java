package listeners.eventListeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;

public class MousePressingActivation extends MouseAdapter {
	Boolean isDeactiv = false;
	Controller controller;

	public MousePressingActivation(Controller controller) {
		this.controller = controller;
	}

	public void mousePressed(MouseEvent event) {
		// if(!controller.isStatusDragged() && !controller.isStatusSelection()){
		if (controller.isObject(event.getPoint())
				&& !event.isControlDown()) {
			controller.getCurrentGraph().deactivateAll();
		}
		if (!controller.isObject(event.getPoint())
				|| event.isControlDown()) {
			if (controller.checkIfActive(event.getPoint())) {
				if (event.isControlDown()) {
					// controller.deactivateObject(event.getPoint());
					isDeactiv = true;
				} else {
					// controller.deactivateAllObject();
					// controller.activateObject(event.getPoint());
				}
			} else {
				if (event.isControlDown()) {
					controller.getCurrentGraph().activate(event.getPoint());
				} else {
					controller.getCurrentGraph().deactivateAll();
					controller.getCurrentGraph().activate(event.getPoint());
				}
			}
		}
		// }
	}

	public void mouseReleased(MouseEvent event) {
		if (!controller.isDragged() && !controller.isSelection()) {
			if (controller.isObject(event.getPoint())
					&& !event.isControlDown()) {
				controller.getCurrentGraph().deactivateAll();
				controller.repaint();
			}
			if (!controller.isObject(event.getPoint())
					|| event.isControlDown()) {
				if (controller.checkIfActive(event.getPoint())) {
					if (event.isControlDown() && isDeactiv) {
						controller.getCurrentGraph().deactivate(event.getPoint());
						isDeactiv = false;
					} else if (!event.isControlDown()) {
						controller.getCurrentGraph().deactivateAll();
						controller.getCurrentGraph().activate(event.getPoint());
					}
				} else {
					if (event.isControlDown()) {
						// controller.activateObject(event.getPoint());
					} else {
						// controller.deactivateAllObject();
						// controller.activateObject(event.getPoint());
					}
				}
			}
		}
		controller.setSelection(false);
		controller.setDragged(false);
	}
}
