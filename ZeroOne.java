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

	public int totalIteration = 0;
	public int[][] mat;

	private static final int[][] DIRECTIONS = new int[][] {
			{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
	};

	public boolean isVisited(int i, int j) {
		return mat[i][j] == valueAt(i, j) + (totalIteration << 1);
	}

	public void markVisited(int i, int j) {

		mat[i][j] = valueAt(i, j) + (totalIteration << 1);

	}

	public int valueAt(int i, int j) {
		return mat[i][j] & 1;
	}

	public int helper(Queue<Pair> queue) {
		totalIteration++;
		int minTime = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			minTime++;

			for (int idx = 0; idx < size; idx++) {

				Pair p = queue.poll();
				int i = p.i, j = p.j;

				markVisited(i, j);

				for (int[] dir : DIRECTIONS) {

					int ni = i + dir[0];
					int nj = j + dir[1];

					boolean cond = (ni >= 0 && nj >= 0 && ni < mat.length && nj < mat[0].length);

					if (!cond)
						continue;
					if (valueAt(ni, nj) == 0)
						return minTime;

					if (!isVisited(ni, nj)) {
						queue.offer(new Pair(ni, nj));
					}

				}

			}
		}

		return minTime;

	}

	public int[][] updateMatrix(int[][] mat) {

		/*
		 * The idea is to do inplace change to keep track of visited cell
		 * every new cell increase totalIteration which is how many time 2 is
		 * to be added to mark new visited cell and not confuse it with previous cells
		 * 
		 * here we add 2 (0b10 (same as << 1)) as the last bit is preserved in
		 * successive iteration signifying whether the number was 0 or 1
		 */

		this.mat = mat;
		this.totalIteration = 0;

		int m = mat.length, n = mat[0].length;

		int result[][] = new int[m][n];

		Queue<Pair> queue = new ArrayDeque<>();

		for (int i = 0; i < m; i++) {

			for (int j = 0; j < n; j++) {

				if (valueAt(i, j) == 0)
					continue;

				queue.offer(new Pair(i, j));
				result[i][j] = helper(queue);
				queue.clear();

			}

		}

		return result;
	}
}