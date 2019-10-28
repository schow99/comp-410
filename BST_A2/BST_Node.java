package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data) {
		this.data = data;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	// --- end used for testing -------------------------------------------

	// --- fill in these methods ------------------------------------------
	//
	// at the moment, they are stubs returning false
	// or some appropriate "fake" value
	//
	// you make them work properly
	// add the meat of correct implementation logic to them

	// you MAY change the signatures if you wish...
	// make the take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations

	public boolean containsNode(String s) {
		if (s == this.getData()) {
			return true;
		} else if (this.data.compareTo(s) > 0) {
			if (this.left == null) {
				return false;
			} else {
				return this.left.containsNode(s);
			}
		} else if (this.data.compareTo(s) < 0) {
			if (this.right == null) {
				return false;
			} else {
				return this.right.containsNode(s);
			}
		} else if (this.data.compareTo(s) < 0) {
			if (this.left == null) {
				return false;
			} else {
				return this.left.containsNode(s);
			}
		}
		return false;
	}

	public boolean insertNode(String s) {
		if (containsNode(s)) {
			return false;
		} else if (s.compareTo(this.getData()) > 0) {
			if (this.right != null) {
				return this.right.insertNode(s);
			} else {
				this.right = new BST_Node(s);
				return true;
			}
		} else {
			if (this.left != null) {
				return this.left.insertNode(s);
			} else {
				this.left = new BST_Node(s);
				return true;
			}
		}

	}

	public boolean removeNode(String s) {
		if (!containsNode(s)) { //if string isn't in the tree
			return false;
		} else {
			boolean hasLeft = false;
			BST_Node parent = this;
			BST_Node remove = this;
			while (remove.data != s) { //if the node isn't the node we want to remove
				if (s.compareTo(remove.data) < 0) { //if value is less than the current node
					if (remove.left != null) { 
						parent = remove; //linking tree upwards
						remove = remove.left; //child replaces current node
					}
					hasLeft = true; 
				} else { //if value is greater than node
					if (remove.right != null) { 
						parent = remove; //linking tree upward
						remove = remove.right; //child replaces current node
					}
					hasLeft = false;
				}
			}
			if (remove.left == null && remove.right == null) { //if remove node is a leaf
				if (hasLeft) { 
					parent.left = null; //unlinks smaller child value
				} else {
					parent.right = null;
				}
			}
			if (remove.left != null && remove.right == null) { //if there is one child that is smaller
				if (hasLeft) {
					parent.left = remove.left; //links two nodes together after removing 
				} else {
					parent.right = remove.left;
				}
			}
			if (remove.left == null && remove.right != null) { //if there is one child that is larger
				if (hasLeft) {
					parent.left = remove.right; //links two nodes together after removing 
				} else {
					parent.right = remove.right;
				}
			}
			
			if (remove.left != null && remove.right != null) { //if remove node has two children 
				BST_Node min = remove.right.findMin(); //creating a new node with the minimum of the subtree
				this.removeNode(min.data); //recursion
				remove.data = min.data; 
			}
			return true;
		}
	}

	public BST_Node findMin() {
		BST_Node current = this;
		if (current.left != null) {
			return current.left.findMin();
		}
		return current;
	}

	public BST_Node findMax() {
		BST_Node current = this;
		if (current.right != null) {
			return current.right.findMax();
		}
		return current;
	}

	public int getHeight() {
		if (this.left == null && this.right == null) {
			return 1;
		}
		if (this.left == null && this.right != null) {
			return this.right.getHeight() + 1;
		}
		if (this.left != null && this.right == null) {
			return this.left.getHeight() + 1;
		} else {
			if (this.left.getHeight() >= this.right.getHeight()) {
				return this.left.getHeight() + 1;
			} else {
				return this.right.getHeight() + 1;
			}
		}
	}

	public BST_Node getParent(BST_Node node, String s) {
		if (node == null) {
			return null;
		}
		BST_Node parent = null;
		while (node != null) {
			if (s.compareTo(node.data) > 0) {
				parent = node;
				node = node.right;
			} else if (s.compareTo(node.data) < 0) {
				parent = node;
				node = node.left;
			} else {
				break;
			}

		}
		System.out.println(parent);
		return parent;

	}

	// --- end fill in these methods --------------------------------------

	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}
}
