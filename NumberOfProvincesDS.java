
/*
 * Leetcode : 547. Number of Provinces
*/

import java.util.*;

class DisjointSet {
	int[] rank, parent;

	DisjointSet(int V) {

		this.rank = new int[V + 1];
		this.parent = new int[V + 1]; // for 1 based indexing

		for (int i = 0; i <= V; i++)
			parent[i] = i;

	}

	public int findUltimateParent(int node) {
		if (node == parent[node])
			return node;

		return parent[node] = findUltimateParent(parent[node]);
	}

	public void union(int u, int v) {
		// a undirected edge between u and v

		int pu = findUltimateParent(u);
		int pv = findUltimateParent(v);

		if (pu == pv)
			return; // in same set
		if (rank[pu] == rank[pv]) {
			parent[pv] = pu;
			rank[pu]++;
		} else if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		} else {
			parent[pv] = pu;
		}
	}
}

class Solution {
	public int findCircleNum(int[][] isConnected) {


		int V = isConnected.length;
		DisjointSet djSet = new DisjointSet(V);


		for (int u = 0; u < V; u++) {
			for (int v = 0; v < V; v++) {

				if(isConnected[u][v]==1) djSet.union(u, v);			
				
			}
		}




		int totalProvince = 0;

		for (int i = 0; i < V; i++) {
			

			if(djSet.parent[i] == i) totalProvince++;
		}



		return totalProvince;

	}
}