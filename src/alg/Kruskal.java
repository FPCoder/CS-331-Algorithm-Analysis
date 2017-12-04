package alg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Kruskal {
	private final Random R = new Random();
	private int numNodes = 0;
	
	public Set<Edge> kruskal(Graph g) {
		int n = g.size();
		List<Edge> sortedEdges = sort(g.getAllEdges());
		HashSet<Edge> result = new HashSet<Edge>();
		DisjointSet sets = new DisjointSet(n);
		
		do {
			
		} while (result.size() < n-1);
		
		return result;
	}
	
	public HashSet<Integer> find(DisjointSet sets, int u) {
		HashSet<Integer> ret = null;
		
		//TODO
		
		return ret;
	}
	
	public List<Edge> sort(Collection<Edge> edges) {
		List<Edge> sorted = new LinkedList<Edge>();
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		
		q.addAll(edges); // put in queue to order from least to greatest
		sorted.addAll(q); // add all into a LinkedList
		
		return sorted;
	}
	
	public Edge genRandEdge() {
		return new Edge(numNodes, R.nextDouble(), numNodes);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
