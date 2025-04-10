import java.util.*;

class DisjointSet {
	int parent[], size[];

	DisjointSet(int n) {
		parent = new int[n];
		size = new int[n];
		Arrays.fill(size, 1);

		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	int findUltimateParent(int node) {
		if (parent[node] == node)
			return node;

		int f = findUltimateParent(parent[node]);

		return parent[node] = f;
	}

	public void union(int u, int v) {
		int pu = findUltimateParent(u);
		int pv = findUltimateParent(v);

		if (pu == pv)
			return;

		if (size[pu] > size[pv]) {
			parent[pv] = pu;
			size[pu] += size[pv];
		} else {
			parent[pu] = pv;
			size[pv] += size[pu];
		}
	}
}

class Solution {
	public static final boolean isValidIndex(int i, int j, int[][] grid) {
		return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;
	}

	public static final int[][] DIRECTIONS = new int[][] {
			{ 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
	};

	public int largestIsland(int[][] grid) {
		int n = grid.length, m = grid[0].length;

		int maxIslandSize = 0;
		DisjointSet djSet = new DisjointSet(n * m);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 0)
					continue;
				maxIslandSize = Math.max(maxIslandSize, 1); // there is minimum one element

				for (int[] VEC : DIRECTIONS) {
					int ni = i + VEC[0];
					int nj = j + VEC[1];
					int cellIndex = i * m + j;
					int adjIndex = ni * m + nj;

					if (isValidIndex(ni, nj, grid) && grid[ni][nj] == 1) {

						djSet.union(cellIndex, adjIndex);
						maxIslandSize = Math.max(maxIslandSize,
								djSet.size[djSet.findUltimateParent(adjIndex)]);

					}
				}
			}
		}

		Set<Integer> visitedComponents = new HashSet<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1)
					continue;

				visitedComponents.clear();
				int sizeConnected = 0;

				for (int[] VEC : DIRECTIONS) {
					int ni = i + VEC[0];
					int nj = j + VEC[1];

					if (isValidIndex(ni, nj, grid) && grid[ni][nj] == 1) {
						int adjIndex = ni * m + nj;
						int p = djSet.findUltimateParent(adjIndex);

						if (visitedComponents.contains(p))
							continue;

						sizeConnected += djSet.size[p];
						visitedComponents.add(p);
					}
				}

				maxIslandSize = Math.max(maxIslandSize, sizeConnected + 1);
			}
		}

		return maxIslandSize;
	}
}
