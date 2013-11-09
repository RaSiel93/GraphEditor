package shell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Listeners {
	private Map<String, ActionListener> listeners;
	
	private ActionListener enabledVertexMode;
	private ActionListener enabledEdgeMode;
	private ActionListener enabledEditMode;
	private ActionListener createFile;
	private ActionListener openFromFile;
	private ActionListener saveInFile;
	private ActionListener exitFromProgramm;
	private ActionListener selectAllObjects;
	private ActionListener removeSelectedObjects;
	private ActionListener runAlgo;
	private ActionListener stepAlgo;
	private ActionListener breakAlgo;
	
	Listeners() {
		enabledVertexMode = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				mainFrame.enableVertexMode();
			}
		};
		enabledEdgeMode = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				mainFrame.enableEdgeMode();
			}
		};
		enabledEditMode = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				mainFrame.enableEditMode();
			}
		};
		createFile = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				createGraph();
			}
		};
		openFromFile = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				removeTempEdge();
//				try {
//					loadFile();
//				} catch (ClassNotFoundException e1) {
//					e1.printStackTrace();
//				}
			}
		};
		saveInFile = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				removeTempEdge();
//				try {
//					saveFile();
//				} catch (ClassNotFoundException e1) {
//					e1.printStackTrace();
//				}
			}
		};
		exitFromProgramm = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};

		selectAllObjects = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				selectAllObject();
			}
		};
		removeSelectedObjects = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				removeSelectedObjects();
			}
		};
		runAlgo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				removeTempEdge();
//				try {
//					setAlgoritmFlag(true);
//					runAlgoritm();
//					setAlgoritmFlag(false);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
			}
		};
		stepAlgo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// setAlgoritmFlag(true);
			}
		};
		breakAlgo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				setAlgoritmFlag(false);
			}
		};

		listeners = new HashMap<String, ActionListener>();

		
	}
	public ActionListener getMethod(String method) {
		return listeners.get(method);
	}
}
