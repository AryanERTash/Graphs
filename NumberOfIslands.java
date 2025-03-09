/*
 * leetcode 200. Number of Islands
 */

class Solution {

    public static boolean isValidIndex(int i, int j, char[][] grid) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;
    }

	
    public static void dfs(int i, int j, char[][] grid, boolean[][] visited) {
        int[] directions = {-1, 1};

        for (int di : directions) {
            if (isValidIndex(i + di, j, grid) && !visited[i + di][j] && grid[i + di][j] == '1') {
                visited[i + di][j] = true;
                dfs(i + di, j, grid, visited);
            }
        }

        for (int dj : directions) {
            if (isValidIndex(i, j + dj, grid) && !visited[i][j + dj] && grid[i][j + dj] == '1') {
                visited[i][j + dj] = true;
                dfs(i, j + dj, grid, visited);
            }
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int islands = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    islands += 1;
                    visited[i][j] = true;
                    dfs(i, j, grid, visited);
                }
            }
        }
        return islands;
    }
}
