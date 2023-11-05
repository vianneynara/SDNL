import praktikum.*;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph(9);
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
		graph.addEdge(0, 1); // AB
		graph.addEdge(0, 5); // AF
		graph.addEdge(0, 8); // AI
		graph.addEdge(1, 2); // BC
		graph.addEdge(1, 4); // BE
		graph.addEdge(2, 3); // CD
		graph.addEdge(2, 4); // CE
		graph.addEdge(3, 6); // DG
		graph.addEdge(3, 7); // DH
		graph.addEdge(4, 6); // EG
		graph.addEdge(5, 6); // FG
		graph.addEdge(6, 4); // GE
		System.out.println("Adjacency Matrix:");
		graph.displayMatrix();

		System.out.println("Depth First Search:");
		graph.depthFirstSearch();

		System.out.println("Breadth First Search:");
		graph.breadthFirstSearch();
	}
}
