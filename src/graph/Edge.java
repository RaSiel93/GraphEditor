package graph;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class Edge extends Line2D.Double{
	public Edge(Vertex beginVertex, Vertex endVertex){
		super(beginVertex.getX()+SIDELENGTH, beginVertex.getY()+SIDELENGTH, endVertex.getX()+SIDELENGTH, endVertex.getY()+SIDELENGTH);
		v1 = beginVertex;
		v2 = endVertex;
		activate();
	}
	public Vertex getV1(){
		return v1;
	}
	public Vertex getV2(){
		return v2;
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
	public boolean isContentVertexInEdge(Vertex vertex) {
		return (v1 == vertex || v2 == vertex);
	}

	public void refresh(){
		setLine(v1.getX() + SIDELENGTH, v1.getY() + SIDELENGTH, v2.getX() + SIDELENGTH, v2.getY() + SIDELENGTH);
	}
	private Color status;
	private Vertex v1;
	private Vertex v2;
	private int lenght;
	private boolean active;

	private static final int SIDELENGTH = 15;
}
