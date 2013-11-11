package listeners.eventListeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Controller;

public class MouseDrawClicked extends MouseAdapter {
	Controller controller;

	public MouseDrawClicked(Controller controller) {
		this.controller = controller;
	}

	public void mousePressed(MouseEvent event) {
		controller.repaint();
	}
	
	public void mouseClicked(MouseEvent event) {
		controller.repaint();
	}
	
	public void mouseReleased(MouseEvent event) {
		controller.repaint();
	}
	
	public void mouseExited(MouseEvent event) {
		controller.repaint();
	}

	public void mouseEntered(MouseEvent event) {
		controller.repaint();
	}
}
