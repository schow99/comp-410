package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST() {
		size = 0;
		root = null;
	}


	@Override
	// used for testing, please leave as is
	public BST_Node getRoot() {
		return root;
	}

	@Override
	public boolean insert(String s) {
		if (size == 0) {
			root = new BST_Node(s);
			size++;
			return true;
		}
		if (this.contains(s)) {
			return false;
		}
		if (root.insertNode(s)) {
			size++;
			return true;
		}
		return false;

	}

	@Override
	public boolean remove(String s) {
		if (size == 0) {
			return false;
		} 
		if (this.contains(s)) {
			root.removeNode(s);
			size--;
			return true;
		}
		return false;
	}

	@Override
	public String findMin() {
		if (size == 0) {
			return null;
		} else {
			return root.findMin().getData();
		}
	}

	@Override
	public String findMax() {
		if (size == 0) {
			return null;
		} else {
			return root.findMax().getData();
		}
	}

	@Override
	public boolean empty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(String s) {
		return root.containsNode(s);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		return root.getHeight() -1 ;
	}

}
