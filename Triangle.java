// Memorize search

public class Solution {
    private int n;
    private int[][] minSum;
    private int[][] triangle;

    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0) {
            return -1;
        }
        if (triangle[0] == null || triangle[0].length == 0) {
            return -1;
        }
        
        this.triangle = triangle;
        n = triangle.length;
        // NOTE: minSum[x][y]表示minimum path from bottom to (x, y)
        minSum = new int[n][n];
        
        // 把三角形翻转当作一个二维矩阵.这个多初始化了三角形中没有的元素也没关系
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minSum[i][j] = Integer.MAX_VALUE;
            }
        }
        return divideConquer(0, 0);        
    }
    
    // divide conquer是bottom up的
    private int divideConquer(int x, int y) {
        // row index from 0 to n - 1
        if (x == n) {
            return 0;
        }
        
        // 说明已经找到过最小的数了，直接返回
        if (minSum[x][y] != Integer.MAX_VALUE) {
            return minSum[x][y];
        }
        
        minSum[x][y] = triangle[x][y] + Math.min(
            divideConquer(x + 1, y), divideConquer(x + 1, y + 1));
        
        return minSum[x][y];
    }
}
