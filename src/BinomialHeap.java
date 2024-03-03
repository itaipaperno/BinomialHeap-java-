

import java.nio.channels.NonReadableChannelException;

/**
 * BinomialHeap 
 *
 * An implementation of binomial heap over non-negative integers.
 * Based on exercise from previous semester .
 */
public class BinomialHeap
{
	public int size;
	public HeapNode last;
	public HeapNode min;

	/**
	 * 
	 * @pre x.size == y.size
	 * 
	 * links 2 binary heaps with the roots x,y 
	 * 
	 */
	protected static HeapNode link(HeapNode x, HeapNode y){
		if (x.item.key > y.item.key){
			HeapNode tmp = y;
			y = x;
			x = tmp;
		}
		if (x.child == null)
			y.next = y;
		else{
			y.next = x.child.next;
			x.child.next = y;
		}
		x.child = y;
		y.parent = x;
		x.rank++;
		return x;
	}
	
	/**
	 * 
	 * sifts up node until it is at the right location in the tree
	 * 
	 */
	protected static void siftup(HeapNode x){
		HeapNode curr = x;
		if (curr == null)  // x is null
			return;
		
		// iterate up the tree until we're at the right spot or at the root, each iteration, switch the items of the node and its parent.
		while ((curr.parent != null) && (curr.item.key < curr.parent.item.key)) {
			// temporary values
			HeapNode curr_node = curr;
			HeapItem curr_item = curr.item;
			HeapNode parent_node = curr.parent;
			HeapItem parent_item = curr.parent.item;

			parent_node.item = curr_item; 
			parent_node.item.node = parent_node;
			
			curr_node.item = parent_item;
			curr_node.item.node = curr_node;
			
			curr = curr.parent; // advance curr
		
		}
	}
	
	/**
	 * 
	 * pre: key > 0
	 *
	 * Insert (key,info) into the heap and return the newly generated HeapItem.
	 *
	 */
	public HeapItem insert(int key, String info) 
	{    
		return new HeapItem(0); // should be replaced by student code
	}

	/**
	 * 
	 * Delete the minimal item
	 *
	 */
	public void deleteMin()
	{
		if (this.empty()) 
			return;
	
		HeapNode min_node = this.findMin().node; // find minimal node
		BinomialHeap heap2 = new BinomialHeap(); // new empty bin heap
		heap2.last = min_node.child;
		
		
		
		HeapNode curr = min_node.child;
		// iterate over roots of new heap and delete parent pointers
		do {
			if (curr != null) {
				curr.parent = null;
				curr = curr.next;
			}
		}
		while ((curr != min_node.child) && (curr != null));
		
		// delete pointer from min_node
		min_node.next = null;
		
		// meld with new heap
		this.meld(heap2); 
		
		// size of main heap
		this.size -= 1; 
		
		// size of new heap
		int n = min_node.rank; 
		heap2.size = (int) (Math.pow(2, n)-1); // size = (2^n) - 1
		
		// update this.min
		// update this.last(if min_node was last)
	}

	/**
	 * 
	 * Return the minimal HeapItem
	 *
	 */
	public HeapItem findMin()
	{
		return this.min.item;
	} 

	/**
	 * 
	 * pre: 0 < diff < item.key
	 * 
	 * Decrease the key of item by diff and fix the heap. 
	 * 
	 */
	public void decreaseKey(HeapItem item, int diff) 
	{   
		if (item == null) 
			return;
		
		item.key -= diff;
		HeapNode node = item.node; 
		siftup(node);
	}

	/**
	 * 
	 * Delete the item from the heap.
	 *
	 */
	public void delete(HeapItem item) 
	{
		if (item == null) 
			return;
		
		int neg_inf = Integer.MIN_VALUE;
		this.decreaseKey(item, neg_inf); // make key of item negative infinity, so it moves to the top of the tree.
		this.deleteMin(); // delete it
	}

	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(BinomialHeap heap2)
	{
		return; // should be replaced by student code   		
	}

