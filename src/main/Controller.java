package main;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import shell.MainFrame;

public class Controller {
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

	private Map<String, ActionListener> listeners;

	private List<Graph> graphs;
	private int currentGraph;
	
	private Algoritm algoritm;
	private MainFrame mainFrame;

	private int numActualVertex;
	private int numActualEdge;

	private Point pointSelectionBegin;
	private Point pointSelectionEnd;

	private Point pointDragged;

	private boolean dragged;
	private boolean selection;
	private boolean algoritmFlag;

	private Vertex beginTempEdge;
	private Point endTempEdge;

	public Controller() throws NoSuchMethodException,
			SecurityException {
		InitializeLesteners();

		this.graphs = new ArrayList<Graph>();

		algoritm = new Algoritm(this);

		numActualVertex = -1;
		numActualEdge = -1;

		pointSelectionBegin = new Point(0, 0);
		pointSelectionEnd = new Point(0, 0);

		pointDragged = new Point(0, 0);

		beginTempEdge = null;
		endTempEdge = new Point(0, 0);

		dragged = false;
		selection = false;
	}

	private void InitializeLesteners() {
		enabledVertexMode = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.enableVertexMode();
			}
		};
		enabledEdgeMode = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.enableEdgeMode();
			}
		};
		enabledEditMode = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.enableEditMode();
			}
		};
		createFile = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createGraph();
			}
		};
		openFromFile = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTempEdge();
				try {
					loadFile();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		saveInFile = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTempEdge();
				try {
					saveFile();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		exitFromProgramm = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};

		selectAllObjects = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAllObject();
			}
		};
		removeSelectedObjects = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSelectedObjects();
			}
		};
		runAlgo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTempEdge();
				try {
					setAlgoritmFlag(true);
					runAlgoritm();
					setAlgoritmFlag(false);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		};
		stepAlgo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// setAlgoritmFlag(true);
			}
		};
		breakAlgo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAlgoritmFlag(false);
			}
		};

		listeners = new HashMap<String, ActionListener>();

		listeners.put("VERTEX_MODE", this.enabledVertexMode);
		listeners.put("EDGE_MODE", this.enabledEdgeMode);
		listeners.put("EDIT_MODE", this.enabledEditMode);
		listeners.put("CREATE", this.createFile);
		listeners.put("OPEN", this.openFromFile);
		listeners.put("SAVE", this.saveInFile);
		listeners.put("EXIT", this.exitFromProgramm);
		listeners.put("SELECT_ALL", this.selectAllObjects);
		listeners.put("REMOVE", this.removeSelectedObjects);
		listeners.put("RUN_ALGO", this.runAlgo);
		listeners.put("STEP_ALGO", this.stepAlgo);
		listeners.put("BREAK_ALGO", this.breakAlgo);
	}

	public void repaint() {
		mainFrame.paint();
	}

	public ActionListener getMethod(String method) {
		return listeners.get(method);
	}

	public void setView(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		createGraph();
	}
	
	private Graph getCurrentGraph(){
		return graphs.get(currentGraph);
	}

	// ----------------------------------------
	public Point getMaxCoords() {
		return getCurrentGraph().getMaxCoords();
	}

	// ----------------------------------------
	public void setStatusDragged(boolean flag) {
		dragged = flag;
		repaint();
	}

	public boolean isStatusDragged() {
		return dragged;
	}

	public void setStatusSelection(boolean flag) {
		selection = flag;
		repaint();
	}

	public boolean isStatusSelection() {
		return selection;
	}

	public boolean isAlgoritmFlag() {
		return algoritmFlag;
	}

	public void setAlgoritmFlag(boolean algoritmFlag) {
		this.algoritmFlag = algoritmFlag;
	}

	// ----------------------------------------
	public int countVertex() {
		return getCurrentGraph().countVertex();
	}

	public Vertex getVertex(int i) {
		return getCurrentGraph().getVertex(i);
	}

	public int countEdge() {
		return getCurrentGraph().countEdge();
	}

	public Edge getEdge(int i) {
		return getCurrentGraph().getEdge(i);
	}

	// ----------------------------------------
	public void selectActualObject(Point p) {
		numActualVertex = getCurrentGraph().findVertex(getCurrentGraph().findVertex(p));
		numActualEdge = getCurrentGraph().findEdge(getCurrentGraph().findEdge(p));
	}

	public void actualOn() {
		if (numActualVertex != -1) {
			getCurrentGraph().getVertex(numActualVertex).actualOn();
		} else if (numActualEdge != -1) {
			getCurrentGraph().getEdge(numActualEdge).actualOn();
		}
		repaint();
	}

	public void actualOff() {
		if (numActualVertex != -1) {
			getCurrentGraph().getVertex(numActualVertex).actualOff();
		} else if (numActualEdge != -1) {
			getCurrentGraph().getEdge(numActualEdge).actualOff();
		}
		repaint();
	}

	// ----------------------------------------
	public void activateObject(Point p) {
		if (getCurrentGraph().findVertex(p) != null) {
			getCurrentGraph().findVertex(p).activeOn();
		} else if (getCurrentGraph().findEdge(p) != null) {
			getCurrentGraph().findEdge(p).activeOn();
		}
		repaint();
	}

	public void deactivateObject(Point p) {
		if (getCurrentGraph().findVertex(p) != null) {
			getCurrentGraph().findVertex(p).activeOff();
		} else if (getCurrentGraph().findEdge(p) != null) {
			getCurrentGraph().findEdge(p).activeOff();
		}
		repaint();
	}

	public int countActiveObjects() {
		int countObjects = 0;
		for (int numVertex = 0; numVertex < getCurrentGraph().countVertex(); numVertex++) {
			if (getCurrentGraph().getVertex(numVertex).isActivate())
				countObjects++;
		}
		for (int numEdge = 0; numEdge < getCurrentGraph().countEdge(); numEdge++) {
			if (getCurrentGraph().getEdge(numEdge).isActive())
				countObjects++;
		}
		return countObjects;
	}

	// ----------------------------------------
	public boolean checkPointIfEmpty(Point p) {
		if (getCurrentGraph().findVertex(p) == null && getCurrentGraph().findEdge(p) == null) {
			return true;
		}
		return false;
	}

	public boolean checkPointIfVertex(Point p) {
		if (getCurrentGraph().findVertex(p) != null)
			return true;
		return false;
	}

	public boolean checkPointIfEdge(Point p) {
		if (getCurrentGraph().findEdge(p) != null)
			return true;
		return false;
	}

	public boolean checkIfActive(Point p) {
		if (getCurrentGraph().findVertex(p) != null) {
			if (getCurrentGraph().findVertex(p).isActivate())
				return true;
		}
		if (getCurrentGraph().findEdge(p) != null) {
			if (getCurrentGraph().findEdge(p).isActive())
				return true;
		}
		return false;
	}

	// ----------------------------------------
	public void selectAllObject() {
		for (int numVertex = 0; numVertex < getCurrentGraph().countVertex(); numVertex++) {
			getCurrentGraph().getVertex(numVertex).activeOn();
		}
		for (int numEdge = 0; numEdge < getCurrentGraph().countEdge(); numEdge++) {
			getCurrentGraph().getEdge(numEdge).activeOn();
		}
		repaint();
	}

	public void deactivateAllObject() {
		for (int numVertex = 0; numVertex < getCurrentGraph().countVertex(); numVertex++) {
			getCurrentGraph().getVertex(numVertex).activeOff();
		}
		for (int numEdge = 0; numEdge < getCurrentGraph().countEdge(); numEdge++) {
			getCurrentGraph().getEdge(numEdge).activeOff();
		}
		repaint();
	}

	// ------------------------------------------
	public void renameVertex(Point p) {
		String name = JOptionPane
				.showInputDialog("Input name");
		if (name != null) {
			getCurrentGraph().findVertex(p).setName(name);
		}
	}

	public void renameSelectedVertexes() {
		for (int numVertex = 0; numVertex < getCurrentGraph().countVertex(); numVertex++) {
			if (getCurrentGraph().getVertex(numVertex).isActivate()) {
				String name = JOptionPane
						.showInputDialog("Input name");
				if (name != null) {
					getCurrentGraph().getVertex(numVertex).setName(name);
				}
			}
		}
	}

	public void resizeEdge(Point p) {
		String lenght = JOptionPane
				.showInputDialog("Input size");
		if (checkString(lenght) != -1) {
			getCurrentGraph().findEdge(p).resize(Integer.parseInt(lenght));
		}
	}

	public void resizeSelectedEdges() {
		for (int numEdge = 0; numEdge < getCurrentGraph().countEdge(); numEdge++) {
			if (getCurrentGraph().getEdge(numEdge).isActive()) {
				String lenght = JOptionPane
						.showInputDialog("Input size");
				getCurrentGraph().getEdge(numEdge).resize(checkString(lenght));
			}
		}
	}

	public int checkString(String string) {
		if (string != null && string.matches("^-?\\d+$")) {
			if (Integer.parseInt(string) > -1
					&& Integer.parseInt(string) < 2000000000) {
				return Integer.parseInt(string);
			}
		}
		return -1;
	}

	// ------------------------------------------
	public void removeSelectedObjects() {
		for (int numVertex = 0; numVertex < getCurrentGraph().countVertex(); numVertex++) {
			if (getCurrentGraph().getVertex(numVertex).isActivate()) {
				getCurrentGraph().removeVertex(numVertex);
				numVertex--;
			}
		}
		for (int numEdge = 0; numEdge < getCurrentGraph().countEdge(); numEdge++) {
			if (getCurrentGraph().getEdge(numEdge).isActive()) {
				getCurrentGraph().removeEdge(numEdge);
				numEdge--;
			}
		}
		numActualVertex = -1;
		numActualEdge = -1;
		removeTempEdge();
		repaint();
	}

	// ----------------------------------------
	public void addVertex(Point p) {
		Vertex vertex = new Vertex(p.getX(), p.getY());
		getCurrentGraph().addVertex(vertex);
		numActualVertex = getCurrentGraph().findVertex(vertex);
		getCurrentGraph().getVertex(numActualVertex).activeOn();
		actualOn();

		repaint();
	}

	// ------------------------------------------
	public void setPointSelectionBegin(Point p) {
		pointSelectionBegin = p;
	}

	public void setPointSelectionEnd(Point p) {
		pointSelectionEnd = p;
	}

	public Rectangle2D getSelectionBorder() {
		double x1 = (double) pointSelectionBegin.getX();
		double y1 = (double) pointSelectionBegin.getY();
		double x2 = (double) pointSelectionEnd.getX();
		double y2 = (double) pointSelectionEnd.getY();
		if (x1 > x2) {
			double temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if (y1 > y2) {
			double temp = y1;
			y1 = y2;
			y2 = temp;
		}
		return new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
	}

	/*
	 * static void swapDouble(double temp1, double temp2){ double temp = temp1;
	 * temp1 = temp2; temp2 = temp; }
	 */
	public void setSelectionObjects() {
		for (int numVertex = 0; numVertex < getCurrentGraph().countVertex(); numVertex++) {
			getCurrentGraph().getVertex(numVertex).activeOff();
			if (getCurrentGraph().getVertex(numVertex).isVertexInArea(pointSelectionBegin,
					pointSelectionEnd)) {
				getCurrentGraph().getVertex(numVertex).activeOn();
			}
		}
		for (int numEdge = 0; numEdge < getCurrentGraph().countEdge(); numEdge++) {
			getCurrentGraph().getEdge(numEdge).activeOff();
			if (getCurrentGraph().getEdge(numEdge).isEdgeInArea(pointSelectionBegin,
					pointSelectionEnd)) {
				getCurrentGraph().getEdge(numEdge).activeOn();
			}
		}
		repaint();
	}

	// -------------------------------------
	public void setPointDragged(Point p) {
		pointDragged = p;
	}

	public void dragObjects(Point p) {
		double x = (double) pointDragged.getX() - (double) p.getX();
		double y = (double) pointDragged.getY() - (double) p.getY();

		for (int numVertex = 0; numVertex < getCurrentGraph().countVertex(); numVertex++) {
			Vertex vertex = getCurrentGraph().getVertex(numVertex);
			if (vertex.isActivate()) {
				getCurrentGraph().getVertex(numVertex).setPositionVertex(vertex.getX() - x,
						vertex.getY() - y);
			}
		}
		pointDragged = p;
		repaint();
	}

	// -------------------------------------
	public void setBeginTempEdge(Point p) {
		if (getCurrentGraph().findVertex(p) != null) {
			beginTempEdge = getCurrentGraph().findVertex(p);
		}
		endTempEdge = p;
	}

	public void setEndTempEdge(Point p) {
		endTempEdge = p;
		repaint();
	}

	public boolean checkExistsTempEdge() {
		if (beginTempEdge == null) {
			return false;
		}
		return true;
	}

	public void removeTempEdge() {
		beginTempEdge = null;
		endTempEdge.setLocation(0, 0);
		repaint();
	}

	public Line2D.Double getTempEdge() {
		return new Line2D.Double(beginTempEdge.getCenterX(),
				beginTempEdge.getCenterY(), endTempEdge.getX(),
				endTempEdge.getY());
	}

	public boolean checkPossibilityEdge(Point p) {
		if (getCurrentGraph().findVertex(p) != beginTempEdge && getCurrentGraph().findVertex(p) != null) {
			return true;
		}
		return false;
	}

	public void addEdge(Point p) {
		getCurrentGraph().addEdge(getCurrentGraph().findVertex(p), beginTempEdge);
		repaint();
	}

	// -------------------------------------
	public void loadFile() throws ClassNotFoundException {
		JFileChooser fileopen = new JFileChooser();
		fileopen.setCurrentDirectory(new File(".\\save"));
		if (fileopen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			getCurrentGraph().loadFile(fileopen.getSelectedFile().getAbsolutePath());
		}
		repaint();
	}

	public void saveFile() throws ClassNotFoundException {
		JFileChooser fileopen = new JFileChooser();
		fileopen.setCurrentDirectory(new File(".\\save"));
		if (fileopen.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			getCurrentGraph().saveFile(fileopen.getSelectedFile().getAbsolutePath());
		}
	}

	public void runAlgoritm() throws InterruptedException {
		algoritm.runAlgoritm(getCurrentGraph());
	}

	public boolean[][] getVertexLabels() {

		return algoritm.getVertexLabels();
	}

	// ***********===ALGORITM===************
	/*
	 * public void checkGraphOnBipartition() throws InterruptedException { //
	 * boolean isBipartition = true; if (graph.countVertex() == 0) {
	 * 
	 * } for (int numVertex = 0; numVertex < graph.countVertex(); numVertex++) {
	 * if (graph.getVertex(numVertex).getFlag() == -1) {
	 * searchInDepth(graph.getVertex(numVertex), 0); } } if (isBipartition) {
	 * JOptionPane.showMessageDialog(null,
	 * "���� ����������"); } else {
	 * JOptionPane.showMessageDialog(null,
	 * "���� �� ����������"); }
	 * mainFrame.repaint(); reset(); }
	 * 
	 * void searchInDepth(Vertex vertex, int flag) throws InterruptedException {
	 * // if(graph.getVertex(graph.findVertex(vertex)).getFlag() == -1)
	 * graph.getVertex(graph.findVertex(vertex)).setFlag(flag);
	 * mainFrame.repaint(); // Thread.sleep(2000); for (int numEdge = 0; numEdge
	 * < graph.countEdge(); numEdge++) { Edge edge = graph.getEdge(numEdge); if
	 * (edge.getV1() == vertex) { if (edge.getV2().getFlag() == -1) {
	 * searchInDepth(edge.getV2(), (flag + 1) % 2); } if (edge.getV2().getFlag()
	 * == flag) { isBipartition = false; } } if (edge.getV2() == vertex) { if
	 * (edge.getV1().getFlag() == -1) { searchInDepth(edge.getV1(), (flag + 1) %
	 * 2); } if (edge.getV1().getFlag() == flag) { isBipartition = false; } } }
	 * }
	 * 
	 * public void reset() { deactivateAllObject(); isBipartition = true; for
	 * (int numVertex = 0; numVertex < graph.countVertex(); numVertex++) {
	 * graph.getVertex(numVertex).setFlag(-1); } }
	 * 
	 * boolean isBipartition = true;
	 */

	public void repaintPanel() {
		mainFrame.repaintPanel();
	}
	private int genIndexForNewGraph() {
		return graphs.size();
	}
	private void createGraph() {
		numActualVertex = -1;
		numActualEdge = -1;
		currentGraph = genIndexForNewGraph();
		this.graphs.add(new Graph());
		mainFrame.addTab(currentGraph, "Graph " + currentGraph);
	}
}
