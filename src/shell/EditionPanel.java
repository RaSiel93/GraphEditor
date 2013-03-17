package shell;

import graph.Edge;
import graph.Vertex;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;


import listeners.MouseDragObjects;
import listeners.MouseAdditionEdge;
import listeners.MouseMotionTempEdge;
import listeners.MouseTemporarySelection;
import listeners.MousePressingActivation;
import listeners.MouseAdditionVertex;
import listeners.MouseRegionalActivation;
import main.Controller;

class EditionPanel extends JPanel {
	public EditionPanel(final Controller c) {
		controller = c;
		setBackground(Color.WHITE);
		selectMouseListener = new MousePressingActivation(controller); 
		vertexMouseListener = new MouseAdditionVertex(controller);
		
		edgeMouseListener = new MouseAdditionEdge(controller);
		motionMouseListenerEdge = new MouseMotionTempEdge(controller);
		
		moveMouseListener = new MouseTemporarySelection(controller);
		draggedMouseListener = new MouseDragObjects(controller);
		
		mouseBorderSelected = new MouseRegionalActivation(controller);
		
		
		addMouseListener(selectMouseListener);
		addMouseListener(vertexMouseListener);
		addMouseMotionListener(moveMouseListener);
		addMouseMotionListener(draggedMouseListener);
		addMouseMotionListener(mouseBorderSelected);
		addMouseMotionListener(motionMouseListenerEdge);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(int numEdge = 0; numEdge < controller.countEdge(); numEdge++) {
			Edge edge = controller.getEdge(numEdge);
			edge.refresh();
			if(edge.isActivate())
				g2.setColor(Color.green);
			else
				g2.setColor((Color) edge.getStatus());

			g2.setStroke(new BasicStroke(4.0f));
			g2.draw(edge);
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(2.0f));
			g2.draw(edge);
		}
		for(int numVertex = 0; numVertex < controller.countVertex(); numVertex++) {
			Vertex vertex = controller.getVertex(numVertex);
			if (vertex.isActivate())
				g2.setColor(Color.green);
			else
				g2.setColor((Color) vertex.getStatus());
			g2.setStroke(new BasicStroke(10.0f));
			g2.draw(vertex);
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(4.0f));
			g2.draw(vertex);
		}
		if(controller.checkExistsTempEdge()){
			g2.setColor(Color.orange);
			g2.setStroke(new BasicStroke(4.0f));
			g2.draw(controller.getTempEdge());
		} 
		if(controller.isStatusSelection()){
			g2.setColor(Color.green);
			g2.setStroke(new BasicStroke(1.0f));
			g2.draw(controller.getSelectionBorder());
			//g2.setComposite(new AlphaComposite.SrcOver));
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f); 
			g2.setComposite(ac);
			g2.setColor(Color.orange);
			g2.fill(controller.getSelectionBorder());
		}
	}
	public void enableVertexMode() {
		removeMouseListener(this.edgeMouseListener);
		controller.removeTempEdge();
	}
	public void enableEdgeMode() {
		removeMouseListener(this.edgeMouseListener);
		controller.removeTempEdge();
		
		addMouseListener(this.edgeMouseListener);
	}
	
	private Controller controller;
	private MouseListener vertexMouseListener;
	private MouseListener edgeMouseListener;
	private MousePressingActivation selectMouseListener;
	private MouseTemporarySelection moveMouseListener;
	private MouseDragObjects draggedMouseListener;
	private MouseMotionTempEdge motionMouseListenerEdge;
	private MouseRegionalActivation mouseBorderSelected;
	
}
