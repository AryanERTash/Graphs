/*
 * Bellman ford: detect negative cycle in directed graph
 * iterate for V-1 time on each edge and relax
 * 
 * 
 * if at Vth iteration there is any change then it implies a cycle with negative value
 * 
 * 
 * GFG: https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
 */

import java.util.*;

class Solution {
	static int[] bellmanFord(int V, int[][] edges, int src) {
		int[] distance = new int[V];
		Arrays.fill(distance, (int) 1e8);
		distance[src] = 0;

		boolean change = false;

		for (int i = 0; i < V - 1; i++) {
			change = false;

			for (int[] edge : edges) {

				int from = edge[0];
				int to = edge[1];
				int weight = edge[2];

				int totalWeight = weight + distance[from];

				if (distance[from] != (int) 1e8 && totalWeight < distance[to]) {
					distance[to] = totalWeight;

					change = true;
				}

			}
			if (!change)
				return distance;

		}

		for (int[] edge : edges) {

			int from = edge[0];
			int to = edge[1];
			int weight = edge[2];

			int totalWeight = weight + distance[from];

			if (distance[from] != (int) 1e8 && totalWeight < distance[to]) {
				return new int[] { -1 };
			}

		}

		return distance;

	}
}
