import java.util.*;

class Solution {
	static class Pair implements Comparable<Pair> {
		int node, weight;

		Pair() {
		}

		Pair(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		public int compareTo(Pair p) {
			return Integer.compare(weight, p.weight);
		}
	}

	static int spanningTree(int V, int E, List<List<int[]>> adj) {

		int minWeight = 0;
		boolean visited[] = new boolean[V];

		Queue<Pair> queue = new PriorityQueue<>();

		queue.offer(new Pair(0, 0));

		while (queue.isEmpty() == false) {

			Pair currPair = queue.poll();

			if (visited[currPair.node])
				continue;

			visited[currPair.node] = true;

			minWeight += currPair.weight;

			for (int[] next : adj.get(currPair.node)) {
				int nextNode = next[0];
				int nextWeight = next[1];

				if (!visited[nextNode]) {
					queue.offer(new Pair(nextNode, nextWeight));
				}
			}

		}

		return minWeight;

	}

	public static void main(String[] args) {

	}
}