package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Controller;


public class MouseAdapterEdge extends MouseAdapter {
	public MouseAdapterEdge(Controller c){
		controller = c;
	}
	public void mousePressed(MouseEvent event) {
		if(event.getModifiers() == event.BUTTON1_MASK) {
			if(controller.checkPointIfVertex(event.getPoint())){
				if(!controller.existsBeginEdge()){
					controller.setBeginEdge(event.getPoint());
				}
				else {
					controller.addEdge(event.getPoint());
				}
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

