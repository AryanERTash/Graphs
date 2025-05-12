/*
 * Using tarjan algorithm to find the bridge in a graph
 * Leetcode: https://leetcode.com/problems/critical-connections-in-a-network
 */

import java.util.*;

class Solution {
	public List<List<Integer>> adjacencyFromEdges(int n, List<List<Integer>> connections) {
		List<List<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++) {

			adj.add(new ArrayList<>());
		}

		for (List<Integer> edge : connections) {

			int u = edge.get(0), v = edge.get(1);

			adj.get(u).add(v);
			adj.get(v).add(u);

		}

		return adj;

	}

	public static int timer = 0;

	public static void dfs(int node, int parent, List<List<Integer>> adj, int[] times, int[] lowTime,
			List<List<Integer>> bridges) {

		times[node] = lowTime[node] = ++timer;

		for (int nextNode : adj.get(node)) {

			if (nextNode == parent)
				continue;

			if (times[nextNode] == 0) {
				// not visited
				dfs(nextNode, node, adj, times, lowTime, bridges);

				lowTime[node] = Math.min(lowTime[node], lowTime[nextNode]);

				if (lowTime[nextNode] > times[node])
					bridges.add(Arrays.asList(node, nextNode));

				lowTime[node] = Math.min(lowTime[node], lowTime[nextNode]);

			} else {
				lowTime[node] = Math.min(lowTime[node], lowTime[nextNode]);

			}

		}

	}

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

		List<List<Integer>> adj = adjacencyFromEdges(n, connections);

		int times[] = new int[n];

		int lowTime[] = new int[n];

		List<List<Integer>> bridges = new ArrayList<>();

		timer = 0;

		dfs(0, -1, adj, times, lowTime, bridges);

		return bridges;

	}
}