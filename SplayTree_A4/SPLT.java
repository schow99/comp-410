package SPLT_A4;

public class SPLT implements SPLT_Interface {
	private BST_Node root;
	private int size;

	public SPLT() {
		this.size = 0;
	}

	public BST_Node getRoot() { // please keep this in here! I need your root node to test your tree!
		return root;
	}

	@Override
	public void insert(String s) {
		if (contains(s)) {
			return;
		} else if (empty()) {
			root = new BST_Node(s);
			size = 1;
			return;
		} else {
			size++;
			splay(root.insertNode(s));
			return;
		}

	}

	@Override
	public void remove(String s) {
		if (!contains(s)) {
			return;
		} else if (root == null) {
			return;
		} else {
			if (root.data == s) {
				if (size == 1) {
					root = null;
					size = 0;
					return;
				}
			}
			root.removeNode(s);
			size--;
			return;
		}

	}

	@Override
	public String findMin() {
		if (empty()) {
			return null;
		}
		splay(root.findMin());
		return root.findMin().data;
	}

	@Override
	public String findMax() {
		if (empty()) {
			return null;
		}
		splay(root.findMax());
		return root.findMax().data;
	}

	@Override
	public boolean empty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(String s) {
		if (empty()) {
			return false;
		}
		splay(root.containsNode(s));
		if (s == root.containsNode(s).data && root.containsNode(s) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if (empty()) {
			return -1;
		}
		return root.getHeight();
	}

	public void splay(BST_Node n) {
		while (n != root) {
			BST_Node parent = n.parent;
			if (parent.equals(root)) { // zag
				if (n.equals(parent.left)) {
					rotateRight(parent);
				} else if (n.equals(parent.right)) { //zig 
					rotateLeft(parent);
				}
			} else { //if the node isn't one removed from the root
				BST_Node grand = parent.parent;
				if (n.equals(parent.left) && parent.equals(grand.left)) { // zig-zig
					rotateRight(grand);
					rotateRight(parent);
				} else if (n.equals(parent.right) && parent.equals(grand.left)) {// zig-zag
					rotateLeft(parent);
					rotateRight(grand);
				} else if (n.equals(parent.left) && parent.equals(grand.right)) {// zag-zig
					rotateRight(parent);
					rotateLeft(grand);
				} else if (n.equals(parent.right) && parent.equals(grand.right)) { // zag-zag
					rotateLeft(grand);
					rotateLeft(parent);
				}
			}
		}
	}

	public void rotateLeft(BST_Node node) {
		BST_Node temp = node.right;
		node.right = temp.left;
		temp.parent = node.parent;
		if (node.right != null) {
			node.right.parent = node;
		}
		temp.left = node;
		node.parent = temp;	// parent points to temp 
		if (temp.parent != null) {
			if (node == temp.parent.left) {
				temp.parent.left = temp;
			} else {
				temp.parent.right = temp;
			}
		} else {
			root = temp;
		}
	}

	public void rotateRight(BST_Node node) {
		BST_Node temp = node.left;
		node.left = temp.right;
		temp.parent = node.parent;
		if (node.left != null) {
			node.left.parent = node;
		}
		temp.right = node;
		node.parent = temp;	// parent points to temp 
		if (temp.parent != null) {
			if (node == temp.parent.left) {
				temp.parent.left = temp;
			} else {
				temp.parent.right = temp;
			}
		} else {
			root = temp;
		}
	}

}
