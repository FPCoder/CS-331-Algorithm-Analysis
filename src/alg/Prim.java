package alg;

import java.util.HashSet;
import java.util.Set;

public class Prim {
	
	public Set<Edge> prim(Graph g) {
		HashSet<Edge> result = new HashSet<Edge>();
		int n = g.size();
		int[] nearest = new int[n];
		double[] minDist = new double[n];
		
		for (int i = 0; i < n; ++i) {
			nearest[i] = 0;
			minDist[i] = g.weight(i, -1);
		}
		
		for (int count = 0; count < n-1; ++count) {
			double min = Double.POSITIVE_INFINITY;
			
			for (int i = 1; i < n; ++i) {
				if (0 <= minDist[i] && minDist[i] < min) {
					min = minDist[j];
					//TODO what is "j" when initialized? when is it updated?
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
