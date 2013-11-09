package graph;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Graph {
	int SELECTION_OFFSET = 8;
	private List<Vertex> vertexes;
	private List<Edge> edges;

	public Graph() {
		vertexes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}

	public Graph(Graph graph) {
		vertexes = new ArrayList<Vertex>();
		for (Vertex vertex : graph.vertexes) {
			this.vertexes.add(vertex);
		}
		edges = new ArrayList<Edge>();
		for (Edge edge : graph.edges) {
			this.edges.add(edge);
		}
	}

	public Point getMaxCoords() {
		double maxCoordX = 0;
		double maxCoordY = 0;
		for (Vertex vertex : vertexes) {
			if (vertex.getCenterX() > maxCoordX) {
				maxCoordX = vertex.getCenterX();
			}
			if (vertex.getCenterY() > maxCoordY) {
				maxCoordY = vertex.getCenterY();
			}
		}
		return new Point((int) maxCoordX, (int) maxCoordY);
	}

	// VERTEX
	public void addVertex(Vertex vertex) {
		vertexes.add(vertex);
	}

	public Vertex getVertex(int numVertex) {
		return vertexes.get(numVertex);
	}

	public void removeVertex(int numVertex) {
		for (Edge edge : edges) {
			if (edge.isContentVertexInEdge(getVertex(numVertex))) {
				edges.remove(edge);
			}
		}
		vertexes.remove(getVertex(numVertex));
	}

	public int countVertexes() {
		return vertexes.size();
	}

	public Vertex findVertex(Point p) {
		for (Vertex vertex : vertexes) {
			if (vertex.contains(p)) {
				return vertex;
			}
		}
		return null;
	}

	public int getIndexVertex(Vertex vertex) {
		return vertexes.indexOf(vertex);
	}

	// EDGE
	public void addEdge(Vertex v1, Vertex v2) {
		if (!checkExistEdge(v1, v2)) {
			edges.add(new Edge(v1, v2));
		}
	}

	public Edge getEdge(int numEdge) {
		return edges.get(numEdge);
	}

	public void removeEdge(int numEdge) {
		edges.remove(getEdge(numEdge));
	}

	public int countEdge() {
		return edges.size();
	}

	public Edge findEdge(Point p) {
		for (Edge edge : edges) {
			if (edge.intersects(p.getX() - SELECTION_OFFSET / 2, p.getY()
					- SELECTION_OFFSET / 2, SELECTION_OFFSET, SELECTION_OFFSET)) {
				return edge;
			}
		}
		return null;
	}

	public int getIndexEdge(Edge edge) {
		return edges.indexOf(edge);
	}

	private boolean checkExistEdge(Vertex v1, Vertex v2) {
		for (Edge edge : edges) {
			if (edge.getVertex1() == v1 && edge.getVertex2() == v2) {
				return true;
			}
		}
		return false;
	}

	// --------------------------------
	@SuppressWarnings("unchecked")
	public void loadFile(String path) throws ClassNotFoundException {
		File file = new File(path);
		try {
			FileInputStream fstream = new FileInputStream(file);
			ObjectInputStream fostream = new ObjectInputStream(fstream);
			ArrayList<Object> file_graph = (ArrayList<Object>) fostream
					.readObject();
			vertexes = (ArrayList<Vertex>) file_graph.get(0);
			edges = (ArrayList<Edge>) file_graph.get(1);
			fstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveFile(String path) throws ClassNotFoundException {
		File file = new File(path);
		try {
			FileOutputStream fstream = new FileOutputStream(file);
			ObjectOutputStream fostream = new ObjectOutputStream(fstream);
			ArrayList<Object> file_graph = new ArrayList<Object>();
			file_graph.add(vertexes);
			file_graph.add(edges);
			fostream.writeObject(file_graph);
			fstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
