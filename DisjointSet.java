class DisjointSetByRank {
	int parent[], rank[];

	DisjointSetByRank(int n) {
		parent = new int[n + 1];
		rank = new int[n + 1];

		for (int i = 0; i < n; i++) {
			parent[i] = i;

		}
	}

	int findUltimateParent(int node) {
		if (parent[node] == node)
			return node;

		return parent[node] = findUltimateParent(parent[node]); // Path compression
	}

	public void union(int u, int v) {
		int pu = findUltimateParent(u);
		int pv = findUltimateParent(v);

		if (pu == pv)
			return; // Already in the same set

		if (rank[pu] == rank[pv]) {
			parent[pu] = pv;
			rank[pv]++; // Increase rank of the new root
		} else if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		} else {
			parent[pv] = pu;
		}
	}

	public static void main(String[] args) {
		DisjointSetByRank ds = new DisjointSetByRank(7);

		System.out.println(ds.findUltimateParent(3));

		ds.union(1, 2);
		ds.union(2, 3);
		System.out.println(ds.findUltimateParent(1));
		System.out.println(ds.findUltimateParent(3));

		ds.union(4, 5);
		ds.union(6, 7);
		ds.union(5, 6);
		ds.union(3, 7);

		System.out.println(ds.findUltimateParent(1) == ds.findUltimateParent(7));
		System.out.println(ds.findUltimateParent(5));
	}
}
