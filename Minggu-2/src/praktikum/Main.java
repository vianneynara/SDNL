package praktikum;

import praktikum.avltree.AVLTree;
import praktikum.bst.Tree;

import java.util.Random;
import java.util.Scanner;

public class Main {

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

    private static void runBSTTest() {

        Tree<Integer> tree = new Tree<>();
        int[] dataset = {42, 21, 38, 27, 71, 82, 55, 63, 6, 2, 40, 12};
//        int[] dataset = {56, 30, 40, 22, 70, 95, 60, 65, 11, 16, 63, 67, 3, 37, 88};
//        int[] toHapus = {100, 63, 65, 60, 95, 88, 67, 70, 56, 22, 16, 11, 3, 30, 40, 37};

//        int size = 100_000;
//        double[] randomInts = new double[size];
//        for (int i = 0; i < size; i++) {
//            randomInts[i] = (Math.random() * 10000) + 1;
//        }
//
//        for (double n : randomInts) {
//            tree.insert(n);
//        }

        for (int key : dataset) {
            tree.insert(key);
            tree.getRoot().printDrawnStructure();
        }
        tree.traversePostOrder(tree.getRoot());

//        for (int key : toHapus) {
//            System.out.println("-".repeat(40));
//            System.out.println(">>> REMOVING " + key);
//            tree.delete(tree.getRoot(), key);
//            System.out.print("Display in order          : ");
//            tree.traverseInOrder(tree.getRoot());
//            System.out.println();
//            System.out.println("root: " + tree.getRoot());
//            try {
//                tree.getRoot().printDrawnStructure();
//            } catch (NullPointerException npe) {
//                System.out.println("Tree kosong!\n");
//            }
//        }
//
//        System.out.println("\nTest selesai.");
    }

    private static void runCompleteBST() {

        Tree<Integer> tree = new Tree<>();
        int[] isNotComplete = {25, 15, 30, 10, 20, 28, 5, 12, 23};
        int[] isComplete = {25, 15, 30, 31, 10, 20, 28, 5, 12, 23, 18};


        for (int key : isNotComplete) {
            tree.insert(key);
        }

        System.out.println("Is the tree complete? " + ((tree.isComplete()) ? "Yup" : "Nah"));
        tree.getRoot().printDrawnStructure();
    }

    private static void runAVLTestInsert() {

        AVLTree<Integer> tree = new AVLTree<>();
        int[] dataset = {20, 11, 5, 32, 40, 2, 4, 27, 23, 28, 50};

        for (int key : dataset) {                                 // Iterasi untuk setiap elemen di dataset
            System.out.println("-".repeat(40));
            System.out.println(">>> INSERTING " + key);
            tree.insert(key);
            System.out.print("Display in order          : ");
            tree.traverseInOrder(tree.getRoot());
            System.out.println();
            tree.getRoot().printDrawnStructure();
        }

        System.out.println("\nTest selesai.");
    }

    private static void runAVLTestDelete() {

        AVLTree<Integer> tree = new AVLTree<>();
        int[] dataset = {56, 30, 40, 22, 70, 95, 60, 65, 11, 16, 63, 67, 3, 37, 88};
        int[] toHapus = {100, 63, 65, 60, 95, 88, 67, 70, 56, 22, 16, 11, 3, 30, 40, 37};

        for (int key : dataset) {                               // Iterasi untuk setiap elemen di dataset
            tree.insert(key);                   // memasukkan elemen ke tree
        }

        for (int key : toHapus) {
            System.out.println("-".repeat(40));
            System.out.println(">>> REMOVING " + key);
            tree.delete(key);
            System.out.print("Display in order          : ");
            tree.traverseInOrder(tree.getRoot());
            System.out.println();
            System.out.println("root: " + tree.getRoot());
            try {
                tree.getRoot().printDrawnStructure();
            } catch (NullPointerException npe) {
                System.out.println("Tree kosong!\n");
            }
        }

        System.out.println("\nTest selesai.");
    }

    private static void runTest() {

        AVLTree<Integer> tree = new AVLTree<>();
        int[] dataset = {10, 6, 15, 8, 2, 13, 12, 14, 18, 20};

        for (int key : dataset) {                                 // Iterasi untuk setiap elemen di dataset
            System.out.println("-".repeat(40));
            System.out.println(">>> INSERTING " + key);
            tree.insert(key);
        }
        System.out.println();
        System.out.print("Display in order          : ");
        tree.traverseInOrder(tree.getRoot());
        tree.getRoot().printDrawnStructure();
    }

    private static void runUTSTest() {
        UTS1<Integer> tree = new UTS1<>();
        int[] data = {23, 19, 45, 5, 21, 35, 65, 8, 33, 42};

        for (int n : data)
            tree.insert(n);

        tree.getRoot().printDrawnStructure();
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan angka yang ingin dicari: ");
        int toFind = sc.nextInt();
        System.out.printf("Mencari angka %d...%n", toFind + 1);
        var hasil = tree.cariDataTepatLebihKecil(toFind);
        System.out.println(
            (hasil != null) ?
                "Ditemukan " + hasil :
                "Tidak ditemukan pencarian anak sebelum " + (toFind + 1)
        );
    }

    public static void main(String[] args) {
        runBSTTest();
//        runCompleteBST();
//        runAVLTestInsert();
//        runAVLTestDelete();
//        runTest();
//        runUTSTest();
    }
}
