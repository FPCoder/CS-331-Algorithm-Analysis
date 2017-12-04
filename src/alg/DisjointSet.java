package alg;

public class DisjointSet {
	int[] sets;
	int[] rank;
	int n;
	
	DisjointSet(int size) {
		n = size;
		sets = new int[n];
		rank = new int[n];
		
		for (int i = 0; i < n; ++i) {
			sets[i] = i;
		}
	}
	
	public int find(int x) {
		int i = 0;
		int r = x;
		
		while (sets[r] != r) {
			r = sets[r];
		}
		i = x;
		while (i != r) {
			int j = sets[i];
			sets[i] = r;
			i = j;
		}
		
		return r;
	}
	
	public void merge(int a, int b) {
		if (rank[a] == rank[b]) {
			rank[a] = rank[a] + 1;
			sets[b] = a;
		}
		else {
			if (rank[a] > rank[b]) {
				sets[b] = a;
			}
			else {
				sets[a] = b;
			}
		}
	}
}
