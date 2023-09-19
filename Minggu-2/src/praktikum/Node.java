package praktikum;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    /* Inisialisasi atribut kelas: Node, Node, int */
    private Node<T> getLeftLink;
    private Node<T>  getRightLink;
    private T data;

    /**
     * Constructor kelas dengan nilai awal data.
     * */
    public Node(T data) {
        this.getLeftLink = null;
        this.getRightLink = null;
        this.data = data;
    }

    /**
     * Mendapatkan node anak kiri.
     * */
    public Node<T> getLeft() {
        return getLeftLink;
    }

    /**
     * Mengganti anak kiri node.
     * */
    public void setgetLeft(Node<T> getLeftLink) {
        this.getLeftLink = getLeftLink;
    }

    /**
     * Mendapatkan node anak kanan.
     * */
    public Node<T> getRight() {
        return getRightLink;
    }

    /**
     * Mengatur anak kanan node.
     * */
    public void setgetRight(Node<T> getRightLink) {
        this.getRightLink = getRightLink;
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
        return (getLeftLink == null && getRightLink == null);     // mengecek apakah punya anak atau tidak
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
}
