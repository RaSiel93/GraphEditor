package shell;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import listeners.WindowEvents;
import main.Controller;

//import listeners.ControllerFunctions;

public class MainFrame extends JFrame {
    private static final int DEFAUT_WIDTH = 800;
    private static final int DEFAUT_HEIGHT = 600;
    private EditionPanel editionPanel;

    public MainFrame(Controller controller) {

	setTitle("Editor Graph");
	setSize(DEFAUT_WIDTH, DEFAUT_HEIGHT);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	WindowEvents windowsEvents = new WindowEvents();
	addWindowListener(windowsEvents);
	this.editionPanel = new EditionPanel(controller);

	MenuPanel menuPanel = new MenuPanel(controller);
	ButtonPanel buttonPanel = new ButtonPanel(controller);

	Container contentPane = getContentPane();

	buttonPanel.setFocusable(false);
	menuPanel.setFocusable(false);
	editionPanel.setFocusable(true);

	contentPane.add(menuPanel, BorderLayout.NORTH);
	contentPane.add(buttonPanel, BorderLayout.WEST);
	contentPane.add(editionPanel);
	getContentPane().validate();
	getContentPane().repaint();

	editionPanel.setLayout(null);

	JScrollPane scrollPane = new JScrollPane(editionPanel);
	scrollPane
		.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	scrollPane
		.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	scrollPane.setAutoscrolls(true);
	contentPane.add(scrollPane, BorderLayout.CENTER);

	editionPanel.setFocusable(true);
	setLocationRelativeTo(null);
	setVisible(true);
    }

    public void paint() { // editionPanel.update(getGraphics()); //
	editionPanel.repaint();// paintComponent(getGraphics());
    }

    public void enableVertexMode() {
	editionPanel.enableVertexMode();
    }

    public void enableEdgeMode() {
	editionPanel.enableEdgeMode();
    }

    public void enableEditMode() {
	editionPanel.enableEditMode();
    }
}