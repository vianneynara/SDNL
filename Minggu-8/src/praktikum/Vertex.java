package praktikum;

public class Vertex {

	int id;
	String label;
	Object data;

	public Vertex(int id) {
		this.id = id;
	}

	public Vertex(String label) {
		this.label = label;
	}

	// Getters & Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return '{' + label + '}';
	}
}
