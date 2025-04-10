
/*
 * Leetcode: 947. Most Stones Removed with Same Row or Column
 * 
*/
import java.util.*;;

class DisjointSet {
	int parent[], size[];

	DisjointSet(int n) {
		parent = new int[n];
		size = new int[n];
		Arrays.fill(size, 1);

		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	int findUltimateParent(int node) {
		if (parent[node] == node)
			return node;

		int f = findUltimateParent(parent[node]);

		return parent[node] = f;
	}

	public void union(int u, int v) {
		int pu = findUltimateParent(u);
		int pv = findUltimateParent(v);

		if (pu == pv)
			return;

		if (size[pu] > size[pv]) {
			parent[pv] = pu;
			size[pu] += size[pv];
		} else {
			parent[pu] = pv;
			size[pv] += size[pu];
		}
	}
}

class Solution {
	public int removeStones(int[][] stones) {

		int n = 0, m = 0;
		for (int[] p : stones) {
			n = Math.max(n, p[0] + 1);
			m = Math.max(m, p[1] + 1);
		}

		DisjointSet djSet = new DisjointSet(n + m);

		for (int[] p : stones) {
			djSet.union(p[0], p[1] + n);
		}

		int componentCount = 0;
		for (int i = 0; i < djSet.size.length; i++) {

			if (djSet.size[i] > 1 && djSet.parent[i] == i)
				componentCount++;

		}

		return stones.length - componentCount;

	}
}