package shell;

import graph.Edge;
import graph.Vertex;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import listeners.KeyboardHotKeys;
import listeners.MouseAdditionEdge;
import listeners.MouseAdditionVertex;
import listeners.MouseDragObjects;
import listeners.MouseEdit;
import listeners.MouseMotionTempEdge;
import listeners.MousePressingActivation;
import listeners.MouseRegionalActivation;
import listeners.MouseTemporarySelection;
import main.Controller;

public class EditionPanel extends JPanel {
    private int ARROW_LEN = 5;
    private double ARROW_ANGLE = 0.7;
    private Map<String, EventListener> listeners;
    private Controller controller;
    private KeyListener keyboardHotKeys;
    private MouseListener vertexMouseListener;
    private MouseListener edgeMouseListener;
    private MouseListener mouseEdit;
    private MouseListener selectMouseListener;
    private MouseMotionListener motionMouseListenerEdge;
    private MouseMotionListener moveMouseListener;
    private MouseMotionListener draggedMouseListener;
    private MouseMotionListener mouseRegionalActivation;

    public EditionPanel(final Controller controller) {
	setFocusable(true);
	this.controller = controller;
	setBackground(Color.WHITE);

	this.keyboardHotKeys = new KeyboardHotKeys(controller);
	this.vertexMouseListener = new MouseAdditionVertex(controller);
	this.edgeMouseListener = new MouseAdditionEdge(controller);
	this.mouseEdit = new MouseEdit(controller);
	this.selectMouseListener = new MousePressingActivation(controller);
	this.motionMouseListenerEdge = new MouseMotionTempEdge(controller);
	this.moveMouseListener = new MouseTemporarySelection(controller);
	this.draggedMouseListener = new MouseDragObjects(controller);
	this.mouseRegionalActivation = new MouseRegionalActivation(controller);

	listeners = new HashMap<String, EventListener>();
	listeners.put("HotKeys", this.keyboardHotKeys);
	listeners.put("Vertex", this.vertexMouseListener);
	listeners.put("Edge", this.edgeMouseListener);
	listeners.put("Edit", this.mouseEdit);
	listeners.put("Select", this.selectMouseListener);
	listeners.put("EdgeTemp", this.motionMouseListenerEdge);
	listeners.put("Move", this.moveMouseListener);
	listeners.put("Drag", this.draggedMouseListener);
	listeners.put("RSelect", this.mouseRegionalActivation);

	addMouseMotionListener((MouseMotionListener) listeners.get("Drag"));
	addKeyListener((KeyListener) listeners.get("HotKeys"));
	addMouseListener((MouseListener) listeners.get("Vertex"));
	addMouseListener((MouseListener) listeners.get("Select"));
	addMouseMotionListener((MouseMotionListener) listeners.get("EdgeTemp"));
	addMouseMotionListener((MouseMotionListener) listeners.get("Move"));
	// addMouseMotionListener((MouseMotionListener) listeners.get("Drag"));
	addMouseMotionListener((MouseMotionListener) listeners.get("RSelect"));
    }

    private void resizeEditionPanel() {
	if (!controller.isStatusSelection() && !controller.isStatusDragged()) {
	    setPreferredSize(controller.getMaxCoordX());
	    revalidate();
	}
    }

    private void printArrow(Graphics2D g2, Point point, double angle,
	    Color color) {
	g2.setColor(color);
	g2.setStroke(new BasicStroke(4.0f));
	Line2D.Double line1 = new Line2D.Double(point.getX(), point.getY(),
		point.getX() - ARROW_LEN * Math.cos(angle - ARROW_ANGLE),
		point.getY() + ARROW_LEN * Math.sin(angle - ARROW_ANGLE));
	Line2D.Double line2 = new Line2D.Double(point.getX(), point.getY(),
		point.getX() + ARROW_LEN * Math.cos(angle + ARROW_ANGLE),
		point.getY() - ARROW_LEN * Math.sin(angle + ARROW_ANGLE));
	g2.draw(line1);
	g2.draw(line2);

	g2.setColor(Color.black);
	g2.setStroke(new BasicStroke(2.0f));
	g2.draw(line1);
	g2.draw(line2);
    }

