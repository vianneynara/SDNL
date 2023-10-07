package praktikum.avltree;

import java.util.LinkedList;
import java.util.Queue;

// src: https://www.happycoders.eu/algorithms/avl-tree-java/
@SuppressWarnings("DuplicatedCode")
public class AVLTree<T extends Comparable<T>> {

	private AVLNode<T> root;
	private int size;

	public AVLTree() {
		this.root = null;
	}

	/**
	 * Konstruktor dengan sebuah nilai bertipe {@link T} yang {@link Comparable<T>} sebagai root.
	 */
	public AVLTree(T data) {
		this.root = new AVLNode<>(data);
	}

	/**
	 * Konstruktor yang menerima {@link AVLNode} sebagai root.
	 */
	public AVLTree(AVLNode<T> root) {
		this.root = root;
	}

	/**
	 * Mendapatkan height/ketinggian dari sebuah {@link AVLNode<T>} dengan melakukan pengecekan apakah node tersebut
	 * null atau tidak.
	 */
	private int height(AVLNode<T> node) {
		return (node != null) ? node.height : -1;
	}

	/**
	 * Mengembalikan balance factor dari sebuah {@link AVLNode<T>}.
	 */
	private int balanceFactor(AVLNode<T> node) {
		/* Mendapatkan balance factor dengan mengurangi ketinggian node kanan dengan node kiri */
		return height(node.right) - height(node.left);
	}

	/**
	 * Memperbarui ketinggian/height dari sebuah {@link AVLNode<T>} dengan menjumlahkan anak yang memiliki height
	 * tertinggi dengan 1.
	 */
	private AVLNode<T> updateHeight(AVLNode<T> node) {
		/* Mendapatkan tinggi anak yang paling dominan / dalam, ditambah dengan 1 */
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		return node;
	}

	/**
	 * <b>Melakukan rotasi kanan dengan:</b>
	 * <br>1. Mengubah anak kiri node menjadi root (posisi node yang original).
	 * <br>2. Anak kanan root sebelumnya diubah menjadi anak kiri node.
	 * <br>3. Kemudian node diubah menjadi anak kanan root yang baru.
	 * <br>4. Melakukan update ketinggian node yang ditukar dan anak kirinya.
	 *
	 * @param node root asli yang ingin dilakukan penukaran.
	 */
	private AVLNode<T> rotateRight(AVLNode<T> node) {
		AVLNode<T> leftChild = node.left;							// simpan anak kiri

		node.left = leftChild.right;								// isi anak kiri node dengan anak kanan leftChild
		leftChild.right = node;										// ubah anak kanan leftChild dengan node

		updateHeight(node);											// perbarui ketinggian node
		updateHeight(leftChild);									// perbarui ketinggian leftChild

		return leftChild;
	}

	/**
	 * <b>Melakukan rotasi kiri dengan:</b>
	 * <br>1. Mengubah anak kanan node menjadi root (posisi node yang original).
	 * <br>2. Anak kanan root sebelumnya diubah menjadi anak kanan node.
	 * <br>3. Kemudian node diubah menjadi anak kiri root yang baru.
	 * <br>4. Melakukan update ketinggian node yang ditukar dan anak kanannya.
	 *
	 * @param node root asli yang ingin dilakukan penukaran.
	 */
	private AVLNode<T> rotateLeft(AVLNode<T> node) {
		AVLNode<T> rightChild = node.right;							// simpan anak kanan

		node.right = rightChild.left;								// isi anak kanan node dengan anak kiri rightChild
		rightChild.left = node;										// ubah anak kiri rightChild dengan node

		updateHeight(node);											// perbarui ketinggian node
		updateHeight(rightChild);									// perbarui ketinggian rightChild

		return rightChild;
	}

	/**
	 * Melakukan penyeimbangan anak-anak node dari sebuah {@link AVLNode<T>} dengan melakukan rotasi kanan maupun
	 * rotasi kiri.
	 */
	private AVLNode<T> rebalance(AVLNode<T> node) {
		int rootBalanceFactor = balanceFactor(node);			// hitung balance factor root awal dengan fungsi
		System.out.println("AVLNode " + node + " balance factor is: " + rootBalanceFactor);
		node.printDrawnStructure();

		if (rootBalanceFactor < -1) {							// JIKA node berat di kiri
			if (balanceFactor(node.left) > 0) {                	// JIKA anak kirinya berat di kanan
				node.left = rotateLeft(node.left);				// ubah anak kiri dengan anak kiri yang diputer kiri
				node.printDrawnStructure();
			}
			node = rotateRight(node);							// ubah node menjadi node yang diputar kanan
			node.printDrawnStructure();
		} else if (rootBalanceFactor > 1) {						// JIKA node berat di kanan
			if (balanceFactor(node.right) < 0) {                // JIKA anak kanannya berat di kiri
				node.right = rotateRight(node.right);			// ubah anak kanan dengan anak kanan yang diputer kanan
				node.printDrawnStructure();
			}
			node = rotateLeft(node);							// ubah node menjadi node yang diputar kiri
			node.printDrawnStructure();
		}

		System.out.println("AVLNode " + node + " has been balanced.");
		return node;
	}

	/**
	 * Wrapper pemasukan sebuah data {@link T} ke AVL tree ini. Menggunakan pemasukan rekursif
	 * {@link #insert(AVLNode, T)} dari akar pohon.
	 */
	public void insert(T data) {
		this.root = insert(this.root, data);
	}

