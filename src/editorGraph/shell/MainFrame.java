package editorGraph.shell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import editorGraph.controller.Controller;
import editorGraph.listeners.commandListeners.RemoveSelectedObjects;
import editorGraph.listeners.commandListeners.SelectAllObjects;
import editorGraph.listeners.commandListeners.algoritm.AlgoritmRun;
import editorGraph.listeners.commandListeners.main.Close;
import editorGraph.listeners.commandListeners.main.Create;
import editorGraph.listeners.commandListeners.main.Exit;
import editorGraph.listeners.commandListeners.main.Open;
import editorGraph.listeners.commandListeners.main.Save;
import editorGraph.listeners.commandListeners.mode.EnabledEdgeMode;
import editorGraph.listeners.commandListeners.mode.EnabledEditLabelMode;
import editorGraph.listeners.commandListeners.mode.EnabledVertexMode;
import editorGraph.listeners.eventListeners.windows.WindowEvents;

public class MainFrame extends JFrame {
	private static final int DEFAUT_WIDTH = 800;
	private static final int DEFAUT_HEIGHT = 600;

	private Controller controller;
	private Map<String, ActionListener> listeners;

	private JTabbedPane tabbedPane;
	private JScrollPane scrollPane;

	public MainFrame(Controller controller) {
		setTitle("Editor Graph");
		setSize(DEFAUT_WIDTH, DEFAUT_HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.controller = controller;
		listeners = initListeners();

		addWindowListener(new WindowEvents());
		MenuPanel menuPanel = new MenuPanel(listeners);
		ButtonPanel buttonPanel = new ButtonPanel(listeners);

		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.setBorder(BorderFactory.createLineBorder(Color.black));

		// this.scrollPane = new JScrollPane(tabbedPane);
		// scrollPane
		// .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// scrollPane
		// .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// scrollPane.setAutoscrolls(true);

		Container contentPane = getContentPane();
		contentPane.add(menuPanel, BorderLayout.NORTH);
		contentPane.add(buttonPanel, BorderLayout.WEST);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private Map<String, ActionListener> initListeners() {

		Map<String, ActionListener> listeners = new HashMap<String, ActionListener>();
		listeners.put("VERTEX_MODE", new EnabledVertexMode(this));
		listeners.put("EDGE_MODE", new EnabledEdgeMode(this));
		listeners.put("EDIT_MODE", new EnabledEditLabelMode(this));
		listeners.put("CREATE", new Create(controller));
		listeners.put("OPEN", new Open(controller));
		listeners.put("CLOSE", new Close(controller));
		listeners.put("SAVE", new Save(controller));
		listeners.put("EXIT", new Exit(controller));
		listeners.put("SELECT_ALL", new SelectAllObjects(controller));
		listeners.put("REMOVE", new RemoveSelectedObjects(controller));
		listeners.put("ALGO_RUN", new AlgoritmRun(controller));
		// listeners.put("STEP_ALGO", new AlgoritmStep(controller));
		// listeners.put("STOP_ALGO", new AlgoritmStop(controller));

		return listeners;
	}

	public int getCurrentIdGraph() {
		if (getCurrentPanel() != null) {
			return getCurrentPanel().getId();
		}
		return -1;
	}

	EditionPanel getCurrentPanel() {
		if (tabbedPane.getSelectedIndex() != -1) {
			return (EditionPanel) ((JScrollPane) tabbedPane
					.getComponent(tabbedPane.getSelectedIndex())).getViewport()
					.getView();
		}
		return null;
	}

	public void enableVertexMode() {
		EditionPanel editionPanel = getCurrentPanel();
		if(editionPanel != null){
			editionPanel.enableVertexMode();
		}
	}

	public void enableEdgeMode() {
		EditionPanel editionPanel = getCurrentPanel();
		if(editionPanel != null){
			editionPanel.enableEdgeMode();
		}
	}

	public void enableEditLabelMode() {
		EditionPanel editionPanel = getCurrentPanel();
		if(editionPanel != null){
			editionPanel.enableEditMode();
		}
	}

	public void addTab(int id, String header) {
		tabbedPane.addTab(header, new JScrollPane(new EditionPanel(id,
				controller)));
		tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
		enableVertexMode();
	}

	public void removeTab() {
		tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
	}
}