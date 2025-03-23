import java.util.*;

class Tuple implements Comparable<Tuple> {
	int j, i, effort;

	Tuple() {
	}

	Tuple(int i, int j, int w) {
		this.i = i;
		this.j = j;
		this.effort = w;
	}

	public int compareTo(Tuple t2) {
		return Integer.compare(effort, t2.effort);
	}
}

class Solution {
	private static final int[][] DIRECTIONS = {
			{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }
	};

	public int minimumEffortPath(int[][] heights) {

		int m = heights.length, n = heights[0].length;
		if (m == 1 && n == 1)
			return 0;

		int effort[][] = new int[m][n];
		for (int[] efa : effort) {
			Arrays.fill(efa, Integer.MAX_VALUE);
		}
		effort[0][0] = 0;

		Queue<Tuple> queue = new PriorityQueue<>();
		queue.offer(new Tuple(0, 0, 0));

		while (!queue.isEmpty()) {
			Tuple currTpl = queue.poll();

			if (currTpl.i == m - 1 && currTpl.j == n - 1)
				return currTpl.effort;
			for (int[] VEC : DIRECTIONS) {
				int ni = currTpl.i + VEC[0];
				int nj = currTpl.j + VEC[1];

				if (ni >= 0 && nj >= 0 && ni < m && nj < n) {
					int newEffort = Math.abs(heights[currTpl.i][currTpl.j] - heights[ni][nj]);
					newEffort = Math.max(newEffort, currTpl.effort);

					if (newEffort < effort[ni][nj]) {
						effort[ni][nj] = newEffort;
						queue.offer(new Tuple(ni, nj, newEffort));

					}
				}
			}

		}

		return effort[m - 1][n - 1];

	}
}