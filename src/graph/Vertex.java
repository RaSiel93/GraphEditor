package graph;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Vertex extends Ellipse2D.Double {
    private static final int SIDELENGTH = 20;
    private boolean actual;
    private boolean active;
    private String name = "";

    public Vertex(Vertex v) {
	super(v.getX() - SIDELENGTH / 2, v.getY() - SIDELENGTH / 2, SIDELENGTH,
		SIDELENGTH);
	actualOff();
    }

    public Vertex(double x, double y) {
	super(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
	actualOff();
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

    public String getName() {
	return name;
    }

    public void rename(String newName) {
	name = newName;
    }

    public double getCenterX() {
	return this.getX() + SIDELENGTH / 2;
    }

    public double getCenterY() {
	return this.getY() + SIDELENGTH / 2;
    }

    public void setPositionVertex(double x, double y) {
	setFrame(x, y, SIDELENGTH, SIDELENGTH);
    }

    public boolean isVertexInArea(Point p1, Point p2) {
	double[] posX = { p1.getX(), p2.getX() };
	double[] posY = { p1.getY(), p2.getY() };
	if (posX[0] > posX[1]) {
	    swap(posX);
	}
	if (posY[0] > posY[1]) {
	    swap(posY);
	}
	if (getCenterX() >= posX[0] && getCenterX() <= posX[1]
		&& getCenterY() >= posY[0] && getCenterY() <= posY[1]) {
	    return true;
	}
	return false;
    }

    static private void swap(double[] points) {
	double temp = points[0];
	points[0] = points[1];
	points[1] = temp;
    }
}
