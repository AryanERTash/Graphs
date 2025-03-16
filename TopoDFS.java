/*
 * Topological sort using DFS
 * 
 * for directed acyclic graph (DAG)
 */

import java.util.*;

class Solution {

	public static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
		visited[node] = true;

		for (int next : adj.get(node)) {
			if (!visited[next])
				dfs(next, adj, visited, stack);
		}

		stack.push(node);
	}

	// Function to return list containing vertices in Topological order.
	static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
		boolean visited[] = new boolean[adj.size()];

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < adj.size(); i++) {
			if (!visited[i])
				dfs(i, adj, visited, stack);

		}

		ArrayList<Integer> result = new ArrayList<>();

		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}

		return result;
	}
}