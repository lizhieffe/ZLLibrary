package IntegerUtils;

public class IntegerUtils {

	public static int findGCD(int a, int b) {
		a = Math.abs(a);
		b = Math.abs(b);
		if (a < b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		if (b == 0)
			return -1;
		if (a % b == 0)
			return b;
		else
			return findGCD(b, a - b);
	}
}
