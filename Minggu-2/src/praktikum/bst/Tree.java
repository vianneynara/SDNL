package praktikum.bst;

import java.util.LinkedList;
import java.util.Queue;

// source:https://www.happycoders.eu/algorithms/binary-tree-java/
public class Tree<T extends Comparable<T>> {
    /**
     * Mendapatkan height/ketinggian dari sebuah {@link Node<T>} dengan melakukan pengecekan apakah node tersebut
     * null atau tidak. Metode ini statis-protected sehingga dapat digunakan oleh {@link Node<T>}.
     */
    protected static <T extends Comparable<T>> int height(Node<T> node) {
        return (node == null) ? -1 : node.height;           // KEMBALIKAN -1 jika node kosong, selain itu height
    }

    private Node<T> root;
    private int size;                                       // Atribut tambahan: size untuk merekam jumlah node

    public Tree() {
        this.root = null;
    }

    /**
     * Konstruktor dengan sebuah nilai bertipe {@link T} yang {@link Comparable<T>} sebagai root.
     */
    public Tree(T data) {
        this.root = new Node<>(data);
    }


    /**
     * Konstruktor yang menerima {@link Node} sebagai root.
     */
    public Tree(Node<T> root) {
        this.root = root;
    }

    /**
     * Mencari node terkecil dari subtree kanan milik sebuah {@link Node<T>}.
     *
     * @param curr node untuk mencari
     * @return {@link Node<T>} yang terkecil atau null
     */
    public Node<T> findSuccessor(Node<T> curr) {
        if (curr == null) {
            return null;
        } else {
            curr = curr.right;
            if (curr == null) {
                return null;
            } else {
                while (curr.left != null) {
                    curr = curr.left;
                }
                return curr;
            }
        }
    }

    /**
     * Mencari node terbesar dari subtree kiri milik sebuah {@link Node<T>}.
     *
     * @param curr node untuk mencari
     * @return {@link Node<T>} terbesar atau null
     */
    public Node<T> findPredecessor(Node<T> curr) {
        if (curr == null) {
            return null;
        } else {
            curr = curr.left;
            if (curr == null) {
                return null;
            } else {
                while (curr.right != null) {
                    curr = curr.right;
                }
                return curr;
            }
        }
    }

    /**
     * Mencari parent node dari sebuah node di dalam {@link Node<T>} dimulai dari {@link Tree#root}.
     *
     * @param curr  root tree / iterator current node
     * @param child {@link Node<T>} yang dicari
     * @return {@link Node<T>} iterator yang maju atau hasil pencarian parent
     */
    public Node<T> findParent(Node<T> curr, Node<T> child) {
        /* Base Case */
        if (child == this.root || curr == null) {
            return null;
        } else {
            if (curr.left == child || curr.right == child)
                return curr;
            else {
                if (child.compareTo(curr) < 0) {
                    return findParent(curr.left, child);
                } else {
                    return findParent(curr.right, child);
                }
            }
        }
    }

    /**
     * Metode ini memasukkan sebuah value bertipe {@code T} yang bersifat {@link Comparable<T>},
     * memasukkannya ke {@link Node<T>}, dan menyambungkannya ke tree.
     *
     * @param data nilai yang ingin dimasukkan
     */
    public void insert(T data) {
        // <editor-fold defaultstate="collapsed" desc="Iterative code, unused when used as recursive wrapper">
//        var newNode = new Node<>(data);
//        if (this.root == null) {
//            this.root = newNode;
//            size++;
//            return;
//        }
//
//        for (var curr = root; curr != null; ) {
//            int diff = data.compareTo(curr.getData());
//            if (diff < 0) {
//                if (curr.left != null) {
//                    curr = curr.left;
//                } else {
//                    curr.left = newNode;
//                    size++;
//                    updateHeight(curr);
//                    return;
//                }
//            } else {
//                if (curr.right != null) {
//                    curr = curr.right;
//                } else {
//                    curr.right = newNode;
//                    updateHeight(curr);
//                    size++;
//                    return;
//                }
//            }
//        }
        // </editor-fold>

        /* Using recursive */
        this.root = insert(this.root, data);
        updateHeight(this.root);
    }

    /**
     * Metode ini memasukkan sebuah value bertipe {@code T} yang bersifat {@link Comparable<T>},
     * memasukkannya ke {@link Node<T>}, dan menyambungkannya ke tree secara rekursif.
     *
     * @param parent {@link Node<T>} kepala
     * @param data   nilai yang ingin dimasukkan ke parent node
     */
    private Node<T> insert(Node<T> parent, T data) {
        if (parent == null) {
            size++;
            return new Node<>(data);
        }
        if (data.compareTo(parent.getData()) < 0) {
            parent.left = insert(parent.left, data);
        } else {
            parent.right = insert(parent.right, data);
        }
        updateHeight(parent);
        return parent;
    }

