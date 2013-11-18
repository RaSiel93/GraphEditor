package editorGraph.listeners.eventListeners.modes;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import editorGraph.controller.Controller;

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
				if (!controller.isVertex(event.getPoint())) {
					controller.addVertex(event.getPoint());
					controller.getCurrentGraph().setActualObject(event.getPoint());
					controller.repaint();
				}
			}
		}
	}

}