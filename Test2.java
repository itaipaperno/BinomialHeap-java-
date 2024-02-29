import java.nio.channels.NonReadableChannelException;

public class Test2 {

	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		BinomialHeap.HeapItem item1 = new BinomialHeap().new HeapItem(1);
		BinomialHeap.HeapItem item2 = new BinomialHeap().new HeapItem(2);
		BinomialHeap.HeapItem item3 = new BinomialHeap().new HeapItem(3);
		BinomialHeap.HeapItem item4 = new BinomialHeap().new HeapItem(4);
		
		BinomialHeap.HeapNode root1 = new BinomialHeap().new HeapNode(item1);
		BinomialHeap.HeapNode root2 = new BinomialHeap().new HeapNode(item2);
		BinomialHeap.HeapNode root3 = new BinomialHeap().new HeapNode(item3);
		BinomialHeap.HeapNode root4 = new BinomialHeap().new HeapNode(item4);
		
		
		BinomialHeap.HeapNode combinedRoot12 = BinomialHeap.link(root1, root2);

		BinomialHeap.HeapNode combinedRoot34 = BinomialHeap.link(root3, root4);
		
		BinomialHeap.HeapNode combinedRoot1234 = BinomialHeap.link(combinedRoot12, combinedRoot34);
		
		
		BinomialHeap heap1 = new BinomialHeap();
		
//		heap1.printTree(combinedRoot12, 1, combinedRoot12);
//		heap1.printTree(combinedRoot34, 1, combinedRoot34);
		
//		heap1.printTree(combinedRoot1234, 2, combinedRoot1234);
		
//		printTree2(combinedRoot12, 1);
		
//		printTree2(combinedRoot1234, 2, combinedRoot1234);
		heap1.printTree3(combinedRoot1234);
	}
	
	private static void printTree2(BinomialHeap.HeapNode node, int level,BinomialHeap.HeapNode startingnode) {
	    for (int i = 0; i < level; i++) {
	        System.out.print("  "); // Indentation
	    }
	    System.out.println("Node: " + node.item.key);

	    // Recursively print children
	    BinomialHeap.HeapNode child = node.child;
	    while ((child != null) & (child != startingnode)) {
	        printTree2(child, level + 1, child);
	        child = child.child;
	    }
	}
}
