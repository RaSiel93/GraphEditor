package listeners.behaviorListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;



public class MouseDragObjects implements MouseMotionListener {
    public MouseDragObjects(Controller c) {
	controller = c;
    }

    public void mouseDragged(MouseEvent event) {
	if (event.getModifiers() == event.BUTTON1_MASK || event.isControlDown()
		|| event.isShiftDown()) {
	    if (!controller.checkPointIfEmpty(event.getPoint())
		    && !controller.isStatusSelection()) {
		controller.setStatusDragged(true);
	    }
	    if (controller.isStatusDragged()) {
		controller.dragObjects(event.getPoint());
		if (controller.checkExistsTempEdge()) {
		    controller.setEndTempEdge(event.getPoint());
		}
	    }
	}
    }

    public void mouseMoved(MouseEvent event) {
	controller.setPointDragged(event.getPoint());
    }

    Controller controller;
}
