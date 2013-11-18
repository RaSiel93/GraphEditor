package listeners.commandListeners.mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import shell.MainFrame;

import main.Controller;

public class EnabledVertexMode implements ActionListener {
	MainFrame mainFrame;

	public EnabledVertexMode(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.enableVertexMode();
	}
}
