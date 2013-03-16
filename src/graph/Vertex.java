package graph;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Vertex extends Ellipse2D.Double{
	public Vertex(){}
	public Vertex(double x, double y){
		super(x - SIDELENGTH/2, y - SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
		status = Color.black;
	}
	/*public Point getPoint(){
		Point p = new Point();
		return Point(this.getX(), this.getY());
	}
	/*public Point getPoint(){
		Point p = null;
		p = Point((int)this.getX(), (int)this.getY())
		return ;
	}*/
	
	public Color getStatus(){
		return status;
	}
	public void activate(){
		active = true;
	}
	public void deactivate(){
		active = false;
	}
	public boolean isActivate(){
		return active;
	}
	public void tempararyEphasisOn(){
		status = status.orange;
	}
	public void tempararyEphasisOff(){
		status = status.white;
	}
	public void setPositionVertex(double x, double y){
		setFrame(x, y, SIDELENGTH, SIDELENGTH);
	}
	
	private Color status;
	private boolean active;
	private static final int SIDELENGTH = 30;
	
}
