package alg;

public class MakingChange {
	
	private static int findNextCoin(int[] denom, int n, int s) {
		int i = -1;
		
		for (int j = 0; j < denom.length; ++j) {
			if (s + denom[j] <= n) {
				return j;
			}
		}
		
		return i;
	}
	
	/**
	 * Finds the quantity of each denomination required to return the change given.
	 * @param n - change to give in cents
	 * @param denom - each given denomination
	 * @return int[] of the quantity to give for each denomination
	 */
	public static int[] makeChange(int n, int[] denom) {
		int s = 0;
		int[] change = new int[denom.length];
		
		while (s < n) {
			int i = findNextCoin(denom, n, s);
			
			if (i == -1) {
				return null; // return NO SOLUTION
			}
			
			change[i] = change[i] + 1;
			s = s + denom[i];
		}
		
		return change;
	}

	public static void main(String[] args) {
		int[] arr = { 100, 25, 10, 5, 1 };
		int n = 476;
		
		int[] result = makeChange(n, arr);
		
		for (int i : result) {
			System.out.print(i + " ");
		}
	}

}
