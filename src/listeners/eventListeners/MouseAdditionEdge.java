package listeners.eventListeners;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;

public class MouseAdditionEdge extends MouseAdapter {
    public MouseAdditionEdge(Controller c) {
	controller = c;
    }

    @Override
    public void mousePressed(MouseEvent event) {
	if (event.getModifiers() == InputEvent.BUTTON1_MASK
		|| event.isControlDown() || event.isShiftDown()) {
	    if (controller.checkPointIfVertex(event.getPoint())) {
		if (!controller.checkExistsTempEdge()) {
		    controller.setBeginTempEdge(event.getPoint());
		} else {
		    if (controller.checkPossibilityEdge(event.getPoint())) {
			controller.addEdge(event.getPoint());
			controller.removeTempEdge();
		    }
		    if (event.isShiftDown()) {
			controller.setBeginTempEdge(event.getPoint());
		    }
		}
	    }
	}
	if (event.getModifiers() == InputEvent.BUTTON3_MASK) {
	    controller.removeTempEdge();
	    controller.deactivateAllObject();
	}
    }

    private Controller controller;
}
