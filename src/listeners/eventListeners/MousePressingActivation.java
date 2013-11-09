package listeners.eventListeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;



public class MousePressingActivation extends MouseAdapter {
    public MousePressingActivation(int idGraph, Controller c) {
	controller = c;
    }

    public void mousePressed(MouseEvent event) {
	// if(!controller.isStatusDragged() && !controller.isStatusSelection()){
	if (controller.checkPointIfEmpty(event.getPoint())
		&& !event.isControlDown()) {
	    controller.deactivateAllObject();
	}
	if (!controller.checkPointIfEmpty(event.getPoint())
		|| event.isControlDown()) {
	    if (controller.checkIfActive(event.getPoint())) {
		if (event.isControlDown()) {
		    // controller.deactivateObject(event.getPoint());
		    isDeactiv = true;
		} else {
		    // controller.deactivateAllObject();
		    // controller.activateObject(event.getPoint());
		}
	    } else {
		if (event.isControlDown()) {
		    controller.activateObject(event.getPoint());
		} else {
		    controller.deactivateAllObject();
		    controller.activateObject(event.getPoint());
		}
	    }
	}
	// }
    }

    public void mouseReleased(MouseEvent event) {
	if (!controller.isStatusDragged() && !controller.isStatusSelection()) {
	    if (controller.checkPointIfEmpty(event.getPoint())
		    && !event.isControlDown()) {
		// controller.deactivateAllObject();
	    }
	    if (!controller.checkPointIfEmpty(event.getPoint())
		    || event.isControlDown()) {
		if (controller.checkIfActive(event.getPoint())) {
		    if (event.isControlDown() && isDeactiv) {
			controller.deactivateObject(event.getPoint());
			isDeactiv = false;
		    } else if (!event.isControlDown()) {
			controller.deactivateAllObject();
			controller.activateObject(event.getPoint());
		    }
		} else {
		    if (event.isControlDown()) {
			// controller.activateObject(event.getPoint());
		    } else {
			// controller.deactivateAllObject();
			// controller.activateObject(event.getPoint());
		    }
		}
	    }
	}
	controller.setStatusSelection(false);
	controller.setStatusDragged(false);
    }

    Boolean isDeactiv = false;
    Controller controller;
}
