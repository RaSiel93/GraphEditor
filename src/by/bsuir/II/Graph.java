package by.bsuir.II;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

public class Graph {
	public Graph() {
		vertexes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		//vertex = null;
		edge = null;
	}
	private List<Vertex> vertexes;
	private List<Edge> edges;
	//private Vertex vertex;
	private Edge edge;
	
	public Vertex getVertex(int i){
		return vertexes.get(i);
	}
	/*public Vertex getVertex(){
		return vertex;
	}
	public void setVertex(Vertex v){
		vertex = v;
	}*/
	public Edge getEdge(int i){
		return edges.get(i);
	}
	public Edge getEdge(){
		return edge;
	}
	public int countVertex(){
		return vertexes.size();
	}
	public int countEdge(){
		return edges.size();
	}
	/*подходы к разработке базы знаний.
	 *15 страниц курсовой работы. Обоснование. Описание предметной области, что система багет(батон), для кого предназначена, функционал. Аналоги(Сводная табличка). Дост и недост. 
	2е. задание. 50 ключевых узлов из разраб предметной области(обсолютн и относит понятия, атрибуты, высказывания, теоремы,).
	*/
	public void addVertex(Vertex vertex){
		//double x = p.getX();
		//double y = p.getY();
		//vertex = new Vertex(x, y);
		vertexes.add(vertex);
	}
	public boolean isEdgeAdd(){
		if(countEdge() != 0){
			if(getEdge().getV2() == null)
				return false;
		}
		return true;
	}
	public void setEdge(Edge e){
		edge = e;
	}
	public void addEdge(Vertex vertex){
		//if(!isEdgeAdd()){
			edge = new Edge(vertex);
			edges.add(edge);
		//}
	}
	public void removeEdge(){
		edges.remove(getEdge());
	}
	public void removeVertex(){
		//if(getVertex() != null){
			for(int i = 0; i < countEdge(); i++){
				//if(getEdge(i).isContentVertexInEdge(getVertex())){
					edges.remove(i);
					i--;
			}
		}
			//vertexes.remove(getVertex());
		//}
		//setVertex(null);
}
	/*public void addEdgeBegin(Vertex v){
			Edge edge = new Edge(v);
			edges.add(edge);
	}
	public void addEdgeEnd(Vertex v){
			getEdge(countEdge()-1).addV2(v);
	}*/
	/*public Vertex getVertex(){
		return vertex;
	}
	public Edge getEdge(){
		return edge;
	}
	public void setVertex(Vertex v){
		vertex = v;
	}
	public void setEdge(Edge e){
		if(!activButtonMouseRight){
			edge = e;
		}
	}
	public List<Vertex> getVertexes(){
		return vertexes;
	}
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	
	
	public void addVertex(Point p){
		if(!activButtonMouseRight){
			double x = p.getX();
			double y = p.getY();
			vertex = new Vertex(x, y);
			vertexes.add(vertex);
		}
	}
	public void removeVertex(Vertex v){
		for(int i = 0; i < edges.size(); i++){
			if(edges.get(i).getV1() == vertex || edges.get(i).getV2() == vertex){
				edges.remove(edges.get(i));
				i--;
			}
		}
		vertexes.remove(vertex);
	}
	public void setPositionEdge(int x, int y){
		if(activButtonMouseRight){
			edge.setPositionEdge(x, y);
		}
	}*/


//}
