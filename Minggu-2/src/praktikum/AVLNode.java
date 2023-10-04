package praktikum;

public class AVLNode<T extends Comparable<T>> implements Comparable<AVLNode<T>> {

	private AVLNode<T> left;
	private AVLNode<T> right;
	private T data;
	private int height;
	
	public AVLNode(T data) {
        this.left = null;
        this.right = null;
        this.data = data;
    }

    /**
     * Mendapatkan node anak kiri.
     * */
    public AVLNode<T> getLeft() {
        return left;
    }

    /**
     * Mengganti anak kiri node.
     * */
    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    /**
     * Mendapatkan node anak kanan.
     * */
    public AVLNode<T> getRight() {
        return right;
    }

    /**
     * Mengatur anak kanan node.
     * */
    public void setRight(AVLNode<T> right) {
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
     * Mengembalikan penggambaran struktur {@link AVLNode} ini.
     * */
    public void printDrawnStructure() {
        System.out.println(this.drawStructure(new StringBuilder(), true, new StringBuilder()).toString());
    }

    /* Penggambaran struktur secara rekursif */
    private StringBuilder drawStructure(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (right != null) {
            right.drawStructure(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(data.toString()).append("\n");
        if (left != null) {
            left.drawStructure(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    /**
     * Melakukan komparasi / perbandingan antar objek yang bisa dibandingkan.
     * */
	@Override
	public int compareTo(AVLNode<T> node) {
        return this.data.compareTo(node.getData());
	}
}
