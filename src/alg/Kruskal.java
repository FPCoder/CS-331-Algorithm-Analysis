package alg;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Kruskal {
	
	public static Set<Edge> kruskal(Graph g) {
		List<Edge> sortedEdges = sort(g.getAllEdges());
		HashSet<Edge> result = new HashSet<Edge>();
		DisjointSet sets = new DisjointSet(g.size());
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
		} while (result.size() < g.size()-1);
		
		return result;
	}
	
	public static List<Edge> sort(Collection<Edge> edges) {
		List<Edge> sorted = new LinkedList<Edge>();
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		
		q.addAll(edges); // put in queue to order from least to greatest
		sorted.addAll(q); // add all into a LinkedList
		
		return sorted;
	}
	

	public static void main(String[] args) {
		long starttime = 0;
		long endtime = 0;
		HashSet<Edge> list;
		//Graph g = Graph.testGraph1();
		
		starttime = System.currentTimeMillis();
		Graph g = new Graph();
		g.genRandEdges(5000, 0.3);
		list = (HashSet<Edge>) Kruskal.kruskal(g);
		endtime =  System.currentTimeMillis();
		System.out.println("Total time(ms): " + (endtime - starttime));
		/*
		int i = 0;
		for (Edge e : list) {
			System.out.println(i + ": " + e.toString());
			i++;
		}*/
		
	}

}
