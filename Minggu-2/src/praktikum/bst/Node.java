package praktikum.bst;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    /* Inisialisasi atribut kelas: Node, Node, int */
    Node<T> left;
    Node<T> right;
    T data;
    int height;

    /**
     * Constructor kelas dengan nilai awal data.
     * */
    public Node(T data) {
        this.left = null;
        this.right = null;
        this.data = data;
        this.height = 0;
    }

    /**
     * Mendapatkan node anak kiri.
     * */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * Mengganti anak kiri node.
     * */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * Mendapatkan node anak kanan.
     * */
    public Node<T> getRight() {
        return right;
    }

    /**
     * Mengatur anak kanan node.
     * */
    public void setRight(Node<T> right) {
        this.right = right;
    }

    /**
     * Mengembalikan isi dari node.
     * */
    public T getData() {
        return data;
    }

    /**
     * Mengatur isi node. 
     * */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Mengecek apakah node ini memiliki anak.
     * */
    public boolean isTail() {
        return (left == null && right == null);
    }

    /**
     * Mengecek apakah node ini memiliki anak kiri.
     * */
    public boolean hasLeft() {
        return (left != null);
    }

    /**
     * Mengecek apakah node ini memiliki anak kanan.
     * */
    public boolean hasRight() {
        return (right != null);
    }

    @Override
    public String toString() {
        return "{" + data + '}';
    }

    /**
     * Melakukan komparasi / perbandingan antar objek yang bisa dibandingkan.
     * */
    public int compareTo(Node<T> node) {
        return this.data.compareTo(node.getData());
    }

    /**
     * Mengembalikan penggambaran struktur {@link Node} ini.
     * */
    public void printDrawnStructure() {
        System.out.println(this.drawStructure(new StringBuilder(), true, new StringBuilder()));
    }

    /* Penggambaran struktur secara rekursif */
    private StringBuilder drawStructure(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (right != null) {
            right.drawStructure(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(data.toString() + "["+ height + "]" + "("+ balanceFactor(this) + ")").append("\n");
        if (left != null) {
            left.drawStructure(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

	/**
	 * Mendapatkan height / ketinggian sebuah node.
	 * */
	private int height(Node<T> node) {
		return (node == null) ? -1 : node.height;
	}

	/**
	 * Mengembalikan balance factor dari sebuah {@link Node<T>}.
	 */
	private int balanceFactor(Node<T> node) {
		/* Mendapatkan balance factor dengan mengurangi ketinggian node kanan dengan node kiri */
		return height(node.right) - height(node.left);
	}
}

