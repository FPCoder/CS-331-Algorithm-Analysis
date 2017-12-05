package alg;

import java.util.HashSet;
import java.util.Set;

public class Prim {
	
	public Set<Edge> prim(Graph g) {
		HashSet<Edge> result = new HashSet<Edge>();
		int n = g.size();
		int k = 0;
		int[] nearest = new int[n];
		double[] minDist = new double[n];
		
		for (int i = 0; i < n; ++i) {
			nearest[i] = 0;
			minDist[i] = g.weight(i, -1);
		}
		
		for (int count = 0; count < n-1; ++count) {
			double min = Double.POSITIVE_INFINITY;
			
			for (int i = 1; i <= n; ++i) {
				if (0 <= minDist[i] && minDist[i] < min) {
					min = minDist[i];
					k = i;
				}
			}
			
			result.add(g.getEdge(nearest[k], k));
			minDist[k] = -1;
			
			for (int i = 1; i <= n; ++i) {
				if (g.weight(i, k) < minDist[i]) {
					minDist[i] = g.weight(i, k);
					nearest[i] = k;
				}
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		Prim p = new Prim();
		HashSet<Edge> set;

		g.addEdge(new Edge(0, 50, 1));
		g.addEdge(new Edge(1, 51, 0));
		g.addEdge(new Edge(0, 25, 2));
		g.addEdge(new Edge(0, 10, 3));
		g.addEdge(new Edge(2, 100, 3));
		g.addEdge(new Edge(1, 10, 3));
		
		set = (HashSet<Edge>) p.prim(g);
		
		for (Edge e : set) {
			System.out.println(e);
		}
		//TODO: fix graph.size()
	}

}
