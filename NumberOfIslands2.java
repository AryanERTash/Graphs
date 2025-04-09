
class DisjointSet {
	int parent[], rank[];

	DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;

		}
	}

	int findUltimateParent(int node) {
		if (parent[node] == node)
			return node;

		return parent[node] = findUltimateParent(parent[node]); // Path compression
	}

	public void union(int u, int v) {
		int pu = findUltimateParent(u);
		int pv = findUltimateParent(v);

		if (pu == pv)
			return; // Already in the same set

		if (rank[pu] == rank[pv]) {
			parent[pu] = pv;
			rank[pv]++; // Increase rank of the new root
		} else if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		} else {
			parent[pv] = pu;
		}
	}

}

class Solution {

	public static final int[][] DIRECTIONS = new int[][] {
			{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
	};

	public static int[] numOfIslandsII(int n, int m, int[][] q) {
		boolean visited[][] = new boolean[n][m];
		DisjointSet djSet = new DisjointSet(m * n);

		int numIslandsArr[] = new int[q.length];

		int k = 0;
		int numIslands = 0;

		for (int[] operation : q) {
			int i = operation[0];
			int j = operation[1];

			if (!visited[i][j]) {

				numIslands++;
				visited[i][j] = true;

				for (int VEC[] : DIRECTIONS) {

					int ni = i + VEC[0];
					int nj = j + VEC[1];

					int currIndex = i * m + j;
					int neighbourIndex = ni * m + nj;

					if (ni >= 0 && nj >= 0 && ni < n && nj < m && visited[ni][nj]
							&& djSet.findUltimateParent(currIndex) != djSet.findUltimateParent(neighbourIndex)) {

						numIslands--;
						djSet.union(neighbourIndex, currIndex);

					}

				}

				numIslandsArr[k++] = numIslands;

			}
		}

		return numIslandsArr;

	}
}