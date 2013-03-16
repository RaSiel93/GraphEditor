package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.Controller;

public class MouseAdapterMotion implements MouseMotionListener{
	public MouseAdapterMotion(Controller c){
		controller = c;
	}
	@Override
	public void mouseDragged(MouseEvent event) {
		if(event.getModifiers() == event.BUTTON1_MASK || event.isControlDown() || event.isShiftDown()){
			controller.draggedObjects(event.getPoint());
			controller.setMotion(true);
		}
	}
	public void mouseMoved(MouseEvent event) {
		if(event.getModifiers() != event.BUTTON1_MASK){
			controller.setPointShift(event.getPoint());
			if(!controller.checkPointIfEmpty(event.getPoint())){
				controller.tempararyEphasisOff();
				controller.selectObject(event.getPoint());
				controller.tempararyEphasisOn();
			}
			else {
				controller.tempararyEphasisOff();
			}
		}
	}
	
	Controller controller;
}
