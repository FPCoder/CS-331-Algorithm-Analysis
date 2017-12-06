package alg;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Prim {
	private final static Random R = new Random();
	private static int n;
	
	public Set<Edge> prim(Graph g) {
		HashSet<Edge> result = new HashSet<Edge>();
		int k = 0;
		int[] nearest = new int[n];
		double[] minDist = new double[n];
		
		for (int i = 1; i < n; ++i) {
			nearest[i] = 0;
			minDist[i] = g.weight(i, -1);
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
	public static void setn(int x) { n = x; }
	public static Edge genRandEdge() {
		return new Edge(R.nextInt(n), Math.floor(R.nextDouble() * 100), R.nextInt(n));
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		Prim p = new Prim();
		HashSet<Edge> set;
		final int n = 10;
		final double chance = 0.5;

		Prim.setn(n);
		Graph.genRandEdges(n, chance);
		
		set = (HashSet<Edge>) p.prim(g);
		int i = 0;
		for (Edge e : set) {
			System.out.println(i + ": " + e.toString());
			i++;
		}
	}

}
