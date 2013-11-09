package shell;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;

public class Button extends JButton {
	Button(String label) {
		super(label);
		setMaximumSize(new Dimension(100, 30));
		setMargin(new Insets(1, 1, 1, 1));
		setAlignmentX(JComponent.CENTER_ALIGNMENT);
	}
}
