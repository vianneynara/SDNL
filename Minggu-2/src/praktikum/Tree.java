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
                if (curr.getLeft() != null) {
                    curr = curr.getLeft();
                } else {
                    curr.setgetLeft(newNode);
                    return;
                }
            } else {                                            // lebih besar atau sama dengan
                if (curr.getRight() != null) {
                    curr = curr.getRight();
                } else {
                    curr.setgetRight(newNode);
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
                curr = curr.getLeft();
            } else if (diff > 0) {
                parent = curr;
                curr = curr.getRight();
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
        if (curr.isTail()) {
            if (curr == this.root) {
                this.root = null;
            } else if (parent.getLeft().getData() == key) {
                parent.setgetLeft(null);
            } else {
                parent.setgetRight(null);
            }
            return curr;
        }
        /* Jika current punya anak kiri */
        else if (curr.getLeft() != null && curr.getRight() == null) {
            if (curr == this.root) {
                this.root = null;
            } else if (parent.getLeft().getData() == key) {
                parent.setgetLeft(curr.getLeft());
            } else {
                parent.setgetRight(curr.getLeft());
            }
            return curr;
        }
        /* Jika current punya anak kanan */
        else if (curr.getLeft() == null && curr.getRight() != null) {
            if (curr == this.root) {
                this.root = null;
            } else if (parent.getLeft().getData() == key) {
                parent.setgetLeft(curr.getRight());
            } else {
                parent.setgetRight(curr.getRight());
            }
            return curr;
        }
        /* Jika current memiliki anak kanan dan anak kiri */
        else {
            Node<T> temp = curr.getRight();
            Node<T> getLeftmostParent = curr;

            while (curr.getLeft() != null) {
                getLeftmostParent = curr;
                curr = curr.getLeft();
            }

            temp.setData(curr.getData());
            if (getLeftmostParent == curr) {
                temp.setgetRight(curr.getRight());
            } else {
                getLeftmostParent.setgetLeft(curr.getRight());
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
            return search(root.getLeft(), key);
        } else {
            return search(root.getRight(), key);
        }
    }

    /**
     * (rekursif) Pre Order | Melakukan traversal dari ujung kiri ke ujung kanan, mencetak setiap isi {@link Node<T>}.
     *
     * @param curr awal mulai
     * */
    public void traversePreOrder(Node<T> curr) {
        System.out.print(curr.getData() + " ");
        if (curr.getLeft() != null) {
            traversePreOrder(curr.getLeft());
        }
        if (curr.getRight() != null) {
            traversePreOrder(curr.getRight());
        }
    }

    /**
     * (rekursif) In Order | Melakukan traversal dari nilai terkecil hingga terbesar, mencetak setiap isi {@link Node<T>}.
     *
     * @param curr awal mulai
     * */
    public void traverseInOrder(Node<T> curr) {
        if (curr != null) {
            traverseInOrder(curr.getLeft());
            System.out.print((curr.getData()) + " ");
            traverseInOrder(curr.getRight());
        }
    }

    /**
     * (rekursif) Post Order | Melakukan traversal dari leaf kiri-kanan-parent, mencetak setiap isi {@link Node<T>}.
     *
     * @param curr awal mulai
     * */
    public void traversePostOrder(Node<T> curr) {
        if (curr != null) {
            traversePostOrder(curr.getLeft());
            traversePostOrder(curr.getRight());
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
            if (curr.getLeft() != null)
                queue.add(curr.getLeft());
            if (curr.getRight() != null)
                queue.add(curr.getRight());
        }
    }

    /**
     * Melakukan penggambaran traversal secara struktural menggunakan '\t'.
     * */
    public void traverseStructure() {
//        traverseStructureV1(root, 0);
        traverseStructureV2("", root, false);
    }

    /* Traverse rekursif versi 1 */
    private void traverseStructureV1(Node<T> curr, int depth) {
        if (curr == null) {
            return;
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
		if (depth != 0) {
			System.out.print("\u2514\u2500");
		}
        System.out.println(curr);
        traverseStructureV1(curr.getLeft(), depth + 1);
        traverseStructureV1(curr.getRight(), depth + 1);
    }

    /* Traverse rekursif versi 2 */
    // SRC  : https://stackoverflow.com/a/42449385/17299516
    // NOTE : On the first recursion, some-why the previous prefix printed a '|' when n is a left leaf. Checked with this array: {50, 40, 70, 30, 45, 80, 32, 43, 42, 75, 85}. I've fixed the right skewed error.
    public void traverseStructureV2(String prefix, Node<T> curr, boolean isLeft) {
        if (curr != null) {
            System.out.println(prefix + (isLeft ? "\u2514\u2500" : "\u2514\u2500 ") + curr.getData());
            traverseStructureV2(prefix + ( (isLeft && curr.getRight() != null) ? "│   " : "    "), curr.getLeft(), true);
            traverseStructureV2(prefix + (isLeft ? "│   " : "    "), curr.getRight(), false);
        }
    }
}
