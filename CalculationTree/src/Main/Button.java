package Main;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;

public class Button extends JButton{
	public Button(String name){
		super(name);
		setPreferredSize(new Dimension(40, 40));
		setMargin(new Insets(0, 0, 0, 0));
	}
}
