import java.nio.channels.NonReadableChannelException;

public class Test {
	BinomialHeap B = new BinomialHeap();
	
	public static void main(String[] args) {
		testLink();
	}
	
	public static void testLink() {
        // 1. Setup: Create sample trees
		BinomialHeap.HeapItem item1 = new BinomialHeap().new HeapItem(5);
		BinomialHeap.HeapItem item2 = new BinomialHeap().new HeapItem(10);
		
		BinomialHeap.HeapNode root1 = new BinomialHeap().new HeapNode(item1);
		BinomialHeap.HeapNode root2 = new BinomialHeap().new HeapNode(item2);
	
		
        // 2. Execute: Call the link function
		BinomialHeap.HeapNode combinedRoot = BinomialHeap.link(root1, root2);

        // 3. Assertions: 
        printError(5 == combinedRoot.item.key, "root key should be 5 but instead is " + combinedRoot.item.key); // Smaller key should be at combined root
        printError(root1 == combinedRoot.child, "root 1 (10) should become child, but insted child is " + combinedRoot.child.item.key ); // Node 2 should become the child

        // (Optional) Print for visual verification during testing
        printTree(combinedRoot, 0);
    }
	
	public static void printHeap(BinomialHeap heap) {
	    BinomialHeap.HeapNode current = heap.last;
	    while (current != null) {
	        System.out.println("Binomial Tree (Rank " + current.rank + "):");
	        printTree(current, 1); 
	        current = current.next;
	    }
	}

	private static void printTree(BinomialHeap.HeapNode node, int level) {
	    for (int i = 0; i < level; i++) {
	        System.out.print("  "); // Indentation
	    }
	    System.out.println("Node: " + node.item.key);

	    // Recursively print children
	    BinomialHeap.HeapNode child = node.child;
	    while (child != null) {
	        printTree(child, level + 1);
	        child = child.next;
	    }
	}
	
	public static void printError(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException("[ERROR] " + message);
		}
	}
}
