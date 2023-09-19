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
                curr = curr.left();
            } else if (diff > 0) {
                parent = curr;
                curr = curr.right();
            } else {
                if (curr.getData() == key) {
                    if (parent.left() == curr) {
                        return parent.right();
                    } else {
                        return parent.left();
                    }
                }
            }
        }
        return null;
    }
}