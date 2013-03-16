package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;

public class MouseAdapterSelected extends MouseAdapter {
	public MouseAdapterSelected(Controller c){
		controller = c;
	}
	public void mousePressed(MouseEvent event) {
		if(controller.checkPointIfEmpty(event.getPoint()) && !event.isControlDown()){
			controller.deactivateAllObject();
		}
		if(!controller.checkIfActive(event.getPoint()) && !event.isControlDown()){
			controller.deactivateAllObject();
			controller.activateObject(event.getPoint());
			controller.setMotion(true);
		}
			
	}
	public void mouseReleased(MouseEvent event) {
		if (event.getModifiers() == event.BUTTON1_MASK || event.isControlDown()) {
			if(!controller.isMotion()){
				if(event.getClickCount() == 1){
					if(!event.isControlDown()) {
						if(controller.checkIfActive(event.getPoint()) && controller.countActiveObjects() == 1){
							controller.deactivateAllObject();
							controller.activateObject(event.getPoint());
						} else {
							controller.deactivateAllObject();
						}
					}
					if(controller.checkIfActive(event.getPoint())){
						controller.deactivateObject(event.getPoint());
					} else {
						controller.activateObject(event.getPoint());
					}
				} 
				if(event.getClickCount() == 2){
					//changeNameOrLengthObject();
				}	
			}
			controller.setMotion(false);
		}
	}
	Controller controller;
}
