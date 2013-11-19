package editorGraph.algoritm;

import java.util.List;

import editorGraph.controller.Controller;
import editorGraph.graph.Edge;
import editorGraph.graph.Graph;
import editorGraph.graph.Vertex;

import javax.swing.JOptionPane;

public class EulerianPath implements Algorithm {
	Graph graph;
	Controller controller;

	public EulerianPath(Controller controller) {
		this.controller = controller;
	}

	public void run() {
		this.graph = controller.getCurrentGraph();

		boolean eulerianPath = false;
		
		for(Vertex vertex : graph.getVertexes()){
			graph.deselectAll();
			if(findEulerianPath(vertex, vertex)) {
				eulerianPath = true;
				break;
			}
		}
		
		controller.repaint();
		if(eulerianPath){
			JOptionPane.showMessageDialog(null, "Ёйлеров путь найден");
		} else {
			JOptionPane.showMessageDialog(null, "Ёйлеров путь не найден");			
		}
	}

	private boolean findEulerianPath(Vertex startingVertex,
			Vertex currentVertex) {
		if (graph.isSelectAllEdges()) {
			return true;
		} else {
			List<Edge> edges = graph.getAdjacentEdges(currentVertex);
			for (Edge edge : edges) {
				if (!edge.isSelected()) {
					edge.selectOn();
					if (findEulerianPath(startingVertex, edge.getVertex2())) {
						return true;
					}
					edge.selectOff();
				}
			}
		}
		return false;
	}
}