	/**
	 * Metode ini memasukkan sebuah value bertipe {@code T} yang bersifat {@link Comparable<T>},
	 * memasukkannya ke {@link AVLNode<T>}, dan menyambungkan ke tree secara rekursif. Selanjutnya melakukan update
	 * ketinggian node dan melakukan penyeimbangan node.
	 *
	 * @param curr {@link AVLNode<T>} kepala yang dimasukkan
	 * @param data nilai yang ingin dimasukkan ke parent node
	 */
	private AVLNode<T> insert(AVLNode<T> curr, T data) {
		/* Melakukan pemasukan node biasa seperti pada BST secara rekursif */
		if (curr == null) {                                   	// Base case, curr kosong.
			size++;                                             // menambah size dari tree dengan 1
			return new AVLNode<>(data);
		}
		if (data.compareTo(curr.getData()) < 0) {            	// JIKA data lebih besar dari curr
			curr.left = insert(curr.left, data);
		} else if (data.compareTo(curr.getData()) > 0) {      	// JIKA data lebih besar dari curr
			curr.right = insert(curr.right, data);
		} else {                                                // Hindari duplikat
			System.out.println("Duplicate value " + data + " found!");
		}
		/* Melakukan pembaruan ketinggian node curr dan penyeimbangan */
		updateHeight(curr);
		return rebalance(curr);
	}

	/**
	 * Mencari nilai terbesar dari subtree kiri milik sebuah {@link AVLNode <T>}.
	 *
	 * @param node node untuk mencari
	 * @return {@link AVLNode<T>} terbesar atau null
	 */
	private AVLNode<T> findPredecessor(AVLNode<T> node) {
		if (node == null) {
			return null;
		} else {
			node = node.left;
			if (node == null) {
				return null;
			} else {
				while (node.right != null) {
					node = node.right;
				}
				return node;
			}
		}
	}

	/**
	 * Wrapper penghapusan sebuah data {@link T} ke AVL tree ini. Menggunakan penghapusan rekursif
	 * {@link #delete(AVLNode, T)} dari akar pohon.
	 */
	public void delete(T data) {
		this.root = delete(this.root, data);
	}

	/**
	 * Pencarian sebuah node yang bernilai {@code key} yang ingin dihapus pada tree ini secara rekursif.
	 * Jika ditemukan maka akan melakukan proses pengaturan dan rekonfigurasi struktur tree kemudian mengembalikannya.
	 * Selanjutnya melakukan update ketinggian node dan melakukan penyeimbangan node.
	 *
	 * @param curr root node dari nilai yang akan dihapus
	 * @param key  nilai yang akan dihapus
	 * @return {@link AVLNode<T>} yang dihapus atau null jika tidak ditemukan
	 */
	private AVLNode<T> delete(AVLNode<T> curr, T key) {
		/* Melakukan penghapusan node biasa seperti pada BST secara rekursif */
		if (curr == null) {
			size--;
			return null;
		}
		if (key.compareTo(curr.getData()) < 0) {
			curr.left = delete(curr.left, key);
		} else if (key.compareTo(curr.getData()) > 0) {
			curr.right = delete(curr.right, key);
		} else {
			if (curr.left == null) {
				if (curr == this.root)								// invert urutan IF yang sebelumnya pada BST
					this.root = curr.right;
				return curr.right;
			} else if (curr.right == null) {
				if (curr == this.root)								// invert urutan IF yang sebelumnya pada BST
					this.root = curr.left;
				return curr.left;
			} else {
				AVLNode<T> predecessor = findPredecessor(curr);
				curr.data = predecessor.data;
				curr.left = delete(curr.left, predecessor.data);
			}
		}
		/* Melakukan pembaruan ketinggian node curr dan penyeimbangan */
		updateHeight(curr);
		return rebalance(curr);
	}

	/**
     * Mengembalikan size tree.
     *
     * @return {@link AVLNode<T>}
     * */
    public AVLNode<T> getRoot() {
		return this.root;
	}

    /**
     * Mengembalikan root tree.
     *
     * @return {@link AVLNode<T>}
     * */
	public int getSize() {
		return this.size;
	}


	/**
	 * (rekursif) In Order | Melakukan traversal dari nilai terkecil hingga terbesar, mencetak setiap isi {@link AVLNode <T>}.
	 *
	 * @param curr awal mulai
	 */
	public void traverseInOrder(AVLNode<T> curr) {
		if (curr != null) {
			traverseInOrder(curr.left);
			System.out.print((curr.getData()) + " ");
			traverseInOrder(curr.right);
		}
	}

	/**
	 * (rekursif) Post Order | Melakukan traversal dari leaf kiri-kanan-parent, mencetak setiap isi {@link AVLNode<T>}.
	 *
	 * @param curr awal mulai
	 */
	public void traversePostOrder(AVLNode<T> curr) {
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
		Queue<AVLNode<T>> queue = new LinkedList<>();
		queue.add(this.root);

		while (!queue.isEmpty()) {
			AVLNode<T> curr = queue.poll();
			System.out.print(curr.data + " ");
			if (curr.left != null)
				queue.add(curr.left);
			if (curr.right != null)
				queue.add(curr.right);
		}
	}
}