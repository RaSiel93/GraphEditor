package shell;

import graph.Edge;
import graph.Vertex;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import listeners.eventListeners.KeyboardHotKeys;
import listeners.eventListeners.MouseAdditionEdge;
import listeners.eventListeners.MouseAdditionVertex;
import listeners.eventListeners.MouseDragObjects;
import listeners.eventListeners.MouseDrawClicked;
import listeners.eventListeners.MouseDrawMotion;
import listeners.eventListeners.MouseEditLabel;
import listeners.eventListeners.MouseMotionTempEdge;
import listeners.eventListeners.MousePressingActivation;
import listeners.eventListeners.MouseRegionalSelection;
import listeners.eventListeners.MouseTemporarySelection;
import main.Controller;

public class EditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int ARROW_LEN = 5;
	private double ARROW_ANGLE = 0.7;

	private Controller controller;
	int idGraph;

	private MouseListener vertexMouseListener;
	private MouseListener edgeMouseListener;
	private MouseListener editLabelMouseListener;

	public EditionPanel(int idGraph, Controller controller) {
		setFocusable(true);
		setBackground(Color.WHITE);

		this.idGraph = idGraph;
		this.controller = controller;

		this.vertexMouseListener = new MouseAdditionVertex(controller);
		this.edgeMouseListener = new MouseAdditionEdge(controller);
		this.editLabelMouseListener = new MouseEditLabel(controller);

		addKeyListener(new KeyboardHotKeys(controller));
		addMouseListener(new MousePressingActivation(controller));
		addMouseMotionListener(new MouseDrawClicked(controller));
		addMouseMotionListener(new MouseDrawMotion(controller));
		addMouseMotionListener(new MouseDragObjects(controller));
		addMouseMotionListener(new MouseMotionTempEdge(controller));
		addMouseMotionListener(new MouseTemporarySelection(controller));
		addMouseMotionListener(new MouseRegionalSelection(controller));
	}

	public int getId() {
		return idGraph;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		resizeEditionPanel();
		printEdges(g2);
		printVertexes(g2);
		printTemporaryEdge(g2);
		printRegionalActivation(g2);
	}

	public void enableVertexMode() {
		removeListeners();
		addMouseListener(vertexMouseListener);
		controller.getCurrentGraph().removeTempEdge();
	}

	public void enableEdgeMode() {
		removeListeners();
		addMouseListener(edgeMouseListener);
		controller.getCurrentGraph().removeTempEdge();
	}

	public void enableEditMode() {
		removeListeners();
		addMouseListener(editLabelMouseListener);
		controller.getCurrentGraph().removeTempEdge();
	}

	private void removeListeners() {
		// removeMouseListener(vertexMouseListener);
		removeMouseListener(edgeMouseListener);
		removeMouseListener(editLabelMouseListener);
	}

	private void printEdges(Graphics2D g) {
		for (Edge edge : controller.getCurrentGraph().getEdges()) {
			printEdge(g, edge, selectionColor(edge));
		}
	}

	private void printEdge(Graphics2D g, Edge edge, Color color) {
		edge.refresh();

		g.setColor(color);
		g.setStroke(new BasicStroke(4.0f));
		g.draw(edge);

		printArrow(g, edge.getPointBeginEdge(), edge.getAngle(), g.getColor());

		printEdgeLenght(g,
				new Point((int) edge.getCenterX(), (int) edge.getCenterY()),
				edge.getLenght());

		g.setColor(Color.black);
		g.setStroke(new BasicStroke(2.0f));
		g.draw(edge);
	}

	private void printArrow(Graphics2D g, Point point, double angle, Color color) {
		Line2D line1 = new Line2D.Double(point.getX(), point.getY(),
				point.getX() - ARROW_LEN * Math.cos(angle - ARROW_ANGLE),
				point.getY() + ARROW_LEN * Math.sin(angle - ARROW_ANGLE));

		Line2D line2 = new Line2D.Double(point.getX(), point.getY(),
				point.getX() + ARROW_LEN * Math.cos(angle + ARROW_ANGLE),
				point.getY() - ARROW_LEN * Math.sin(angle + ARROW_ANGLE));

		g.setColor(color);
		g.setStroke(new BasicStroke(4.0f));
		g.draw(line1);
		g.draw(line2);

		g.setColor(Color.black);
		g.setStroke(new BasicStroke(2.0f));
		g.draw(line1);
		g.draw(line2);
	}

	private void printEdgeLenght(Graphics2D g, Point point, int lenght) {
		if (lenght != 1) {
			g.setColor(Color.red);
			g.setFont(new Font("Times New Roman", Font.BOLD, 14));
			g.drawString(String.valueOf(lenght), (int) point.getX(),
					(int) point.getY() + 10);
		}
	}

	private void printVertexes(Graphics2D g2) {
		for (Vertex vertex : controller.getCurrentGraph().getVertexes()) {
			printVertex(g2, vertex, selectionColor(vertex));
		}
	}

	private void printVertex(Graphics2D g, Vertex vertex, Color color) {
		g.setColor(color);
		g.setStroke(new BasicStroke(10.0f));
		g.draw(vertex);

		g.setColor(Color.black);
		g.setStroke(new BasicStroke(4.0f));
		g.draw(vertex);

		g.setColor(Color.red);
		g.setFont(new Font("Times New Roman", Font.BOLD, 14));
		g.drawString(vertex.getName(), (int) vertex.getCenterX() - 15,
				(int) vertex.getCenterY() + 20);
	}

	private Color selectionColor(Edge edge) {
		Edge actualEdge = controller.getCurrentGraph().getActualEdge();
		if (edge == actualEdge) {
			return Color.ORANGE;
		} else if (edge.isActivate()) {
			return Color.GREEN;
		} else {
			return Color.LIGHT_GRAY;
		}
	}
	
	private Color selectionColor(Vertex vertex) {
		Vertex actualVertex = controller.getCurrentGraph().getActualVertex();
		if (vertex == actualVertex) {
			return Color.ORANGE;
		} else if (vertex.isActivate()) {
			return Color.GREEN;
		} else {
			return Color.LIGHT_GRAY;
		}
	}

	private void printTemporaryEdge(Graphics2D g) {
		if (controller.getCurrentGraph().checkExistsTempEdge()) {
			g.setColor(Color.orange);
			g.setStroke(new BasicStroke(2.0f));
			g.draw(controller.getCurrentGraph().getTempEdge());
		}
	}

	private void printRegionalActivation(Graphics2D g) {
		if (controller.isSelection()) {
			g.setColor(Color.green);
			g.setStroke(new BasicStroke(1.0f));
			g.draw(controller.getSelectionBorder());
			AlphaComposite ac = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.3f);
			g.setComposite(ac);
			g.setColor(Color.orange);
			g.fill(controller.getSelectionBorder());
		}
	}

	private void resizeEditionPanel() {
		if (!controller.isSelection() && !controller.isDragged()) {
			Point maxCoords = controller.getCurrentGraph().getMaxCoords();
			setPreferredSize(new Dimension((int) maxCoords.getX(),
					(int) maxCoords.getY()));
			revalidate();
		}
	}
}
