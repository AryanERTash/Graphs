/*
 * Strong connected component is a componenet is a graph such that if there is a path from u to v then also the path from v to u exists
 * https://www.naukri.com/code360/problems/count-strongly-connected-components-kosaraju-s-algorithm_1171151
 */

import java.util.*;

class GraphUtils {
	public static ArrayList<ArrayList<Integer>> makeAdj(int v, ArrayList<ArrayList<Integer>> edges) {

		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

		for (int index = 0; index < v; index++)
			adj.add(new ArrayList<>());

		for (ArrayList<Integer> edge : edges)
			adj.get(edge.get(0)).add(edge.get(1));

		return adj;

	}

	public static ArrayList<ArrayList<Integer>> reverseGraph(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<ArrayList<Integer>> reversedAdj = new ArrayList<>();

		for (int i = 0; i < adj.size(); i++)
			reversedAdj.add(new ArrayList<>());

		for (int u = 0; u < adj.size(); u++)
			for (int v : adj.get(u))
				reversedAdj.get(v).add(u);

		return reversedAdj;

	}

	public static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {

		visited[node] = true;

		for (int next : adj.get(node))
			if (!visited[next])
				dfs(next, adj, visited, stack);

		if (stack != null)
			stack.push(node);

	}
}

class Solution {
	public static int stronglyConnectedComponents(int v, ArrayList<ArrayList<Integer>> edges) {

		Stack<Integer> stack = new Stack<>();
		ArrayList<ArrayList<Integer>> adj = GraphUtils.makeAdj(v, edges);
		boolean visited[] = new boolean[v];

		for (int i = 0; i < v; i++)

			if (!visited[i])
				GraphUtils.dfs(i, adj, visited, stack);

		ArrayList<ArrayList<Integer>> reversedAdj = GraphUtils.reverseGraph(adj);

		int sccCnt = 0;

		Arrays.fill(visited, false);

		while (!stack.isEmpty()) {
			int node = stack.pop();

			if (!visited[node]) {
				sccCnt++;
				GraphUtils.dfs(node, reversedAdj, visited, null);
			}
		}

		return sccCnt;

	}
}