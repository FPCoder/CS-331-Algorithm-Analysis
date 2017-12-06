package alg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Graph {
	private static HashMap<Integer, HashMap<Integer, Edge>> edges;
	private final static Random R = new Random();
	private static int vertCount = 0;
	
	Graph() {
		edges = new HashMap<Integer, HashMap<Integer, Edge>>();
	}
	
	/**
	 * get the number of verticies in the graph
	 * @return int of vertices in graph
	 */
	public int size() {
		return vertCount;
	}
	
	public static void addEdge(Edge e) {
		int i = e.getStart();
		int j = e.getEnd();
		
		if (!exists(e)) {
			if (edges.get(i) == null) {
				edges.put(i, new HashMap<Integer, Edge>());
				vertCount++;
			}
			edges.get(i).put(j, e);
			
			if (edges.get(j) == null) {
				edges.put(j, new HashMap<Integer, Edge>());
				vertCount++;
			}
			edges.get(j).put(i, e);
		}
	}
	
	public static boolean exists(Edge e) {
		int st = e.getStart();
		int end = e.getEnd();
		
		if (edges.get(st) != null && edges.get(st).get(end) != null) {
			return true;
		}
		else if (edges.get(end) != null && edges.get(end).get(st) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Collection<Integer> getVerts() {
		return edges.keySet();
	}
	public Collection<Edge> getEdgesAdj(int i) {
		return edges.get(i).values();
	}
	public Collection<Edge> getAllEdges() {
		ArrayList<Edge> ret = new ArrayList<Edge>();
		
		for (HashMap<Integer, Edge> m : edges.values()) {
			for (Edge e : m.values()) {
				ret.add(e);
			}
		}
		
		return ret;
	}
	
	/**
	 * find the edge with the given vertex values and return the weight.
	 * @param i left vertex
	 * @param j right vertex
	 * @return returns the weight of the Edge, or zero if invalid range or not found
	 */
	public double weight(int i, int j) {
		if (j < 0) {
			return 0;
		}
		else {
			if (edges.get(i) != null && edges.get(i).get(j) != null) {
				return edges.get(i).get(j).getWeight();
			}
			else {
				return 0;
			}
		}
	}
	public Edge getEdge(int i, int j) {
		if (edges.get(i) != null) {
			return edges.get(i).get(j);
		}
		else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param v number of vertices
	 * @param density (range 0.0-1.0) chance of creating an edge
	 */
	public static void genRandEdges(int v, double density) {
		for (int i = 0; i < v; ++i) {
			for (int j = 0; j < v; ++j) {
				if (i != j && R.nextDouble() < density) {
					addEdge(new Edge(i, R.nextDouble(), j));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Graph g = new Graph();
		
		/*g.addEdge(new Edge(0, 50, 1));
		g.addEdge(new Edge(1, 51, 0));
		g.addEdge(new Edge(0, 25, 2));
		g.addEdge(new Edge(0, 10, 3));
		g.addEdge(new Edge(2, 100, 3));
		g.addEdge(new Edge(1, 10, 3));*/
		g.genRandEdges(5, 0.1);
		System.out.println(g.getAllEdges());
	}
}
