package shell;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import listeners.WindowEvents;
import main.Controller;

//import listeners.ControllerFunctions;

public class MainFrame extends JFrame {
	private static final int DEFAUT_WIDTH = 800;
	private static final int DEFAUT_HEIGHT = 600;
	private Controller controller;
	private List<EditionPanel> editionPanels;
	private JTabbedPane tabbedPane;
	int currentPanelIndex;

	public MainFrame(Controller controller) {
		this.controller = controller;
		setTitle("Editor Graph");
		setSize(DEFAUT_WIDTH, DEFAUT_HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowEvents windowsEvents = new WindowEvents();
		addWindowListener(windowsEvents);

		MenuPanel menuPanel = new MenuPanel(controller);
		ButtonPanel buttonPanel = new ButtonPanel(controller);

		Container contentPane = getContentPane();

		editionPanels = new ArrayList<EditionPanel>();

		this.tabbedPane = new JTabbedPane();
		// buttonPanel.setFocusable(false);
		// menuPanel.setFocusable(false);
		// editionPanel.setFocusable(true);

		contentPane.add(menuPanel, BorderLayout.NORTH);
		contentPane.add(buttonPanel, BorderLayout.WEST);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		// getContentPane().validate();
		// getContentPane().repaint();

		// editionPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(tabbedPane);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// editionPanel.setFocusable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	EditionPanel getCurrentPanel() {
		return editionPanels.get(currentPanelIndex);
	}

	public void paint() { // editionPanel.update(getGraphics()); //
		getCurrentPanel().repaint();// paintComponent(getGraphics());
	}

	public void enableVertexMode() {
		getCurrentPanel().enableVertexMode();
	}

	public void enableEdgeMode() {
		getCurrentPanel().enableEdgeMode();
	}

	public void enableEditMode() {
		getCurrentPanel().enableEditMode();
	}

	public void repaintPanel() {
		getCurrentPanel().repaint();
	}

	public void addTab(int index, String header) {
		currentPanelIndex = index;
		editionPanels.add(new EditionPanel(controller));
		tabbedPane.addTab(header, getCurrentPanel());
		tabbedPane.setSelectedIndex(currentPanelIndex);
	}
	public void removeTab(){
		tabbedPane.removeTabAt(currentPanelIndex);
		removePanel();
	}

	private void removePanel() {
		editionPanels.remove(currentPanelIndex);
		currentPanelIndex--;
	}
}