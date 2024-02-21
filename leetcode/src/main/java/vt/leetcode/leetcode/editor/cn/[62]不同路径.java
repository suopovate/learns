package vt.leetcode.leetcode.editor.cn;

import cn.hutool.core.lang.Console;

import java.util.Arrays;
import java.util.HashMap;

class UniquePaths {
    public static void main(String[] args) {
        Solution solution = new UniquePaths().new Solution();
        Console.log(solution.uniquePaths(3, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int uniquePaths(int m, int n) {
            // return doRecursion(0, 0, m - 1, n - 1);
            return dpRollArray(m, n);
        }

        public int dp(int m, int n) {
            int[][] matrix = new int[m][n];
            // dp[i][j] = dp[i-1][j] + dp[i][j-1]
            matrix[0][0] = 0;
            // 第一行肯定都是 1
            Arrays.fill(matrix[0], 1);
            // 第一列也是
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
                }
            }
            return matrix[m - 1][n - 1];
        }

        public int dpRollArray(int m, int n) {
            int[] matrix = new int[n];
            // dp[j] = dp[j] + dp[j-1]
            // 初始化第一行
            Arrays.fill(matrix, 1);
            matrix[0] = 0;
            // 初始化第二行第一个
            matrix[0] = 1;
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    matrix[j] = matrix[j] + matrix[j - 1];
                }
            }
            return matrix[n - 1];
        }

        HashMap<String, Integer> mem = new HashMap<>();

        /**
         * 要么从右走,要么从下走,到达某一个点时,就+1,最后走完了,每个点的结果就是所有路径了,不用担心重复路径的问题,不可能重复..
         * 因为它不能往左走,所以不会重复.
         */
        public int doRecursion(int x, int y, int m, int n) {
            if (mem.containsKey(x + "," + y)) {
                return mem.get(x + "," + y);
            }
            if (x > m || y > n) {
                return 0;
            }
            if (x == m && y == n) {
                return 1;
            }
            int res = doRecursion(x + 1, y, m, n) + doRecursion(x, y + 1, m, n);
            mem.put(x + "," + y, res);
            return res;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
