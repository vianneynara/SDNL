package praktikum;

import java.util.*;

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
		vertices[backIdx] = new Vertex(label, backIdx++);
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

	/**
	 * Mengembalikan indikator koneksi atau berat antara dua posisi vertex.
	 * @return boolean
	 * */
	private boolean isConnected(int vertex1, int vertex2) {
		return getWeight(vertex1, vertex2) >= 1;
	}

	/**
	 * (Wrapper) Melakukan traversal dengan depth first search / ke dalam mulai dari vertex paling awal (0).
	 * */
	public void depthFirstSearch() {
		depthFirstSearch(0);
	}

	/**
	 * (Wrapper) Melakukan traversal dengan depth first search / ke dalam mulai dari vertex posisi indeks vertex spesifik.
	 * @param start posisi vertex nya.
	 * */
	public void depthFirstSearch(int start) {
		dfsHandler(start, new boolean[adjMatrix.length]);
		System.out.println();
	}

	/**
	 * (Handler) Melakukan traversal dengan algoritma seperti DFS namun khusus menggunakan adjacent matrix. Menyimpan
	 * posisi vertex yang sudah dilewati dalam sebuah list boolean.
	 * */
	public void dfsHandler(int currVert, boolean[] visited) {
		System.out.print(vertices[currVert].label + " ");
		visited[currVert] = true;
		for (int i = 0; i < adjMatrix[currVert].length; i++) {
			if (adjMatrix[currVert][i] >= 1 && (!visited[i])) {
				dfsHandler(i, visited);
			}
		}
	}

	/**
	 * (Wrapper) Melakukan traversal dengan breadth first search / melebar mulai dari vertex paling awal (0).
	 * */
	public void breadthFirstSearch() {
		breadthFirstSearch(0);
	}

	/**
	 * Melakukan traversal dengan breadth first search / melebar mulai dari posisi indeks vertex
	 * spesifik. BFS menggunakan sistem Queue untuk menyimpan node yang selevel.
	 * @param start posisi vertex nya.
	 * */
	public void breadthFirstSearch(int start) {
		boolean[] visited = new boolean[adjMatrix.length];
		Queue<Integer> queue = new LinkedList<>();
		visited[start] = true;
		queue.add(start);
		printQueueContent(queue);

		int currVert;
		while (!queue.isEmpty()) {
			currVert = queue.poll();
//			System.out.print(vertices[currVert].label + " ");
			System.out.print("Popped: " + vertices[currVert].label + " (" + currVert  + ") ");

			for (int i = 0; i < adjMatrix.length; i++) {
				if (adjMatrix[currVert][i] >= 1 && !visited[i]) {
					queue.add(i);
					visited[i] = true;
				}
			}
			printQueueContent(queue);
		}
		System.out.println();
	}

	private void printQueueContent(Queue<Integer> list) {
		System.out.print("Queue: ");
		for (int v : list) {
			System.out.print(vertices[v] + " ");
		}
		System.out.println();
	}
}
