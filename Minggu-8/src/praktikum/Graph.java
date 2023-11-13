package praktikum;

import java.util.*;

public class Graph {
	private Vertex[] vertices;									// Menyimpan vertex dalam array
	private int[][] adjMatrix;									// Menyimpan adjacency matrix hubungan antar vertex
	private int backIdx;										// Menyimpan posisi vertex terakhir
	private HashMap<String, Integer> vertexPosition;			// Menyimpan korelasi label dengan posisi indeks

	public Graph(int nVertices) {
		this.vertices = new Vertex[nVertices];
		this.adjMatrix = new int[nVertices][nVertices];
		this.backIdx = 0;
		vertexPosition = new HashMap<>();
	}

	/**
	 * Menambahkan sebuah vertex / titik ke dalam graf dengan label vertex.
	 * @param label label vertex
	 * */
	public void addVertex(String label) {
		vertices[backIdx] = new Vertex(label, backIdx);
		vertexPosition.put(label.toUpperCase(), backIdx);
		backIdx++;
	}

	public void removeVertex(String label) {
		// TODO: Menghapus vertex dari graph
	}
	
	/**
	 * Menambahkan edge/penghubung vertex dengan nama label yang terdapat pada graf dengan berat 1.
	 * @param src label vertex 1
	 * @param dst label vertex 2
	 * */
	public void addEdge(String src, String dst) {
		addEdge(
			vertexPosition.get(src.toUpperCase()), 
			vertexPosition.get(dst.toUpperCase())
		);
	}

	/**
	 * Menambahkan edge/penghubung vertex dengan format index pada adjacency matrix dengan berat 1.
	 * @param src posisi vertex 1
	 * @param dst posisi vertex 2
	 * */
	public void addEdge(int src, int dst) {
		addEdge(src, dst, 1);
	}

	/**
	 * Menambahkan edge/penghubung vertex dengan nama label yang terdapat pada graf.
	 * @param src label vertex 1
	 * @param dst label vertex 2
	 * @param weight bobot dari edge
	 * */
	public void addEdge(String src, String dst, int weight) {
		addEdge(
			vertexPosition.get(src.toUpperCase()),
			vertexPosition.get(dst.toUpperCase()),
			weight
		);
	}

	/**
	 * Menambahkan edge/penghubung vertex dengan format index pada adjacency matrix.
	 * @param src posisi vertex 1
	 * @param dst posisi vertex 2
	 * @param weight bobot dari edge
	 * */
	public void addEdge(int src, int dst, int weight) {
		adjMatrix[src][dst] = weight;
		adjMatrix[dst][src] = weight;
	}

	/**
	 * Menghapus edge/penghubung vertex dengan nama label yang terdapat pada graf.
	 * @param src label vertex 1
	 * @param dst label vertex 2
	 * */
	public void removeEdge(String src, String dst) {
		removeEdge(
			vertexPosition.get(src.toUpperCase()),
			vertexPosition.get(dst.toUpperCase())
		);
	}

	/**
	 * Menghapus edge/penghubung vertex dengan format index pada adjacency matrix.
	 * @param src posisi vertex 1
	 * @param dst posisi vertex 2
	 * */
	public void removeEdge(int src, int dst) {
		adjMatrix[src][dst] = 0;
		adjMatrix[dst][src] = 0;
	}

	/** Mengembalikan list vertices / vertex-vertex milik graf ini. */
	public Vertex[] getVertices() {
		return vertices;
	}

