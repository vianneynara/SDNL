package praktikum;                                          // lokasi file

import java.util.LinkedList;
import java.util.Queue;

public class Tree {                                         // deklarasi kelas

    private Node root;                                      // atribut kelas sebagai awal/akar tree

    public Tree() {                                         // constructor default
        this.root = null;                                   // isi root dengan null
    }

    public Tree(Node root) {                                // constructor satu parameter: Node
        this.root = root;                                   // menyimpan root dari parameter ke root kelas
    }

    /**
     * Metode ini memasukkan sebuah value bertipe {@code int}, memasukkannya ke {@link Node}, dan menyambungkannya ke tree.
     * @param data nilai yang ingin dimasukkan
     * */
    public void insert(int data) {
        var newNode = new Node(data);                       // membuat node baru berisi data, simpan ke "newNode"
        if (this.root == null) {                            // JIKA root kelas kosong:
            this.root = newNode;                            // ISI root kelas dengan newNode yang baru dibuat
            return;                                         // METODE SELESAI.
        }

        Node curr = root;                                   // isi iterator curr dengan root Tree
        while (curr != null) {                              // SELAMA curr tidak kosong
            if (newNode.data() < curr.data()) {             // JIKA data newNode lebih kecil dari curr
                if (curr.left() != null) {                  // JIKA child curr tidak kosong
                    curr = curr.left();                     // tunjuk child kiri curr ke curr
                } else {                                        // SELAIN ITU
                    curr.setLeft(newNode);                  // tunjuk newNode sebagai child kiri curr
                    return;                                 // METODE SELESAI.
                }
            } else if (newNode.data() >= curr.data()) {     // NAMUN JIKA data newNode lebih besar/sama dgn. curr
                if (curr.right() != null) {                 // JIKA child kanan curr tidak kosong
                    curr = curr.right();                    // tunjuk child kanan curr sebagai curr
                } else {                                        // SELAIN ITU
                    curr.setRight(newNode);                 // tunjuk newNode sebagai child kanan curr
                    return;                                 // METODE SELESAI.
                }
            } else {                                            // SELAIN ITU
            return;                                         // METODE SELESAI.
            }
        }
    }

    /**
     * Pencarian rekursif sebuah node yang bernilai {@code data} yang ingin dihapus pada tree ini.
     * @param key nilai yang akan dihapus
     * @return {@link Node} yang berisi data
     * */
    public Node remove(int key) {
        Node parent = this.root;
        Node curr = this.root;

        while (curr != null) {
            if (key < curr.data()) {
                parent = curr;
                curr = curr.left();
            } else if (key > curr.data()) {
                parent = curr;
                curr = curr.right();
            } else {
                return performRemoval(parent, curr, key);
            }
        }
        return null;
    }

