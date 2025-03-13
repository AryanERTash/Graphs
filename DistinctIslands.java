/*
 * GFG: Number of Distinct Island
 * https://www.geeksforgeeks.org/problems/number-of-distinct-islands/0
 */

import java.util.*;

class Solution {

	private static final int[][] DIRECTIONS = {
			{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
	};

	static void dfs(int i, int j, int baseI, int baseJ, StringBuffer sb, int[][] grid, boolean[][] visited) {

		visited[i][j] = true;

		sb.append(Integer.toString(i-baseI) + "," + Integer.toString(j-baseJ)+";");

		

		for (int dir[] : DIRECTIONS) {
			int ni = i + dir[0];
			int nj = j + dir[1];

			if (ni >= 0 && nj >= 0 && ni < grid.length && nj < grid[0].length && grid[ni][nj] == 1 &&
					!visited[ni][nj]) {

						dfs(ni, nj, baseI, baseJ, sb, grid, visited);

			}
		}

	}

	int countDistinctIslands(int[][] grid) {
		/*
		 * Remember: two structurally different traversal can not have same linear string of a 
		 * visited node if we are storing normalized indexes in string
		 * 
		 * So you can either use dfs or bfs but stick to same order of traversal.
		 * 
		 * Here we store points in form of i,j;, however to save space for delimiter,
		 * faster hash calculation and faster matching other binary encodings can also be used.
		 * 
		 * We can also use trie to keep track of directions(left/right/top/bottom) istead of storing points
		 * and can traverse the trie to find a duplicate island
		*/
		
		int m = grid.length, n = grid[0].length;

		boolean[][] visited = new boolean[m][n];

		Set<String> ilandSet = new HashSet<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) {

					StringBuffer sb = new StringBuffer();

					dfs(i, j, i, j, sb, grid, visited);

					String s = sb.toString();
					
					ilandSet.add(s ); // string buffer equals is not content comparison

				}

			}
		}

		return ilandSet.size();
	}

}
