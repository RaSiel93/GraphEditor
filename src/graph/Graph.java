package graph;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Graph {
	static int mainId = 0;
	int id;
	int SELECTION_OFFSET = 8;
	private List<Vertex> vertexes;
	private List<Edge> edges;

	private int numberActualVertex;
	private int numberActualEdge;

	private Edge tempararyEdge;
//	private Vertex beginTempEdge;
//	private Vertex endTempEdge;

	public Graph() {
		id = mainId++;
		vertexes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();

		numberActualVertex = -1;
		numberActualEdge = -1;
		
		tempararyEdge = null;
//		beginTempEdge = null;
//		endTempEdge = null;
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

	public int getId() {
		return id;
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public List<Edge> getEdges() {
		return edges;
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

	// --------------------------------
	// VERTEX
	public void addVertex(Vertex vertex) {
		vertexes.add(vertex);
		numberActualVertex = vertexes.indexOf(vertex);
		vertex.activeOn();
		vertex.actualOn();
	}

	public Vertex getVertex(int numVertex) {
		return vertexes.get(numVertex);
	}

	public void removeVertex(Vertex vertex) {
		for (Edge edge : edges) {
			if (edge.isContentVertexInEdge(vertex)) {
				edges.remove(edge);
			}
		}
		vertexes.remove(vertex);
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

	public void removeEdge(Edge edge) {
		edges.remove(edge);
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

	// --------------------------------
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

	// --------------------------------
	public void actualOn() {
		if (numberActualVertex != -1) {
			getVertex(numberActualVertex).actualOn();
		} else if (numberActualEdge != -1) {
			getEdge(numberActualEdge).actualOn();
		}
	}

	public void actualOff() {
		if (numberActualVertex != -1) {
			getVertex(numberActualVertex).actualOff();
		} else if (numberActualEdge != -1) {
			getEdge(numberActualEdge).actualOff();
		}
	}

	public void activateObject(Point point) {
		if (findVertex(point) != null) {
			findVertex(point).activeOn();
		} else if (findEdge(point) != null) {
			findEdge(point).activeOn();
		}
	}

	public void deactivateObject(Point point) {
		if (findVertex(point) != null) {
			findVertex(point).activeOff();
		} else if (findEdge(point) != null) {
			findEdge(point).activeOff();
		}
	}

	public int countActiveObjects() {
		int countVertexes = 0;
		for (Vertex vertex : getVertexes()) {
			if (vertex.isActivate()) {
				countVertexes++;
			}
		}

		int countEdges = 0;
		for (Edge edge : getEdges()) {
			if (edge.isActive()) {
				countEdges++;
			}
		}
		return countVertexes + countEdges;
	}

	public void activateAll() {
		for (Vertex vertex : getVertexes()) {
			vertex.activeOn();
		}
		for (Edge edge : edges) {
			edge.activeOn();
		}
	}

	public void deactivateAll() {
		for (Vertex vertex : getVertexes()) {
			vertex.activeOff();
		}
		for (Edge edge : edges) {
			edge.activeOff();
		}
	}
	
	public void removeSelectedObjects() {
		for (Vertex vertex : getVertexes()) {
			if (vertex.isActivate()) {
				removeVertex(vertex);
			}
		}
		for (Edge edge : getEdges()) {
			if (edge.isActive()) {
				removeEdge(edge);
			}
		}
		numberActualVertex = -1;
		numberActualEdge = -1;
		removeTempEdge();
	}
	
	public void removeTempEdge() {
		tempararyEdge = null;
//		beginTempEdge = null;
//		endTempEdge = null;
	}
	
	public void setBeginTempEdge(Point point) {
		if (findVertex(point) != null) {
			tempararyEdge = new Edge(findVertex(point), new Vertex(point));
		}
	}

	public void setEndTempEdge(Point point) {
		tempararyEdge.setVertex2(new Vertex(point));
	}

	public boolean checkExistsTempEdge() {
		if (tempararyEdge != null) {
			return true;
		}
		return false;
	}

	public Edge getTempEdge() {
		return tempararyEdge;
	}

	public boolean checkPossibilityEdge(Point p) {
		if (findVertex(p) != tempararyEdge.getVertex1()
				&& findVertex(p) != null) {
			return true;
		}
		return false;
	}
}
