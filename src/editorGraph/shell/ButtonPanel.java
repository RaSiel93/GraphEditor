package editorGraph.shell;

import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JPanel;

class ButtonPanel extends JPanel {
	public ButtonPanel(Map<String, ActionListener> listeners) {
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(100));
		for (final ButtonEnum button : ButtonEnum.values()) {
			Button buttonComponent = new Button(button.getLabel());
			buttonComponent
					.addActionListener(listeners.get(button.getMethod()));
			box.add(buttonComponent);
			box.add(Box.createVerticalStrut(10));
		}
		add(box);
	}
}
