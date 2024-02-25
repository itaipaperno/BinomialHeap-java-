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

	/*
	 * : x.size == y.size
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
		return; // should be replaced by student code

	}

	/**
	 * 
	 * Return the minimal HeapItem
	 *
	 */
	public HeapItem findMin()
	{
		return null; // should be replaced by student code
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
		return; // should be replaced by student code
	}

	/**
	 * 
	 * Delete the item from the heap.
	 *
	 */
	public void delete(HeapItem item) 
	{    
		return; // should be replaced by student code
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
		return 42; // should be replaced by student code
	}

	/**
	 * 
	 * The method returns true if and only if the heap
	 * is empty.
	 *   
	 */
	public boolean empty()
	{
		return false; // should be replaced by student code
	}

	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees()
	{
		return 0; // should be replaced by student code
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

}
