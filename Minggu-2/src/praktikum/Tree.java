package praktikum;

import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    public Tree() {
        this.root = null;
    }

    /**
     * Konstruktor dengan sebuah nilai bertipe {@link T} yang {@link Comparable<T>} sebagai root.
     * */
    public Tree(T data) {
        this.root = new Node<>(data);
    }


    /**
     * Konstruktor yang menerima {@link Node} sebagai root.
     * */
    public Tree(Node<T> root) {
        this.root = root;
    }

    /**
     * Mencari nilai terkecil dari subtree kanan milik sebuah {@link Node <T>}.
     *
     * @param curr node untuk mencari
     * @return {@link Node <T>} yang terkecil atau null
     * */
    public Node<T> findSuccessor(Node<T> curr) {
        if (curr == null) {                                     // JIKA curr kosong
            return null;                                        // KEMBALIKAN null
        } else {                                                // SELAIN ITU (curr tidak kosong)
            curr = curr.right;                                  // pindah curr ke subtree kanan
            if (curr == null) {                                 // JIKA curr kosong
                return null;                                    // KEMBALIKAN null
            } else {                                            // SELAIN ITU (curr tidak kosong)
                while (curr.left != null) {                     // SELAMA curr punya subtree kiri
                    curr = curr.left;                           // pindah curr ke kiri
                }
                return curr;                                    // kembalikan curr (node terkecil  pada subtree kiri)
            }
        }
    }

    /**
     * Mencari nilai terbesar dari subtree kiri milik sebuah {@link Node <T>}.
     *
     * @param curr node untuk mencari
     * @return {@link Node <T>} terbesar atau null
     * */
    public Node<T> findPredecessor(Node<T> curr) {
        if (curr == null) {                                     // JIKA curr kosong
            return null;                                        // KEMBALIKAN null
        } else {                                                // SELAIN ITU (curr tidak kosong)
            curr = curr.left;                                   // pindah curr ke subtree kiri
            if (curr == null) {                                 // JIKA curr kosong
                return null;                                    // KEMBALIKAN null
            } else {                                            // SELAIN ITU (curr tidak kosong)
                while (curr.right != null) {                    // SELAMA curr punya subtree kanan
                    curr = curr.right;                          // pindah curr ke kanan
                }
                return curr;                                    // kembalikan curr (node terbesar pada subtree kiri)
            }
        }
    }

    /**
     * Mencari parent node dari sebuah node di dalam {@link Node <T>} dimulai dari {@link Tree#root}.
     *
     * @param curr root tree / iterator current node
     * @param child {@link Node <T>} yang dicari
     * @return {@link Node <T>} iterator yang maju atau hasil pencarian parent
     * */
    public Node<T> findParent(Node<T> curr, Node<T> child) {
        /* Base Case */
        if (child == this.root || curr == null) {               // JIKA child merupakan root tree atau curr kosong
            return null;                                        // KEMBALIKAN null
        } else {                                                // SELAIN ITU
            if (curr.left == child || curr.right == child)      // JIKA anak kiri atau kanan curr adalah child
                return curr;                                    // KEMBALIKAN curr
            else {                                              // SELAIN ITU
                if (child.compareTo(curr) < 0) {                // JIKA perbandingan child dgn. curr adalah -1 (kecil)
                    return findParent(curr.left, child);        // KEMBLAIKAN rekursif dengan anak kiri curr & child
                } else {                                        // SELAIN ITU
                    return findParent(curr.right, child);       // KEMBALIKAN rekursif dengan anak kanan curr & child
                }
            }
        }
    }

    /**
     * Metode ini memasukkan sebuah value bertipe {@code T} yang bersifat {@link Comparable<T>}, memasukkannya ke {@link Node <T>}, dan menyambungkannya ke tree.
     *
     * @param data nilai yang ingin dimasukkan
     * */
    public void insert(T data) {
        var newNode = new Node<>(data);
        if (this.root == null) {
            this.root = newNode;
            size++;
            return;
        }

        for (var curr = root; curr != null;) {
            int diff = data.compareTo(curr.getData());
            if (diff < 0) {                                     // lebih kecil
                if (curr.left != null) {
                    curr = curr.left;
                } else {
                    curr.left = newNode;
                    size++;
                    return;
                }
            } else {                                            // lebih besar atau sama dengan
                if (curr.right != null) {
                    curr = curr.right;
                } else {
                    curr.right = newNode;
                    size++;
                    return;
                }
            }
        }
    }

    /**
     * Metode ini memasukkan sebuah value bertipe {@code T} yang bersifat {@link Comparable<T>}, memasukkannya ke {@link Node <T>}, dan menyambungkannya ke tree secara rekursif.
     *
     * @param parent {@link Node <T>} kepala
     * @param data nilai yang ingin dimasukkan ke parent node
     * */
    public Node<T> insert(Node<T> parent, T data) {
        if (parent == null) {
            parent = new Node<>(data);
            size++;
            return parent;
        }
        if (data.compareTo(parent.getData()) < 0) {                 // if the data is less than the parent's data
            parent.left = insert(parent.left, data);
        } else {                                                    // if the data is more than or the same as parent's data
            parent.right = insert(parent.right, data);
        }
        return parent;
    }

    /**
     * Pencarian sebuah node yang bernilai {@code key} yang ingin dihapus pada tree ini. Jika ditemukan maka akan
     * melakukan proses pengaturan dan rekonfigurasi struktur tree.
     *
     * @param key nilai yang akan dihapus
     * @return {@link Node <T>} yang dihapus atau null jika tidak ditemukan
     */
    public boolean delete(T key) {
        Node<T> parent = this.root;
        Node<T> curr = this.root;

        /* Mencari kunci dan parent dari kunci */
        while (curr != null) {                                      // SELAMA curr tidak kosong
            if (key.compareTo(curr.getData()) < 0) {                // JIKA perbandingan key dgn. curr -1 (kurang dari)
                parent = curr;                                      // masukkan curr ke parent
                curr = curr.left;                                   // curr bergerak ke anak kiri
            } else if (key.compareTo(curr.getData()) > 0) {         // JIKA perbandingan key dgn. curr 1 (lebih dari)
                parent = curr;                                      // masukkan curr ke parent
                curr = curr.right;                                  // curr bergerak ke anak kanan
            } else {                                                // SELAIN ITU (nilainya ditemukan)
                break;                                              // HENTIKAN loop
            }
        }

        if (curr == null) {                                         // JIKA curr kosong (tdk. ditemukan kecocokan)
            return false;                                           // KEMBALIKAN gagal
        }

        size--;                                                     // mengurangi jumlah elemen pada tree
        /* Jika curr adalah root tree */
        if (curr == this.root) {
            if (curr.left != null) {                                // JIKA anak kiri curr tidak kosong
                if (curr.left.right == null) {                      // JIKA anak kanan dari anak kiri curr kosong
                    this.root = curr.left;                          // ubah root tree menjadi anak kiri curr
                } else {                                            // Selain itu
                    Node<T> prevPredecessor = curr.left;            // simpan anak kiri curr
                    Node<T> replacement = curr.left.right;          // simpan anak kanan dari anak kiri curr

                    while (replacement.right != null) {             // selama anak kiri node pengganti tidak kosong
                        prevPredecessor = replacement;              // tukar (untuk mendapat anak paling kiri)
                        replacement = replacement.right;            // ubah node pengganti dengan node selanjutnya
                    }

                    /* Atur anak kanan node paling kiri dengan anak kiri node pengganti root */
                    prevPredecessor.right = replacement.left;
                    replacement.left = curr.left;                   // ubah anak kiri node pengganti dgn. anak kiri curr
                    replacement.right = curr.right;                 // ubah anak kanan node pengganti dgn. anak kanan curr
                    this.root = replacement;                        // ubah pointer root tree ke node pengganti
                }
            } else {                                                // SELAIN ITU (anak kirinya kosong)
                this.root = curr.right;                             // langsung arahkan pointer root ke anak kanan curr
            }
        }
        /* Jika current tidak punya anak/tail */
        if (curr.isTail()) {
            if (parent.left == curr) {                              // JIKA anak kiri parent adalah curr (sama)
                parent.left = null;                                 // kosongkan anak kiri parent
            } else {                                                // SELAIN ITU (anak kiri bukan curr)
                parent.right = null;                                // kosongkan anak kanan parent
            }
//            return curr;                                            // KEMBALIKAN curr
            return true;                                            // KEMBALIKAN berhasil
        /* Jika current memiliki maksimal 1 anak */
        } else if (curr.left == null || curr.right == null) {
            Node<T> child = (curr.left != null) ?                   /* JIKA anak kiri curr tidak kosong: */
                curr.left :                                         // masukkan anak kiri curr ke child
                curr.right;                                         // masukkan anak kiana curr ke child
            if (parent.left == curr) {                              // JIKA anak kiri parent adalah curr (sama)
                parent.left = child;                                // kosongkan anak kiri parent
            } else {                                                // SELAIN ITU (anak kiri bukan curr)
                parent.right = child;                               // kosongkan anak kanan parent
            }
//            return curr;                                            // KEMBALIKAN curr
            return true;                                            // KEMBALIKAN berhasil
        /* Jika current memiliki 2 anak */
        } else { // ngerjain ini doang 6 jam T_T
//            final Node<T> removed = new Node<>(curr.data);        // menyimpan node yang ingin dihapus

            Node<T> leastOnRight = findSuccessor(curr);             // mendapatkan nilai yang paling kecil di subtree kanan
            Node<T> leastParent = findParent(this.root, leastOnRight);  // mencari parent dari leastOnRight
            curr.data = leastOnRight.data;                          // masukkan data leastOfRight ke curr

            /* JIKA leastParent tidak kosong, maka dapatkan anak kirinya, selain itu null (safety system) */
            // Bandingkan leastParent hasil ternary, apakah sama dengan leastOnRight
            if ((leastParent != null) && (leastParent.left == leastOnRight)) {
                leastParent.left = null;                            // kosongkan anak kiri leastParent
            } else {                                                // SELAIN ITU (leastOnRight tidak punya anak kiri)
                if (leastParent != null) {                          // JIKA leastParent kosong.
                    leastParent.right = null;                       // kosongkan anak kanan leastParent
                }
            }
//            return removed;                                         // KEMBALIKAN curr
            return true;                                            // KEMBALIKAN berhasil
        }
    }

    /**
     * Mengembalikan size tree.
     *
     * @return {@link Node <T>}
     * */
    public int getSize() {
        return this.size;
    }

    /**
     * Mengembalikan root tree.
     *
     * @return {@link Node <T>}
     * */
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Mencari sebuah nilai dari {@link Node} yang dipanggil.
     *
     * @param key nilai yang dicari.
     * */
    public Node<T> search(T key) {
        return search(this.root, key);
    }

    /**
     * (rekursif) Melakukan pencarian secara rekursif hingga {@code root} bernilai null atau {@code root} bernilai {@code key}.
     *
     * @param root {@link Node <T>} untuk mencari
     * @param key nilai yang dicari
     * @return {@link Node <T>} yang dicari atau nilai null bila tidak ditemukan
     * */
    public static <T extends Comparable<T>> Node<T> search(Node<T> root, T key) {
        if (root == null || root.data == key) {
            return root;
        } else if (key.compareTo(root.data) < 0) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    /**
     * (rekursif) Pre Order | Melakukan traversal dari ujung kiri ke ujung kanan, mencetak setiap isi {@link Node <T>}.
     *
     * @param curr awal mulai
     * */
    public void traversePreOrder(Node<T> curr) {
        System.out.print(curr.getData() + " ");
        if (curr.left != null) {
            traversePreOrder(curr.left);
        }
        if (curr.right != null) {
            traversePreOrder(curr.right);
        }
    }

    /**
     * (rekursif) In Order | Melakukan traversal dari nilai terkecil hingga terbesar, mencetak setiap isi {@link Node <T>}.
     *
     * @param curr awal mulai
     * */
    public void traverseInOrder(Node<T> curr) {
        if (curr != null) {
            traverseInOrder(curr.left);
            System.out.print((curr.getData()) + " ");
            traverseInOrder(curr.right);
        }
    }

    /**
     * (rekursif) Post Order | Melakukan traversal dari leaf kiri-kanan-parent, mencetak setiap isi {@link Node <T>}.
     *
     * @param curr awal mulai
     * */
    public void traversePostOrder(Node<T> curr) {
        if (curr != null) {
            traversePostOrder(curr.left);
            traversePostOrder(curr.right);
            System.out.print((curr.data) + " ");
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
            System.out.print(curr.data + " ");
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

    /**
     * Melakukan penggambaran traversal secara struktural menggunakan '\t'.
     * */
    public void printStructure() {
//        printStructureV1(root, 0);
        printStructureV2("", root, false);
    }

    /* Traverse rekursif versi 1 */
    // SRC  : https://github.com/hersa37/SDNL/blob/master/src/main/java/tree/BinaryTree.java
    private void printStructureV1(Node<T> curr, int depth) {
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
        printStructureV1(curr.left, depth + 1);
        printStructureV1(curr.right, depth + 1);
    }

    /* Traverse rekursif versi 2 */
    // SRC  : https://stackoverflow.com/a/42449385/17299516
    // NOTE : On the first recursion, some-why the previous prefix printed a '|' when n is a left leaf.
    // Checked with this array: {50, 40, 70, 30, 45, 80, 32, 43, 42, 75, 85}. I have fixed the right skewed error.
    public void printStructureV2(String prefix, Node<T> curr, boolean isLeft) {
        if (curr != null) {
            System.out.println(prefix + ("└─") + curr.getData());
            printStructureV2(prefix + ( (isLeft && curr.right != null) ? "│   " : "    "), curr.left, true);
            printStructureV2(prefix + (isLeft ? "│   " : "    "), curr.right, false);
        }
    }
}
