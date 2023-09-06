package praktikum;                                          // lokasi file

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
                    return;                                 // METODE SELESAI.
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

    public Node search(int key) {
        Node curr = this.root;
        return search(curr, key);
    }

    public Node search(Node root, int key) {
        if (root == null || root.data() == key) {
            return root;
        } else if (key < root.data()) {
            return search(root.left(), key);
        } else {
            return search(root.right(), key);
        }
    }

    /**
     * (rekursif) Melakukan traversal dari ujung kiri ke ujung kanan, mencetak setiap isi {@link Node}.
     * @param curr awal mulai.
     * */
    public void traverseLeftToRight(Node curr) {
        System.out.print(curr.data() + " ");                // CETAK data dan spasi
        if (curr.left() != null) {                          // JIKA child kiri curr tidak kosong
            traverseLeftToRight(curr.left());               // rekursif dengan argumen child kiri curr
        }
        if (curr.right() != null) {                         // JIKA child kanan curr tidak kosong
            traverseLeftToRight(curr.right());              // rekursif dengan argumen child kanan curr
        }
    }

    /**
     * (rekursif) Melakukan traversal dari nilai terkecil hingga terbesar, mencetak setiap isi {@link Node}.
     * @param curr awal mulai.
     * */
    public void traverseInOrder(Node curr) {
        if (curr == null) {                                 // JIKA curr kosong
            return;                                         // METODE SELESAI.
        }
        traverseInOrder(curr.left());                       // rekursif dengan argumen child kiri curr
        System.out.print((curr.data()) + " ");              // CETAK data dan spasi
        traverseInOrder(curr.right());                      // rekursif dengan argumen child kanan curr
    }
}
