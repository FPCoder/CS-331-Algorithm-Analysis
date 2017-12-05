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
	
	@Override
	public String toString() {
		return "(" + new String() + start + ", " + weight + ", "+ end + ")";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
