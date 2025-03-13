/*
 * Leetcode: 1020. Number of Enclaves
*/

class Solution {

	private static final int[][] DIRECTIONS = {
			{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
	};

	public void dfs(int i, int j, int[][] grid, boolean[][] visited) {
		visited[i][j] = true;

		for (int dir[] : DIRECTIONS) {
			int ni = i + dir[0];
			int nj = j + dir[1];

			if (ni >= 0 && nj >= 0 && ni < grid.length && nj < grid[0].length && grid[ni][nj] == 1 &&
					!visited[ni][nj]) {

				dfs(ni, nj, grid, visited);

			}
		}
	}

	public int numEnclaves(int[][] grid) {

		int m = grid.length, n = grid[0].length;

		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {

			if (!visited[i][0] && grid[i][0] == 1) {
				dfs(i, 0, grid, visited);
			}

			if (!visited[i][n - 1] && grid[i][n - 1] == 1) {
				dfs(i, n - 1, grid, visited);
			}

		}

		for (int j = 0; j < n; j++) {
			if (!visited[0][j] && grid[0][j] == 1) {
				dfs(0, j, grid, visited);
			}

			if (!visited[m - 1][j] && grid[m - 1][j] == 1) {
				dfs(m - 1, j, grid, visited);
			}
		}

		int cnt = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (grid[i][j] == 1 && !visited[i][j]) {
					cnt++;
				}

			}
		}

		return cnt;

	}
}