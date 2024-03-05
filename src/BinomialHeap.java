

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
	 * merges two heaps into one without linking same ranks
	 * 
	 */
	private void merge(BinomialHeap heap2) {
		this.size += heap2.size;
		
		// if one one the heaps is empty
		if (heap2.empty()) {
			return;
		}
		if (this.empty()) {
			this.last = heap2.last;
			this.min = heap2.min;
			this.size = heap2.size;
			return;
		}	
		
		// update minimum
		if (this.min.item.key > heap2.min.item.key)
			this.min = heap2.min;
		
		HeapNode p1 = this.last.next;
		HeapNode p2 = heap2.last.next;
		this.last.next = null;
		heap2.last.next = null;
		HeapNode newhead = null;
		HeapNode connector = null;
		
		// merge root lists in order of increasing rank
		do {
			if (p1.rank <= p2.rank) {
				if (newhead == null) {
					newhead = connector = p1;
				}
				else {
					connector.next = p1;
					connector = p1;
				}
				p1 = p1.next;
			}
			else {
				if (newhead == null){
					newhead = connector = p2;
				}
				else {
					connector.next = p2;
					connector = p2;
				}
				p2 = p2.next;
			}
		}
		while((p1 != null) && (p2 != null));
		
		// add the remaining nodes to the root list
		if (p1 == null) {
			connector.next = p2;
			this.last = heap2.last;
			this.last.next = newhead;
		}
		else {
			connector.next = p1;
			this.last.next = newhead;
		}
	}
	
	/**
	 * 
	 * links same trees with same rank in heap
	 * 
	 */
	private void union() {
		HeapNode curr = this.last.next;
		HeapNode prev = null;
		this.last.next = null;
		HeapNode next = curr.next;
		HeapNode newhead = curr;
		
		while (next != null) {
			// 3 trees with same rank or no match at all
			if ((curr.rank != next.rank) || ((next.next != null) && (next.next.rank == curr.rank))) {
				prev = curr;
				curr = next;
			}
			else {
				HeapNode tmpnext = next.next;
				HeapNode linkedroot = link(curr, next);
				linkedroot.next = tmpnext;
				curr = linkedroot;
				next = tmpnext;

				if (prev != null) 
					prev.next = linkedroot;
				else 
					newhead = linkedroot;
				
			}
		}
		curr.next = newhead;
		this.last = curr;
		
		this.min = this.findMin().node;
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
		// create new item, node, heap
		HeapItem new_item = new HeapItem(key, info);
		HeapNode new_node = new HeapNode(new_item);
		BinomialHeap new_heap = new BinomialHeap();
		new_heap.last = new_node;
		new_heap.min = new_node;
		new_heap.size = 1;
		
		// meld new heap with this
		this.meld(new_heap);
		
		return new_item; 
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
	
		HeapNode min_node = this.min; // find minimal node
		BinomialHeap heap2 = new BinomialHeap(); // new empty bin heap
		
		/*
		 * min_node has children
		 */
		if (min_node.child != null) {
			heap2.last = min_node.child;
			HeapNode curr = min_node.child;
			heap2.min = curr;
			
			// iterate over roots of new heap and delete parent pointers and find new heap2 min
			do {
				curr.parent = null;
				curr = curr.next;
					
				if (curr.item.key < heap2.min.item.key)
					heap2.min = curr;	
			   }
			while ((curr != min_node.child) && (curr != null));
			
			// size of new heap
			int n = min_node.rank; 
			heap2.size = (int) (Math.pow(2, n)-1); // size = (2^n) - 1
		}
		
		/*
		 * min_node isnt lone tree
		 */
		if (min_node.next != min_node){
			// size of main heap
			this.size -= (heap2.size + 1); 
			
			// find new min of this and min_node.prev
			HeapNode curr = min_node.next;
			this.min = curr;
		
			while (curr.next != min_node) {
				if (curr.item.key < this.min.item.key) {
					this.min = curr;
				}
				curr = curr.next;
			}
			
			// min_node was last
			if(this.last == min_node) { 
				this.last = curr;
			}
			curr.next = min_node.next;
		}
		
		/*
		 * min_node is lone tree
		 */
		else { 
			this.last = null;
			this.min = null;
			this.size = 0;
		}
		
		// delete pointer from min_node
		min_node.child = null;
		min_node.next = null;
				
		// meld with new heap
		this.meld(heap2); 
	}

	/**
	 * 
	 * Return the minimal HeapItem
	 *
	 */
	public HeapItem findMin()
	{
		HeapNode curr = this.last;
		this.min = curr;
		
		while (curr.next != this.last) {
			if (curr.item.key < this.min.item.key) {
				this.min = curr;
				curr = curr.next;
			}
		}
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
		this.min = this.findMin().node; // update new min
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
		this.merge(heap2);
		this.union();
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
			this.next = this;
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
		
		public HeapItem(int key, String info) {
			this.key = key;
			this.info = info;
		}
	}
}

	
