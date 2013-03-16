package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseAdapterVertex extends MouseAdapter {
	public MouseAdapterVertex(Controller c){
		controller = c;
	}
	public void mousePressed(MouseEvent event) {
		if(event.getModifiers() == event.BUTTON1_MASK || event.isControlDown() || event.isShiftDown()) {
			if(event.getClickCount() == 2 || event.isShiftDown()){
				if(controller.checkPointIfEmpty(event.getPoint())){
					controller.addVertex(event.getPoint());
				}
			}
		}
	}
	Controller controller;
}
	/*public void mousePressed(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK) {
			if(!controller.selectObject(event)){	
				controller.addVertex(event);
			}
		}
		// if(event.getModifiers() == event.BUTTON3_MASK &&
		// controller.getVertexes().size() >= 2){
		/*
		 * if(!controller.addEdgeBegin(event)){
		 * controller.addEdgeEnd(event); }
		 
		// }
	}
	public void mouseDragged(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK && event.getClickCount() >= 2) {
			// controller.remove(event);
		}
	}
	
	public void mouseClicked(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK && event.getClickCount() >= 2) {
			// controller.remove(event);
		}
	}
	private Controller controller;
}*/