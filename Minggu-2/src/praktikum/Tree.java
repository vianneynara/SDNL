package praktikum;

import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {

    private Node<T> root;

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
     * Metode ini memasukkan sebuah value bertipe {@code T} yang bersifat {@link Comparable<T>}, memasukkannya ke {@link Node<T>}, dan menyambungkannya ke tree.
     *
     * @param data nilai yang ingin dimasukkan
     * */
    public void insert(T data) {
        var newNode = new Node<>(data);
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
                    curr.setLeft(newNode);
                    return;
                }
            } else {                                            // lebih besar atau sama dengan
                if (curr.getRight() != null) {
                    curr = curr.getRight();
                } else {
                    curr.setRight(newNode);
                    return;
                }
            }
        }
    }

    /**
     * Metode ini memasukkan sebuah value bertipe {@code T} yang bersifat {@link Comparable<T>}, memasukkannya ke {@link Node<T>}, dan menyambungkannya ke tree secara rekursif.
     *
     * @param parent {@link Node<T>} kepala
     * @param data nilai yang ingin dimasukkan ke parent node
     * */
    public Node<T> r_insert(Node<T> parent, T data) {
        if (parent == null) {
            parent = new Node<>(data);
            return parent;
        }
        if (data.compareTo(parent.getData()) < 0) {             // if the data is less than the parent's data
            parent.setLeft(r_insert(parent.getLeft(), data));
        } else {                                                // if the data is more than or the same as parent's data
            parent.setRight(r_insert(parent.getRight(), data));
        }
        return parent;
    }

    /**
     * Pencarian sebuah node yang bernilai {@code key} yang ingin dihapus pada tree ini.
     *
     * @param key nilai yang akan dihapus
     */
    public void remove(T key) {
        System.out.println("removing... " + key);
        Node<T> parent = this.root;
        Node<T> curr = this.root;

        while (curr != null) {
            if (key.compareTo(curr.getData()) < 0) {
                parent = curr;
                curr = curr.getLeft();
            } else if (key.compareTo(curr.getData())  > 0) {
                parent = curr;
                curr = curr.getRight();
            } else {
                performRemoval(parent, curr);
                return;
            }
        }
        System.out.println("can't find " + key);
    }

    /**
     * Melakukan proses penghapusan dan mengatur posisi {@link Node<T>}-node di bawahnya.
     *
     * @param parent orang tua dari curr
     * @param curr anak dari orang tua
     * @return {@link Node<T>}
     * */
    private Node<T> performRemoval(Node<T> parent, Node<T> curr) {
        /* Jika curr adalah root tree */
        if (curr == this.root) {
            if (curr.getLeft() != null) {                           // JIKA anak kiri curr tidak kosong
                if (curr.getLeft().getRight() == null) {            // JIKA anak kanan dari anak kiri curr kosong
                    this.root = curr.getLeft();                     // ubah root tree menjadi anak kiri curr
                } else {                                            // Selain itu
                    Node<T> prevRootLeftSucc = curr.getLeft();      // simpan anak kiri curr
                    Node<T> replacement = curr.getLeft().getRight();// simpan anak kanan dari anak kiri curr

                    while (replacement.getRight() != null) {        // selama anak kiri node pengganti tidak kosong
                        prevRootLeftSucc = replacement;             // tukar (untuk mendapat anak paling kiri)
                        replacement = replacement.getRight();       // ubah node pengganti dengan node selanjutnya
                    }

                    /* Atur anak kanan node paling kiri dengan anak kiri node pengganti root */
                    prevRootLeftSucc.setRight(replacement.getLeft());
                    replacement.setLeft(curr.getLeft());            // ubah anak kiri node pengganti dgn. anak kiri curr
                    replacement.setRight(curr.getRight());          // ubah anak kanan node pengganti dgn. anak kanan curr
                    this.root = replacement;                        // ubah pointer root ke node pengganti
                }
            } else {                                                // Jika anak kirinya kosong
                this.root = curr.getRight();                        // langsung arahkan pointer root ke anak kanan curr
            }
        }
        /* Jika current tidak punya anak */
        if (curr.isTail()) {
            if (parent.getLeft() == curr) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
            return curr;
        }
        /* Jika current anak kiri kosong */
        else if (curr.getLeft() == null) {
            if (parent.getRight() == curr) {
                parent.setRight(curr.getRight());
            } else {
                parent.setLeft(curr.getRight());
            }
            return curr;
        }
        /* Jika current anak kanan kosong */
        else if (curr.getRight() == null) {
            if (parent.getLeft() == curr) {
                parent.setLeft(curr.getLeft());
            } else {
                parent.setRight(curr.getLeft());
            }
            return curr;
        }
        /* Jika current memiliki anak kanan dan anak kiri */
        else {
            Node<T> temp = curr.getRight();
            Node<T> leftMostParent = curr;

            while (curr.getLeft() != null) {
                leftMostParent = curr;
                curr = curr.getLeft();
            }

            temp.setData(curr.getData());
            if (leftMostParent == curr) {
                temp.setRight(curr.getRight());
            } else {
                leftMostParent.setLeft(curr.getRight());
            }
            return temp;
        }
    }

    /* Versi lain */
    public Node<T> remove(Node<T> root, T key) {
        Node<T> curr = root;
        Node<T> parent = null;

        /* Mencari node dengan nilai yang sama selama curr tidak kosong dan kunci belum ditemukan */
        while (curr != null && key.compareTo(curr.getData()) != 0) {
            parent = curr;
            if (key.compareTo(curr.getData()) < 0) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }

        /* curr dari kondisi sebelumnya tidak menemukan kecocokan dengan kunci manapun */
        if (curr == null) {
            return root;
        }

        /* Mengecek dimana node harus punya tidak lebih dari 1 anak */
        if (curr.getLeft() == null || curr.getRight() == null) {
            Node<T> replacement;                                // penyimpan node yang akan menggantikan node yg dihapus

            if (curr.getLeft() == null) {                       // JIKA tidak punya anak kiri
                replacement = curr.getRight();                  // ubah node pengganti dengan anak kanan curr
            } else {                                            // JIKA tidak punya anak kanan
                replacement = curr.getLeft();                   // ubah node pengganti dengan anak kiri cur
            }

            /* Jika node yang akan dihapus adalah root (root tidak punya parent (parent)) */
            if (parent == null) {
                return replacement;
            }

            if (curr == parent.getLeft()) {                     // JIKA curr (yg akan dihapus) adalah anak kiri
                parent.setLeft(replacement);                    // atur anak kiri parent dgn replacement
            } else {                                            // JIKA curr (yg akan dihapus) adalah anak kanan
                parent.setRight(replacement);                   // atur anak kanan parent (parent) dgn replacement
            }
        }
        /* Node yang akan dihapus memiliki 2 anak */
        else {
            Node<T> tempParent = null;
            Node<T> temp;

            /* Mendapatkan node terkecil yang masih lebih besar (subtree kanan) dari nilai node yang akan dihapus */
            temp = curr.getRight();
            while (temp.getLeft() != null) {                    // SELAMA anak kiri temp tidak kosong
                tempParent = temp;                              // isi tempParent dengan temp
                temp = temp.getLeft();                          // pindah temp ke anak kirinya
            }

            /* Mengecek apakah node terkecil memiliki parent bernilai sama dengan yang ingin dihapus */
            // ini memastikan node terkecil subtree dipindah sebagai anak kiri parent
            if (tempParent != null) {
                tempParent.setLeft(temp.getRight());
            }

            /* Node terkecil merupakan anak kanan langsung dari node dengan nilai yang ingin dihapus (curr) */
            // ini memastikan anak kanan dari node yang ingin dihapus menjadi anak kanan curr
            else {
                curr.setRight(temp.getRight());
            }

            /* Update the parent of the node to be deleted with the node that replaces it */
            if (parent == null) {                               // JIKA node yang akan dihapus adalah root
                root = temp;                                    // ubah root menjadi temp
            } else if (curr == parent.getLeft()) {              // JIKA node yang akan dihapus adalah anak kiri parent
                parent.setLeft(temp);                           // atur anak kiri parent menjadi temp
            } else {                                            // JIKA node yang akan dihapus adalah anak kanan parent
                parent.setRight(temp);                          // atur anak kanan parent menjadi temp
            }

            curr.setData(temp.getData());                       // menukar data curr dengan data temp
        }
        return root;
    }

    /**
     * Melakukan proses penghapusan dan mengatur anak dari sebuah nilai bertipe {@link T} secara rekursif.
     *
     * @param parent orang tua dari curr
     * @param key nilai yang ingin dihapus
     * @return {@link Node<T>}
     * */
    public Node<T> r_remove(Node<T> parent, T key) {
        /* Base case: Menyelesaikan stack rekursif */
        if (parent == null) {
            return null;
        }
        /* Pemanggilan rekursif untuk MENCARI nilai yang dicari */
        if (key.compareTo(parent.getData()) < 0) {              // JIKA kunci lebih kecil dari parent
            parent.setLeft(r_remove(parent.getLeft(), key));    // mengatur anak kiri parent dengan successor kiri
            return parent;                                      // KEMBALIKAN parent node
        } else if (key.compareTo(parent.getData()) > 0) {       // JIKA kunci lebih besar dari parent
            parent.setRight(r_remove(parent.getRight(), key));  // mengatur anak kanan parent dengan successor kanan
            return parent;                                      // KEMBALIKAN parent node
        }
        /* Penghapusan */
        if (parent.getLeft() == null) {                         // JIKA tidak ada anak kiri
            return parent.getRight();                           // kembalikan anak kanan
        } else if (parent.getRight() == null) {                 // JIKA tidak ada anak kanan
            return parent.getLeft();                            // kembalikan anak kiri
        } else {                                                // JIKA memiliki dua anak
            Node<T> temp = parent.getRight();                   // simpan anak kanan parent
            Node<T> leftMostParent = parent;                    // simpan parent sebagai leftest traverse agent

            while (temp.getLeft() != null) {                    // SAAT temp.getLeft / anak kiri temp tidak kosong
                leftMostParent = temp;                          // ubah pointer leftest traverse agent dgn. isi temp
                temp = temp.getLeft();                          // masukan anak kiri temp ke dalam temp
            }

            parent.setData(temp.getData());                     // atur data parent dengan nilai pada temp
            if (leftMostParent == parent) {                     // JIKA leftest traverse agent ama dengan parent
                parent.setRight(temp.getRight());               // atur anak kanan parent dengan anak kanan temp
            } else {                                            // SELAIN ITU
                leftMostParent.setLeft(temp.getRight());        // atur anak kiri anak node terkiri dgn. anak kanan temp
            }
            return parent;                                      // KEMBALIKAN parent node
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
        printStructureV1(curr.getLeft(), depth + 1);
        printStructureV1(curr.getRight(), depth + 1);
    }

    /* Traverse rekursif versi 2 */
    // SRC  : https://stackoverflow.com/a/42449385/17299516
    // NOTE : On the first recursion, some-why the previous prefix printed a '|' when n is a left leaf.
    // Checked with this array: {50, 40, 70, 30, 45, 80, 32, 43, 42, 75, 85}. I have fixed the right skewed error.
    public void printStructureV2(String prefix, Node<T> curr, boolean isLeft) {
        if (curr != null) {
            System.out.println(prefix + (isLeft ? "\u2514\u2500" : "\u2514\u2500") + curr.getData());
            printStructureV2(prefix + ( (isLeft && curr.getRight() != null) ? "│   " : "    "), curr.getLeft(), true);
            printStructureV2(prefix + (isLeft ? "│   " : "    "), curr.getRight(), false);
        }
    }
}
