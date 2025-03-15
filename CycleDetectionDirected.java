/*


github: @AryanERTash
* GFG: Cycle in a Directed Grap
* https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
* 
*/

import java.util.*;

class Solution {
	private static boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] visited) {
		visited[node] = 2; // visited and in path

		for (int nextNode : adj.get(node)) {
			if (visited[nextNode] == 0) {

				if (dfs(nextNode, adj, visited)) {
					return true;
				}

			} else if (visited[nextNode] == 2) {
				return true; // in the path we encounter a node again
			}

			// if a node is already visited it implies there is no cycle following it hence there
			// is no use to traverse it
		}

		visited[node] = 1; // visited

		return false;
	}

	// Function to detect cycle in a directed graph.
	public boolean isCyclic(ArrayList<ArrayList<Integer>> adj) {
		// code here

		int visited[] = new int[adj.size()];
		// 0 means not visited
		// 1 means visited but not currently exploring in path
		// 2 means visited and in path that is currently being explored

		//or we can use 2 array one for visited and one for visited in current path

		for (int i = 0; i < adj.size(); i++) {

			if (visited[i] == 0) {
				if (dfs(i, adj, visited))
					return true;
			}
		}

		return false;
	}
}