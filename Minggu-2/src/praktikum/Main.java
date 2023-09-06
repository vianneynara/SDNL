package praktikum;

public class Main {

    public static void main(String[] args) {
        var tree = new Tree();                                  // membuat objek Tree ke var. tree
        int[] data = {42, 21, 38, 27, 71, 82, 55, 63, 6, 2, 40, 12};

        for (int e : data) {                                    // Iterasi untuk setiap elemen di data
            tree.insert(e);                                     // memasukkan elemen ke tree
        }

        System.out.print("Display dari kiri ke kanan    : ");
        tree.traverseLeftToRight(tree.getRoot());               // traverse kiri ke kanan di tree (dari root)

        System.out.println();

        System.out.print("Display dari terkecil         : ");
        tree.traverseInOrder(tree.getRoot());                   // traverse dari kecil ke besar (dari root)

        System.out.println();

//        cari(tree, 1);
//        cari(tree, 71);
    }

    /**
     * Mencari {@code key} pada sebuah {@link Tree}
     * @param tree
     * Tree yang digunakan untuk mencari {@code key}
     * @param key
     * Data atau kunci yang ingin dicari di dalam {@code tree}.
     * */
    public static void cari(Tree tree, int key) {               // ABAIKAN
        System.out.println((tree.search(key) != null) ? "Ditemukan " + key : "Tidak ditemukan " + key);
    }
}