    /**
     * Melakukan proses penghapusan dan mengatur posisi {@link Node}.
     * @param parent orang tua dari curr
     * @param curr anak dari orang tua
     * @param key nilai yang ingin dihapus
     * @return {@link Node}
     * */
    private Node performRemoval(Node parent, Node curr, int key) {
        /* Jika current tidak punya anak */
        if (curr.left() == null && curr.right() == null) {
            if (curr == this.root) {
                this.root = null;
            } else if (parent.left().data() == key) {
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
            } else if (parent.left().data() == key) {
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
            } else if (parent.left().data() == key) {
                parent.setLeft(curr.right());
            } else {
                parent.setRight(curr.right());
            }
            return curr;
        }
        /* Jika current memiliki anak kanan dan anak kiri */
        else {
            Node temp = curr.right();
            Node leftmostParent = curr;

            while (curr.left() != null) {
                leftmostParent = curr;
                curr = curr.left();
            }

            temp.setData(curr.data());
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
     * @return {@link Node}
     * */
    public Node getRoot() {
        return this.root;                                   // kembalikan root kelas
    }

    /**
     * Mengatur root kelas.
     * */
    public void setRoot(Node root) {
        this.root = root;                                   // atur root kelas dengan root di argumen
    }

    /**
     * Mencari sebuah nilai dari {@link Tree} yang dipanggil.
     * @param key nilai yang dicari.
     * */
    public Node search(int key) {
        Node curr = this.root;                              // mengatur node yang akan digunakan sebagai iterator
        return search(curr, key);                           // pencarian rekursif dengan curr dan key
    }

    /**
     * (rekursif) Melakukan pencarian secara rekursif hingga {@code root} bernilai null atau {@code root} bernilai {@code key}.
     * @param root {@link Node} untuk mencari
     * @param key nilai yang dicari
     * @return {@link Node} yang dicari atau nilai null bila tidak ditemukan
     * */
    public static Node search(Node root, int key) {
        if (root == null || root.data() == key) {           // JIKA root kosong ATAU root.data() sama dengan kunci
            return root;                                    // kembalikan root
        } else if (key < root.data()) {                     // NAMUN JIKA key kurang dari root.data()
            return search(root.left(), key);                // rekursif dengan arg: node kiri root DAN key
        } else {                                            // SELAIN ITU
            return search(root.right(), key);               // rekursif dengan arg: node kanan DAN key
        }
    }

    /**
     * (rekursif) Pre Order | Melakukan traversal dari ujung kiri ke ujung kanan, mencetak setiap isi {@link Node}.
     * @param curr awal mulai
     * */
    public void traversePreOrder(Node curr) {
        System.out.print(curr.data() + " ");                // CETAK data dan spasi
        if (curr.left() != null) {                          // JIKA child kiri curr tidak kosong
            traversePreOrder(curr.left());                  // rekursif dengan argumen child kiri curr
        }
        if (curr.right() != null) {                         // JIKA child kanan curr tidak kosong
            traversePreOrder(curr.right());                 // rekursif dengan argumen child kanan curr
        }
    }

    /**
     * (rekursif) In Order | Melakukan traversal dari nilai terkecil hingga terbesar, mencetak setiap isi {@link Node}.
     * @param curr awal mulai
     * */
    public void traverseInOrder(Node curr) {
        if (curr != null) {                                 // JIKA curr kosong
            traverseInOrder(curr.left());                   // rekursif dengan argumen child kiri curr
            System.out.print((curr.data()) + " ");          // CETAK data dan spasi
            traverseInOrder(curr.right());                  // rekursif dengan argumen child kanan curr
        }
    }

    /**
     * (rekursif) Post Order | Melakukan traversal dari leaf kiri-kanan-parent, mencetak setiap isi {@link Node}.
     * @param curr awal mulai
     * */
    public void traversePostOrder(Node curr) {
        if (curr != null) {                                 // JIKA curr kosong
            traversePostOrder(curr.left());                 // rekursif dengan argumen child kiri curr
            traversePostOrder(curr.right());                // rekursif dengan argumen child kanan curr
            System.out.print((curr.data()) + " ");          // CETAK data dan spasi
        }
    }

    /**
     * Breadth First Search / Level Order Traversal. Melakukan traversal dari atas ke bawah berurutan secara melebar.
     * */
    public void traverseLevelOrder() {
        Queue<Node> queue = new LinkedList<>();             // membuat LinkedList dengan tipe Queue
        queue.add(this.root);                               // memasukkan root ke queue

        while (!queue.isEmpty()) {                          // SAAT queue tidak kosong
            Node curr = queue.poll();                       // masukan penarikan dari queue ke curr
            System.out.print(curr.data() + " ");            // cetak isi curr
            if (curr.left() != null)                        // JIKA node kiri curr tidak kosong
                queue.add(curr.left());                     // tambahkan node kiri curr ke queue
            if (curr.right() != null)                       // JIKA node kanan curr tidak kosong
                queue.add(curr.right());                    // tambahkan node kanan curr ke queue
        }
    }
}
