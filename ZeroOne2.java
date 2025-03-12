
import java.util.*;

class Solution {

	class Pair {
		int i, j;

		public Pair() {
		}

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;

		}

	}

	public int[][] mat;

	private static final int[][] DIRECTIONS = new int[][] {
			{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
	};

	public boolean isVisited(int i, int j) {
		return mat[i][j] == valueAt(i, j) + 2;
	}
	
	public boolean isValid(int i, int j) {
		return i >= 0 && j >= 0 && i < mat.length && j < mat[0].length;
	}
	
	public void markVisited(int i, int j) {
		// instead of marking by adding 2 we can simple mark it with any constant also
		// but anyway

		mat[i][j] = valueAt(i, j) + 2;

	}

	public int valueAt(int i, int j) {
		return mat[i][j] & 1;
	}

	public int[][] updateMatrix(int[][] mat) {

		this.mat = mat; 

		Queue<Pair> queue = new ArrayDeque<>();

		int m = mat.length, n = mat[0].length;

		int result[][] = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (mat[i][j] == 0) {
					queue.offer(new Pair(i, j));
					markVisited(i, j);

				}

			}
		}

		while (!queue.isEmpty()) {
			Pair p = queue.poll();

			int i = p.i, j = p.j;

			for (int[] dir : DIRECTIONS) {
				int ni = p.i + dir[0];
				int nj = p.j + dir[1];
				int steps = result[i][j] + 1;

				boolean f = isValid(ni, nj);

				if (!f)
					continue;
				boolean visited = isVisited(ni, nj);

				if (valueAt(ni, nj) == 0)
					continue;

				if (visited && valueAt(ni, nj) == 1) {
					result[ni][nj] = Math.min(result[ni][nj], steps);

				} else if (!visited && valueAt(ni, nj) == 1) {
					queue.offer(new Pair(ni, nj));
					markVisited(ni, nj);
					result[ni][nj] = steps;
				}

			}

		}

		return result;

	}
}