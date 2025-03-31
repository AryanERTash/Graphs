
class Solution {
	public int findTheCity(int n, int[][] edges, int distanceThreshold) {

		int dist[][] = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (i == j)
					dist[i][j] = 0;
				else
					dist[i][j] = Integer.MAX_VALUE;

			}
		}

		for (int edge[] : edges) {
			dist[edge[0]][edge[1]] = edge[2];
			dist[edge[1]][edge[0]] = edge[2];
		}

		for (int interim = 0; interim < n; interim++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					int p1 = dist[i][interim];
					int p2 = dist[interim][j];

					int d = p1 + p2; // overflow

					if (p1 != Integer.MAX_VALUE && p2 != Integer.MAX_VALUE &&
							d < dist[i][j]) {
						dist[i][j] = d;
					}

				}
			}
		}

		int city = -1;

		int minNeighbourCount = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			int neighbourCount = 0;
			for (int j = 0; j < n; j++) {

				if (dist[i][j] <= distanceThreshold)
					neighbourCount++;

			}

			if (neighbourCount == minNeighbourCount && i > city) {
				city = i;
			} else if (neighbourCount < minNeighbourCount) {
				city = i;
				minNeighbourCount = neighbourCount;
			}

		}

		return city;

	}
}