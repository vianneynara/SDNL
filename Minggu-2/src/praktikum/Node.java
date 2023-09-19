package praktikum;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    /* Inisialisasi atribut kelas: Node, Node, int */
    private Node<T> leftLink;
    private Node<T>  rightLink;
    private T data;

    /**
     * Constructor kelas dengan nilai awal data.
     * */
    public Node(T data) {
        this.leftLink = null;
        this.rightLink = null;
        this.data = data;
    }

    /**
     * Mendapatkan node anak kiri.
     * */
    public Node<T> getLeft() {
        return leftLink;
    }

    /**
     * Mengganti anak kiri node.
     * */
    public void setgetLeft(Node<T> leftLink) {
        this.leftLink = leftLink;
    }

    /**
     * Mendapatkan node anak kanan.
     * */
    public Node<T> getRight() {
        return rightLink;
    }

    /**
     * Mengatur anak kanan node.
     * */
    public void setgetRight(Node<T> rightLink) {
        this.rightLink = rightLink;
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
    public void setData(T data) {                           // METODE mengatur getData, param: int
        this.data = data;                                   // ATUR getData kelas dgn. getData parameter
    }

    /**
     * Mengecek apakah node ini memiliki anak.
     * */
    public boolean isLeaf() {
        return (leftLink == null && rightLink == null);     // mengecek apakah punya anak atau tidak
    }

    @Override
    public String toString() {                              // metode toString / self-explanatory
        return "{" + data + '}';                            // KEMBALIKAN getData dengan format
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
    public String getDrawnStructure() {
        return this.drawStructure(new StringBuilder(), true, new StringBuilder()).toString();
    }

    /* Penggambaran struktur secara rekursif */
    private StringBuilder drawStructure(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (rightLink != null) {
            rightLink.drawStructure(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(data.toString()).append("\n");
        if (leftLink != null) {
            leftLink.drawStructure(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }
}
