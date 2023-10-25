package praktikum;

import praktikum.bst.Node;
import praktikum.bst.Tree;

public class UTS1<T extends Number & Comparable<T>> extends Tree<T> {
	/**
	 * INTERPRETASI:
	 * Mencari key "tepat lebih kecil dari sebuah nilai" artinya mengembalikan key tepat sebelum
	 * key yang dicari.<br>
	 * <br>
	 * Penjelasan:
	 * Disini menambahkan nilai key dengan 1, kemudian melakukan pencarian PARENT / node sebelum node yang memiliki
	 * nilai key.
	 * */
	public Node<T> cariDataTepatLebihKecil(int key) {
		return cariDataTepatLebihKecil(super.getRoot(), key + 1);
	}

	private Node<T> cariDataTepatLebihKecil(Node<T> curr, Integer key) {
		try {
			/* Base Case */
			if (key == super.getRoot().getData().intValue() || curr == null) {
				return null;
			} else {
				if (curr.getLeft().getData().intValue() == key || curr.getRight().getData().intValue() == key)
					return curr;
				else {
					if (key < curr.getData().intValue()) {
						return cariDataTepatLebihKecil(curr.getLeft(), key);
					} else {
						return cariDataTepatLebihKecil(curr.getRight(), key);
					}
				}
			}
		} catch (NullPointerException e) {
			return null;
		}
	} 
}