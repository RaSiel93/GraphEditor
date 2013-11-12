package listeners.eventListeners;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;

public class MouseAdditionEdge extends MouseAdapter {
	Controller controller;

	public MouseAdditionEdge(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if (event.getModifiers() == InputEvent.BUTTON1_MASK
				|| event.isControlDown() || event.isShiftDown()) {
			if (controller.checkPointIfVertex(event.getPoint())) {
				if (!controller.getCurrentGraph().checkExistsTempEdge()) {
					controller.getCurrentGraph().setBeginTempEdge(event.getPoint());
				} else {
					if (controller.getCurrentGraph().checkPossibilityEdge(event.getPoint())) {
						controller.getCurrentGraph().addTemporaryEdgeInGraph(event.getPoint());
						controller.removeTempEdge();
					}
					if (event.isShiftDown()) {
						controller.getCurrentGraph().setBeginTempEdge(event.getPoint());
					}
				}
			}
		}
		if (event.getModifiers() == InputEvent.BUTTON3_MASK) {
			controller.removeTempEdge();
			controller.getCurrentGraph().deactivateAll();
		}
		controller.repaint();
	}
	///relise for passible create edge dragabble
}
