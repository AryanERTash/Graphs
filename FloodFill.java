/*
 * GFG: Flood fill Algorithm 
 * https://www.geeksforgeeks.org/problems/flood-fill-algorithm1856/1
 */

class Solution {

    public static boolean isValidIndex(int i, int j, int[][] image) {
        return i >= 0 && j >= 0 && i < image.length && j < image[0].length;
    }

    public static void helper(int[][] image, int sr, int sc, int target, int newColor) {

        if (isValidIndex(sr, sc, image) && image[sr][sc] == target) {

            image[sr][sc] = newColor;

            for (int di = -1; di <= 1; di++) {

                for (int dj = -1; dj <= 1; dj++) {

                    if (!(di == 0 ^ dj == 0)) {
                        continue;
                    }

                    helper(image, sr + di, sc + dj, target, newColor);

                }

            }

        }

    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null) {
            return null;
        }
        int target = image[sr][sc];

        if (target != newColor) {
            helper(image, sr, sc, target, newColor);
        }

        return image;
    }
}
