package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {

	Node sentinel; // this will be the entry point to your linked list (the head)

	public LinkedListImpl() {// this constructor is needed for testing purposes. Please don't modify!
		sentinel = new Node(0); // Note that the root's data is not a true part of your data set!

	}

	// implement all methods in interface, and include the getRoot method we made
	// for testing purposes. Feel free to implement private helper methods!

	public Node getRoot() { // leave this method as is, used by the grader to grab your linkedList easily.
		return sentinel;
	}

	@Override
	public boolean insert(double elt, int index) {
		Node present = new Node(elt); // new node to add
		if (index < 0 || index > size()) { // index is out of bounds
			return false;
		} else if (isEmpty()) { // if list is empty
			sentinel.next = sentinel.prev = present;
			present.prev = present.next = sentinel;
			return true;
		} else if (index == size()) { // if new node is being placed at the end of the list
			sentinel.prev.next = present;
			present.prev = sentinel.prev;
			present.next = sentinel;
			sentinel.prev = present;
			return true;
		} else {
			Node temp = sentinel.next;
			for (int i = 0; i < index; i++) { // go to index position
				temp = temp.next;
			}
			temp.prev.next = present; // sets the previous and next nodes
			present.prev = temp.prev;
			temp.prev = present;
			present.next = temp;
			return true;
		}

	}

	@Override
	public boolean remove(int index) {
		if (isEmpty() || index < 0 || index >= size()) { // if list is empty or index is out of bounds
			return false;
		} else if (index == (size() - 1)) { // if index is at the end of list
			Node present = sentinel.prev; //unlink node from list
			present.prev.next = sentinel;
			sentinel.prev = present.prev;
			return true;
		} else {
			Node present = sentinel.next;
			for (int i = 0; i < size(); i++) {
				if (i == index) {
					present.next.prev = present.prev;//unlink node from list
					present.prev.next = present.next;
				}
				present = present.next;
			}
			return true;

		}

	}

	@Override
	public double get(int index) {
		Node present = sentinel.next;
		if (index >= size() || size() == 0) {//if index is out of bounds
			return Double.NaN;
		} else {
			for (int i = 0; i < index; i++) {//go through nodes until index
				present = present.next;
			}

			return present.data;
		}
	}

	@Override
	public int size() {
		int count = 0;
		Node temp = new Node(0);
		temp = sentinel;
		if (temp.next != null) {//if next element is not null
			while (temp.next != sentinel) {//go to next node if it is not starting node
				temp = temp.next;
				count++; //increase count of size
			}
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		if (size() == 0) { //if size is 0
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		sentinel.next = null; //sets next node from sentinel to null
		sentinel.prev = null; //sets previous node from sentinel to null
	}

}
