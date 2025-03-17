
/*
 * GFG: Shortest Path in Undirected
 *  https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1
 */
import java.util.*;

class Solution {
	// Function to find the shortest path from a source node to all other nodes
	public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {

		int[] distance = new int[adj.size()];

		for (int i = 0; i < distance.length; i++)
			distance[i] = -1;

		Queue<Integer> queue = new LinkedList<>();

		distance[src] = 0;

		queue.offer(src);	// saving both node and distance in queue instead of a object
		queue.offer(0);

		while (!queue.isEmpty()) {
			int node = queue.poll();
			int dist = queue.poll();

			for (int nextNode : adj.get(node)) {
				if (distance[nextNode] == -1) {		// bfs ensure the distance is minimum
					distance[nextNode] = dist+1;
					queue.offer(nextNode);
					queue.offer(dist + 1);
				}
			}
		}

		return distance;

	}
}
