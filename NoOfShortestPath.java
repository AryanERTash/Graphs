import java.util.*;

/*
 * Leetcode :https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
 */
class Solution {
	class Pair implements Comparable<Pair> {
		int city;
		long weight;

		Pair(int city, long weight) {
			this.city = city;
			this.weight = weight;
		}

		public int compareTo(Pair t2) {
			return Long.compare(this.weight, t2.weight);
		}
	}

	public int countPaths(int n, int[][] roads) {
		List<List<Pair>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());

		for (int[] road : roads) {
			int city1 = road[0], city2 = road[1], weight = road[2];
			adj.get(city1).add(new Pair(city2, weight));
			adj.get(city2).add(new Pair(city1, weight));
		}

		PriorityQueue<Pair> queue = new PriorityQueue<>();

		long[] distance = new long[n];
		long[] paths = new long[n];

		Arrays.fill(distance, Long.MAX_VALUE);

		distance[0] = 0;
		paths[0] = 1;
		queue.offer(new Pair(0, 0));

		int MOD = 1_000_000_007;

		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			int u = p.city;
			long d = p.weight;

			if (d > distance[u])
				continue;

			for (Pair nextPair : adj.get(u)) {
				int v = nextPair.city;
				long newWeight = d + nextPair.weight;

				if (newWeight < distance[v]) {
					distance[v] = newWeight;
					paths[v] = paths[u];
					queue.offer(new Pair(v, newWeight));

				} else if (newWeight == distance[v]) {

					paths[v] = (paths[v] + paths[u]) % MOD;
				}
			}
		}
		return (int) (paths[n - 1] % MOD);
	}
}
