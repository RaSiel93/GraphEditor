package by.bsuir.II;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

class Edge extends Line2D.Double{
	public Edge(Vertex v){
		super(v.getX()+SIDELENGTH, v.getY()+SIDELENGTH, v.getX()+SIDELENGTH, v.getY()+SIDELENGTH);
		activate();
		addV1(v);
	}
	public void addV1(Vertex v){
		v1 = v;
	}
	public Vertex getV1(){
		return v1;
	}
	public void addV2(Vertex v){
		v2 = v;
	}
	public Vertex getV2(){
		return v2;
	}
	public Color getStatus(){
		return status;
	}
	public void setEdge(double x, double y){
		setLine(v1.getX() + SIDELENGTH, v1.getY() + SIDELENGTH, x, y);
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
	public boolean isContentVertexInEdge(Vertex vertex) {
		return (v1 == vertex || v2 == vertex);
	}
	

	public void refresh(){
		if( v2 != null ){
			setLine(v1.getX() + SIDELENGTH, v1.getY() + SIDELENGTH, v2.getX() + SIDELENGTH, v2.getY() + SIDELENGTH);
		}
	}
	private static final int SIDELENGTH = 10;
	private Color status;
	private Vertex v1;
	private Vertex v2;
	private int lenght;
	private boolean active;

}
