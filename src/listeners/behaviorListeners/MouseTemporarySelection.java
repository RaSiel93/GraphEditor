package listeners.behaviorListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;



public class MouseTemporarySelection implements MouseMotionListener {
    public MouseTemporarySelection(Controller c) {
	controller = c;
    }

    public void mouseDragged(MouseEvent event) {
    }

    public void mouseMoved(MouseEvent event) {
	if (event.getModifiers() != event.BUTTON1_MASK) {
	    if (!controller.checkPointIfEmpty(event.getPoint())) {
		controller.actualOff();
		controller.selectActualObject(event.getPoint());
		controller.actualOn();
	    } else {
		controller.actualOff();
	    }
	}
    }

    Controller controller;
}