package editorGraph.graph;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Vertex extends Ellipse2D.Double {
	private static final int SIDELENGTH = 20;
	private boolean selected = false;
	private String name = "";

	public Vertex(Point point) {
		super(point.getX() - SIDELENGTH / 2, point.getY() - SIDELENGTH / 2,
				SIDELENGTH, SIDELENGTH);
		selectOn();
	}

	public Point2D getPoint() {
		return new Point((int) getX() + SIDELENGTH / 2, (int) getY()
				+ SIDELENGTH / 2);
	}

	public void selectOn() {
		selected = true;
	}

	public void selectOff() {
		selected = false;
	}

	public boolean isSelected() {
		return selected;
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

	public void shift(double x, double y) {
		setFrame(getX() - x, getY() - y, SIDELENGTH, SIDELENGTH);
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