	/** Mengembalikan matriks adjacency milik graf ini. */
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
		dfsOriginalHandler(start);
//		dfsRecursiveHandler(start, new boolean[adjMatrix.length]);
		System.out.println();
	}

	/**
	 * (Handler) Melakukan traversal dengan algoritma seperti DFS namun khusus menggunakan adjacent matrix. Menyimpan
	 * posisi vertex yang sudah dilewati dalam sebuah HashSet (isinya unik) dan menggunakan Stack.
	 * */
	private void dfsOriginalHandler(int start) {
		boolean[] visited = new boolean[adjMatrix.length];		// List boolean untuk simpan index yg sudah dilewati
		Stack<Integer> stack = new Stack<>();					// Inisialisasi stack
		stack.push(start);										// Isi stack awal dengan posisi index mulai

		while (!stack.isEmpty()) {								// Selama stack tidak kosong
			int curr = stack.pop();								// Ambil isi paling belakang stack dalam curr
			System.out.print("Popped: " + vertices[curr].label + " (" + curr  + ") ");
			if (!visited[curr]) {								// Jika visited pada posisi curr masih false
				printListContent(visited);
				visited[curr] = true;							// Tambahkan nilai curr pada HashSet visited
//				System.out.print(vertices[curr].label + " ");	// Cetak label pada vertex di index curr di vertices

				/* Melakukan looping dari belakang (karena stack menggunakan sistem First In Last Out) */
				for (int vPos = adjMatrix[curr].length - 1; vPos >= 0; vPos--) {
					/* Jika terdapat koneksi antar vertex curr dan vPos DAN vPos belum true ada di visited */
					if (adjMatrix[curr][vPos] >= 1 && !visited[vPos]) {
						stack.push(vPos);						// Masukkan vPos pada stack
					}
				}
				printListContent(stack);
			}
//			System.out.println();
		}
	}

	/**
	 * (Handler) Melakukan traversal dengan algoritma seperti DFS rekursif namun khusus menggunakan adjacent matrix.
	 * Menyimpan posisi vertex yang sudah dilewati dalam sebuah list boolean. Lebih sederhana, namun mahal.
	 * */
	private void dfsRecursiveHandler(int curr, boolean[] visited) {
		System.out.print(vertices[curr].label + " ");		// Mencetak label vertex
		visited[curr] = true;								// Mengatur nilai visited pada indeks sekarang dgn. true

		/* Loop dari 0 selama kurang dari penjang matrix - 1; iterator menaik 1 per loop */
		for (int vPos = 0; vPos < adjMatrix[curr].length; vPos++) {
			/* Jika terdapat koneksi antar vertex curr dan vPos DAN vPos belum ada di visited */
			if (adjMatrix[curr][vPos] >= 1 && (!visited[vPos])) {
				dfsRecursiveHandler(vPos, visited);				// Memanggil rekursi metode ini sendiri
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
		boolean[] visited = new boolean[adjMatrix.length];		// List boolean untuk simpan index yg sudah dilewati
		Queue<Integer> queue = new LinkedList<>();				// Sistem queue untuk proses BFS
		visited[start] = true;									// Mengatur index awal menjadi dilewati
		queue.add(start);										// Memasukkan posisi awal ke queue
		printListContent(queue);

		int curr;												// Iterator untuk loop, berisi posisi index vertex
		while (!queue.isEmpty()) {								// Selama queue masih memiliki isi
			curr = queue.poll();								// Ambil nilai paling depan di queue
			System.out.print(vertices[curr].label + " ");		// Cetak label pada vertices di posisi curr
			System.out.print("Polled: " + vertices[curr].label + " (" + curr  + ") ");

			for (int i = 0; i < adjMatrix.length; i++) {		// Loop dari 0, selama iterator kurang dari panjang matrix
				/* Jika terdapat koneksi antar vertex curr dan i DAN i belum true di visited */
				if (adjMatrix[curr][i] >= 1 && !visited[i]) {
					queue.add(i);								// Masukkan i ke dalam queue
					visited[i] = true;							// Atur visited pada i menjadi true
				}
			}
			printListContent(queue);
		}
		System.out.println();
	}

	public void minimumSpanningTree_Prim() {
		boolean[] visited = new boolean[adjMatrix.length];
		int edgeCount = 0;
		int totalCost = 0;

		while (edgeCount < adjMatrix.length) {
			int min = Integer.MAX_VALUE;
			int row = 0;
			int col = 0;

			for (int i = 0; i < adjMatrix.length; i++) {
				if (visited[i]) {
					for (int j = 0; j < adjMatrix.length; j++) {
						if (!visited[j] && adjMatrix[i][j] != 0) {
							if (min > adjMatrix[i][j]) {
								min = adjMatrix[i][j];
								row = i;
								col = j;
							}
						}
					}
				}
			}

			if (edgeCount == 0)
				min = 0;
			totalCost += min;
			System.out.println("Edge " + edgeCount++ + ": (" + vertices[row].label + ", " + vertices[col].label + ") cost: " + min);
			visited[col] = true;
		}

		System.out.println("Total cost: " + totalCost);
	}

	/* Metode untuk mencetak isi Queue */
	private void printListContent(Queue<Integer> list) {
		System.out.print("; Queue: ");
		for (int v : list) {
			System.out.print(vertices[v].label + " ");
		}
		System.out.println();
	}

	/* Metode untuk mencetak isi Stack */
	private void printListContent(Stack<Integer> list) {
		System.out.print("; Stack: ");
		for (int v : list) {
			System.out.print(vertices[v].label + " ");
		}
		System.out.println();
	}

	/* Metode untuk mencetak isi Stack */
	private void printListContent(boolean[] list) {
		System.out.print("; Visited: ");
		for (int v = 0; v < list.length; v++) {
			if (list[v])
				System.out.print(vertices[v].label + " ");
		}
		System.out.println();
	}
}
