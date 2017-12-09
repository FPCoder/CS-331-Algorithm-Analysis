package alg;

import java.util.Random;

public class DynamicMakingChange {

	static double[][] makeChange(int n, int[] denom) {
		double[][] change = new double[denom.length][n];
		
		for (int i = 0; i < denom.length; ++i) {
			change[i][0] = 0;
		}
		
		for (int i = 0; i < denom.length; ++i) {
			for (int j = 1; j < n; ++j) {
				if (i == 0 && j < denom[i]) {
					change[i][j] = Double.POSITIVE_INFINITY;
				}
				else if (i == 0) {
					change[i][j] = 1.0 + change[0][j-denom[0]];
				}
				else if (j < denom[i]) {
					change[i][j] = change[i-1][j];
				}
				else {
					change[i][j] = Math.min(change[i-1][j], 1.0+change[i][j-denom[i]]);
				}
			}
		}
		
		return change;
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		long start = 0, end = 0;
		int[] arr = { 25, 10, 5, 1 };
		int n = 0;
		
		for (int x = 0; x < 100; ++x) {
			start = System.currentTimeMillis();
			n = r.nextInt(99);
			double[][] result = makeChange(n, arr);
			end = System.currentTimeMillis();
			System.out.println((end - start));
	
			/*System.out.print("    ");
			for (int i = 0; i < n; ++i) {
				System.out.print(String.format("%5s", i));
			}
			System.out.println();
		
			for (int i = 0; i < result.length; ++i) {
				System.out.print(i + ": ");
				for (int j = 0; j < result[i].length; ++j) {
					if (result[i][j] != Double.POSITIVE_INFINITY) {
						System.out.print(String.format("%5s", result[i][j]));
					}
					else {
						System.out.print(String.format("%5s", "___"));
					}
				}
				System.out.println();
			}*/
		}
	}

}
