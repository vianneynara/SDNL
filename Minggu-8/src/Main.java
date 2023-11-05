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
		graph.addEdge("A", "B"); // AB | graph.addEdge(0, 1);
		graph.addEdge("A", "F"); // AF | graph.addEdge(0, 5);
		graph.addEdge("A", "I"); // AI | graph.addEdge(0, 8);
		graph.addEdge("B", "C"); // BC | graph.addEdge(1, 2);
		graph.addEdge("B", "E"); // BE | graph.addEdge(1, 4);
		graph.addEdge("C", "D"); // CD | graph.addEdge(2, 3);
		graph.addEdge("C", "E"); // CE | graph.addEdge(2, 4);
		graph.addEdge("D", "G"); // DG | graph.addEdge(3, 6);
		graph.addEdge("D", "H"); // DH | graph.addEdge(3, 7);
		graph.addEdge("E", "G"); // EG | graph.addEdge(4, 6);
		graph.addEdge("F", "G"); // FG | graph.addEdge(5, 6);
		graph.addEdge("G", "E"); // GE | graph.addEdge(6, 4);
		System.out.println("Adjacency Matrix:");
		graph.displayMatrix();

		System.out.println("Depth First Search:");
		graph.depthFirstSearch();

		System.out.println("Breadth First Search:");
		graph.breadthFirstSearch();
	}
}
