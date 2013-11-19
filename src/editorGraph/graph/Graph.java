package editorGraph.graph;

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

	static int mainId = 0;
	int id;

	private List<Vertex> vertexes;
	private List<Edge> edges;

	private Vertex actualVertex = null;
	private Edge actualEdge = null;

	private Edge tempararyEdge = null;

	public Graph() {
		id = mainId++;
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

	public int getId() {
		return id;
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public Vertex getActualVertex() {
		return this.actualVertex;
	}

	public Edge getActualEdge() {
		return this.actualEdge;
	}

	public Point getMinCoords() {
		double minCoordX = 0;
		double minCoordY = 0;
		for (Vertex vertex : vertexes) {
			if (vertex.getCenterX() < minCoordX) {
				minCoordX = vertex.getCenterX();
			}
			if (vertex.getCenterY() < minCoordY) {
				minCoordY = vertex.getCenterY();
			}
		}
		return new Point((int) minCoordX, (int) minCoordY);
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
	}

	public Vertex getVertex(int numVertex) {
		if (numVertex < countVertexes()) {
			return vertexes.get(numVertex);
		} else {
			return null;
		}
	}

	public void removeVertex(Vertex vertex) {
		removeEdgesInVertex(vertex);
		vertexes.remove(vertex);
	}

	private void removeEdgesInVertex(Vertex vertex) {
		List<Edge> removedEdges = new ArrayList<Edge>();
		for (Edge edge : this.edges) {
			if (edge.isContentVertexInEdge(vertex)) {
				removedEdges.add(edge);
			}
		}
		for (Edge edge : removedEdges) {
			removeEdge(edge);
		}
	}

	public int countVertexes() {
		return vertexes.size();
	}

	private int countEdges() {
		return edges.size();
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
	public void addEdge(Vertex vertex1, Vertex vertex2) {
		if (!checkExistEdge(vertex1, vertex2)) {
			edges.add(new Edge(vertex1, vertex2));
		}
	}

	public void addTemporaryEdgeInGraph(Point point) {
		Vertex vertex1 = tempararyEdge.getVertex1();
		Vertex vertex2 = findVertex(point);
		addEdge(vertex1, vertex2);
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
	private boolean checkExistEdge(Vertex vertex1, Vertex vertex2) {
		for (Edge edge : edges) {
			if (edge.getVertex1() == vertex1 && edge.getVertex2() == vertex2) {
				return true;
			}
		}
		return false;
	}

	// --------------------------------
	public void loadFile(String path) {
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
		} catch (ClassNotFoundException e) {
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
	public void setActualObject(Point point) {
		actualVertex = findVertex(point);
		actualEdge = findEdge(point);
	}

	public void select(Point point) {
		if (findVertex(point) != null) {
			findVertex(point).selectOn();
		} else if (findEdge(point) != null) {
			findEdge(point).selectOn();
		}
	}

	public void deactivate(Point point) {
		if (findVertex(point) != null) {
			findVertex(point).selectOff();
		} else if (findEdge(point) != null) {
			findEdge(point).selectOff();
		}
	}

	public int countActiveObjects() {
		int countVertexes = 0;
		for (Vertex vertex : getVertexes()) {
			if (vertex.isSelected()) {
				countVertexes++;
			}
		}

		int countEdges = 0;
		for (Edge edge : getEdges()) {
			if (edge.isSelected()) {
				countEdges++;
			}
		}
		return countVertexes + countEdges;
	}

	public void activateAll() {
		for (Vertex vertex : getVertexes()) {
			vertex.selectOn();
		}
		for (Edge edge : edges) {
			edge.selectOn();
		}
	}

	public void deselectAll() {
		for (Vertex vertex : getVertexes()) {
			vertex.selectOff();
		}
		for (Edge edge : edges) {
			edge.selectOff();
		}
	}

	public void removeSelectedObjects() {
		removeTempEdge();

		List<Vertex> selectionVertexes = getSelectionVertexes();
		for (Vertex vertex : selectionVertexes) {
			if (vertex.isSelected()) {
				removeVertex(vertex);
			}
		}

		List<Edge> selectionEdges = getSelectionEdges();
		for (Edge edge : selectionEdges) {
			if (edge.isSelected()) {
				removeEdge(edge);
			}
		}
	}

	public void removeTempEdge() {
		tempararyEdge = null;
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

	public boolean checkPossibilityEdge(Point point) {
		if (findVertex(point) != tempararyEdge.getVertex1()
				&& findVertex(point) != null) {
			return true;
		}
		return false;
	}

	public List<Vertex> getSelectionVertexes() {
		List<Vertex> selectionVertexes = new ArrayList<Vertex>();
		for (Vertex vertex : getVertexes()) {
			if (vertex.isSelected()) {
				selectionVertexes.add(vertex);
			}
		}
		return selectionVertexes;
	}

	public List<Edge> getSelectionEdges() {
		List<Edge> selectionEdges = new ArrayList<Edge>();
		for (Edge edge : getEdges()) {
			if (edge.isSelected()) {
				selectionEdges.add(edge);
			}
		}
		return selectionEdges;
	}

	public List<Edge> getAdjacentEdges(Vertex vertex) {
		List<Edge> adjacentEdges = new ArrayList<Edge>();
		for (Edge edge : getEdges()) {
			if (edge.getVertex1() == vertex) {
				adjacentEdges.add(edge);
			}
		}
		return adjacentEdges;
	}

	public boolean isSelectAllVertex() {
		return countVertexes() == getSelectionVertexes().size();
	}
	
	public boolean isSelectAllEdges() {
		return countEdges() == getSelectionEdges().size();
	}

	public Edge getCommonEdge(Vertex vertex1, Vertex vertex2) {
		for (Edge edge : getEdges()) {
			if (edge.getVertex1() == vertex1 && edge.getVertex2() == vertex2) {
				return edge;
			}
		}
		return null;
	}
}
