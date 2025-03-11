

/*
 * GFG : Undirected Graph Cycle
 * https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
 */
import java.util.*;

class Solution {

    static class ParentNodePair {

        public int parent = -1;
        public int node = -1;

        public ParentNodePair() {
        }

        public ParentNodePair(int node, int parent) {
            this.parent = parent;
            this.node = node;
        }
    }

    public static boolean helper(ArrayList<ArrayList<Integer>> adj, int node, boolean[] visited, Queue<ParentNodePair> queue) {
        if (visited[node]) {
            return false;
        }

        queue.offer(new ParentNodePair(node, Integer.MIN_VALUE));
        visited[node] = true;

        while (!queue.isEmpty()) {
            ParentNodePair npPair = queue.poll();

            for (int nextNode : adj.get(npPair.node)) {
                if (nextNode == npPair.parent) {
                    continue;
                }
                if (visited[nextNode]) {
                    return true;
                }

                queue.offer(new ParentNodePair(nextNode, npPair.node));
                visited[nextNode] = true;
            }
        }

        return false;
    }

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {

        Queue<ParentNodePair> queue = new LinkedList<>(); // queue is passed so you dont have to allocate queue every time
        boolean[] visited = new boolean[adj.size()];

        for (int i = 0; i < adj.size(); i++) {

            if (!visited[i]
                    && helper(adj, i, visited, queue)) {
                return true;
            }
        }

        return false;
    }
}
