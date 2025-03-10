
import java.util.*;

class Pair {

    int i, j;

    Pair() {

    }

    Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Solution {

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        int freshCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 2) {
                    queue.offer(i);
                    queue.offer(j);
                } else if (grid[i][j] == 1) {
                    freshCount += 1;
                }

            }
        }

        if (freshCount == 0) {
            return 0;
        }

        int time = -1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int index = 0; index < size; index++) {

                int i = queue.poll();
                int j = queue.poll();

                if (visited[i][j]) {
                    continue;
                }

                grid[i][j] = 2;
                time += 1;

                visited[i][j] = true;

                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {

                        if (!(di == 0 ^ dj == 0)) {
                            continue;
                        }

                        //todo
                        int ni = i + di;
                        int nj = j + dj;

                        if (ni >= 0 && nj >= 0 && ni < m && nj < n && !visited[ni][nj]) {
                            visited[ni][nj] = true;
                        }
                    }
                }

            }
        }

    }
}
