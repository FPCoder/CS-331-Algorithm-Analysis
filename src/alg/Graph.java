package alg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Graph {
	private HashMap<Integer, HashMap<Integer, Edge>> edges;
	private final Random R = new Random();
	private int verts = 0;
	
	Graph(int n) {
		edges = new HashMap<Integer, HashMap<Integer, Edge>>();
		verts = n;
	}
	
	/**
	 * get the number of verticies in the graph
	 * @return int of vertices in graph
	 */
	public int size() {
		return verts;
	}
	
	public void addEdge(Edge e) {
		int i = e.getStart();
		int j = e.getEnd();
		
		if (!exists(e)) {
			if (edges.get(i) == null) {
				edges.put(i, new HashMap<Integer, Edge>());
			}
			edges.get(i).put(j, e); // add the new edge
		}
	}
	
	public boolean exists(Edge e) {
		int st = e.getStart();
		int ed = e.getEnd();
		if (edges.get(st) != null && edges.get(st).get(ed) != null) {
			return true;
		}
		else if (edges.get(ed) != null && edges.get(ed).get(st) != null) {
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
			else if (edges.get(j) != null && edges.get(j).get(i) != null) {
				return edges.get(j).get(i).getWeight();
			}
			else {
				return Double.POSITIVE_INFINITY;
			}
		}
	}
	public Edge getEdge(int i, int j) {
		if (edges.get(i) != null && edges.get(i).get(j) != null) {
			return edges.get(i).get(j);
		}
		else if (edges.get(j) != null) {
			return edges.get(j).get(i); // returns null if there is no edge
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
	public void genRandEdges(int v, double density) {
		double w = 0;
		for (int i = 0; i < v; ++i) {
			for (int j = 0; j < v; ++j) {
				w = R.nextDouble();
				if (i != j && w <= density) {
					addEdge(new Edge(i, w, j));
				}
			}
		}
	}
	
	public static Graph testGraph1() {
		Graph g = new Graph(5);
		ArrayList<Edge> edges = new ArrayList<Edge>();
		g.addEdge(new Edge(0, 1, 1));
		g.addEdge(new Edge(0, 1, 2));
		g.addEdge(new Edge(0, 1, 3));
		g.addEdge(new Edge(1, 0, 3));
		g.addEdge(new Edge(2, 1, 4));
		
		for (Edge e : edges) {
			g.addEdge(e);
		}
		
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = Graph.testGraph1();
		//Graph g = new Graph();
		//g.genRandEdges(3, 1.0);

		int i = 0;
		for (Edge e : g.getAllEdges()) {
			System.out.println(i + ": " + e.toString());
			i++;
		}
	}
}
