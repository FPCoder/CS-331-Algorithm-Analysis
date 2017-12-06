package alg;


public class Edge implements Comparable<Edge> {
	private double weight;
	private int start;
	private int end;
	
	Edge() {
		start = -1;
		weight = Double.POSITIVE_INFINITY;
		end = -1;
	}
	Edge(int e1, double w, int e2) {
		start = e1;
		weight = w;
		end = e2;
	}
	
	public double getWeight() { return weight; }
	public int getStart() { return start; }
	public int getEnd() { return end; }

	@Override
	public int compareTo(Edge o) {
		return (int) (weight - o.weight);
	}
	
	public boolean equals(Edge e) {
		int i = e.start, j = e.end;
		
		if (start == i && end == j) {
			return true;
		}
		else if (start == j && end == i) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "(" + start + ", " + weight + ", "+ end + ")";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