    /**
     * Pencarian sebuah node yang bernilai {@code key} yang ingin dihapus pada tree ini secara iteratif / rekursif.
     * Jika ditemukan maka akan melakukan proses pengaturan dan rekonfigurasi struktur tree kemudian mengembalikannya.
     *
     * @param key nilai yang akan dihapus
     * @return {@link Node<T>} yang dihapus atau null jika tidak ditemukan
     */
    public Node<T> delete(T key) {
        // <editor-fold defaultstate="collapsed" desc="Long ass boring DELETE method code">
//        Node<T> parent = null;
//        Node<T> curr = this.root;
//
//        /* Mencari kunci dan parent dari kunci */
//        while (curr != null) {
//            if (key.compareTo(curr.getData()) < 0) {
//                parent = curr;
//                curr = curr.left;
//            } else if (key.compareTo(curr.getData()) > 0) {
//                parent = curr;
//                curr = curr.right;
//            } else {
//                break;
//            }
//        }
//
//        if (curr == null) {
//            return null;
//        }
//
//        size--;
//        /* If curr is the root of the tree */
//        if (curr == this.root) {
//            if (curr.left == null && curr.right == null) {
//                this.root = null;
//            } else if (curr.right != null) {
//                Node<T> leftChild = this.root.left;
//                this.root = this.root.right;
//                if (curr.left != null) {
//                    this.root.left = leftChild;
//                }
//            } else {
//                this.root = curr.left;
//            }
//        }
//        /* If curr doesn't have children */
//        else if (curr.isTail()) {
//            if (parent.left == curr) {
//                parent.left = null;
//            } else {
//                parent.right = null;
//            }
//            /* If curr has at maximum 1 child */
//        } else if (curr.left == null || curr.right == null) {
//            Node<T> child = (curr.left != null) ? curr.left : curr.right;
//            if (parent.left == curr) {
//                parent.left = child;
//            } else {
//                parent.right = child;
//            }
//            /* If curr has 2 children */
//        } else {
//            final Node<T> removed = new Node<>(curr.data);
//
//            Node<T> leastOnRight = findSuccessor(curr);
//            Node<T> leastParent = findParent(this.root, leastOnRight);
//            curr.data = leastOnRight.data;
//
//            if ((leastParent != null) && (leastParent.left == leastOnRight)) {
//                leastParent.left = null;
//            } else {
//                if (leastParent != null) {
//                    leastParent.right = null;
//                }
//            }
//            updateHeight(removed);
//            return removed;
//        }
//        updateHeight(curr);
//        return curr;
         //</editor-fold>

        /* Using recursive */
        this.root = delete(this.root, key);
        updateHeight(this.root);
        return this.root;
    }

    /**
     * Pencarian sebuah node yang bernilai {@code key} yang ingin dihapus pada tree ini secara rekursif.
     * Jika ditemukan maka akan melakukan proses pengaturan dan rekonfigurasi struktur tree kemudian mengembalikannya.
     *
     * @param curr root node dari nilai yang akan dihapus
     * @param key  nilai yang akan dihapus
     * @return {@link Node<T>} yang dihapus atau null jika tidak ditemukan
     */
    public Node<T> delete(Node<T> curr, T key) {
        if (curr == null) {
            return null;
        }
        if (key.compareTo(curr.getData()) < 0) {
            curr.left = delete(curr.left, key);
        } else if (key.compareTo(curr.getData()) > 0) {
            curr.right = delete(curr.right, key);
        } else {
            if (curr.left == null) {
                if (curr != this.root)
                    return curr.right;
                this.root = curr.right;

            } else if (curr.right == null) {
                if (curr != this.root)
                    return curr.left;
                this.root = curr.left;
            } else {
                Node<T> predecessor = findPredecessor(curr);
                curr.data = predecessor.data;
                curr.left = delete(curr.left, predecessor.data);
            }
            size--;
        }
        updateHeight(curr);
        return curr;
    }

    /**
     * Mengembalikan size / ukuran / banyaknya node dari tree.
     * */
    public int getSize() {
        return this.size;
    }

    /**
     * Mengembalikan root tree.
     *
     * @return {@link Node<T>}
     */
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Mencari sebuah nilai dari {@link Node} yang dipanggil.
     *
     * @param key nilai yang dicari.
     */
    public Node<T> search(T key) {
        return search(this.root, key);
    }

    /**
     * (rekursif) Melakukan pencarian secara rekursif hingga {@code root} bernilai null
     * atau {@code root} bernilai {@code key}.
     *
     * @param root {@link Node<T>} untuk mencari
     * @param key  nilai yang dicari
     * @return {@link Node<T>} yang dicari atau nilai null bila tidak ditemukan
     */
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
     * Mencari node terkecil sebuah {@link Node<T>}.
     *
     * @param curr node untuk mencari
     * @return {@link Node<T>} yang terkecil atau null
     */
    public Node<T> findMin(Node<T> curr) {
        if (curr == null) {                                     // JIKA curr bernilai kosong
            return null;                                        // KEMBALIKAN null
        } else {                                                // SELAIN ITU
            while (curr.left != null) {                         // SELAMA anak kiri dari curr tidak kosong
                curr = curr.left;                               // traverse dengan mengganti curr dengan anak kirinya
            }
            return curr;                                        // (setelah selesai loop) KEMBALIKAN curr
        }
    }

