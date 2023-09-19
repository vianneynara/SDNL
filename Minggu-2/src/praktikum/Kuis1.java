package praktikum;

/**
 * @author 225314091 Yohanes Maria Vianney Nara Narwandaru
 * */
public class Kuis1 {
    /**
     * Mengembalikan anak di sisi lain (opposite sibling). (static)
     *
     * @param root root dari binary tree
     * @param key nilai yang dicari
     * */
    public static <T extends Comparable<T>> Node<T> soalKuis(Node<T> root, T key) {
        Node<T> parent = root;
        Node<T> curr = root;

        while (curr != null) {
            int diff = key.compareTo(curr.getData());
            if (diff < 0) {
                parent = curr;
                curr = curr.getLeft();
            } else if (diff > 0) {
                parent = curr;
                curr = curr.getRight();
            } else {
                if (curr.getData() == key) {
                    if (parent.getLeft() == curr) {
                        return parent.getRight();
                    } else {
                        return parent.getLeft();
                    }
                }
            }
        }
        return null;
    }
}
