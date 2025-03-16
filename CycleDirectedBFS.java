/*
 * Cycle detection in directed graph using BFS
*/

// find toposort if elements in toposort < total vertices then there exists as cycle

import java.util.*;

class Solution {
	// Function to detect cycle in a directed graph.
	public boolean isCyclic(ArrayList<ArrayList<Integer>> adj) {

		int[] inComing = new int[adj.size()];

		for (ArrayList<Integer> n : adj) {

			for (int node : n) {
				inComing[node]++;
			}

		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < inComing.length; i++) {
			if (inComing[i] == 0) {
				queue.offer(i);
			}
		}


		int count = 0;
		while(!queue.isEmpty()) {
			count++;

			int top = queue.poll();


			for(int next : adj.get(top)) {
				if(--inComing[next] == 0){
					queue.offer(next);
				}
			}


		}

		return count!=adj.size();

	}
}