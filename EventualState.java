/*
 * leetcode: 802. Find Eventual Safe States
*/

import java.util.*;

class Solution {

	public static boolean dfs(int node, int[][] graph, int[] marker) {

		marker[node] = 2;

		for (int nextNode : graph[node]) {

			if ((marker[nextNode] == 0 && dfs(nextNode, graph, marker)) ||
					marker[nextNode] == 1 || marker[nextNode] == 2) {

				marker[node] = 1;
				return true;

			}

		}

		marker[node] = 3;

		return false;

	}

	public List<Integer> eventualSafeNodes(int[][] graph) {

		int marker[] = new int[graph.length];

		// 0: not yet visited
		// 1: currently visited but has cycle and not in path
		// 2: currently in path being searched
		// 3: visited and has a terminal node not in path

		// however this can aslo be done via 3 values 0 1 2 or with three arrays

		for (int i = 0; i < graph.length; i++) {

			if (marker[i] == 0)
				dfs(i, graph, marker);
		}

		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < marker.length; i++) {
			if (marker[i] == 3)
				result.add(i);
		}

		return result;
	}
}