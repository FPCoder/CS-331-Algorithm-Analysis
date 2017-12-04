package alg;

public class DynamicMakingChange {

	static int[][] makeChange(int n, int[] denom) {
		int[][] change = new int[denom.length][n];
		
		for (int i = 0; i < n; ++i) {
			change[i][0] = 0;
		}
		
		for (int i = 0; i < denom.length; ++i) {
			for (int j = 0; j < n; ++j) {
				if (i == 0 && j < denom[i]) {
					change[i][j] = Integer.MAX_VALUE;
				}
				else if (i == 0) {
					change[i][j] = 1 + change[0][j-denom[0]];
				}
				else if (j < denom[i]) {
					change[i][j] = change[i-1][j];
				}
				else {
					change[i][j] = Math.min(change[i-1][j], 1+change[i][j-denom[i]]);
				}
			}
		}
		
		return change;
	}
	
	public static void main(String[] args) {
		int[] arr = { 100, 25, 10, 5, 1 };
		int n = 476;
		
		int[][] result = makeChange(n, arr);
		
		for (int i = 0; i < arr.length; ++i) {
			
		}
	}

}
