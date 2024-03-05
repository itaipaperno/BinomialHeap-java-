
/**
 * 
 */
public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BinomialHeap.HeapItem item1 = new BinomialHeap().new HeapItem(1, "");
		BinomialHeap.HeapItem item2 = new BinomialHeap().new HeapItem(2, "");
		BinomialHeap.HeapNode root1 = new BinomialHeap().new HeapNode(item1);
		BinomialHeap.HeapNode root2 = new BinomialHeap().new HeapNode(item2);
		BinomialHeap.link(root1, root2);
		BinomialHeap.HeapItem item3 = new BinomialHeap().new HeapItem(3, "");
		BinomialHeap.HeapItem item4 = new BinomialHeap().new HeapItem(4, "");
		BinomialHeap.HeapNode root3 = new BinomialHeap().new HeapNode(item3);
		BinomialHeap.HeapNode root4 = new BinomialHeap().new HeapNode(item4);
		BinomialHeap.link(root3, root4);
		BinomialHeap.link(root1, root3);
		
		BinomialHeap.HeapItem item5 = new BinomialHeap().new HeapItem(5, "");
		BinomialHeap.HeapItem item6 = new BinomialHeap().new HeapItem(6, "");
		BinomialHeap.HeapNode root5 = new BinomialHeap().new HeapNode(item5);
		BinomialHeap.HeapNode root6 = new BinomialHeap().new HeapNode(item6);
		BinomialHeap.link(root5, root6);
		BinomialHeap.HeapItem item7 = new BinomialHeap().new HeapItem(7, "");
		BinomialHeap.HeapItem item8 = new BinomialHeap().new HeapItem(8, "");
		BinomialHeap.HeapNode root7 = new BinomialHeap().new HeapNode(item7);
		BinomialHeap.HeapNode root8 = new BinomialHeap().new HeapNode(item8);
		BinomialHeap.link(root7, root8);
		BinomialHeap.link(root7, root5);
		
		BinomialHeap.link(root5, root1);
		
		
		
		
		
		BinomialHeap heap1 = new BinomialHeap();
		heap1.last = root1;
		heap1.min = root1;
		heap1.size = 8;
		
		PrintHeap.printHeap(heap1, true);
		
		System.out.println(heap1.numTrees());
		

	}


}
