import praktikum.*;

public class Main {

	public static void regularBFSDFS() {
		Graph graph = new Graph(10);
		graph.addVertex("A"); // 0
		graph.addVertex("B"); // 1
		graph.addVertex("C"); // 2
		graph.addVertex("D"); // 3
		graph.addVertex("E"); // 4
		graph.addVertex("F"); // 5
		graph.addVertex("G"); // 6
		graph.addVertex("H"); // 7
		graph.addVertex("I"); // 8

		graph.displayVertices();
		graph.addEdge("A", "B");
		graph.addEdge("A", "F");
		graph.addEdge("A", "I");
		graph.addEdge("B", "C");
		graph.addEdge("B", "E");
		graph.addEdge("C", "D");
		graph.addEdge("C", "E");
		graph.addEdge("D", "G");
		graph.addEdge("D", "H");
		graph.addEdge("E", "G");
		graph.addEdge("F", "G");
		graph.addEdge("G", "E");
		System.out.println("Adjacency Matrix:");
		graph.displayMatrix();

		System.out.println("\nDepth First Search:");
		graph.depthFirstSearch();

		System.out.println("\nBreadth First Search:");
		graph.breadthFirstSearch();
	}

	public static void regularMSTs() {
		Graph graph = new Graph(10);
		graph.addVertex("A"); // 0
		graph.addVertex("B"); // 1
		graph.addVertex("C"); // 2
		graph.addVertex("D"); // 3
		graph.addVertex("E"); // 4
		graph.addVertex("F"); // 5
		graph.addVertex("G"); // 6
		graph.addVertex("H"); // 7
		graph.addVertex("I"); // 8
//		graph.addVertex("J"); // 9

		graph.displayVertices();
		graph.addEdge("A", "B", 5);
		graph.addEdge("A", "C", 5);
		graph.addEdge("B", "D", 4);
		graph.addEdge("B", "E", 9);
		graph.addEdge("C", "D", 4);
		graph.addEdge("C", "F", 7);
		graph.addEdge("D", "E", 8);
		graph.addEdge("D", "F", 6);
		graph.addEdge("E", "G", 10);
		graph.addEdge("E", "H", 8);
		graph.addEdge("E", "I", 9);
		graph.addEdge("F", "E", 10);
		graph.addEdge("F", "G", 3);
		graph.addEdge("G", "H", 6);
		graph.addEdge("G", "J", 7);
		graph.addEdge("H", "I", 4);
		graph.addEdge("H", "J", 6);
		graph.addEdge("I", "J", 7);
		System.out.println("Adjacency Matrix:");
		graph.displayMatrix();

		System.out.println("\nDepth First Search:");
		graph.depthFirstSearch();

		System.out.println("\nBreadth First Search:");
		graph.breadthFirstSearch();

		System.out.println("\nMinimum Spanning Tree (PRIM):");
		graph.minimumSpanningTree_Prim();

		System.out.println("\nMinimum Spanning Tree (KRUSKAL):");
		graph.minimumSpanningTree_KruskalV1();
	}

	public static void testDijkstra() {
		// make Graph from A to F
		Graph graph = new Graph(6);
		graph.addVertex("A"); // 0
		graph.addVertex("B"); // 1
		graph.addVertex("C"); // 2
		graph.addVertex("D"); // 3
		graph.addVertex("E"); // 4
		graph.addVertex("F"); // 5

		graph.addDirectedEdge("A", "B", 50);
		graph.addDirectedEdge("A", "C", 30);
		graph.addDirectedEdge("A", "D", 100);
		graph.addDirectedEdge("A", "F", 10);
		graph.addDirectedEdge("B", "C", 5);
		graph.addDirectedEdge("C", "B", 5);
		graph.addDirectedEdge("D", "B", 20);
		graph.addDirectedEdge("D", "C", 50);
		graph.addDirectedEdge("E", "D", 15);
		graph.addDirectedEdge("E", "F", 15);
		graph.addDirectedEdge("F", "D", 10);

		System.out.println("Adjacency Matrix:");
		graph.displayMatrix();

		var src = "A";
		var dst = "D";
		System.out.printf("\nDijkstra from %s to %s: %n", src, dst);
		graph.dijkstra(src, dst);

		System.out.println("\n\nGeneral Dijkstra (from A):");
		graph.dijkstra();
	}

	public static void topologicalSortTest() {
		Graph graph = new Graph(7);
		graph.addVertex("J1"); // 0
		graph.addVertex("J2"); // 1
		graph.addVertex("J3"); // 2
		graph.addVertex("J4"); // 3
		graph.addVertex("J5"); // 4
		graph.addVertex("J6"); // 5
		graph.addVertex("J7"); // 6

		graph.addDirectedEdge("J1", "J2");
		graph.addDirectedEdge("J1", "J3");
		graph.addDirectedEdge("J2", "J4");
		graph.addDirectedEdge("J2", "J5");
		graph.addDirectedEdge("J2", "J6");
		graph.addDirectedEdge("J3", "J4");
		graph.addDirectedEdge("J4", "J5");
		graph.addDirectedEdge("J5", "J7");

		graph.displayMatrix();
		System.out.println();

		System.out.println("Topological Sort	: ");
		graph.topologicalSort();
	}

	public static void main(String[] args) {
//		regularBFSDFS();
//		regularMSTs();
//		topologicalSortTest();
		testDijkstra();
	}
}
