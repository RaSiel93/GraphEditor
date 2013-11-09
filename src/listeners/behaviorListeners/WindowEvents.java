package listeners.behaviorListeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

public class WindowEvents implements WindowListener {
    public void windowActivated(WindowEvent event) {
    }

    public void windowClosed(WindowEvent event) {
    }

    public void windowClosing(WindowEvent event) {
	Object[] options = { "Да", "Нет!" };
	int n = JOptionPane.showOptionDialog(event.getWindow(),
		"Закрыть окно?", "Подтверждение", JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	if (n == 0) {
	    event.getWindow().setVisible(false);
	    System.exit(0);
	}
    }

    public void windowDeactivated(WindowEvent event) {
    }

    public void windowDeiconified(WindowEvent event) {
    }

    public void windowIconified(WindowEvent event) {
    }

    public void windowOpened(WindowEvent event) {
    }
}
