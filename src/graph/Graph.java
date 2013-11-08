package graph;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class Graph {
	private List<Vertex> vertexes;
	private List<Edge> edges;

	public Graph() {
		vertexes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}

	public Graph(Graph graph) {
		vertexes = new ArrayList<Vertex>();
		for (int numVertex = 0; numVertex < graph.countVertex(); numVertex++) {
			this.vertexes.add(graph.getVertex(numVertex));
		}
		edges = new ArrayList<Edge>();
		for (int numEdge = 0; numEdge < graph.countEdge(); numEdge++) {
			this.edges.add(graph.getEdge(numEdge));
		}
	}

	public void setVertexes(ArrayList<Vertex> vertexes) {
		this.vertexes = vertexes;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	public Point getMaxCoords() {
		double maxCoordX = 0;
		double maxCoordY = 0;
		for (Vertex vertex : this.vertexes) {
			if (vertex.getCenterX() > maxCoordX) {
				maxCoordX = vertex.getCenterX();
			}
			if (vertex.getCenterY() > maxCoordY) {
				maxCoordY = vertex.getCenterY();
			}
		}
		return new Point((int)maxCoordX, (int)maxCoordY);
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

	public int countVertex() {
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

	public int findVertex(Vertex vertex) {
		for (int numVertex = 0; numVertex < countVertex(); numVertex++) {
			if (getVertex(numVertex) == vertex)
				return numVertex;
		}
		return -1;
	}

	// EDGE
	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public void addEdge(Vertex v1, Vertex v2) {
		if (!checkRepeatEdge(v1, v2)) {
			// if (checkReverseEdge(v1, v2) != -1) {
			// edges.get(checkReverseEdge(v1, v2)).revers();
			// } else {
			edges.add(new Edge(v1, v2));
			// }
		}
	}

	public Edge getEdge(int numEdge) {
		return edges.get(numEdge);
	}

	public int countEdge() {
		return edges.size();
	}

	public boolean checkRepeatEdge(Vertex v1, Vertex v2) {
		for (int numEdge = 0; numEdge < countEdge(); numEdge++) {
			if (getEdge(numEdge).getVertex1() == v1
					&& getEdge(numEdge).getVertex2() == v2) {
				return true;
			}
		}
		return false;
	}

	public int checkReverseEdge(Vertex v1, Vertex v2) {
		for (int numEdge = 0; numEdge < countEdge(); numEdge++) {
			if (getEdge(numEdge).getVertex1() == v2
					&& getEdge(numEdge).getVertex2() == v1) {
				return numEdge;
			}
		}
		return -1;
	}

	public void removeEdge(int numEdge) {
		edges.remove(getEdge(numEdge));
	}

	public Edge findEdge(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		int SIDELENGTH = 8;
		for (Edge edge : edges) {
			if (edge.intersects(x - SIDELENGTH / 2, y - SIDELENGTH / 2,
					SIDELENGTH, SIDELENGTH)) {
				return edge;
			}
		}
		return null;
	}

	public int findEdge(Edge edge) {
		for (int numEdge = 0; numEdge < countEdge(); numEdge++) {
			if (getEdge(numEdge) == edge)
				return numEdge;
		}
		return -1;
	}

	// --------------------------------
	public void loadFile(String path) throws ClassNotFoundException {
		File file = new File(path);
		ArrayList<Vertex> temp_vertexes = new ArrayList<Vertex>();
		ArrayList<Edge> temp_edges = new ArrayList<Edge>();

		try {
			FileInputStream fstream = new FileInputStream(file);
			ObjectInputStream fostream = new ObjectInputStream(fstream);
			ArrayList<Object> file_graph = (ArrayList<Object>) fostream
					.readObject();
			temp_vertexes = (ArrayList<Vertex>) file_graph.get(0);
			temp_edges = (ArrayList<Edge>) file_graph.get(1);
			fstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		edges = temp_edges;
		vertexes = temp_vertexes;
	}

	public void saveFile(String path) throws ClassNotFoundException {
		File file = new File(path);
		ArrayList<Vertex> temp_vertexes = (ArrayList) vertexes;
		ArrayList<Edge> temp_edges = (ArrayList) edges;

		try {
			FileOutputStream fstream = new FileOutputStream(file);
			ObjectOutputStream fostream = new ObjectOutputStream(fstream);
			ArrayList<Object> file_graph = new ArrayList<Object>();
			file_graph.add(temp_vertexes);
			file_graph.add(temp_edges);
			fostream.writeObject(file_graph);
			fstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
