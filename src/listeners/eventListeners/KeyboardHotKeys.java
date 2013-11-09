package listeners.eventListeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Controller;

public class KeyboardHotKeys extends KeyAdapter {
	public KeyboardHotKeys(int idGraph, Controller c) {
		controller = c;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			controller.deactivateAllObject();
			if (controller.checkExistsTempEdge())
				controller.removeTempEdge();
		}
		if (!controller.isStatusDragged() && !controller.isStatusSelection()
				&& event.getKeyCode() == KeyEvent.VK_DELETE) {
			controller.removeSelectedObjects();
		}
		if (!controller.isStatusDragged() && !controller.isStatusSelection()
				|| event.isControlDown()) {
			if (event.getKeyCode() == KeyEvent.VK_A && event.isControlDown()) {
				controller.selectAllObject();
			}
			if (event.getKeyCode() == KeyEvent.VK_S && event.isControlDown()) {
				try {
					controller.saveFile();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
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

	private Controller controller;
}