    /**
     * Mencari node terbesar sebuah {@link Node<T>}.
     *
     * @param curr node untuk mencari
     * @return {@link Node<T>} terbesar atau null
     */
    public Node<T> findMax(Node<T> curr) {
        if (curr == null) {                                     // JIKA curr bernilai kosong
            return null;                                        // KEMBALIKAN null
        } else {                                                // SELAIN ITU
            while (curr.right != null) {                        // SELAMA anak kanan dari curr tidak kosong
                curr = curr.right;                              // traverse dengan mengganti curr dengan anak kanannya
            }
            return curr;                                        // (setelah selesai loop) KEMBALIKAN curr
        }
    }

    /**
     * Mencari nilai terkecil sebuah {@link Node<T>}.
     *
     * @param curr node untuk mencari
     * @return {@link T} terkecil atau null
     */
    public T findMinValue(Node<T> curr) {
        return findMin(curr).data;
    }

    /**
     * Mencari nilai terbesar sebuah {@link Node<T>}.
     *
     * @param curr node untuk mencari
     * @return {@link T} terbesar atau null
     */
    public T findMaxValue(Node<T> curr) {
        return findMax(curr).data;
    }

    /**
     * Memperbarui ketinggian/height dari sebuah {@link Node<T>} dengan menjumlahkan anak yang memiliki height
     * tertinggi dengan 1. Diasumsikan bahwa node di bawahnya punya height.
     */
    private Node<T> updateHeight(Node<T> node) {
        /* Mendapatkan tinggi anak yang paling dominan / dalam, ditambah dengan 1 */
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    /**
     * (rekursif) Pre Order | Melakukan traversal dari ujung kiri ke ujung kanan, mencetak setiap isi {@link Node<T>}.
     *
     * @param curr awal mulai
     */
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
     * (rekursif) In Order | Melakukan traversal dari nilai terkecil hingga terbesar, mencetak setiap isi {@link Node<T>}.
     *
     * @param curr awal mulai
     */
    public void traverseInOrder(Node<T> curr) {
        if (curr != null) {
            traverseInOrder(curr.left);
            System.out.print((curr.getData()) + " ");
            traverseInOrder(curr.right);
        }
    }

    /**
     * (rekursif) Post Order | Melakukan traversal dari leaf kiri-kanan-parent, mencetak setiap isi {@link Node<T>}.
     *
     * @param curr awal mulai
     */
    public void traversePostOrder(Node<T> curr) {
        if (curr != null) {
            traversePostOrder(curr.left);
            traversePostOrder(curr.right);
            System.out.print((curr.data) + " ");
        }
    }

    /**
     * Breadth First Search / Level Order Traversal. Melakukan traversal dari atas ke bawah berurutan secara melebar.
     */
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
     * Breadth First Search / Level Order -like traversal. Melakukan traversal dari atas ke bawah berurutan secara
     * melebar untuk menentukan apakah pohon ini bertipe complete (seluruh anak paling bawah terurut dari kiri).
     *
     * @see <a href="https://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees">
     * Complete binary tree - Wikipedia
     * </a>
     */
    public boolean isComplete() {
        Queue<Node<T>> queue = new LinkedList<>();              // buat queue
        queue.add(this.root);                                   // memasukkan root sebagai awalan
        boolean endOfLevel = false;                             // penanda apakah sudah di level terakhir

        int counter = 0;
        while (!queue.isEmpty()) {                              // SELAMA queue tidak
            System.out.println("(%02d) current queue: ".formatted(++counter) + queue);
            Node<T> curr = queue.poll();                        // ambil queue terdepan, simpan pada
            System.out.println("<< POPPED " + curr);
            if (curr.left != null) {                            // JIKA anak kiri tidak kosong
                if (endOfLevel) {                               // JIKA penanda bernilai benar
                    return false;                               // KEMBALIKAN FALSE
                }
                System.out.println(">> added " + curr.left);
                queue.add(curr.left);                           // Masukkan anak kiri curr ke queue
            } else {                                            // SELAIN ITU
                endOfLevel = true;                              // ubah penanda ke TRUE
            }
            if (curr.right != null) {                           // JIKA anak kanan tidak kosong
                if (endOfLevel) {                               // JIKA penanda bernilai benar
                    return false;                               // KEMBALIKAN FALSE
                }
                System.out.println(">> added " + curr.right);
                queue.add(curr.right);                          // Masukkan anak kanan curr ke queue
            } else {                                            // SELAIN
                endOfLevel = true;                              // ubah penanda ke TRUE
            }
        }
        return true;                                            // KEMBALIKAN TRUE
    }
}

