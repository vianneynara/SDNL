package praktikum;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    /* Inisialisasi atribut kelas: Node, Node, int */
    Node<T> left;
    Node<T> right;
    T data;

    /**
     * Constructor kelas dengan nilai awal data.
     * */
    public Node(T data) {
        this.left = null;
        this.right = null;
        this.data = data;
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
        printDrawnStructure(null);
    }

    /**
     * Mengembalikan penggambaran struktur {@link Node} ini.
     * */
    public void printDrawnStructure(T highLightItem) {
        var highLighted = Tree.search(this, highLightItem).data;
        System.out.println(this.drawStructure(new StringBuilder(), true, new StringBuilder(), highLighted).toString());
    }

    /* Penggambaran struktur secara rekursif */
    private StringBuilder drawStructure(StringBuilder prefix, boolean isTail, StringBuilder sb, T highLighted) {
        if (right != null) {
            right.drawStructure(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, highLighted);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append( (highLighted == data) ? "#" + data.toString() : data.toString() ).append("\n");
        if (left != null) {
            left.drawStructure(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, highLighted);
        }
        return sb;
    }
}
