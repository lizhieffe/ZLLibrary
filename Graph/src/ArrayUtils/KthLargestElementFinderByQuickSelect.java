package ArrayUtils;

import Interface.IKthLargestElementFinder;

/*
 * o(n) average
 * o(n^2) worst case
 */
public class KthLargestElementFinderByQuickSelect 
		implements IKthLargestElementFinder {

	@Override
	public Object find(Object[] array, int k) {
		if (array == null || k <= 0 || k > array.length)
			return null;
		return find(array, k - 1, 0, array.length - 1);
	}
	
	private Object find(Object[] array, int k, int beg, int end) {
		if (end == beg && k == 0)
			return array[beg];
		Object pivot = array[end];
		int mid = beg;
		for (int i = beg; i < end; i++) {
			if (((Comparable)array[i]).compareTo(pivot) == -1) {
				Object tmp = array[i];
				array[i] = array[mid];
				array[mid] = tmp;
				mid++;
			}
		}
		Object tmp = array[end];
		array[end] = array[mid];
		array[mid] = tmp;
		if (mid - beg == k)
			return array[mid];
		else if (mid - beg > k)
			return find(array, k, beg, mid - 1);
		else
			return find(array, k - mid + beg, mid, end);
	}
}