	/**
	 * 
	 * Return the number of elements in the heap
	 *   
	 */
	public int size()
	{
		return this.size; 
	}

	/**
	 * 
	 * The method returns true if and only if the heap
	 * is empty.
	 *   
	 */
	public boolean empty()
	{
		if (this.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees()
	{
		if (this.empty()) // if heap is empty it has 0 trees
			return 0;
	
		int cnt = 0;
		HeapNode curr = this.last;
		
		// iterate over roots of all trees and count, until the end of the cycle
		do {  
			curr = curr.next;
			cnt++;
		}
		while (curr != this.last);
		
		return cnt;
	}
	

	/**
	 * Class implementing a node in a Binomial Heap.
	 *  
	 */
	public class HeapNode{
		public HeapItem item;
		public HeapNode child;
		public HeapNode next;
		public HeapNode parent;
		public int rank;
		
		public HeapNode() {
		}
		
		public HeapNode(HeapItem item) {
			this.item = item;
			item.node = this;
			this.rank = 0;
		}
	}

	/**
	 * Class implementing an item in a Binomial Heap.
	 *  
	 */
	public class HeapItem{
		public HeapNode node;
		public int key;
		public String info;
		
		public HeapItem() {
		}
		
		public HeapItem(int key) {
			this.key = key;
		}
	}

	
	/*
	 * 
	 * 
	 * printing heap
	 * 
	 * 
	 */
	public void printHeap() {
		if (empty()) {
			System.out.println("Heap is empty");
			return;
		}
		System.out.println("Binomial Heap:");
		HeapNode currentRoot = last;
		HeapNode stopNode = last.next; // Stop condition for circular list of roots
		boolean stop = false;

		do {
			System.out.println("Root: " + currentRoot.item.key);
			printTree(currentRoot, 0, currentRoot); // Print the tree rooted at current root
			currentRoot = currentRoot.next;
			if (currentRoot == stopNode) {
				stop = true; // We've visited all roots
			}
		} while (!stop);
	}

	public void printTree(HeapNode node, int depth, HeapNode initialRoot) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append("  "); // Adjust spacing for depth
		}
		sb.append(node.item.key).append(" [").append(node.rank).append("]");

		System.out.print(sb.toString());

		if (node.next != node.parent && node.next != null && node.next != initialRoot) {
			printTree(node.next, depth, initialRoot); // Print sibling recursively until we reach the initial root
		}
		
		
		
		if (node.child != null) {
			System.out.println();
			printTree(node.child, depth + 1, node.child); // Print child recursively
		}

		
	}
	
	
	
	public void printTree3(HeapNode root) {
	    if (root == null) {
	        return;
	    }

	    int level = 0;
	    int spacesNeeded = (int) Math.pow(2, size(root) + 1) - 1; // Calculate spaces needed for balanced display

	    printTreeHelper(root, level, spacesNeeded);
	}
	
	private static void printTreeHelper(HeapNode node, int level, int spacesNeeded) {
	    if (node == null) {
	        return;
	    }

	    // Print spaces for indentation
	    // ... (same as before)

	    // Print node information
	    System.out.print(node.item.key + " (" + node.rank + ")" + (node.parent == null ? "" : " (parent: " + node.parent.item.key + ")"));

	    System.out.println();

	    // Loop through siblings and recursively print them with adjusted level
	    HeapNode current = node.child;
	    while (current != node) { // Loop until reaching the starting node again
	        printTreeHelper(current, level + 1, spacesNeeded / 2);
	        current = current.next;
	    }
	}

	private static int size(HeapNode root) {
	    if (root == null) {
	        return 0;
	    }

	    int count = 1; // Count the root node
	    HeapNode current = root.child;
	    while (current != root) { // Loop until reaching the starting node again
	        count++;
	        current = current.next;
	    }

	    return count;
	}
}
