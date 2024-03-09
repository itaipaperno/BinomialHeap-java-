

/**
 * 
 */
public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		testdeletemin();
		
		
	}

	public static BinomialHeap createheap(int[] keylist) {
		BinomialHeap heap = new BinomialHeap();

		for (int key : keylist) {
            heap.insert(key, String.valueOf(key));
        }
        return heap;
	}

	public static BinomialHeap addtoheap(int[] keylist, BinomialHeap heap) {
		for (int key : keylist) {
            heap.insert(key, String.valueOf(key));
        }
        return heap;
	}

	public static void testinsert(){

		BinomialHeap heap1 = createheap(new int[]{35, 20, 31, 40, 43, 45, 50, 15, 55, 60, 65, 70, 75, 80, 85, 14});
		HeapGraph.draw(heap1);
		System.out.println("check insertion of minimum on heap with multiple trees: ");
		if (heap1.min.item.key != 14){
			System.out.println("min bad");
		}
		else{
			System.out.println("min good");
		}

		heap1.insert(13, "13");
		System.out.println("check insertion of minimum on heap with 1 trees: ");
		if (heap1.min.item.key != 13){
			System.out.println("min bad");
		}
		else{
			System.out.println("min good");
		}

		BinomialHeap heap2 = createheap(new int[]{1,1,1,1,1,2,1,1,1});
		HeapGraph.draw(heap2);
	}

	public static void testdecresekey(){
		BinomialHeap heap1 = createheap(new int[]{35, 20, 31});

		addtoheap(new int[]{2,3,4,5}, heap1);
		HeapGraph.draw(heap1);
		//heap1.decreaseKey(node.child.item, 34);
		System.out.println(heap1.last.next.item.key);
		heap1.decreaseKey(heap1.last.next.next.child.item, 2);
		System.out.println("min: " + heap1.min.item.key);
		
	}

	public static void testmeld(){
		BinomialHeap heap1 = createheap(new int[]{});
		BinomialHeap heap2 = createheap(new int[]{});
		//HeapGraph.draw(heap2);
		heap2.meld(heap1);
		//HeapGraph.draw(heap2);
		//System.out.println("min: " + heap2.min.item.key);
		//if (heap2.min == heap2.last)
		//	System.out.println("min good");
		//if (heap2.size == 13)
		//System.out.println("size good");

	}

	public static void testdeletemin(){
		
		//min is last
		BinomialHeap heap1 = createheap(new int[]{1,2,3,4,5,6,7});
		heap1.deleteMin();
		HeapGraph.draw(heap1);
		if (heap1.min.item.key != 2){
			System.out.println("min bad");
		}
		else{
			System.out.println("min good");
		}
		
		//min isnt last
		BinomialHeap heap2 = createheap(new int[]{6,2,3,4,5,1,7});
		heap2.deleteMin();
		HeapGraph.draw(heap2);
		if (heap2.min.item.key != 2){
			System.out.println("min bad");
		}
		else{
			System.out.println("min good");
		}
		
		//min is lone tree
		BinomialHeap heap2 = createheap(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
		heap2.deleteMin();
		HeapGraph.draw(heap2);
		if (heap2.min.item.key != 2){
			System.out.println("min bad");
		}
		else{
			System.out.println("min good");
		}
		

		//min is lone tree with 1 node
		BinomialHeap heap2 = createheap(new int[]{1});
		heap2.deleteMin();
		if (heap2.empty()){
			System.out.println("good");
		}
		else{
			System.out.println("bad");
		}
		

		//empty tree
		BinomialHeap heap2 = createheap(new int[]{});
		heap2.deleteMin();
		if (heap2.empty()){
			System.out.println("good");
		}
		else{
			System.out.println("bad");
		}
		

		//two identical min keys
		BinomialHeap heap2 = createheap(new int[]{1,2,3,4,5,1,7});
		heap2.deleteMin();
		HeapGraph.draw(heap2);
		System.out.println(heap2.min.item.key);
		if (heap2.min.item.key != 1){
			System.out.println("min bad");
		}
		else{
			System.out.println("min good");
		}
	}

}
