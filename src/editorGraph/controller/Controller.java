package editorGraph.controller;

import editorGraph.algoritm.Algorithm;
import editorGraph.algoritm.EnumAlgorithms;
import editorGraph.algoritm.EulerianCycle;
import editorGraph.algoritm.EulerianPath;
import editorGraph.algoritm.HamiltonianCycle;
import editorGraph.algoritm.HamiltonianPath;
import editorGraph.graph.Edge;
import editorGraph.graph.Graph;
import editorGraph.graph.ListGraphs;
import editorGraph.graph.Vertex;
import editorGraph.shell.MainFrame;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Controller {
	private ListGraphs listGraphs;
	private MainFrame mainFrame;

	private Algorithm algoritm;

	private Point pointSelectionBegin;
	private Point pointSelectionEnd;

	private Point pointShift;

	private boolean selection = false;
	private boolean dragged = false;

	List<Vertex> selectionVertexes;
	List<Edge> selectionEdges;

	public Controller() {
		listGraphs = new ListGraphs();
		selectionVertexes = new ArrayList<Vertex>();
		selectionEdges = new ArrayList<Edge>();

		algoritm = new HamiltonianCycle(this);
	}

	public void startActions() {
		create();
	}

	public void setView(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public Graph getCurrentGraph() {
		return listGraphs.get(mainFrame.getCurrentIdGraph());
	}

	// ----------------------------------------
	public void setDragged(boolean flag) {
		dragged = flag;
	}

	public boolean isDragged() {
		return dragged;
	}

	public boolean isPassibleDragged() {
		if (!isSelection() && !isDragged()
				&& !getCurrentGraph().checkExistsTempEdge()) {
			return true;
		}
		return false;
	}

	public void setSelection(boolean flag) {
		selection = flag;
	}

	public boolean isSelection() {
		return selection;
	}

	public boolean isPassibleSelection() {
		if (!isSelection() && !isDragged()
				&& !getCurrentGraph().checkExistsTempEdge()) {
			return true;
		}
		return false;
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
	// ----------------------------------------
	public boolean isObject(Point point) {
		if (isVertex(point) || isEdge(point)) {
			return true;
		}
		return false;
	}

	public boolean isVertex(Point point) {
		if (getCurrentGraph().findVertex(point) != null) {
			return true;
		}
		return false;
	}

	public boolean isEdge(Point point) {
		if (getCurrentGraph().findEdge(point) != null) {
			return true;
		}
		return false;
	}

	public boolean checkSelectedObject(Point point) {
		Graph graph = getCurrentGraph();
		if (graph.findVertex(point) != null) {
			if (graph.findVertex(point).isSelected()) {
				return true;
			}
		}
		if (graph.findEdge(point) != null) {
			if (graph.findEdge(point).isSelected()) {
				return true;
			}
		}
		return false;
	}

	// ------------------------------------------
	public void renameVertex(Point point) {
		String name = JOptionPane.showInputDialog("Input name");
		if (name != null) {
			getCurrentGraph().findVertex(point).setName(name);
		}
	}

	public void renameSelectedVertexes() {
		for (Vertex vertex : getCurrentGraph().getVertexes()) {
			if (vertex.isSelected()) {
				String name = JOptionPane.showInputDialog("Input name");
				if (name != null) {
					vertex.setName(name);
				}
			}
		}
	}

	public void resizeEdge(Point point) {
		String lenght = JOptionPane.showInputDialog("Input size");
		try {
			int size = Integer.parseInt(lenght);
			if (checkSize(size)) {
				getCurrentGraph().findEdge(point).resize(size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resizeSelectedEdges() {
		for (Edge edge : getCurrentGraph().getEdges()) {
			if (edge.isSelected()) {
				String lenght = JOptionPane.showInputDialog("Input size");
				try {
					int size = Integer.parseInt(lenght);
					if (checkSize(size)) {
						edge.resize(size);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean checkSize(int size) {
		if (size >= 0 && size < 2000000000) {
			return true;
		}
		return false;
	}

	// ----------------------------------------
	public void addVertex(Point point) {
		Vertex vertex = new Vertex(point);
		getCurrentGraph().addVertex(vertex);
	}

	// ------------------------------------------
	public void setPointSelectionBegin(Point point) {
		selectionVertexes = getCurrentGraph().getSelectionVertexes();
		selectionEdges = getCurrentGraph().getSelectionEdges();
		pointSelectionBegin = point;
	}

	public void setPointSelectionEnd(Point point) {
		pointSelectionEnd = point;
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

	public void setSelectionObjects() {
		getCurrentGraph().deselectAll();
		for (Vertex vertex : selectionVertexes) {
			vertex.selectOn();
		}
		for (Edge edge : selectionEdges) {
			edge.selectOn();
		}
		for (Vertex vertex : getCurrentGraph().getVertexes()) {
			if (vertex.isVertexInArea(pointSelectionBegin, pointSelectionEnd)) {
				vertex.selectOn();
			}
		}
		for (Edge edge : getCurrentGraph().getEdges()) {
			if (edge.isEdgeInArea(pointSelectionBegin, pointSelectionEnd)) {
				edge.selectOn();
			}
		}
	}

	// -------------------------------------
	public void setPointDragged(Point point) {
		pointShift = point;
	}

	public void shiftObjects(List<Vertex> vertexes, Point point) {
		double x = pointShift.getX() - point.getX();
		double y = pointShift.getY() - point.getY();

		for (Edge edge : getCurrentGraph().getSelectionEdges()) {
			if (!vertexes.contains(edge.getVertex1())) {
				vertexes.add(edge.getVertex1());
			}
			if (!vertexes.contains(edge.getVertex2())) {
				vertexes.add(edge.getVertex2());
			}
		}

		for (Vertex vertex : vertexes) {
			vertex.shift(x, y);
		}

		pointShift = point;
	}

	// -------------------------------------

	// public void runAlgoritm() throws InterruptedException {
	// algoritm.runAlgoritm(getGraph());
	// }

	// public boolean[][] getVertexLabels() {
	// return algoritm.getVertexLabels();
	// }

	// public void repaintPanel() {
	// mainFrame.repaintPanel();
	// }

	// -------------------------------------
	// COMMANDS
	public void create() {
		listGraphs.add(new Graph());
		int id = listGraphs.getIdLastGraph();
		mainFrame.addTab(id, "Graph " + id);
	}

	public void close() {
		int id = mainFrame.getCurrentIdGraph();
		if (id != -1) {
			listGraphs.remove(id);
			mainFrame.removeTab();
		}
	}

	public void open() {
		JFileChooser fileopen = new JFileChooser();
		fileopen.setCurrentDirectory(new File(".\\save"));
		if (fileopen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			create();
			getCurrentGraph().loadFile(
					fileopen.getSelectedFile().getAbsolutePath());
		}
	}

	public void save() {
		JFileChooser fileopen = new JFileChooser();
		fileopen.setCurrentDirectory(new File(".\\save"));
		if (fileopen.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				getCurrentGraph().saveFile(
						fileopen.getSelectedFile().getAbsolutePath());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void exit() {
		System.exit(0);
	}

	public void removeSelectedObjects() {
		getCurrentGraph().removeSelectedObjects();
	}

	public void selectAll() {
		getCurrentGraph().activateAll();
	}

	public void removeTemporaryEdge() {
		getCurrentGraph().removeTempEdge();
	}

	public void repaint() {
		mainFrame.repaint();
	}

	// ALGORITM
	public void switchAlgorithm(EnumAlgorithms algoritm) {
		switch (algoritm) {
		case HamiltonianCycle:
			this.algoritm = new HamiltonianCycle(this);
			break;
		case HamiltonianPath:
			this.algoritm = new HamiltonianPath(this);
			break;
		case EulerianCycle:
			this.algoritm = new EulerianCycle(this);
			break;
		case EulerianPath:
			this.algoritm = new EulerianPath(this);
			break;
		}
	}

	public void runAlgorithm() {
		if (getCurrentGraph() != null) {
			algoritm.run();
		}
	}

	public void invertSelection() {
		Graph graph = getCurrentGraph();
		for (Vertex vertex : graph.getVertexes()) {
			if (vertex.isSelected()) {
				vertex.selectOff();
			} else {
				vertex.selectOn();
			}
		}
		for (Edge edge : graph.getEdges()) {
			if (edge.isSelected()) {
				edge.selectOff();
			} else {
				edge.selectOn();
			}
		}
	}
}
