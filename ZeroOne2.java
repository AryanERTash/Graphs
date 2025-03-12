import java.util.*;

class Solution {

	private static final int[][] DIRECTIONS = {
			{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
	};

	public int[][] updateMatrix(int[][] mat) {
		int m = mat.length, n = mat[0].length;
		int[][] result = new int[m][n];
		boolean[][] visited = new boolean[m][n];
		Queue<int[]> queue = new ArrayDeque<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (mat[i][j] == 0) {
					queue.offer(new int[] { i, j });
					visited[i][j] = true;
				} else {
					result[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		while (!queue.isEmpty()) {
			int[] p = queue.poll();
			int i = p[0], j = p[1];

			for (int[] dir : DIRECTIONS) {
				int ni = i + dir[0], nj = j + dir[1];

				if (ni >= 0 && nj >= 0 && ni < m && nj < n && !visited[ni][nj]) {
					queue.offer(new int[] { ni, nj });
					visited[ni][nj] = true;
					result[ni][nj] = result[i][j] + 1;
				}
			}
		}

		return result;
	}
}
