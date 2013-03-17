package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Controller;


public class MouseAdditionEdge extends MouseAdapter {
	public MouseAdditionEdge(Controller c){
		controller = c;
	}
	public void mousePressed(MouseEvent event) {
		if(event.getModifiers() == event.BUTTON1_MASK || event.isControlDown() || event.isShiftDown()){
			if(controller.checkPointIfVertex(event.getPoint())){
				if(!controller.checkExistsTempEdge()){
					controller.setBeginTempEdge(event.getPoint());
				} else {
					if(controller.checkPossibilityEdge(event.getPoint())){
						controller.addEdge(event.getPoint());
						controller.removeTempEdge();
					}
					if(event.isShiftDown()){
						controller.setBeginTempEdge(event.getPoint());
					}
				}
			}
		}
	}
	
	private Controller controller;
}

