/*
 * leetcode: 994. Rotting Oranges
 */
import java.util.*;

class Solution {

    class Pair {

        int i, j;

        Pair() {

        }

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        Queue<Pair> queue = new ArrayDeque<>();

        int freshCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Pair(i, j));
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }
        System.out.println(queue.size());
        System.out.println(freshCount);
        if (freshCount == 0) {
            return 0;
        }

        int totalTime = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            boolean spread = false;

            for (int idx = 0; idx < size; idx++) {

                Pair p = queue.poll();
                int i = p.i, j = p.j;

                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        int ni = i + di;
                        int nj = j + dj;

                        if ((di == 0) ^ (dj == 0) && ni >= 0 && nj >= 0 && ni < m && nj < n && grid[ni][nj] == 1)  {

                            spread = true;
                            freshCount--;
                            grid[ni][nj] = 2;
                            queue.offer(new Pair(ni, nj));

                        }
                    }
                }

            }
            if (spread) {
                totalTime++;
            }
        }
        return freshCount == 0 ? totalTime : -1;

    }
}
