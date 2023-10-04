package praktikum.bst;

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
     * Mencari nilai terkecil dari subtree kanan milik sebuah {@link Node<T>}.
     *
     * @param curr node untuk mencari
     * @return {@link Node<T>} yang terkecil atau null
     * */
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
     * Mencari nilai terbesar dari subtree kiri milik sebuah {@link Node<T>}.
     *
     * @param curr node untuk mencari
     * @return {@link Node<T>} terbesar atau null
     * */
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
     * @param curr root tree / iterator current node
     * @param child {@link Node<T>} yang dicari
     * @return {@link Node<T>} iterator yang maju atau hasil pencarian parent
     * */
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
            if (diff < 0) {
                if (curr.left != null) {
                    curr = curr.left;
                } else {
                    curr.left = newNode;
                    size++;
                    return;
                }
            } else {
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
     * Metode ini memasukkan sebuah value bertipe {@code T} yang bersifat {@link Comparable<T>},
     * memasukkannya ke {@link Node<T>}, dan menyambungkannya ke tree secara rekursif.
     *
     * @param parent {@link Node<T>} kepala
     * @param data nilai yang ingin dimasukkan ke parent node
     * */
    public Node<T> insert(Node<T> parent, T data) {
        if (parent == null) {
            size++;
            return new Node<>(data);
        }
        if (data.compareTo(parent.getData()) < 0) {                 
            parent.left = insert(parent.left, data);
        } else {                                                    
            parent.right = insert(parent.right, data);
        }
        return parent;
    }

    /**
     * Pencarian sebuah node yang bernilai {@code key} yang ingin dihapus pada tree ini secara iteratif.
     * Jika ditemukan maka akan melakukan proses pengaturan dan rekonfigurasi struktur tree kemudian mengembalikannya.
     *
     * @param key nilai yang akan dihapus
     * @return {@link Node<T>} yang dihapus atau null jika tidak ditemukan
     */
    public Node<T> delete(T key) {
        Node<T> parent = null;
        Node<T> curr = this.root;

        /* Mencari kunci dan parent dari kunci */
        while (curr != null) {                                      
            if (key.compareTo(curr.getData()) < 0) {                
                parent = curr;                                      
                curr = curr.left;                                   
            } else if (key.compareTo(curr.getData()) > 0) {         
                parent = curr;                                      
                curr = curr.right;                                  
            } else {                                                
                break;                                              
            }
        }

        if (curr == null) {                                         
            return null;
        }

        size--;
        /* If curr is the root of the tree */
        if (curr == this.root) {
            if (curr.left == null && curr.right == null) {
                this.root = null;
            } else if (curr.right != null) {
                Node<T> leftChild = this.root.left;
                this.root = this.root.right;
                if (curr.left != null) {
                    this.root.left = leftChild;
                }
            } else {
                this.root = curr.left;
            }
        }
        /* If curr doesn't have children */
        else if (curr.isTail()) {
            if (parent.left == curr) {                              
                parent.left = null;                                 
            } else {                                                
                parent.right = null;                                
            }
        /* If curr has at maximum 1 child */
        } else if (curr.left == null || curr.right == null) {
            Node<T> child = (curr.left != null) ? curr.left : curr.right;
            if (parent.left == curr) {                              
                parent.left = child;                                
            } else {                                                
                parent.right = child;                               
            }
        /* If curr has 2 children */
        } else {
            final Node<T> removed = new Node<>(curr.data);

            Node<T> leastOnRight = findSuccessor(curr);
            Node<T> leastParent = findParent(this.root, leastOnRight);  
            curr.data = leastOnRight.data;

            if ((leastParent != null) && (leastParent.left == leastOnRight)) {
                leastParent.left = null;                            
            } else {                                                
                if (leastParent != null) {                          
                    leastParent.right = null;                       
                }
            }
            return removed;
        }
        return curr;
    }

    /**
     * Pencarian sebuah node yang bernilai {@code key} yang ingin dihapus pada tree ini secara rekursif.
     * Jika ditemukan maka akan melakukan proses pengaturan dan rekonfigurasi struktur tree kemudian mengembalikannya.
     *
     * @param curr root node dari nilai yang akan dihapus
     * @param key nilai yang akan dihapus
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
        }
        return curr;
    }

    /**
     * Mengembalikan size tree.
     *
     * @return {@link Node<T>}
     * */
    public int getSize() {
        return this.size;
    }

    /**
     * Mengembalikan root tree.
     *
     * @return {@link Node<T>}
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
     * (rekursif) Melakukan pencarian secara rekursif hingga {@code root} bernilai null
     * atau {@code root} bernilai {@code key}.
     *
     * @param root {@link Node<T>} untuk mencari
     * @param key nilai yang dicari
     * @return {@link Node<T>} yang dicari atau nilai null bila tidak ditemukan
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
     * (rekursif) Pre Order | Melakukan traversal dari ujung kiri ke ujung kanan, mencetak setiap isi {@link Node<T>}.
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
     * (rekursif) In Order | Melakukan traversal dari nilai terkecil hingga terbesar, mencetak setiap isi {@link Node<T>}.
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
     * (rekursif) Post Order | Melakukan traversal dari leaf kiri-kanan-parent, mencetak setiap isi {@link Node<T>}.
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
