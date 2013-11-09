package listeners.behaviorListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;



public class MouseMotionTempEdge implements MouseMotionListener {
    public MouseMotionTempEdge(Controller c) {
	controller = c;
    }

    public void mouseDragged(MouseEvent event) {
	if (controller.checkExistsTempEdge()) {
	    controller.setEndTempEdge(event.getPoint());
	}
    }

    public void mouseMoved(MouseEvent event) {
	if (controller.checkExistsTempEdge()) {
	    controller.setEndTempEdge(event.getPoint());
	}
    }

    Controller controller;
}
