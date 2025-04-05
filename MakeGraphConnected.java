/*
 * Leetcode: 1319. Number of Operations to Make Network Connected

*/

class DisjointSet {
	int parent[], rank[];

	DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];

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

}

class Solution {
	public int makeConnected(int n, int[][] connections) {

		DisjointSet djSet = new DisjointSet(n);

		int extraEdges = 0;

		for (int[] connection : connections) {

			int u = connection[0];
			int v = connection[1];

			if (djSet.findUltimateParent(u) != djSet.findUltimateParent(v)) {
				djSet.union(u, v);
			} else {
				extraEdges++;
			}
		}

		int components = 0;

		for (int i = 0; i < n; i++) {

			if (djSet.parent[i] == i)
				components++;
		}


		return extraEdges>= components-1 ? components - 1 : -1;

	}
}