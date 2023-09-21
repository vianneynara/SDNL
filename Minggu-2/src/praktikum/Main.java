package praktikum;

public class Main<T extends Comparable<T>> {

    public static void main(String[] args) {
        var tree = new Tree<Integer>();                         // membuat objek Tree ke var. tree
        int[] dataset = {56, 30, 40, 22, 70, 95, 60, 65, 11, 16, 63, 67, 3, 37, 88};
        int[] toHapus = {100, 63, 65, 60, 95, 88, 67, 70, 56, 22, 16, 11, 3, 30, 40, 37, 1};

        for (int key : dataset) {                                 // Iterasi untuk setiap elemen di dataset
            tree.insert(key);                                     // memasukkan elemen ke tree
        }

        tree.getRoot().printDrawnStructure();

        for (int key : toHapus) {
            System.out.println("removing... " + key);
            boolean isRemoved = (tree.remove(key) != null);
            try {
                System.out.println( (isRemoved) ? "removed {" + key + "}" : "failed to remove " + key);
                System.out.print("Display in order          : ");
                tree.traverseInOrder(tree.getRoot());
                System.out.println();
                tree.getRoot().printDrawnStructure();
            } catch (NullPointerException npe) {
                System.out.println("Tree kosong!\n");
            }
        }

        System.out.println("\nTest selesai.");
    }

    /**
     * Sebuah wrapper untuk mencari {@code key} pada sebuah {@link Tree} menggunakan metode {@link Tree#search}.
     *
     * @param tree
     * Tree yang digunakan untuk mencari {@code key}
     * @param key
     * dataset atau kunci yang ingin dicari di dalam {@code tree}.
     * */
    public static <T extends Comparable<T>> void cari(Tree<T> tree, T key) {
        System.out.println(                                     // mencetak:
          (tree.search(key) != null) ?                          // JIKA hasil pencarian tidak kosong
            "Ditemukan " + key :                                // cetak "Ditemukan " + key
            "Tidak ditemukan " + key);                          // SELAIN ITU cetak "Tidak ditemukan " + key
    }
}
