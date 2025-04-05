/*
 * Leetcode : 743. Network Delay Time

*/

import java.util.*;

class Solution {

	class Pair implements Comparable<Pair> {
		int node, weight;

		Pair() {
		}

		Pair(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		public int compareTo(Pair t2) {
			return Integer.compare(this.weight, t2.weight);
		}
	}

	public int networkDelayTime(int[][] times, int n, int k) {

		List<List<Pair>> adj = new ArrayList<>();

		for (int i = 0; i <= n; i++)
			adj.add(new ArrayList<>());

		for (int[] edge : times)
			adj.get(edge[0]).add(new Pair(edge[1], edge[2]));

		int[] distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[k] = 0;

		Queue<Pair> queue = new PriorityQueue<>();

		queue.offer(new Pair(k, 0));

		while (!queue.isEmpty()) {
			Pair currPair = queue.poll();

			for (Pair neighbour : adj.get(currPair.node)) {
				int newWeight = neighbour.weight + currPair.weight;
				if (newWeight < distance[neighbour.node]) {
					distance[neighbour.node] = newWeight;
					queue.offer(new Pair(neighbour.node, newWeight));
				}
			}

		}

		int time = Integer.MIN_VALUE;

		for (int i = 1; i <= n; i++) {
			time = Math.max(time, distance[i]);
		}

		return time == Integer.MAX_VALUE ? -1 : time;

	}
}