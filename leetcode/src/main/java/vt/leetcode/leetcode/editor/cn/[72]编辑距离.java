package vt.leetcode.leetcode.editor.cn;

import cn.hutool.core.lang.Console;

import java.util.HashSet;
import java.util.Set;

class EditDistance {

    public static void main(String[] args) {
        Solution solution = new EditDistance().new Solution();

        Console.log(solution.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"));
        ;
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        char[] w1, w2;
        int minEdit = Integer.MAX_VALUE;

        public int minDistance(String word1, String word2) {
            w1 = word1.toCharArray();
            w2 = word2.toCharArray();
            recursion(0, 0, 0);
            return minEdit;
            //            if (w1.length == 0 || w2.length == 0) {
            //                return Math.abs(w1.length - w2.length);
            //            }
            //            return dp();
        }

        public int dp() {
            int[][] dp = new int[w1.length][w2.length];
            for (int j = 0; j < w2.length; ++j) {
                // 如果有任意一个字母和其相等,那总数必然就是 length - 1
                if (w1[0] == w2[j]) dp[0][j] = j;
                    // 如果字母不相等,那就等于 前面字符串 + 1 呗
                else if (j != 0) dp[0][j] = dp[0][j - 1] + 1;
                    // 如果 是 [0][0] 且不相等 那就是 1
                else dp[0][j] = 1;
            }
            for (int i = 0; i < w1.length; ++i) {
                if (w1[i] == w2[0]) dp[i][0] = i;
                else if (i != 0) dp[i][0] = dp[i - 1][0] + 1;
                else dp[i][0] = 1;
            }
            //            printMatrix(dp);
            for (int i = 1; i < w1.length; i++) {
                for (int j = 1; j < w2.length; j++) {
                    if (w1[i] == w2[j]) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                    }
                }
            }
            return dp[w1.length - 1][w2.length - 1];
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

        Set<String> mem = new HashSet<>();

        /**
         * 递归:
         * i 和 j
         * 如果不相等,有以下三种递归
         * w[i] 和 w[j]
         * w[i+1] 和 w[j]
         * w[i] 和 w[j+1]
         * dp[i][j] = min(dp[i-1][j-1] + 1, dp[i-1][j] + 1, dp[i][j-1] + 1)
         * dp[i][j] = dp[i-1][j-1](w[i]==w[j])
         */
        public void recursion(int i, int j, int edit) {
            if (mem.contains(i + "," + j + "," + edit)) {
                return;
            }
            if (i == w1.length || j == w2.length) {
                minEdit = Math.min(minEdit, edit + w1.length - i + w2.length - j);
                return;
            }
            if (w1[i] != w2[j]) {
                recursion(i + 1, j + 1, edit + 1);
                mem.add(i + 1 + "," + j + 1 + "," + (edit + 1));
                recursion(i + 1, j, edit + 1);
                mem.add(i + 1 + "," + j + "," + (edit + 1));
                recursion(i, j + 1, edit + 1);
                mem.add(i + "," + j + 1 + "," + (edit + 1));
            } else {
                recursion(i + 1, j + 1, edit);
                mem.add(i + 1 + "," + j + 1 + "," + edit);
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
