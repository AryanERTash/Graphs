/*
 * GFG:https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1
 * 
 */

import java.util.*;

class Solution {
	public static final int MODNUM = 100000;

	int minimumMultiplications(int[] arr, int start, int end) {

		if (start == end)
			return 0;

		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[MODNUM];

		queue.add(start);
		visited[start] = true;
		int level = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			++level;
			for (int i = 0; i < size; i++) {

				int num = queue.poll();
				

				for (int mNum : arr) {

					int p = (mNum * num) % MODNUM;

					if (p == end)
						return level;

					if (!visited[p]) {
						queue.offer(p);
						visited[p] = true;
					}

				}

			}
		}

		return -1;
	}
}
