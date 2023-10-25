package praktikum;

public class Graph {
	private Vertex[] vertices;
	private int verticesIdx = 0;
	private int[][] adjMatrix;

	public Graph(int nVertices) {
		vertices = new Vertex[nVertices];
		adjMatrix = new int[nVertices][nVertices];
	}

	// Getters & Setters

	public void addVertex(String label) {
		vertices[verticesIdx++] = new Vertex(label);
	}

	public void addEdge(int src, int dest) {
		adjMatrix[src][dest] = 1;
		adjMatrix[dest][src] = 1;
	}

	public void removeVertex(String label) {
		// Menghapus vertex dari graph
	}

	public void removeEdge(int src, int dest) {
		adjMatrix[src][dest] = 0;
		adjMatrix[dest][src] = 0;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public int[][] getAdjMatrix() {
		return adjMatrix;
	}

	public int getWeight(int i, int j) {
		return adjMatrix[i][j];
	}

	public void displayVertices() {
		System.out.print("Vertices: ");
		for (Vertex v : vertices)
			System.out.print(v + " ");
		System.out.println();
	}

	public void displayMatrix() {
		System.out.print("%  ");
		for (Vertex vertex : vertices) {
			if (vertex != null)
				System.out.print(vertex.label + "  ");
		}
		System.out.println();

		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i] != null) {
				System.out.print(vertices[i].label + "  ");
				for (int j = 0; j < vertices.length; j++) {
					if (vertices[j] != null)
						System.out.print(adjMatrix[i][j] + "  ");
				}
			}
			System.out.println();
		}
	}
}
