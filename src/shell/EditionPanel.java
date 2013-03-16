package shell;

import graph.Edge;
import graph.Vertex;

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


import listeners.MouseAdapterEdge;
import listeners.MouseAdapterMotion;
import listeners.MouseAdapterSelected;
import listeners.MouseAdapterVertex;
import main.Controller;

class EditionPanel extends JPanel {
	public EditionPanel(final Controller c) {
		controller = c;
		setBackground(Color.WHITE);
		selectMouseListener = new MouseAdapterSelected(controller); 
		vertexMouseListener = new MouseAdapterVertex(controller);
		edgeMouseListener = new MouseAdapterEdge(controller);
		motionMouseListener = new MouseAdapterMotion(controller);
		
		addMouseMotionListener(this.motionMouseListener);
		addMouseListener(this.selectMouseListener);
		//addMouseListener(this.vertexMouseListner);
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
	}
	public void enableVertexMode() {
		removeMouseListener(this.edgeMouseListener);
		addMouseListener(this.vertexMouseListener);
	}
	public void enableEdgeMode() {
		removeMouseListener(this.vertexMouseListener);
		addMouseListener(this.edgeMouseListener);
	}
	
	private Controller controller;
	private MouseListener vertexMouseListener;
	private MouseListener edgeMouseListener;
	private MouseAdapterSelected selectMouseListener;
	private MouseAdapterMotion motionMouseListener;
}
