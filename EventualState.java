/*
 * leetcode: 802. Find Eventual Safe States
 * 
 * 
 * The code used DFS to find safe nodes
*/

import java.util.*;

class Solution {

	public static boolean dfs(int node, int[][] graph, int[] marker) {

		marker[node] = 1;

		for (int nextNode : graph[node]) {

			if ((marker[nextNode] == 0 && dfs(nextNode, graph, marker)) 
					|| marker[nextNode] == 1) {
				return true;
			}
			// else (2) node is safe node
		}

		marker[node] = 2;

		return false;

	}

	public List<Integer> eventualSafeNodes(int[][] graph) {

		int marker[] = new int[graph.length];

		// 0: not yet visited
		// 1: currently in path
		// 2: is a safe node

		// however this can aslo be done via three arrays(visited, path, safenodes)

		for (int i = 0; i < graph.length; i++) {

			if (marker[i] == 0)
				dfs(i, graph, marker);
		}

		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < marker.length; i++) {
			if (marker[i] == 2)
				result.add(i);
		}

		return result;
	}
}