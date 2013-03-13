package by.bsuir.II;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

class Controller{
	public boolean buttonEdge = false;
	public void buttonOn(){
		buttonEdge = true;
	}
	public void buttonOff(){
		if(isEdgeAdd()) graph.removeEdge();
		buttonEdge = false;
		mainFrame.repaint();
	}
	public void setModel(Graph g){
		graph = g;
	}
	public void setView(MainFrame mf){
		mainFrame = mf;
	}
	private Graph graph;
	private MainFrame mainFrame;
	private Vertex vertex;
	//-----------------------------

	public void removeSelectedObjects(){
		for(int i = 0; i < graph.countVertex(); i++){
			if(graph.getVertex(i).isActivate()){
				//graph.setVertex(graph.getVertex(i));
				graph.removeVertex();
				i--;
			}
		}
		for(int i = 0; i < graph.countEdge(); i++){
			if(graph.getEdge(i).isActivate()){
				//graph.setVertex(graph.getVertex(i));
				graph.removeEdge();
				i--;
			}
		}
		mainFrame.repaint();
	}
	
	//-----------------------------
	public Vertex getVertex(int i){
		return graph.getVertex(i);
	}
	public int countVertex(){
		return graph.countVertex();
	}
	public Vertex findVertex(Point p){
		for(int i = 0; i < graph.countVertex(); i++){
			Vertex vertex = (Vertex)graph.getVertex(i);
			if(vertex.contains(p)) return vertex;
		}
		return null;
	}
	public void addVertex(MouseEvent event) {
		Vertex vertex = findVertex(event.getPoint());
		if(vertex == null){
			vertex = new Vertex(event.getPoint().getX(), event.getPoint().getY());
			graph.addVertex(vertex);
			mainFrame.repaint();
		}
	}
	public Edge getEdge(int i){
		return graph.getEdge(i);
	}
	public Edge getEdge(){
		return graph.getEdge();
	}
	public int countEdge(){
		return graph.countEdge();
	}
	public Edge findEdge(Point p){
		int x = (int) p.getX();
	    int y = (int) p.getY();
		int width = 8;
		int height = 8;
		for (int i = 0; i < graph.countEdge(); i++) {
			if (graph.getEdge(i).intersects(x-width/2, y-height/2, width, height)) {
				return graph.getEdge(i);
			}		
		}
		return null;
	}
	public boolean addEdge(MouseEvent event) {
		Vertex vertex = findVertex(event.getPoint());
		if(vertex != null){
			if(!graph.isEdgeAdd()){
				getEdge().addV2(vertex);
			} else {
				graph.addEdge(vertex);
			}
			return true;
		}
		mainFrame.repaint();
		return false;
	}
	public boolean isObject(MouseEvent event) {
		deactiveObject();
		if(findVertex(event.getPoint()) != null){ 
			findVertex(event.getPoint()).tempararyEphasisOn();
			mainFrame.repaint();
			return true;
		} else if(findEdge(event.getPoint()) != null){
			findEdge(event.getPoint()).tempararyEphasisOn();
			mainFrame.repaint();
			return true;
		}
		mainFrame.repaint();
		return false;
	}
	public void selectAllObject() {
		for(int i = 0; i < graph.countVertex(); i++){
			graph.getVertex(i).activate();
		}
		for(int i = 0; i < graph.countEdge(); i++){
			graph.getEdge(i).activate();
		}
		mainFrame.repaint();
	}
	public void deselectAllObject() {
		for(int i = 0; i < graph.countVertex(); i++){
			graph.getVertex(i).deactivate();
		}
		for(int i = 0; i < graph.countEdge(); i++){
			graph.getEdge(i).deactivate();
		}
		mainFrame.repaint();
	}
	public void deactiveObject() {
		for(int i = 0; i < graph.countVertex(); i++){
			graph.getVertex(i).tempararyEphasisOff();
		}
		for(int i = 0; i < graph.countEdge(); i++){
			graph.getEdge(i).tempararyEphasisOff();
		}
		mainFrame.repaint();
	}
	public void detempAllObject() {
		for(int i = 0; i < graph.countVertex(); i++){
			if(!graph.getVertex(i).isActivate()) graph.getVertex(i).deactivate();
		}
		for(int i = 0; i < graph.countEdge(); i++){
			if(!graph.getEdge(i).isActivate()) graph.getEdge(i).deactivate();
		}
		mainFrame.repaint();
	}
	public void selectObject(MouseEvent event) {
		if(findVertex(event.getPoint()) != null){
			//graph.setVertex(findVertex(event.getPoint()));
			if(findVertex(event.getPoint()).isActivate()){
				findVertex(event.getPoint()).deactivate();
			}else{
				findVertex(event.getPoint()).activate();	
			}
		} else {
			if(findEdge(event.getPoint()) != null){
				//graph.setVertex(null);
				if(findEdge(event.getPoint()).isActivate()){
					findEdge(event.getPoint()).deactivate();
				}else{
					findEdge(event.getPoint()).activate();	
				}
			}
		}
		mainFrame.repaint();
	}

