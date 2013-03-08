package by.bsuir;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class EditionGraph {
	public static void main(String[] args) {
		MouseFrame frame = new MouseFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}
}


class MouseFrame extends JFrame {
	public MouseFrame() {
		setTitle("EditionGraph");
		setSize(DEFAUT_WIDTH, DEFAUT_HEIGHT);
		
		MousePanel panel = new MousePanel();
		Container contentPane = getContentPane();
		contentPane.add(panel);
	}
	public static final int DEFAUT_WIDTH = 500;
	public static final int DEFAUT_HEIGHT = 300;
}

class Circle extends Ellipse2D.Double {
	public Circle(){}
	public Circle(double x, double y, double w, double h){
		super(x,y,w,h);
	}
	public Color getColor() {
		return color;
	}
	public void activate() {
		color = Color.RED;
	}
	public void deactivate() {
		color = Color.BLACK;
	}
	private Color color;
}

class MousePanel extends JPanel {
	public MousePanel() {
		circles = new ArrayList<Circle>();
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionGandler());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		for(int i = 0; i < circles.size(); i++ ){
			g2.setColor((Color)circles.get(i).getColor());
			g2.setStroke(new BasicStroke(6.0f));
			g2.draw(circles.get(i));
		}
	}
	/*public class Vertex extends JComponent
	{
	  public void paintComponent(Graphics g)
	  {
	    g.clearRect(0, 0, getWidth(), getHeight());
	    g.setColor(Color.RED);
	    g.drawOval(0, 0, getWidth(), getHeight());
	  }
	}*/
	
	public Circle find(Point2D p){
		for(int i = 0; i < circles.size(); i++) {
			Circle r = (Circle)circles.get(i);
			if(r.contains(p)) return r;
		}
		return null;
	}
	
	public void add(Point2D p) {
		double x = p.getX();
		double y = p.getY();
		
		circle = new Circle(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
		circles.add(circle);
		repaint();
	}
	
	public void remove(Circle s) {
		if(s == null) return;
		circle = null;
		//colors.remove(colors.get(s));
		circles.remove(s);
		repaint();
	}
	
	private static final int SIDELENGTH = 16;
	private ArrayList<Circle> circles;
	//private ArrayList<Color> colors;
	private Circle circle;
	//private Color color;
	
	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event){
			circle = find(event.getPoint());
			if(circle != null && event.getClickCount() == 1){
				//if(circle != null){
					circle.activate();
				//} else deActivate(find(event.getPoint()));
			}
			if(circle == null)
				add(event.getPoint());
		}
		
		public void mouseClicked(MouseEvent event){
			circle = find(event.getPoint());
			if(circle != null && event.getClickCount() >= 2){
				remove(circle);
			}
		}		
	}
	
	private class MouseMotionGandler implements MouseMotionListener {
		public void mouseMoved(MouseEvent event) {
			circle = find(event.getPoint());
			if(circle == null)
				setCursor(Cursor.getDefaultCursor());
			else {
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		}
		public void mouseDragged(MouseEvent event){
			if(circle != null){
				int x = event.getX();
				int y = event.getY();
				
				//activate(find(event.getPoint()));
				circle.setFrame( x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
				repaint();
			}
		}
	}
}