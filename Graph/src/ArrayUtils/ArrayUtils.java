package ArrayUtils;

public class ArrayUtils {

	public long maxProductSubarray (long[] array) {
		if (array == null || array.length == 0)
			return 1;
		long result = array[0];
		long max = array[0];
		long min = array[0];
		for (int i = 1; i < array.length; i++) {
			long tmp1 = max * array[i];
			long tmp2 = min * array[i];
			max = Math.max(array[i], Math.max(tmp1, tmp2));
			min = Math.min(array[i], Math.min(tmp1, tmp2));
			result = Math.max(result, max);
		}
		return result;
	}
	
	public static void main(String[] args) {
		long[] array = {1, 2, -3, (long) 0.5, (long) -0.3, 5};
		System.out.println(new ArrayUtils().maxProductSubarray(array));
	}
}
