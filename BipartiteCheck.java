/*
 * Leetcode: 785. Is Graph Bipartite?
 */

class Solution {

	private static boolean dfsHelper(int node, int[][] graph, int[] visited) {

		int nextColor = visited[node] ^ 0b11; // toogle 1 to 2 and 2 to 1

		for (int nextNode : graph[node]) {

			if (visited[nextNode] == 0) {
				visited[nextNode] = nextColor;

				if (!dfsHelper(nextNode, graph, visited))
					return false;

			} else if (visited[nextNode] != nextColor) {
				return false;
			}

		}

		return true;
	}

	public boolean isBipartite(int[][] graph) {
		/*
		 * A bipartite graph is a graph whose vertices can be partitoned in 2 sets (S1
		 * and S2)
		 * such that in within the set there is no edge(edge only occur between a node
		 * from set1 and set2)
		 * 
		 * S1 intersection s2 = phi && s1 union sr = V
		 * 
		 * This problem can be mapped to the question whether we can colour vertex of
		 * graph
		 * such that no two adjacent vertex has same colour. (by using only 2 colours)
		 * 
		 * A linear graph with no cycle can always be partitoned in two set 
		 * such that they are bipartite
		 * A graph with cycle can only be bipartite if number of vertext/node in cycle are even.
		 * 
		 * in a complete bipartite graph every node frm s1 has a node in s2 to each node
		 * total edges = n(s1) * n(s2)
		 */

		int[] visited = new int[graph.length]; // 0 for not visited , 1 for color 1 and 2 for colour 2

		for (int i = 0; i < graph.length; i++) {
			if (visited[i] == 0) {
				visited[i] = 1; // initial colour of 1

				if (!dfsHelper(i, graph, visited))
					return false;

			}
		}

		return true;

	}
}