package praktikum;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        var tree = new Tree();                                  // membuat objek Tree ke var. tree
//        int[] data = {42, 21, 38, 27, 71, 82, 55, 63, 6, 2, 40, 12};
//        int[] data = {27, 13, 42, 6, 17, 33, 48};
//        int[] data = {60, 41, 74, 16, 53, 65, 25, 46, 55, 63, 70, 42, 62, 64};
        int[] data = {56, 30, 40, 22, 70, 95, 60, 65, 11, 16, 63, 67, 3, 37, 88};

        for (int e : data) {                                    // Iterasi untuk setiap elemen di data
            tree.insert(e);                                     // memasukkan elemen ke tree
        }

        System.out.print("[1] Display pre order         : ");
        tree.traversePreOrder(tree.getRoot());                  // traverse pre order (dari root)
        System.out.println();

        System.out.print("[2] Display in order          : ");
        tree.traverseInOrder(tree.getRoot());                   // traverse dari kecil ke besar (dari root)
        System.out.println();

        System.out.print("[3] Display post order        : ");
        tree.traversePostOrder(tree.getRoot());                 // traverse secara post order (dari root)
        System.out.println();

        System.out.print("[4] Display level order       : ");
        tree.traverseLevelOrder();                              // traverse secara level order (dari root)
        System.out.println();

        cari(tree, 16);                                         // mencari nilai 16 pada tree dengan method cari
        cari(tree, 63);                                         // mencari nilai 63 pada tree dengan method cari
    }

    /**
     * Sebuah wrapper untuk mencari {@code key} pada sebuah {@link Tree} menggunakan metode {@link Tree#search}.
     * @param tree
     * Tree yang digunakan untuk mencari {@code key}
     * @param key
     * Data atau kunci yang ingin dicari di dalam {@code tree}.
     * */
    public static void cari(Tree tree, int key) {
        System.out.println(                                     // mencetak:
          (tree.search(key) != null) ?                          // JIKA hasil pencarian tidak kosong
            "Ditemukan " + key :                                // cetak "Ditemukan " + key
            "Tidak ditemukan " + key);                          // SELAIN ITU cetak "Tidak ditemukan " + key
    }
}
