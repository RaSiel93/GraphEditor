package by.bsuir.II;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MouseAdapterEdge extends MouseAdapter {
	public MouseAdapterEdge(Controller c){
		controller = c;
	}
	public void mousePressed(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK) {
			//if (false)
				//controller.deselectAllObject();
			if (!controller.addEdge(event)){
				controller.selectObject(event);
			}
		}
		// if(event.getModifiers() == event.BUTTON3_MASK &&
		// controller.getVertexes().size() >= 2){
		/*
		 * if(!controller.addEdgeBegin(event)){
		 * controller.addEdgeEnd(event); }
		 */
		// }
	}
	public void mouseClicked(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK && event.getClickCount() >= 2) {
			// controller.remove(event);
		}
	}
	private Controller controller;
}

