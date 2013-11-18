package editorGraph.listeners.eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import editorGraph.controller.Controller;
import editorGraph.shell.MainFrame;

public class MouseRegionalSelection implements MouseMotionListener {
	Controller controller;

	public MouseRegionalSelection(Controller controller) {
		this.controller = controller;
	}

	public void mouseDragged(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK || event.isControlDown()) {
			if (controller.isPassibleSelection() && !controller.isObject(event.getPoint())) {
				controller.setSelection(true);
				controller.setPointSelectionBegin(event.getPoint());
			}
			if (controller.isSelection()) {
				controller.setPointSelectionEnd(event.getPoint());
				controller.setSelectionObjects();
			}
			controller.repaint();
		}
	}

	public void mouseMoved(MouseEvent event) {
	}
}