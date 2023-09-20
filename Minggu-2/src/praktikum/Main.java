package praktikum;

public class Main<T extends Comparable<T>> {

    public static void main(String[] args) {
        var tree = new Tree<Integer>();                                  // membuat objek Tree ke var. tree
//        int[] getData = {42, 21, 38, 27, 71, 82, 55, 63, 6, 2, 40, 12};
//        int[] getData = {27, 13, 42, 6, 17, 33, 48};
//        int[] getData = {60, 41, 74, 16, 53, 65, 25, 46, 55, 63, 70, 42, 62, 64};
//        int[] getData = {56, 30, 40, 22, 70, 95, 60, 65, 11, 16, 63, 67, 3, 37, 88};
//        int[] getData = {56, 30, 11, 40, 70, 95};
        int[] getData = {56, 30, 40, 22, 70, 95, 60, 65, 11, 16, 63, 67, 3, 37, 88};
        int[] toHapus = {100, 63, 65, 60, 95, 88, 67, 70, 56, 22, 16, 11, 3, 30, 40, 37};
//        int[] getData = {45, 21, 6, 2, 12, 30, 40, 35, 43, 37, 36, 71, 55, 82, 63, 60, 67};
//        int[] toHapus = {30, 55};

        for (int e : getData) {                                 // Iterasi untuk setiap elemen di getData
            tree.insert(e);                                     // memasukkan elemen ke tree
        }

        tree.getRoot().printDrawnStructure();

        for (int e : toHapus) {
            tree.remove(e);
            System.out.print("Display in order          : ");
            tree.traverseInOrder(tree.getRoot());
            System.out.println();
            try {
                tree.getRoot().printDrawnStructure();
            } catch (NullPointerException npe) {
                System.out.println("Tree kosong!");
            }
        }

        System.out.println("Test selesai.");
    }

    /**
     * Sebuah wrapper untuk mencari {@code key} pada sebuah {@link Tree} menggunakan metode {@link Tree#search}.
     *
     * @param tree
     * Tree yang digunakan untuk mencari {@code key}
     * @param key
     * getData atau kunci yang ingin dicari di dalam {@code tree}.
     * */
    public static <T extends Comparable<T>> void cari(Tree<T> tree, T key) {
        System.out.println(                                     // mencetak:
          (tree.search(key) != null) ?                          // JIKA hasil pencarian tidak kosong
            "Ditemukan " + key :                                // cetak "Ditemukan " + key
            "Tidak ditemukan " + key);                          // SELAIN ITU cetak "Tidak ditemukan " + key
    }
}
