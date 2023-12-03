package praktikum;

public class Edge {

	Vertex src;
	Vertex dest;
	int weight;

	public Edge(Vertex src, Vertex dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	// Getters & Setters

	public Vertex getSrc() {
		return src;
	}

	public void setSrc(Vertex src) {
		this.src = src;
	}

	public Vertex getDest() {
		return dest;
	}

	public void setDest(Vertex dest) {
		this.dest = dest;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
