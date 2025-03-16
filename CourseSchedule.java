/*
 * leetcode: 207. Course Schedule
 * 
 * The topo sort is similar to solving problem of dependence of work
 */

import java.util.*;

class Solution {
	public boolean canFinish(int numCourses, int[][] prerequisites) {

		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < numCourses; i++) {
			adj.add(new ArrayList<Integer>());
		}

		int[] indegree = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			int from = prerequisites[i][1];
			int to = prerequisites[i][0];

			indegree[to]++;

			adj.get(from).add(to);
		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0)
				queue.add(i);
		}

		int count = 0;

		while (!queue.isEmpty()) {
			int node = queue.poll();

			count++;

			for (int nextNode : adj.get(node)) {
				if (--indegree[nextNode] == 0) {

					queue.add(nextNode);

				}
			}
		}

		return count == numCourses;

	}
}