package praktikum;

public class Vertex {

	String label;											// Menyimpan nama dari sebuah vertex
	int id;													// Menyimpan id, untuk sekarang posisi indeks pada graf
	Object data;											// Data yang disimpan di dalam vertex

	/* Konstruktor tidak spesifik */
	public Vertex(String label, int id) {
		this(label, id, null);
	}

	/* Konstruktor utama */
	public Vertex(String label, int id, Object data) {
		this.label = label;
		this.id = id;
		this.data = data;
	}

	// Getters & Setters

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{%s: %d}".formatted(label, id);
	}
}
