
class Solution {
	public static final char CROSS = 'X';
	public static final char DOT = 'O';
	public static final char MARKER = '#';

	public static final int[][] DIRECTIONS = {
			{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
	};

	private void dfs(int i, int j, char[][] board) {
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != DOT) {
			return;
		}

		board[i][j] = MARKER; // mark as MARKER

		for (int[] dir : DIRECTIONS) {
			dfs(i + dir[0], j + dir[1], board);
		}
	}

	public void solve(char[][] board) {

		int m = board.length, n = board[0].length;

		for (int i = 0; i < m; i++) {
			if (board[i][0] == DOT)

				dfs(i, 0, board);

			if (board[i][n - 1] == DOT)
				dfs(i, n - 1, board);
		}

		for (int j = 0; j < n; j++) {
			if (board[0][j] == DOT)
				dfs(0, j, board);
			if (board[m - 1][j] == DOT)
				dfs(m - 1, j, board);
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (board[i][j] == DOT) {

					board[i][j] = CROSS;

				} else if (board[i][j] == MARKER) {

					board[i][j] = DOT;

				}
			}
		}
	}

}
