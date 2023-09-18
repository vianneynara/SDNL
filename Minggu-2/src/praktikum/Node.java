package praktikum;

public class Node {
    /* Inisialisasi atribut kelas: Node, Node, int */
    private Node leftLink;
    private Node rightLink;
    private int data;

    public Node(int data) {                                 // Constructor satu parameter: data
        this.leftLink = null;                               // Isi leftLink kelas dengan null
        this.rightLink = null;                              // Isi rightLink kelas dengan null
        this.data = data;                                   // Isi leftLink kelas dengan data dari parameter
    }

    public Node left() {                                    // METODE mengembalikan Node
        return leftLink;                                    // KEMBALIKAN leftLink
    }

    public void setLeft(Node leftLink) {                    // METODE mengatur Node kiri, param: Node
        this.leftLink = leftLink;                           // ATUR leftLink kelas dgn. leftLink parameter
    }

    public Node right() {                                   // METODE mengembalikan Node
        return rightLink;                                   // KEMBALIKAN rightLink
    }

    public void setRight(Node rightLink) {                  // METODE mengatur Node kanan, param: Node
        this.rightLink = rightLink;                         // ATUR rightLink kelas dgn. rightLink parameter
    }

    public int data() {                                     // METODE mengatur data pada kelas ini
        return data;                                        // KEMBALIKAN data
    }

    public void setData(int data) {                         // METODE mengatur data, param: int
        this.data = data;                                   // ATUR data kelas dgn. data parameter
    }

    public boolean isLeaf() {
        return (leftLink == null && rightLink == null);     // mengecek apakah punya anak atau tidak
    }

    @Override
    public String toString() {                              // metode toString
        return "{" + data + '}';                            // KEMBALIKAN data dengan format
    }
}
