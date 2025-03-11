/*
 * a follow up of cycle detection via dfs
 */


import java.util.*;

class Solution {

    public static boolean helper(ArrayList<ArrayList<Integer>> adj, int node, int parent, boolean[] visited) {

        visited[node] = true;

        for (int nextNode : adj.get(node)) {
            if (nextNode == parent) {
                continue; 
				
            } else if (visited[nextNode]) {
                return true; // cycle
            } else if (helper(adj, nextNode, node, visited)) {
                return true;
            }
        }

        return false;
    }

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];

        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i] && helper(adj, i, -1, visited)) { // Start DFS on unvisited nodes
                return true;
            }
        }

        return false;
    }
}
