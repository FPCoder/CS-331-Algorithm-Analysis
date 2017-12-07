package alg;

import java.util.HashSet;
import java.util.Set;

public class Prim {
	
	public static Set<Edge> prim(Graph g) {
		int n = g.size();
		HashSet<Edge> result = new HashSet<Edge>();
		int k = 0;
		int[] nearest = new int[n];
		double[] minDist = new double[n];
		
		for (int i = 1; i < n; ++i) {
			nearest[i] = 0;
			minDist[i] = g.weight(i, 0);
		}
		
		for (int count = 0; count < n; ++count) {
			double min = Double.POSITIVE_INFINITY;
			
			for (int i = 1; i < n; ++i) {
				if (0 <= minDist[i] && minDist[i] < min) {
					min = minDist[i];
					k = i;
				}
			}
			
			Edge e = g.getEdge(nearest[k], k);
			if (e != null) {
				result.add(e);
			}
			minDist[k] = -1;
			
			for (int i = 1; i < n; ++i) {
				if (g.weight(i, k) < minDist[i]) {
					minDist[i] = g.weight(i, k);
					nearest[i] = k;
				}
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
		int n = 5000;
		//Graph g = Graph.testGraph1();
		Graph g = new Graph(n);
		g.genRandEdges(n, 0.8);
		
		HashSet<Edge> set;
		set = (HashSet<Edge>) Prim.prim(g);
		long endtime = System.currentTimeMillis();
		System.out.println("Total time(ms): " + (endtime - starttime));
		/*
		int i = 0;
		for (Edge e : set) {
			System.out.println(i + ": " + e.toString());
			i++;
		}*/
	}

}
