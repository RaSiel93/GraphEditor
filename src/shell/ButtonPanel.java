package shell;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;

import main.Controller;

class ButtonPanel extends JPanel {
    public ButtonPanel(Controller controller) {
	// setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	// setPreferredSize(new Dimension(110, 0));
	Box box = Box.createVerticalBox();
	box.add(Box.createVerticalStrut(100));

	for (final ButtonEnum but : ButtonEnum.values()) {
	    Button button = new Button(but.getLabel());
	    button.addActionListener((ActionListener) controller.getMethod(but
		    .getMethod()));
	    box.add(button);
	    box.add(Box.createVerticalStrut(10));
	}
	add(box);
    }
}
