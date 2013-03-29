package graph;

import java.awt.Point;
import java.awt.geom.Line2D;

public class Edge extends Line2D.Double {
    private static final int SIDELENGTH = 25;
    private Vertex v1;
    private Vertex v2;
    private int lenght;
    private boolean active;
    private boolean actual;

    public Edge(Vertex beginVertex, Vertex endVertex) {
	v1 = beginVertex;
	v2 = endVertex;
	lenght = 1;
	activate();
	refresh();
    }

    public Edge(Edge edge) {
	v1 = edge.getV1();
	v2 = edge.getV2();
	lenght = 1;
	activate();
	refresh();
    }

    public boolean isActual() {
	return actual;
    }

    public void actualOn() {
	actual = true;
    }

    public void actualOff() {
	actual = false;
    }

    public void activate() {
	active = true;
    }

    public void deactivate() {
	active = false;
    }

    public boolean isActivate() {
	return active;
    }

    public Point getPointBeginEdge() {
	Point pointBegin = new Point((int) (v1.getCenterX() - SIDELENGTH
		* Math.sin(getAngle())), (int) (v1.getCenterY() - SIDELENGTH
		* Math.cos(getAngle())));

	return pointBegin;
    }

    public Point getPointEndEdge() {
	Point pointEnd = new Point((int) (v2.getCenterX() + SIDELENGTH
		* Math.sin(getAngle())), (int) (v2.getCenterY() + SIDELENGTH
		* Math.cos(getAngle())));

	return pointEnd;
    }

    public double getAngle() {
	return Math.atan2(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    public int getLenght() {
	return lenght;
    }

    public double getCenterX() {
	return (v1.getCenterX() + v2.getCenterX()) / 2;
    }

    public double getCenterY() {
	return (v1.getCenterY() + v2.getCenterY()) / 2;
    }

    public Vertex getV1() {
	return v1;
    }

    public Vertex getV2() {
	return v2;
    }

    public boolean isContentVertexInEdge(Vertex vertex) {
	return (v1 == vertex || v2 == vertex);
    }

    public void revers() {
	Vertex vertexTemp = v1;
	v1 = v2;
	v2 = vertexTemp;
	setLine(getPointEndEdge(), getPointBeginEdge());
    }

    public void refresh() {
	setLine(getPointBeginEdge(), getPointEndEdge());
    }

    public boolean isEdgeInArea(Point p1, Point p2) {
	double x1 = p1.getX();
	double y1 = p1.getY();
	double x2 = p2.getX();
	double y2 = p2.getY();

	if (x1 > x2) {
	    double temp = x1;
	    x1 = x2;
	    x2 = temp;
	}
	if (y1 > y2) {
	    double temp = y1;
	    y1 = y2;
	    y2 = temp;
	}

	if (getCenterX() >= x1 && getCenterX() <= x2 && getCenterY() >= y1
		&& getCenterY() <= y2) {
	    return true;
	}
	return false;
    }

    public void resize(int value) {
	lenght = value;
    }

}
