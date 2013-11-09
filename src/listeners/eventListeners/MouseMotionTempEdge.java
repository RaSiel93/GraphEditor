package listeners.eventListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseMotionTempEdge implements MouseMotionListener {
	Controller controller;

	public MouseMotionTempEdge(Controller controller) {
		this.controller = controller;
	}

	public void mouseDragged(MouseEvent event) {
		if (controller.getCurrentGraph().checkExistsTempEdge()) {
			controller.getCurrentGraph().setEndTempEdge(event.getPoint());
		}
	}

	public void mouseMoved(MouseEvent event) {
		if (controller.getCurrentGraph().checkExistsTempEdge()) {
			controller.getCurrentGraph().setEndTempEdge(event.getPoint());
		}
	}

}
