import java.util.*;

class VertexWeightPair {
	int node, weight;

	public VertexWeightPair(int node, int w) {
		this.node = node;
		this.weight = w;
	}
}

class Solution {
	public static int[] shortestPathInDAG(int n, int m, int[][] edges) {
		ArrayList<ArrayList<VertexWeightPair>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}

		int[] inDegree = new int[n];

		for (int i = 0; i < edges.length; i++) {
			int[] tpl = edges[i];
			int u = tpl[0], v = tpl[1], w = tpl[2];
			adj.get(u).add(new VertexWeightPair(v, w));
			inDegree[v]++;
		}

		Queue<Integer> searchBuffer = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			if (inDegree[i] == 0) {
				searchBuffer.add(i);
			}
		}

		Queue<Integer> topo = new LinkedList<>();
		while (!searchBuffer.isEmpty()) {
			int node = searchBuffer.poll();
			topo.add(node);
			for (VertexWeightPair vwPair : adj.get(node)) {
				if (--inDegree[vwPair.node] == 0) {
					searchBuffer.add(vwPair.node);
				}
			}
		}

		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0;

		while (!topo.isEmpty()) {
			int node = topo.poll();
			if (distance[node] != Integer.MAX_VALUE)
				for (VertexWeightPair vwPair : adj.get(node))
					distance[vwPair.node] = Math.min(distance[vwPair.node], distance[node] + vwPair.weight);

		}

		for (int i = 0; i < n; i++) {
			if (distance[i] == Integer.MAX_VALUE)
				distance[i] = -1;

		}

		return distance;
	}
}
