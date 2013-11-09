package main;

import graph.Edge;
import graph.Graph;
import graph.ListGraphs;
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
	private ListGraphs listGraphs;
	private MainFrame mainFrame;

	private Algoritm algoritm;

	private Point pointSelectionBegin;
	private Point pointSelectionEnd;
	private Point pointDragged;

	private boolean selection;
	private boolean dragged;
	private boolean algoritmFlag;

	public Controller() {
		listGraphs = new ListGraphs();

		algoritm = new Algoritm(this);

		pointSelectionBegin = new Point(0, 0);
		pointSelectionEnd = new Point(0, 0);
		pointDragged = new Point(0, 0);
		dragged = false;
		selection = false;
	}

	public void startActions() {
		createGraph();
	}

	public void setView(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void repaint() {
		mainFrame.paint();
	}

	public Graph getGraph(int id) {
		return listGraphs.get(id);
	}

	// ----------------------------------------
	public void setStatusDragged(boolean flag) {
		dragged = flag;
		// repaint();
	}

	public boolean isStatusDragged() {
		return dragged;
	}

	public void setStatusSelection(boolean flag) {
		selection = flag;
		// repaint();
	}

	public boolean isStatusSelection() {
		return selection;
	}

	// public boolean isAlgoritmFlag() {
	// return algoritmFlag;
	// }
	//
	// public void setAlgoritmFlag(boolean algoritmFlag) {
	// this.algoritmFlag = algoritmFlag;
	// }

	// ----------------------------------------
	// public void selectActualObject(int id, Point p) {
	// numActualVertex = getGraph(id).getIndexVertex(
	// getGraph(id).findVertex(p));
	// numActualEdge = getGraph(id).getIndexEdge(getGraph(id).findEdge(p));
	// }
	// ----------------------------------------
	public void activateObject(Point p) {
		if (getGraph().findVertex(p) != null) {
			getGraph().findVertex(p).activeOn();
		} else if (getGraph().findEdge(p) != null) {
			getGraph().findEdge(p).activeOn();
		}
		repaint();
	}

	public void deactivateObject(Point p) {
		if (getGraph().findVertex(p) != null) {
			getGraph().findVertex(p).activeOff();
		} else if (getGraph().findEdge(p) != null) {
			getGraph().findEdge(p).activeOff();
		}
		repaint();
	}

	public int countActiveObjects() {
		int countObjects = 0;
		for (int numVertex = 0; numVertex < getGraph().countVertexes(); numVertex++) {
			if (getGraph().getVertex(numVertex).isActivate())
				countObjects++;
		}
		for (int numEdge = 0; numEdge < getGraph().countEdge(); numEdge++) {
			if (getGraph().getEdge(numEdge).isActive())
				countObjects++;
		}
		return countObjects;
	}

	// ----------------------------------------
	public boolean checkPointIfEmpty(int idGraph, Point p) {
		if (listGraphs.getGraph(idGraph).findVertex(p) == null
				&& getGraph().findEdge(p) == null) {
			return true;
		}
		return false;
	}

	public boolean checkPointIfVertex(int idGraph, Point p) {
		if (getGraph(idGraph).findVertex(p) != null)
			return true;
		return false;
	}

	public boolean checkPointIfEdge(int idGraph, Point p) {
		if (getGraph(idGraph).findEdge(p) != null)
			return true;
		return false;
	}

	public boolean checkIfActive(int idGraph, Point p) {
		Graph graph = getGraph(idGraph);
		if (graph.findVertex(p) != null) {
			if (graph.findVertex(p).isActivate()) {
				return true;
			}
		}
		if (graph.findEdge(p) != null) {
			if (graph.findEdge(p).isActive()) {
				return true;
			}
		}
		return false;
	}

	// ------------------------------------------
	public void renameVertex(Point p) {
		String name = JOptionPane.showInputDialog("Input name");
		if (name != null) {
			getGraph().findVertex(p).setName(name);
		}
	}

	public void renameSelectedVertexes() {
		for (int numVertex = 0; numVertex < getGraph().countVertexes(); numVertex++) {
			if (getGraph().getVertex(numVertex).isActivate()) {
				String name = JOptionPane.showInputDialog("Input name");
				if (name != null) {
					getGraph().getVertex(numVertex).setName(name);
				}
			}
		}
	}

	public void resizeEdge(Point p) {
		String lenght = JOptionPane.showInputDialog("Input size");
		if (checkString(lenght) != -1) {
			getGraph().findEdge(p).resize(Integer.parseInt(lenght));
		}
	}

	public void resizeSelectedEdges() {
		for (int numEdge = 0; numEdge < getGraph().countEdge(); numEdge++) {
			if (getGraph().getEdge(numEdge).isActive()) {
				String lenght = JOptionPane.showInputDialog("Input size");
				getGraph().getEdge(numEdge).resize(checkString(lenght));
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
		for (int numVertex = 0; numVertex < getGraph().countVertexes(); numVertex++) {
			if (getGraph().getVertex(numVertex).isActivate()) {
				getGraph().removeVertex(numVertex);
				numVertex--;
			}
		}
		for (int numEdge = 0; numEdge < getGraph().countEdge(); numEdge++) {
			if (getGraph().getEdge(numEdge).isActive()) {
				getGraph().removeEdge(numEdge);
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
		getGraph().addVertex(vertex);
		numActualVertex = getGraph().getIndexVertex(vertex);
		getGraph().getVertex(numActualVertex).activeOn();
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
		for (int numVertex = 0; numVertex < getGraph().countVertexes(); numVertex++) {
			getGraph().getVertex(numVertex).activeOff();
			if (getGraph().getVertex(numVertex).isVertexInArea(
					pointSelectionBegin, pointSelectionEnd)) {
				getGraph().getVertex(numVertex).activeOn();
			}
		}
		for (int numEdge = 0; numEdge < getGraph().countEdge(); numEdge++) {
			getGraph().getEdge(numEdge).activeOff();
			if (getGraph().getEdge(numEdge).isEdgeInArea(pointSelectionBegin,
					pointSelectionEnd)) {
				getGraph().getEdge(numEdge).activeOn();
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

		for (int numVertex = 0; numVertex < getGraph().countVertexes(); numVertex++) {
			Vertex vertex = getGraph().getVertex(numVertex);
			if (vertex.isActivate()) {
				getGraph().getVertex(numVertex).setPositionVertex(
						vertex.getX() - x, vertex.getY() - y);
			}
		}
		pointDragged = p;
		repaint();
	}

	// -------------------------------------
	public void setBeginTempEdge(Point p) {
		if (getGraph().findVertex(p) != null) {
			beginTempEdge = getGraph().findVertex(p);
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
		if (getGraph().findVertex(p) != beginTempEdge
				&& getCurrentGraph().findVertex(p) != null) {
			return true;
		}
		return false;
	}

	public void addEdge(Point p) {
		getGraph().addEdge(getCurrentGraph().findVertex(p), beginTempEdge);
		repaint();
	}

	// -------------------------------------
	public void loadFile() throws ClassNotFoundException {
		JFileChooser fileopen = new JFileChooser();
		fileopen.setCurrentDirectory(new File(".\\save"));
		if (fileopen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			getGraph().loadFile(fileopen.getSelectedFile().getAbsolutePath());
		}
		repaint();
	}

	public void saveFile() throws ClassNotFoundException {
		JFileChooser fileopen = new JFileChooser();
		fileopen.setCurrentDirectory(new File(".\\save"));
		if (fileopen.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			getGraph().saveFile(fileopen.getSelectedFile().getAbsolutePath());
		}
	}

	public void runAlgoritm() throws InterruptedException {
		algoritm.runAlgoritm(getGraph());
	}

	public boolean[][] getVertexLabels() {

		return algoritm.getVertexLabels();
	}

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

	public void create() {
		// TODO Auto-generated method stub

	}
}
