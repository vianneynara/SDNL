import praktikum.*;

public class Main {

	public static void main(String[] args) {
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
		graph.addVertex("J"); // 9

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
		graph.addEdge("F", "G", 3);
		graph.addEdge("G", "H", 6);
		graph.addEdge("G", "J", 7);
		graph.addEdge("H", "I", 4);
		graph.addEdge("H", "J", 6);
		graph.addEdge("I", "J", 7);
//		System.out.println("Adjacency Matrix:");
//		graph.displayMatrix();
//
//		System.out.println("Depth First Search:");
//		graph.depthFirstSearch();
//
//		System.out.println("Breadth First Search:");
//		graph.breadthFirstSearch();

		System.out.println("Minimum Spanning Tree:");
		graph.minimumSpanningTree_Prim();
	}
}
