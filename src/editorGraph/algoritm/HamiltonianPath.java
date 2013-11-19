package editorGraph.algoritm;

import java.util.List;

import editorGraph.controller.Controller;
import editorGraph.graph.Edge;
import editorGraph.graph.Graph;
import editorGraph.graph.Vertex;

import javax.swing.JOptionPane;

public class HamiltonianPath implements Algorithm {
	Graph graph;
	Controller controller;
	
	public HamiltonianPath(Controller controller) {
		this.controller = controller;
	}

	public void run() {
		this.graph = controller.getCurrentGraph();
		
		graph.deselectAll();

		Vertex startingVertex = graph.getVertex(0);

		if (startingVertex != null
				&& findHamiltonianCycle(startingVertex, startingVertex)) {
			controller.repaint();
			JOptionPane.showMessageDialog(null, "Гамильтонов путь найден");
		} else {
			controller.repaint();
			JOptionPane.showMessageDialog(null, "Гамильтонов путь не найден");
		}
	}

	private boolean findHamiltonianCycle(Vertex startingVertex,
			Vertex currentVertex) {
		currentVertex.selectOn();
		Edge commonEdge = graph.getCommonEdge(currentVertex, startingVertex);
		if (graph.isSelectAllVertexes() && commonEdge != null) {
			commonEdge.selectOn();
			return true;
		} else {
			List<Edge> edges = graph.getAdjacentEdges(currentVertex);
			for (Edge edge : edges) {
				if (!edge.isSelected()) {
					edge.selectOn();
					if (!edge.getVertex2().isSelected() && findHamiltonianCycle(startingVertex, edge.getVertex2())) {
						return true;
					} else {
						edge.selectOff();
					}
				}
			}
		}
		currentVertex.selectOff();
		return false;
	}

}
