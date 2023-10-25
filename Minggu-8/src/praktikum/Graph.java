package praktikum;

public class Graph {
	private Vertex[] vertices;									// Menyimpan vertex dalam array
	private int[][] adjMatrix;									// Menyimpan adjacency matrix hubungan antar vertex
	private int backIdx;										// Menyimpan posisi vertex terakhir

	public Graph(int nVertices) {
		this.vertices = new Vertex[nVertices];
		this.adjMatrix = new int[nVertices][nVertices];
		this.backIdx = 0;
	}

	// Getters & Setters

	/**
	 * Menambahkan sebuah vertex / titik ke dalam graf dengan label vertex.
	 * @param label label vertex
	 * */
	public void addVertex(String label) {
		vertices[backIdx++] = new Vertex(label);
	}

	/**
	 * Menambahkan edge/penghubung vertex dengan format adjacency matrix.
	 * @param src posisi node 1
	 * @param dest posisi node 2
	 * */
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

	/**
	 * Menampilkan seluruh vertex yang ada dalam graf.
	 * */
	public void displayVertices() {
		System.out.print("Vertices: ");
		for (Vertex v : vertices)
			System.out.print(v + " ");
		System.out.println();
	}

	/**
	 * Menggambarkan adjacency matrix hubungan antar vertex.
	 * */
	public void displayMatrix() {
		System.out.print("%  ");
		for (Vertex vertex : vertices) {								// Mencetak title label horizontal
			if (vertex != null) {										// Jika vertex tidak null / vertex ada
				System.out.print(vertex.label + "  ");
			}
		}
		System.out.println();

		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i] != null) {									// Jika vertex tidak null / vertex ada
				System.out.print(vertices[i].label + "  ");				// Mencetak title label vertikal
				for (int j = 0; j < vertices.length; j++) {
					if (vertices[j] != null)							// Jika vertex tidak null / vertex ada
						System.out.print(adjMatrix[i][j] + "  ");
				}
			}
			System.out.println();
		}
	}
}
