package editorGraph.algoritm;

import java.util.List;

import editorGraph.controller.Controller;
import editorGraph.graph.Edge;
import editorGraph.graph.Graph;
import editorGraph.graph.Vertex;

import javax.swing.JOptionPane;

public class EulerianCycle implements Algorithm {
	Graph graph;
	Controller controller;

	public EulerianCycle(Controller controller) {
		this.controller = controller;
	}

	public void run() {
		this.graph = controller.getCurrentGraph();

		graph.deselectAll();

		Vertex startingVertex = graph.getVertex(0);

		if (startingVertex != null
				&& findHamiltonianCycle(startingVertex, startingVertex)) {
			controller.repaint();
			JOptionPane.showMessageDialog(null, "Ёйлеров цикл найден");
		} else {
			controller.repaint();
			JOptionPane.showMessageDialog(null, "Ёйлеров цикл не найден");
		}
	}

	private boolean findHamiltonianCycle(Vertex startingVertex,
			Vertex currentVertex) {
		if (graph.isSelectAllEdges()) {
			return true;
		} else {
			List<Edge> edges = graph.getAdjacentEdges(currentVertex);
			for (Edge edge : edges) {
				if (!edge.isSelected()) {
					edge.selectOn();
					if (findHamiltonianCycle(startingVertex, edge.getVertex2())) {
						return true;
					} else {
						edge.selectOff();
					}
				}
			}
		}
		return false;
	}

}
