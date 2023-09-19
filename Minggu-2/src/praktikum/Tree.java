package praktikum;

import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {

    private Node<T> root;


    public Tree() {
        this.root = null;
    }

    /**
     * Konstruktor dengan sebuah {@link Node} sebagai root.
     * */
    public Tree(Node<T> root) {
        this.root = root;
    }

    /**
     * Metode ini memasukkan sebuah value bertipe {@code int}, memasukkannya ke {@link Node<T>}, dan menyambungkannya ke tree.
     *
     * @param data nilai yang ingin dimasukkan
     * */
    public void insert(T data) {
        var newNode = new Node<T>(data);
        if (this.root == null) {
            this.root = newNode;
            return;
        }

        Node<T> curr = root;
        while (curr != null) {
            int diff = data.compareTo(curr.getData());
            if (diff < 0) {                                     // lebih kecil
                if (curr.left() != null) {
                    curr = curr.left();
                } else {
                    curr.setLeft(newNode);
                    return;
                }
            } else {                                            // lebih besar atau sama dengan
                if (curr.right() != null) {
                    curr = curr.right();
                } else {
                    curr.setRight(newNode);
                    return;
                }
            }
        }
    }

    /**
     * Pencarian rekursif sebuah node yang bernilai {@code getData} yang ingin dihapus pada tree ini.
     *
     * @param key nilai yang akan dihapus
     * @return {@link Node<T>} yang berisi getData
     * */
    public Node<T> remove(T key) {
        Node<T> parent = this.root;
        Node<T> curr = this.root;

        while (curr != null) {
            int diff = key.compareTo(curr.getData());
            if (diff < 0) {
                parent = curr;
                curr = curr.left();
            } else if (diff > 0) {
                parent = curr;
                curr = curr.right();
            } else {
                return performRemoval(parent, curr, key);
            }
        }
        return null;
    }

    /**
     * Melakukan proses penghapusan dan mengatur posisi {@link Node<T>}.
     *
     * @param parent orang tua dari curr
     * @param curr anak dari orang tua
     * @param key nilai yang ingin dihapus
     * @return {@link Node<T>}
     * */
    private Node<T> performRemoval(Node<T> parent, Node<T> curr, T key) {
        /* Jika current tidak punya anak */
        if (curr.isLeaf()) {
            if (curr == this.root) {
                this.root = null;
            } else if (parent.left().getData() == key) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
            return curr;
        }
        /* Jika current punya anak kiri */
        else if (curr.left() != null && curr.right() == null) {
            if (curr == this.root) {
                this.root = null;
            } else if (parent.left().getData() == key) {
                parent.setLeft(curr.left());
            } else {
                parent.setRight(curr.left());
            }
            return curr;
        }
        /* Jika current punya anak kanan */
        else if (curr.left() == null && curr.right() != null) {
            if (curr == this.root) {
                this.root = null;
            } else if (parent.left().getData() == key) {
                parent.setLeft(curr.right());
            } else {
                parent.setRight(curr.right());
            }
            return curr;
        }
        /* Jika current memiliki anak kanan dan anak kiri */
        else {
            Node<T> temp = curr.right();
            Node<T> leftmostParent = curr;

            while (curr.left() != null) {
                leftmostParent = curr;
                curr = curr.left();
            }

            temp.setData(curr.getData());
            if (leftmostParent == curr) {
                temp.setRight(curr.right());
            } else {
                leftmostParent.setLeft(curr.right());
            }
            return temp;
        }
    }

    /**
     * Mengembalikan root kelas.
     *
     * @return {@link Node<T>}
     * */
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Mengatur root kelas.
     * */
    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /**
     * Mencari sebuah nilai dari {@link Tree} yang dipanggil.
     *
     * @param key nilai yang dicari.
     * */
    public Node<T> search(T key) {
        Node<T> curr = this.root;
        return search(curr, key);
    }

    /**
     * (rekursif) Melakukan pencarian secara rekursif hingga {@code root} bernilai null atau {@code root} bernilai {@code key}.
     *
     * @param root {@link Node<T>} untuk mencari
     * @param key nilai yang dicari
     * @return {@link Node<T>} yang dicari atau nilai null bila tidak ditemukan
     * */
    public static <T extends Comparable<T>> Node<T> search(Node<T> root, T key) {
        if (root == null || root.getData() == key) {
            return root;
        } else if (key.compareTo(root.getData()) < 0) {
            return search(root.left(), key);
        } else {
            return search(root.right(), key);
        }
    }

    /**
     * (rekursif) Pre Order | Melakukan traversal dari ujung kiri ke ujung kanan, mencetak setiap isi {@link Node<T>}.
     *
     * @param curr awal mulai
     * */
    public void traversePreOrder(Node<T> curr) {
        System.out.print(curr.getData() + " ");
        if (curr.left() != null) {
            traversePreOrder(curr.left());
        }
        if (curr.right() != null) {
            traversePreOrder(curr.right());
        }
    }

    /**
     * (rekursif) In Order | Melakukan traversal dari nilai terkecil hingga terbesar, mencetak setiap isi {@link Node<T>}.
     *
     * @param curr awal mulai
     * */
    public void traverseInOrder(Node<T> curr) {
        if (curr != null) {
            traverseInOrder(curr.left());
            System.out.print((curr.getData()) + " ");
            traverseInOrder(curr.right());
        }
    }

    /**
     * (rekursif) Post Order | Melakukan traversal dari leaf kiri-kanan-parent, mencetak setiap isi {@link Node<T>}.
     *
     * @param curr awal mulai
     * */
    public void traversePostOrder(Node<T> curr) {
        if (curr != null) {
            traversePostOrder(curr.left());
            traversePostOrder(curr.right());
            System.out.print((curr.getData()) + " ");
        }
    }

    /**
     * Breadth First Search / Level Order Traversal. Melakukan traversal dari atas ke bawah berurutan secara melebar.
     * */
    public void traverseLevelOrder() {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            System.out.print(curr.getData() + " ");
            if (curr.left() != null)
                queue.add(curr.left());
            if (curr.right() != null)
                queue.add(curr.right());
        }
    }
}
