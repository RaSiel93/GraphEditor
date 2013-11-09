package graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ListGraphs {
	private List<Graph> graphs;
	private int currentGraph;

	private int numActualVertex;
	private int numActualEdge;
	
	private Vertex beginTempEdge;
	private Point endTempEdge;
	
	public ListGraphs(){
		graphs = new ArrayList<Graph>();
		numActualVertex = -1;
		numActualEdge = -1;
		beginTempEdge = null;
		endTempEdge = new Point(0, 0);
	}

	public Graph get(int id) {
		for(Graph graph : graphs){
			if(graph.getId() == id){
				return graph;
			}
		}
		return null;
	}
}
