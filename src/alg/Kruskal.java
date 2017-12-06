package alg;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class Kruskal {
	private final static Random R = new Random();
	private static int numNodes = 0;
	
	public Set<Edge> kruskal(Graph g) {
		List<Edge> sortedEdges = sort(g.getAllEdges());
		HashSet<Edge> result = new HashSet<Edge>();
		DisjointSet sets = new DisjointSet(numNodes);
		Iterator<Edge> i = sortedEdges.iterator();
		Edge e;
		int u = 0, v = 0;
		
		do {
			e = (Edge) i.next();
			u = e.getStart();
			v = e.getEnd();
			u = sets.find(u);
			v = sets.find(v);
			if (u != v) {
				sets.merge(u, v);
				result.add(e);
			}
		} while (i.hasNext() && result.size() < g.size()-1);
		
		return result;
	}
	
	public List<Edge> sort(Collection<Edge> edges) {
		List<Edge> sorted = new LinkedList<Edge>();
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		
		q.addAll(edges); // put in queue to order from least to greatest
		sorted.addAll(q); // add all into a LinkedList
		
		return sorted;
	}
	
	public static Edge genRandEdge() {
		return new Edge(R.nextInt(numNodes), Math.floor(R.nextDouble() * 100), R.nextInt(numNodes));
	}
	public static void setNumNodes(int n) {
		numNodes = n;
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		HashSet<Edge> list;
		Kruskal k = new Kruskal();
		final int n = 100;

		setNumNodes(n);
		for (int i = 0; i < n; ++i) {
			g.addEdge(genRandEdge());
		}
		
		list = (HashSet<Edge>) k.kruskal(g);
		int i = 0;
		for (Edge e : list) {
			System.out.println(i + ": " + e.toString());
			i++;
		}
	}

}
