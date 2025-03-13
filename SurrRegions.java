class Solution {
	public static final char CROSS = 'X';
	public static final char DOT = 'O';
	public static final int[][] DIRECTIONS = {
			{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
	};

	public static void dfs(int i, int j, char[][] board, boolean[][] visited) {

		visited[i][j] = true;
		/*
		 * instead of visited array we can also mark it inplace and change the marking
		 * at the last
		 */

		for (int dir[] : DIRECTIONS) {
			int ni = i + dir[0];
			int nj = j + dir[1];

			if (ni >= 0 && nj >= 0 && ni < board.length && nj < board[0].length &&
					board[ni][nj] == DOT && !visited[ni][nj]) {
				dfs(ni, nj, board, visited);
			}
		}

	}

	public void solve(char[][] board) {

		int m = board.length, n = board[0].length;

		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {

			if (!visited[i][0] && board[i][0] == DOT) {
				dfs(i, 0, board, visited);
			}

			if (!visited[i][n - 1] && board[i][n - 1] == DOT) {
				dfs(i, n - 1, board, visited);
			}

		}

		for (int j = 0; j < n; j++) {
			if (!visited[0][j] && board[0][j] == DOT) {
				dfs(0, j, board, visited);
			}

			if (!visited[m - 1][j] && board[m - 1][j] == DOT) {
				dfs(m - 1, j, board, visited);
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (!visited[i][j] && board[i][j] == DOT) {
					board[i][j] = CROSS;
				}
			}
		}

	}
}