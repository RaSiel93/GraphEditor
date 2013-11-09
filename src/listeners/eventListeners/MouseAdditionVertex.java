package listeners.eventListeners;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;

public class MouseAdditionVertex extends MouseAdapter {
	Controller controller;
	
	public MouseAdditionVertex(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if (event.getModifiers() == InputEvent.BUTTON1_MASK
				|| event.isControlDown() || event.isShiftDown()) {
			if (event.getClickCount() == 2 || event.isShiftDown()) {
				if (controller.checkPointIfEmpty(event.getPoint())) {
					controller.addVertex(event.getPoint());
					// controller.activateObject(event.getPoint());
					// controller.setMotion(true);
				}
			}
		}
	}

}