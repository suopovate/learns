package vt.leetcode.leetcode.editor.cn;

class FibonacciNumber {
    public static void main(String[] args) {
        Solution solution = new FibonacciNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int fib(int n) {
            return dp(n);
        }

        public int dp(int n) {
            // dp[i][j] = dp[i-1][j-1] + dp[i-1][j-2] (i>1,j>1)
            if (n <= 1) return n;
            int[][] dp = new int[n + 1][n + 1];
            dp[1][1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i][0] = 0;
                dp[i][1] = 1;
                for (int j = 2; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j - 2];
                }
            }
            printMatrix(dp);
            return dp[n][n];
        }

        public int doRecursion(int n) {
            if (n == 0 || n == 1) return n;
            return doRecursion(n - 1) + doRecursion(n - 2);
        }

        public void printMatrix(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
