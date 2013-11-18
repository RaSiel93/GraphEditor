package editorGraph.listeners.eventListeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import editorGraph.controller.Controller;

public class MousePressingSelection extends MouseAdapter {
	Boolean isDeactive = false;
	Controller controller;

	public MousePressingSelection(Controller controller) {
		this.controller = controller;
	}

	public void mousePressed(MouseEvent event) {
		if (!controller.isObject(event.getPoint()) && !event.isControlDown()) {
			controller.getCurrentGraph().deselectAll();
		} else {
			if (controller.checkSelectedObject(event.getPoint())) {
				if (event.isControlDown()) {
					isDeactive = true;
				}
			} else {
				if (event.isControlDown()) {
					controller.getCurrentGraph().select(event.getPoint());
				} else {
					controller.getCurrentGraph().deselectAll();
					controller.getCurrentGraph().select(event.getPoint());
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
			if (!event.isControlDown() && controller.checkSelectedObject(event.getPoint())) {
				controller.getCurrentGraph().deselectAll();
				controller.getCurrentGraph().select(event.getPoint());				
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
