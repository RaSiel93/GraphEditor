package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseAdditionVertex extends MouseAdapter {
	public MouseAdditionVertex(Controller c){
		controller = c;
	}
	public void mousePressed(MouseEvent event) {
		if(event.getModifiers() == event.BUTTON1_MASK || event.isControlDown() || event.isShiftDown()) {
			if(event.getClickCount() == 2 || event.isShiftDown()){
				if(controller.checkPointIfEmpty(event.getPoint())){
					controller.addVertex(event.getPoint());
					//controller.activateObject(event.getPoint());
					//controller.setMotion(true);
				}
			}
		}
	}
	
	Controller controller;
}