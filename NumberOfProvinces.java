

/*
 * Leetcode : 547. Number of Provinces
 */
class Solution {

    public static void visit(int[][] isConnected, int node, boolean[] visited) {
		//the func
		

        int[] conection = isConnected[node];

        for (int i = 0; i < conection.length; i++) {

            int bit = conection[i];

            if (i != node && bit == 1 && !visited[i]) {
				visited[i] = true;
                visit(isConnected, i, visited);
            }

        }

    }

    public int findCircleNum(int[][] isConnected) {

        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        int n = isConnected.length;

        int numberOfProvince = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                visit(isConnected, i, visited);
                numberOfProvince += 1;
            }
        }

        return numberOfProvince;
    }
}
