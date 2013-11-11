package graph;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Vertex extends Ellipse2D.Double {
	private static final int SIDELENGTH = 20;
	private boolean active = false;
	private String name = "";

	public Vertex(Vertex v) {
		super(v.getX() - SIDELENGTH / 2, v.getY() - SIDELENGTH / 2, SIDELENGTH,
				SIDELENGTH);
	}

	public Vertex(double x, double y) {
		super(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
	}

	public Vertex(Point point) {
		super(point.getX() - SIDELENGTH / 2, point.getY() - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
	}

	public void activeOn() {
		active = true;
	}

	public void activeOff() {
		active = false;
	}

	public boolean isActivate() {
		return active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
			swapCoords(posX);
		}
		if (posY[0] > posY[1]) {
			swapCoords(posY);
		}
		if (getCenterX() >= posX[0] && getCenterX() <= posX[1]
				&& getCenterY() >= posY[0] && getCenterY() <= posY[1]) {
			return true;
		}
		return false;
	}

	static private void swapCoords(double[] coords) {
		double temp = coords[0];
		coords[0] = coords[1];
		coords[1] = temp;
	}
}
