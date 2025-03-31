/*
 * GFG: https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
 * Instead of floyd warshall we can use dijkstra on each node giving complexity EVlogV
 */

class Solution {
	public void shortestDistance(int[][] mat) {

		int m = mat.length; // number of npodes

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {

				if (mat[i][j] == -1)
					mat[i][j] = Integer.MAX_VALUE;

			}
		}

		for (int interim = 0; interim < m; interim++) { // interim must be outermost
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < m; j++) {

					if (i == interim || j == interim)
						continue;

					int p1 = mat[i][interim];
					int p2 = mat[interim][j];

					if (p1 == Integer.MAX_VALUE || p2 == Integer.MAX_VALUE)
						continue;

					int d = p1 + p2;
					if (d < mat[i][j])
						mat[i][j] = d;

				}

			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {

				if (mat[i][j] == Integer.MAX_VALUE)
					mat[i][j] = -1;
			}
		}

		// for negative cycle diagonal weight < 0

	}
}