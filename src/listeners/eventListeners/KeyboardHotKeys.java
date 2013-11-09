package listeners.eventListeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Controller;

public class KeyboardHotKeys extends KeyAdapter {
	Controller controller;	
	
	public KeyboardHotKeys(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			controller.getCurrentGraph().deactivateAll();
			if (controller.getCurrentGraph().checkExistsTempEdge())
				controller.removeTempEdge();
		}
		if (!controller.isStatusDragged() && !controller.isStatusSelection()
				&& event.getKeyCode() == KeyEvent.VK_DELETE) {
			controller.removeSelectedObjects();
		}
		if (!controller.isStatusDragged() && !controller.isStatusSelection()
				|| event.isControlDown()) {
			if (event.getKeyCode() == KeyEvent.VK_A && event.isControlDown()) {
				controller.activateAll();
			}
			if (event.getKeyCode() == KeyEvent.VK_S && event.isControlDown()) {
				controller.save();
			}
		}
		if (!controller.isStatusDragged() && !controller.isStatusSelection()
				|| event.isControlDown()) {
			if (event.getKeyCode() == KeyEvent.VK_I) {
				controller.renameSelectedVertexes();
				controller.resizeSelectedEdges();
			}
		}
	}
}
