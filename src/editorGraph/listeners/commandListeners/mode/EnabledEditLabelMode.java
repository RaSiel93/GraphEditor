package editorGraph.listeners.commandListeners.mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editorGraph.controller.Controller;
import editorGraph.shell.MainFrame;

public class EnabledEditLabelMode implements ActionListener {
	MainFrame mainFrame;

	public EnabledEditLabelMode(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.enableEditLabelMode();
	}
}
