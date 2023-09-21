package archived;

import praktikum.Node;

public class ArchivedMethods<T extends Comparable<T>> {

    private Node<T> root;

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

            /* Menukar parent node dengan node yang ingin di hapus */
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
}
