package graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ListGraphs {
	private List<Graph> graphs;

	public ListGraphs() {
		graphs = new ArrayList<Graph>();
	}

	public Graph get(int id) {
		for (Graph graph : graphs) {
			if (graph.getId() == id) {
				return graph;
			}
		}
		return null;
	}

	public void add(Graph graph) {
		graphs.add(graph);
	}

	public void remove(int id) {
		for (Graph graph : graphs) {
			if (graph.getId() == id) {
				graphs.remove(graph);
				break;
			}
		}
	}
	
	public int getIdLastGraph(){
		if(graphs.size() > 0){
			return graphs.get(graphs.size() - 1).getId();
		}
		return -1;
	}
}
