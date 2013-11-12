package listeners.eventListeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;

public class MousePressingActivation extends MouseAdapter {
	Boolean isDeactive = false;
	Controller controller;

	public MousePressingActivation(Controller controller) {
		this.controller = controller;
	}

	public void mousePressed(MouseEvent event) {
		if (!controller.isObject(event.getPoint()) && !event.isControlDown()) {
			controller.getCurrentGraph().deactivateAll();
		} else {
			if (controller.checkActivateObject(event.getPoint())) {
				if (event.isControlDown()) {
					isDeactive = true;
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
		controller.repaint();
	}

	public void mouseReleased(MouseEvent event) {
		if (!controller.isDragged() && !controller.isSelection()) {
			// if (controller.isObject(event.getPoint()) &&
			// !event.isControlDown()) {
			// controller.getCurrentGraph().deactivateAll();
			// controller.repaint();
			// }
			if (event.isControlDown() && isDeactive) {
				controller.getCurrentGraph().deactivate(event.getPoint());
			}
			if (!event.isControlDown() && controller.checkActivateObject(event.getPoint())) {
				controller.getCurrentGraph().deactivateAll();
				controller.getCurrentGraph().activate(event.getPoint());				
			}
			// else if (!event.isControlDown()) {
			// controller.getCurrentGraph().deactivateAll();
			// controller.getCurrentGraph().activate(event.getPoint());
			// }
			// }
			// else {
			// if (event.isControlDown()) {
			// // controller.activateObject(event.getPoint());
			// } else {
			// // controller.deactivateAllObject();
			// // controller.activateObject(event.getPoint());
			// }
			// }
			// }
		}
		controller.repaint();

		isDeactive = false;
		controller.setSelection(false);
		controller.setDragged(false);
	}
}
