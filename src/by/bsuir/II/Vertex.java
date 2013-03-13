package by.bsuir.II;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

class Vertex extends Ellipse2D.Double{
	public Vertex(){}
	public Vertex(double x, double y){
		super(x - SIDELENGTH/2, y - SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
		status = Color.black;
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
	
	public void tempararyEphasisOn(){
		status = status.orange;
	}
	public void tempararyEphasisOff(){
		status = status.black;
	}
	public void setPositionVertex(double x, double y){
		setFrame(x - SIDELENGTH/2, y - SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
	}
	private Color status;
	private boolean active;
	private static final int SIDELENGTH = 20;
	
}