    private void printLenght(Graphics2D g2, Point point, int lenght) {
	if (lenght != 1) {
	    g2.setColor(Color.red);
	    g2.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    g2.drawString(String.valueOf(lenght), (int) point.getX(),
		    (int) point.getY() + 10);
	}
    }

    public void printEdge(Graphics2D g2) {
	for (int numEdge = 0; numEdge < controller.countEdge(); numEdge++) {
	    Edge edge = controller.getEdge(numEdge);
	    edge.refresh();
	    if (edge.isActual())
		g2.setColor(Color.orange);
	    else if (edge.isActivate())
		g2.setColor(Color.green);
	    else
		g2.setColor(Color.LIGHT_GRAY);
	    g2.setStroke(new BasicStroke(4.0f));
	    g2.draw(edge);

	    printArrow(g2, edge.getPointBeginEdge(), edge.getAngle(),
		    g2.getColor());

	    printLenght(
		    g2,
		    new Point((int) edge.getCenterX(), (int) edge.getCenterY()),
		    edge.getLenght());

	    g2.setColor(Color.black);
	    g2.setStroke(new BasicStroke(2.0f));
	    g2.draw(edge);
	}
    }

    private void printVertex(Graphics2D g2) {
	boolean[][] algoVertexLabels = controller.getVertexLabels();
	for (int numVertex = 0; numVertex < controller.countVertex(); numVertex++) {
	    Vertex vertex = controller.getVertex(numVertex);
	    if (vertex.isActual())
		g2.setColor(Color.orange);
	    else if (vertex.isActivate())
		g2.setColor(Color.green);
	    else
		g2.setColor(Color.LIGHT_GRAY);
	    g2.setStroke(new BasicStroke(10.0f));
	    g2.draw(vertex);

	    g2.setColor(Color.black);
	    g2.setStroke(new BasicStroke(4.0f));
	    g2.draw(vertex);

	    g2.setColor(Color.red);
	    g2.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    g2.drawString(vertex.getName(), (int) vertex.getCenterX() - 15,
		    (int) vertex.getCenterY() + 20);

	    if (controller.isAlgoritmFlag()) {
		if (algoVertexLabels[numVertex][0] == true) {
		    if (algoVertexLabels[numVertex][1] == true) {
			g2.setColor(Color.BLUE);
		    } else {
			g2.setColor(Color.RED);
		    }
		    // g2.setStroke(new BasicStroke(10.0f));
		    g2.fill(new Ellipse2D.Double(vertex.getX(), vertex.getY(),
			    21, 21));
		}
	    }
	}
    }

    private void printTempEdge(Graphics2D g2) {
	if (controller.checkExistsTempEdge()) {
	    g2.setColor(Color.orange);
	    g2.setStroke(new BasicStroke(2.0f));
	    g2.draw(controller.getTempEdge());
	}
    }

    private void printRegionalActivation(Graphics2D g2) {
	if (controller.isStatusSelection()) {
	    g2.setColor(Color.green);
	    g2.setStroke(new BasicStroke(1.0f));
	    g2.draw(controller.getSelectionBorder());
	    AlphaComposite ac = AlphaComposite.getInstance(
		    AlphaComposite.SRC_OVER, 0.3f);
	    g2.setComposite(ac);
	    g2.setColor(Color.orange);
	    g2.fill(controller.getSelectionBorder());
	}
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);

	resizeEditionPanel();
	printEdge(g2);
	printVertex(g2);
	printTempEdge(g2);
	printRegionalActivation(g2);
    }

    public void enableVertexMode() {
	removeListener(listeners.get("Edge"), listeners.get("Edit"));
	// addMouseMotionListener((MouseMotionListener) listeners.get("Drag"));
    }

    public void enableEdgeMode() {
	removeListener(listeners.get("Edge"), listeners.get("Edit"));
	addMouseListener((MouseListener) listeners.get("Edge"));
	// addMouseMotionListener((MouseMotionListener) listeners.get("Drag"));
    }

    public void enableEditMode() {
	removeListener(listeners.get("Edge"), listeners.get("Edit"));
	addMouseListener((MouseListener) listeners.get("Edit"));
    }

    private void removeListener(final EventListener ml1, final EventListener ml2) {
	removeMouseListener((MouseListener) ml1);
	removeMouseListener((MouseListener) ml2);
	controller.removeTempEdge();
    }
}
