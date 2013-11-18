package editorGraph.listeners.eventListeners.modes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import editorGraph.controller.Controller;

public class MouseEditLabel extends MouseAdapter {
	Controller controller;

	public MouseEditLabel(Controller controller) {
		this.controller = controller;
	}

	public void mouseClicked(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK) {
			if (event.getClickCount() == 1
					&& controller.isVertex(event.getPoint())) {
				controller.renameVertex(event.getPoint());
			} else if (event.getClickCount() == 1
					&& controller.isEdge(event.getPoint())) {
				controller.resizeEdge(event.getPoint());
			}
		}
	}

}