	public void setEdge(MouseEvent event){
		graph.getEdge().setEdge(event.getPoint().getX(), event.getPoint().getY());
		mainFrame.repaint();
	}
	
	public boolean isEdgeAdd(){
		if(graph.countEdge() != 0){
			if(graph.getEdge().getV2() == null)
				return true;
		}
		return false;
	}
	
	public void draggedVertex(MouseEvent event){
		//if(graph.getVertex() != null){	
			int x = event.getX();
			int y = event.getY();
			//graph.getVertex().setPositionVertex(x, y);
			//graph.getVertex().activate();
			mainFrame.repaint();
	}
}
	/*public ArrayList<Vertex> getVertexes(){
		return model.getVertexes();
	}
	public ArrayList<Edge> getEdges(){
		return model.getEdges();
	}
	
	public Vertex getClickedVertex(Point p){
		for(int i = 0; i < model.getVertexes().size(); i++){
			Vertex v = (Vertex)model.getVertexes().get(i);
			if(v.contains(p)) return v;
		}
		return null;
	}
/	public Edge getClickedEdge(Point p) {
		int x = (int) p.getX();
	    int y = (int) p.getY();
		
		int boxX = x;
		int boxY = y;

		int width = 4;
		int height = 4;

		for (Edge edge : model.getEdges()) {
			if (edge.intersects(boxX, boxY, width, height)) {
				return edge;
			}		
		}
		return null;
	}
/	
public void addVertex(MouseEvent event) {
		Vertex vertex = getClickedVertex(event.getPoint());
		if(vertex == null){
			model.addVertex(event.getPoint());
		}
		view.repaint();
	}
	
	public void deactivateAll(){
		for(int i = 0; i < model.getVertexes().size(); i++){
			model.getVertexes().get(i).deactivate();
		}
		for(int i = 0; i < model.getEdges().size(); i++){
			model.getEdges().get(i).deactivate();
		}
		view.repaint();
	}
	public void remove(MouseEvent event){
		Vertex vertex = getClickedVertex(event.getPoint());
		if(vertex != null){
			model.removeVertex(vertex);
		}
		view.repaint();
	}
	public boolean addEdgeBegin(MouseEvent event) {
		Vertex vertex = getClickedVertex(event.getPoint());
		if(vertex != null){
			model.addEdgeBegin(vertex);
			view.repaint();
			return true;
		}
		return false;
	}
	public boolean addEdgeEnd(MouseEvent event) {
		Vertex vertex = getClickedVertex(event.getPoint());
		if(vertex != null){
			model.addEdgeEnd(vertex);
			view.repaint();
			return true;
		}
		return false;
	}
	public void additionEdge(MouseEvent event){
		int x = event.getX();
		int y = event.getY();
		model.setPositionEdge(x, y);
		view.repaint();
	}

	public void cut() {
		// TODO Auto-generated method stub
		
	}
	public void selectAllObject() {
		for(int i = 0; i < model.getVertexes().size(); i++){
			model.getVertexes().get(i).activate();
		}
		for(int i = 0; i < model.getEdges().size(); i++){
			model.getEdges().get(i).activate();
		}
		view.repaint();
	}
	
	*/
	/*
	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event){
			if (event.getModifiers() == event.BUTTON1_MASK){
			    vertex = find(event.getPoint());
				if(vertex == null){
					add(event.getPoint());
				}
		    }
			if (event.getModifiers() == event.BUTTON3_MASK && !activButtonMouseRight){
				vertex2 = find(event.getPoint());
				if(vertex2 != null){
					activButtonMouseRight = true;
					addEdge(vertex.getX(), vertex.getY(), vertex.getX(), vertex.getY());
					edge.addV1(find(event.getPoint()));
				}
		    } else if (event.getModifiers() == event.BUTTON3_MASK && activButtonMouseRight){
				vertex = find(event.getPoint());
				if(vertex != null){
					activButtonMouseRight = false;
					edge.addV2(find(event.getPoint()));
					edge.setLine(edge.getV2().getX()+SIDELENGTH / 2, edge.getV2().getY()+SIDELENGTH / 2,edge.getV1().getX()+SIDELENGTH / 2, edge.getV1().getY()+SIDELENGTH / 2);
				}
		    }
			repaint();
		}
		public void mouseClicked(MouseEvent event){
			if (event.getModifiers() == event.BUTTON1_MASK){
				vertex = find(event.getPoint());
				if(vertex != null && event.getClickCount() >= 2){
					remove(vertex);
				}
			}
		}	
		public void mouseReleased(MouseEvent event){
			if (event.getModifiers() == event.BUTTON1_MASK){	
				vertex = find(event.getPoint());
				if(vertex != null){
					vertex.deactivate();
				}
				repaint();
			}
		}
	}
	
	private class MouseMotionGandler implements MouseMotionListener {
		public void mouseMoved(MouseEvent event){
			if (event.getModifiers() == event.BUTTON1_MASK){
				vertex = find(event.getPoint());
				//edgePrev = edge;
				if(vertex == null)
					setCursor(Cursor.getDefaultCursor());
				else {
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				}
			}
			if (activButtonMouseRight){
				edge.activate();
				edge.setLine(event.getPoint().getX(), event.getPoint().getY(),edge.getV1().getX()+SIDELENGTH / 2, edge.getV1().getY()+SIDELENGTH / 2);
				repaint();
			}
		}
		public void mouseDragged(MouseEvent event){
			if (event.getModifiers() == event.BUTTON1_MASK){
				if(vertex != null){
					int x = event.getX();
					int y = event.getY();
					
					vertex.activate();
					vertex.setFrame( x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
					//if(edge != null){						
						//edge.setLine(x, y, edge.getX2(), edge.getY2());
					//}
					repaint();
				}
			}
		}
	}
	public Vertex find(Point2D p){
		for(int i = 0; i < vertexes.size(); i++){
			Vertex r = (Vertex)vertexes.get(i);
			if(r.contains(p)) return r;
		}
		return null;
	}
	public void add(Point2D p){
		double x = p.getX();
		double y = p.getY();
		vertex = new Vertex(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
		vertexes.add(vertex);
		repaint();
	}
	public void remove(Vertex s){
		if(s == null) return;
		vertex = null;
		vertexes.remove(s);
		repaint();
	}
	public Edge findEdge(Point2D p){
		for(int i = 0; i < edges.size(); i++){
			Edge r = edges.get(i);
			if(r.contains(p)) return r;
		}
		return null;
	}
	public void addEdge(double x1, double y1, double x2, double y2){
		edge = new Edge(x1 + SIDELENGTH / 2,y1 + SIDELENGTH / 2,x2 + SIDELENGTH / 2,y2 + SIDELENGTH / 2);
		edges.add(edge);
		repaint();
	}
	public void removeEdge(Edge s){
		if(s == null) return;
		edge = null;
		edges.remove(s);
		repaint();
	}*/
//}
