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
	public double getCenterX(){
		return this.getX() + SIDELENGTH/2;
	}
	public double getCenterY(){
		return this.getY() + SIDELENGTH/2;
	}
	
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
	public void actualOn(){
		status = status.orange;
	}
	public void actualOff(){
		status = status.white;
	}
	public void setPositionVertex(double x, double y){
		setFrame(x, y, SIDELENGTH, SIDELENGTH);
	}
	
	public boolean isVertexInArea(Point p1, Point p2){
		double x1 = (double)p1.getX();
		double y1 = (double)p1.getY();
		double x2 = (double)p2.getX();
		double y2 = (double)p2.getY();
		
		if(x1 > x2){
			double temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if(y1 > y2){
			double temp = y1;
			y1 = y2;
			y2 = temp;
		}
		
		if(getCenterX() >= x1 && getCenterX() <= x2 && getCenterY() >= y1 && getCenterY() <= y2){
			return true;
		}
		return false;
	}
	
	
	private Color status;
	private boolean active;
	private static final int SIDELENGTH = 30;
	
}
