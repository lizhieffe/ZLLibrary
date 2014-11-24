package ArrayUtils;

import java.util.Collections;
import java.util.PriorityQueue;

import Interface.IKthLargestElementFinder;

public class KthLargestElementFinderByMaxHeapImpl implements IKthLargestElementFinder {

	@Override
	public Object find(Object[] array, int k) {
		if (array == null || k <= 0 || k > array.length)
			return null;
		PriorityQueue<Object> maxHeap = new PriorityQueue<Object>(k, Collections.reverseOrder());
		for (int i = 0; i < array.length; i++) {
			if (maxHeap.size() == k 
					&& ((Comparable)maxHeap.peek()).compareTo(array[i]) == 1) {
				maxHeap.poll();
				maxHeap.add(array[i]);
			}
			else if (maxHeap.size() < k)
				maxHeap.add(array[i]);
		}
		return maxHeap.peek();
	}

	
}
