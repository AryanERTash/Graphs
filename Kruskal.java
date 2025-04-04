import java.util.*;

class Solution {
	static class Tuple implements Comparable<Tuple> {
		int weight, u, v;

		Tuple() {
		}

		Tuple(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		public int compareTo(Tuple t) {
			return Integer.compare(this.weight, t.weight);
		}
	}

	static class DisjointSet {
		int[] rank, parent;

		DisjointSet(int V) {

			this.rank = new int[V + 1];
			this.parent = new int[V + 1]; // for 1 based indexing

			for (int i = 0; i <= V; i++)
				parent[i] = i;

		}

		public int findUltimateParent(int node) {
			if (node == parent[node])
				return node;

			return parent[node] = findUltimateParent(parent[node]);
		}

		public void union(int u, int v) {
			// a undirected edge between u and v

			int pu = findUltimateParent(u);
			int pv = findUltimateParent(v);

			if (pu == pv)
				return; // in same set
			if (rank[pu] == rank[pv]) {
				parent[pv] = pu;
				rank[pu]++;
			} else if (rank[pu] < rank[pv]) {
				parent[pu] = pv;
			} else {
				parent[pv] = pu;
			}
		}
	}

	static int spanningTree(int V, int E, List<List<int[]>> adj) {

		Queue<Tuple> queue = new PriorityQueue<>();

		for (int i = 0; i < V; i++) {

			for (int[] tpl : adj.get(i)) {
				queue.offer(new Tuple(i, tpl[0], tpl[1]));
			}
		}

		DisjointSet djSet = new DisjointSet(V);

		int weight = 0;

		while (!queue.isEmpty()) {
			Tuple tpl = queue.poll();

			if (djSet.findUltimateParent(tpl.u) != djSet.findUltimateParent(tpl.v)) {
				weight += tpl.weight;
				djSet.union(tpl.u, tpl.v);
			}
		}

		return weight;

	}
}