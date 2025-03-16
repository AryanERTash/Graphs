import java.util.*;

class Solution {
	public List<Integer> eventualSafeNodes(int[][] graph) {
		List<List<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < graph.length; i++) {
			adj.add(new ArrayList<>());
		}

		int indegree[] = new int[graph.length];

		for (int from = 0; from < graph.length; from++) {
			indegree[from] += graph[from].length;
			for (int to : graph[from]) {
				adj.get(to).add(from);
			}
		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0)
				queue.add(i);
		}

		List<Integer> result = new LinkedList<>();

		while (!queue.isEmpty()) {
			int node = queue.poll();
			result.add(node);

			for (int next : adj.get(node)) {
				if (--indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}

		Collections.sort(result);

		return result;
	}
}