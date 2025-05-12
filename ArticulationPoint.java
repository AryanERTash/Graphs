/*
 * GFG: https://www.geeksforgeeks.org/problems/articulation-point-1
 */

import java.util.*;

class Solution {
	public int timer = 0;

	public void dfs(int node, int parent, int[] times, int[] lowTime, ArrayList<ArrayList<Integer>> adj,
			HashSet<Integer> ap) {

		times[node] = lowTime[node] = ++timer;

		int child = 0;

		for (int nextNode : adj.get(node)) {
			if (nextNode == parent)
				continue;
			if (times[nextNode] == 0) {

				dfs(nextNode, node, times, lowTime, adj, ap);

				lowTime[node] = Math.min(lowTime[node], lowTime[nextNode]);

				if (parent != -1 && lowTime[nextNode] >= times[node]) {

					ap.add(node);

				}
				child++;

			} else {
				lowTime[node] = Math.min(lowTime[node], times[nextNode]);
			}
		}

		if (child > 1 && parent == -1)
			ap.add(node);

	}

	// Function to return Breadth First Traversal of given graph.
	public ArrayList<Integer> articulationPoints(int V,
			ArrayList<ArrayList<Integer>> adj) {

		HashSet<Integer> ap = new HashSet<>();

		int times[] = new int[V], lowTime[] = new int[V];
		timer = 0;

		for (int i = 0; i < V; i++) {

			if (times[i] == 0)
				dfs(i, -1, times, lowTime, adj, ap);

		}

		ArrayList<Integer> aplist = new ArrayList<>();

		if (ap.isEmpty()) {
			aplist.add(-1);
		} else {
			aplist.addAll(ap);
			Collections.sort(aplist);

		}

		return aplist;

	}
}