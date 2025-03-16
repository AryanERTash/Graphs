
/*
 * GFG: Kahn Algorithm, topo sort via bfs (kahn algo)
 * https://www.geeksforgeeks.org/problems/topological-sort/1
 */



import java.util.*;


class Solution {
	// Function to return list containing vertices in Topological order.
	static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
		/*
		 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of 
		 * vertices such that for every directed edge u -> v, vertex u comes before v in 
		 * the ordering.
		 * 
		 * if there is a edge u->v then it implies work v require work u to complete first
		 * v is followed by u
		 * 
		 * a pair of task can not be performed if toposort cant be found(acyclic or not)
		 *
		 */
		
		
		int [] inEdgeCount = new int[adj.size()];

		// no need for visited array

		for (int i = 0; i < adj.size(); i++) {
			for(int n: adj.get(i)) {
				inEdgeCount[n]++;
			}
		}

		Queue<Integer> queue = new ArrayDeque<>();


		for (int i = 0; i < inEdgeCount.length; i++) {
			if(inEdgeCount[i] == 0) {

				queue.offer(i);

			}
		}

		ArrayList<Integer> topo = new ArrayList<>();

		while(!queue.isEmpty()) {
			int top = queue.poll();

			topo.add(top);


			for(int next: adj.get(top)) {
				if(--inEdgeCount[next] == 0) {
					queue.add(next);
				}
			}
		}


		return topo;
	}
}