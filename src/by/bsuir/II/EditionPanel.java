package by.bsuir.II;

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

class EditionPanel extends JPanel {
	public EditionPanel(final Controller c) {
		controller = c;
		setBackground(Color.WHITE);
		vertexMouseListner = new MouseAdapterVertex(controller);
		edgeMouseLisner = new MouseAdapterEdge(controller);	
		// setLayout(new BorderLayout());
		// addMouseListener(new ModeVertex());

		//addMouseListener(new MouseAdapterVert());
		/*addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent event) {
				// controller.detempAllObject();
				if (controller.isObject(event)) {
					setCursor(Cursor
							.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				} else {
					setCursor(Cursor.getDefaultCursor());
				}
				if (controller.isEdgeAdd()) {
					controller.setEdge(event);
					// repaint();
				}
			}

			public void mouseDragged(MouseEvent event) {
				if (event.getModifiers() == event.BUTTON1_MASK
						&& !controller.buttonEdge) {
					controller.draggedVertex(event);
				}
				if (event.getModifiers() == event.BUTTON1_MASK
						&& controller.buttonEdge) {
					controller.setEdge(event);
				}
			}
		});*/
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < controller.countEdge(); i++) {
			Edge edge = controller.getEdge(i);
			edge.refresh();
			if (edge.isActivate())
				g2.setColor(Color.green);
			else
				g2.setColor((Color) edge.getStatus());

			g2.setStroke(new BasicStroke(4.0f));
			g2.draw(edge);
			// if(edge.isActivate()){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(2.0f));
			g2.draw(edge);
			// }
		}
		for (int i = 0; i < controller.countVertex(); i++) {
			Vertex vertex = controller.getVertex(i);
			if (vertex.isActivate())
				g2.setColor(Color.green);
			else
				g2.setColor((Color) vertex.getStatus());
			g2.setStroke(new BasicStroke(6.0f));
			g2.draw(vertex);
			// if(vertex.isActivate()){
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(2.0f));
			g2.draw(vertex);
			// }
		}
	}

	public void enableVertexMode() {
		removeMouseListener(this.edgeMouseLisner);
		addMouseListener(this.vertexMouseListner);
	}
	public void enableEdgeMode() {
		removeMouseListener(this.vertexMouseListner);
		addMouseListener(this.edgeMouseLisner);
	}
	
	private Controller controller;
	private MouseListener vertexMouseListner;
	private MouseListener edgeMouseLisner;	
}
