package ArrayUtils;

import java.util.Arrays;

import Interface.IKthLargestElementFinder;

/*
 * n*log(n) time complexity
 */

public class KthLargestElementFinderBySortingImpl implements IKthLargestElementFinder {

	/*
	 * k starts from 1
	 */
	@Override
	public Object find(Object[] array, int k) {
		if (array == null || k <= 0 || k > array.length)
			return null;
		Arrays.sort(array);
		return array[k - 1];
	}
}
