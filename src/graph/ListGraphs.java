package graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ListGraphs {
	private List<Graph> graphs;

	
	public ListGraphs(){
		graphs = new ArrayList<Graph>();
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
