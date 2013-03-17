package graph;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;


public class Graph {
	public Graph() {
		vertexes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}
	//VERTEX
	public void addVertex(Vertex vertex){
		vertexes.add(vertex);
	}
	public Vertex getVertex(int numVertex){
		return vertexes.get(numVertex);
	}
	public void removeVertex(int numVertex){
		for(int numEdge = 0; numEdge < countEdge(); numEdge++){
			if(getEdge(numEdge).isContentVertexInEdge(getVertex(numVertex))){
				edges.remove(numEdge);
				numEdge--;
			}
		}
		vertexes.remove(getVertex(numVertex));
	}
	public int countVertex(){
		return vertexes.size();
	}
	public Vertex findVertex(Point p){
		for(Vertex vertex : vertexes){
			if(vertex.contains(p)){
				return vertex;
			}
		}
		return null;
	}
	public int findVertex(Vertex vertex){
		for(int numVertex = 0; numVertex < countVertex(); numVertex++){
			if(getVertex(numVertex) == vertex) return numVertex;
		}
		return -1;
	}
	//EDGE
	public Edge getEdge(int numEdge){
		return edges.get(numEdge);
	}
	public int countEdge(){
		return edges.size();
	}
	public boolean checkRepeatEdge(Vertex v1, Vertex v2){
		for(int numEdge = 0; numEdge < countEdge(); numEdge++){
			if(getEdge(numEdge).getV1() == v1 && getEdge(numEdge).getV2() == v2){
				return true;
			}
		}
		return false;
	}
	public int checkReverseEdge(Vertex v1, Vertex v2){
		for(int numEdge = 0; numEdge < countEdge(); numEdge++){
			if(getEdge(numEdge).getV1() == v2 && getEdge(numEdge).getV2() == v1){
				return numEdge;
			}
		}
		return -1;
	}
	public void addEdge(Vertex v1, Vertex v2){
		if(!checkRepeatEdge(v1, v2)){
			if(checkReverseEdge(v1, v2) != -1){
				edges.get(checkReverseEdge(v1, v2)).revers();
			} else {
				edges.add(new Edge(v1, v2));
			}
		}
	}
	public void removeEdge(int numEdge){
		edges.remove(getEdge(numEdge));
	}
	public Edge findEdge(Point p){
		int x = (int) p.getX();
	    int y = (int) p.getY();
		int SIDELENGTH = 8;
		for (Edge edge : edges) {
			if (edge.intersects(x-SIDELENGTH/2, y-SIDELENGTH/2, SIDELENGTH, SIDELENGTH)) {
				return edge;
			}		
		}
		return null;
	}
	public int findEdge(Edge edge){
		for(int numEdge = 0; numEdge < countEdge(); numEdge++){
			if(getEdge(numEdge) == edge) return numEdge;
		}
		return -1;
	}
	
	private List<Vertex> vertexes;
	private List<Edge> edges;
}
