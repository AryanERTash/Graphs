import java.util.*;

class Tuple {
	int i, j, weight;

	Tuple() {
	}

	Tuple(int i, int j, int w) {
		this.i = i;
		this.j = j;
		this.weight = w;
	}
}

class Solution {
	public int shortestPathBinaryMatrix(int[][] grid) {
		/*
		 * Since each path in grid has same weight ensuring distance are minimum in
		 * first update
		 * Hence if we encounter bottom right element we can terminate
		 * 
		 */
		if (grid[0][0] == 1)
			return -1;
		int m = grid.length, n = grid[0].length;

		int[][] distance = new int[m][n];

		for (int[] d : distance)
			Arrays.fill(d, Integer.MAX_VALUE);

		distance[0][0] = 1;

		Queue<Tuple> queue = new ArrayDeque<>();

		queue.offer(new Tuple(0, 0, 1));

		while (!queue.isEmpty()) {
			Tuple tp = queue.poll();

			for (int di = -1; di <= 1; di++) {
				for (int dj = -1; dj <= 1; dj++) {
					int ni = tp.i + di;
					int nj = tp.j + dj;
					int nextWeight = tp.weight + 1;

					if (ni >= 0 && nj >= 0 && ni < m && nj < n &&
							(ni != 0 || nj != 0) && grid[ni][nj] == 0
							&& nextWeight < distance[ni][nj]) {

						if (ni == m - 1 && nj == n - 1)
							return nextWeight;

						distance[ni][nj] = nextWeight;
						queue.offer(new Tuple(ni, nj, nextWeight));

					}
				}
			}
		}

		return distance[m - 1][n - 1] != Integer.MAX_VALUE ? distance[m - 1][n - 1] : -1;

	}
}