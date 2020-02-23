package DiGraph_A5;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; // Everything in the array will initially
												// be null. This is ok! Just build out
												// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for simplicity
													// of child/parent computations...
													// the book/animation page both do this.
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { // returns whole array
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		if (size() >= arraySize) { // exits if the size of array is too big
			return;
		}
		if (size() == 0) { // if empty
			array[1] = entry; // set first index to be the entry
		} else {
			int nextNode = size() + 1; // getting the next empty node
			array[nextNode] = entry; // puts new entry into next node
			while ((nextNode / 2) > 0) {
				if (array[nextNode].getPriority() < array[nextNode / 2].getPriority()) { // if the priority of the
																							// parent is greater than
																							// the child
					EntryPair temp = array[nextNode / 2]; // switch positions
					array[nextNode / 2] = array[nextNode];
					array[nextNode] = temp;
					nextNode = nextNode / 2; // moves up level in tree to continue
				} else {
					break;
				}
			}
		}

	}

	@Override
	public void delMin() {
		this.array[1] = this.array[size()]; // sets first element in heap to last element
		this.array[size()] = null; // makes last element null
		bubbleDown(1); // bubble down starting from the first index
	}

	@Override
	public EntryPair getMin() {
		if (size() == 0) { // if heap is empty
			return null;
		}
		return this.array[1]; // returns first element
	}

	@Override
	public int size() {
		int count = 0; // starts size 0
		int i = 1; // initial element
		while (array[i] != null) { // while there is an element
			count++;
			i++;
		}
		return count; // return total count
	}

	@Override
	public void build(EntryPair[] entries) {
		int entrySize = entries.length;
		for (int i = 0; i < entrySize; i++) {
			array[i + 1] = entries[i]; // sets next element in array to element in respective element in entry
		}
		int size = size();
		int index = size / 2;
		while (index > 0) { // order the binary heap
			bubbleDown(index); // bubble down the elements
			index--;
		}

	}

	public void bubbleDown(int index) {
		while (2 * index <= size()) { // stops once index is out of bounds
			int min = smallerChild(index); // sets min to index of smaller child
			if (this.array[min].getPriority() < this.array[index].getPriority()) { // compares the priority of smaller
																					// child and current node
				EntryPair temp = this.array[index]; // swap
				this.array[index] = this.array[min];
				this.array[min] = 	temp;
				index = min; // sets new index to min
			} else {
				break;
			}
		}
	}

	public void swap(int a, int b) { // swap function
		EntryPair temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	public int smallerChild(int index) {
		if (2 * index + 1 <= size()) {
			if (this.array[2 * index + 1].getPriority() > this.array[2 * index].getPriority()) { // compares children
				return 2 * index; // return left
			} else {
				return 2 * index + 1; // return right
			}
		} else {
			return 2 * index;
		}
	}

}
