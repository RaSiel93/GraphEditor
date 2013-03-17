package main;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import shell.MainFrame;

public class Controller{
	Controller(){
		numActualVertex = -1;
		numActualEdge = -1;
		
		pointSelectionBegin = new Point(0, 0);
		pointSelectionEnd = new Point(0, 0);
		
		pointDragged = new Point(0, 0);
		
		beginTempEdge = null;
		endTempEdge = new Point(0, 0);
		
		dragged = false;
		selection = false;
	}
	public void setModel(Graph g){
		graph = g;
	}
	public void setView(MainFrame mf){
		mainFrame = mf;
	}

	private Graph graph;
	private MainFrame mainFrame;

	private int numActualVertex;
	private int numActualEdge;
	
	private Point pointSelectionBegin;
	private Point pointSelectionEnd;
	
	private Point pointDragged;
	
	private boolean dragged;
	private boolean selection;

	private Vertex beginTempEdge;
	private Point endTempEdge;
	//----------------------------------------
	public void setStatusDragged(boolean flag){
		dragged = flag;
		mainFrame.repaint();
	}
	public boolean isStatusDragged(){
		return dragged;
	}
	
	public void setStatusSelection(boolean flag){
		selection = flag;
		mainFrame.repaint();
	}
	public boolean isStatusSelection(){
		return selection;
	}
	//----------------------------------------
	public int countVertex(){
		return graph.countVertex();
	}
	public Vertex getVertex(int i){
		return graph.getVertex(i);
	}
	public int countEdge(){
		return graph.countEdge();
	}
	public Edge getEdge(int i){
		return graph.getEdge(i);
	}
	//----------------------------------------
	public void selectActualObject(Point p){
		numActualVertex = graph.findVertex(graph.findVertex(p));
		numActualEdge =  graph.findEdge(graph.findEdge(p));
	}
	public void actualOn(){
		if(numActualVertex != -1){
			graph.getVertex(numActualVertex).actualOn();
		} else if(numActualEdge != -1){
			graph.getEdge(numActualEdge).actualOn();
		}
		mainFrame.repaint();
	}
	public void actualOff(){
		if(numActualVertex != -1){
			graph.getVertex(numActualVertex).actualOff();
		} else if(numActualEdge != -1){
			graph.getEdge(numActualEdge).actualOff();
		}
		mainFrame.repaint();
	}
	//----------------------------------------
	public void activateObject(Point p){
		if(graph.findVertex(p) != null){
			graph.findVertex(p).activate();
		} else if(graph.findEdge(p) != null){
			graph.findEdge(p).activate();
		}
		mainFrame.repaint();
	}
	public void deactivateObject(Point p){
		if(graph.findVertex(p) != null){
			graph.findVertex(p).deactivate();
		} else if(graph.findEdge(p) != null){
			graph.findEdge(p).deactivate();
		}
		mainFrame.repaint();
	}
	public int countActiveObjects(){
		int countObjects = 0;
		for(int numVertex = 0; numVertex < graph.countVertex(); numVertex++){
			if(graph.getVertex(numVertex).isActivate()) countObjects++;
		}
		for(int numEdge = 0; numEdge < graph.countEdge(); numEdge++){
			if(graph.getEdge(numEdge).isActivate()) countObjects++;
		}
		return countObjects;
	}
	//----------------------------------------
	public boolean checkPointIfEmpty(Point p){
		if(graph.findVertex(p) == null && graph.findEdge(p) == null){
			return true;
		}
		return false;
	}
	public boolean checkPointIfVertex(Point p){
		if( graph.findVertex(p) != null ) return true;
		return false;
	}
	public boolean checkIfActive(Point p){
		if(graph.findVertex(p) != null){
			if(graph.findVertex(p).isActivate())
				return true;
		} 
		if (graph.findEdge(p) != null){
			if(graph.findEdge(p).isActivate())
				return true;
		}
		return false;
	}
	//----------------------------------------
	public void selectAllObject(){
		for(int numVertex = 0; numVertex < graph.countVertex(); numVertex++){
			graph.getVertex(numVertex).activate();
		}
		for(int numEdge = 0; numEdge < graph.countEdge(); numEdge++){
			graph.getEdge(numEdge).activate();
		}
	}
	public void deactivateAllObject(){
		for(int numVertex = 0; numVertex < graph.countVertex(); numVertex++){
			graph.getVertex(numVertex).deactivate();
		}
		for(int numEdge = 0; numEdge < graph.countEdge(); numEdge++){
			graph.getEdge(numEdge).deactivate();
		}
		mainFrame.repaint();
	}
	//------------------------------------------
	public void removeSelectedObjects(){
		for(int numVertex = 0; numVertex < graph.countVertex(); numVertex++){
			if(graph.getVertex(numVertex).isActivate()){
				graph.removeVertex(numVertex);
				numVertex--;
			}
		}
		for(int numEdge = 0; numEdge < graph.countEdge(); numEdge++){
			if(graph.getEdge(numEdge).isActivate()){
				graph.removeEdge(numEdge);
				numEdge--;
			}
		}
		numActualVertex = -1;
		numActualEdge = -1;
		removeTempEdge();
		mainFrame.repaint();
	}
	//----------------------------------------
	public void addVertex(Point p){
		Vertex vertex = new Vertex(p.getX(), p.getY());
		graph.addVertex(vertex);
		numActualVertex = graph.findVertex(vertex);
		graph.getVertex(numActualVertex).activate();
		actualOn();
		
		mainFrame.repaint();
	}
	//------------------------------------------
	public void setPointSelectionBegin(Point p){
		pointSelectionBegin = p;
	}
	public void setPointSelectionEnd(Point p){
		pointSelectionEnd = p;
	}
	public Rectangle2D getSelectionBorder(){
		double x1 = (double)pointSelectionBegin.getX();
		double y1 = (double)pointSelectionBegin.getY();
		double x2 = (double)pointSelectionEnd.getX();
		double y2 = (double)pointSelectionEnd.getY();
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
		return new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
	}
	/*static void swapDouble(double temp1, double temp2){
		double temp = temp1;
		temp1 = temp2;
		temp2 = temp;
	}*/
	public void setSelectionObjects(){
		for(int numVertex = 0; numVertex < graph.countVertex(); numVertex++){
			//Если закомитить деактивацию, то станет возможным выделение рамкой несколько раз через Ctrl. 
			//Но при этом сама область будет выделять все подряд.
			graph.getVertex(numVertex).deactivate();
			if(graph.getVertex(numVertex).isVertexInArea(pointSelectionBegin, pointSelectionEnd)){
				graph.getVertex(numVertex).activate();
			}
		}
		for(int numEdge = 0; numEdge < graph.countEdge(); numEdge++){
			graph.getEdge(numEdge).deactivate();
			if(graph.getEdge(numEdge).isEdgeInArea(pointSelectionBegin, pointSelectionEnd)){
				graph.getEdge(numEdge).activate();
			}
		}
		mainFrame.repaint();
	}
	//-------------------------------------
	public void setPointDragged(Point p){
		pointDragged = p;
	}
	public void dragObjects(Point p){
		double x = (double)pointDragged.getX() - (double)p.getX();
		double y = (double)pointDragged.getY() - (double)p.getY();
		
		for(int numVertex = 0; numVertex < graph.countVertex(); numVertex++){
			Vertex vertex = graph.getVertex(numVertex);
			if(vertex.isActivate()){
				graph.getVertex(numVertex).setPositionVertex(vertex.getX() - x, vertex.getY() - y);
			}	
		}
		pointDragged = p;
		mainFrame.repaint();
	}
	//-------------------------------------
	public void setBeginTempEdge(Point p){
		if(graph.findVertex(p) != null){
			beginTempEdge = graph.findVertex(p);
		}
		endTempEdge = p;
	}
	public void setEndTempEdge(Point p){
		endTempEdge = p;
		mainFrame.repaint();
	}
	public boolean checkExistsTempEdge(){
		if(beginTempEdge == null){
			return false;
		}
		return true;
	}
	public void removeTempEdge(){
		beginTempEdge = null;
		endTempEdge.setLocation(0, 0);
		mainFrame.repaint();
	}
	public Line2D.Double getTempEdge(){
		return new Line2D.Double(beginTempEdge.getCenterX(), beginTempEdge.getCenterY(), endTempEdge.getX(), endTempEdge.getY());
	}
	public boolean checkPossibilityEdge(Point p){
		if(graph.findVertex(p) != beginTempEdge && graph.findVertex(p) != null){
			return true;
		}
		return false;
	}
	public void addEdge(Point p){
		graph.addEdge(beginTempEdge, graph.findVertex(p));
		mainFrame.repaint();
	}
}
