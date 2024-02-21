package vt.leetcode.leetcode.editor.cn;

class DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new DeleteOperationForTwoStrings().new Solution();
        solution.minDistance("leetcode", "etco");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            //            return recursion(word1.toCharArray(), word2.toCharArray(), 0, 0);
            //            recursion(word1.toCharArray(), word2.toCharArray(), 0, 0, 0);
            //            return minEdit;
            return dp(word1.toCharArray(), word2.toCharArray());
        }

        public int dp(char[] word1, char[] word2) {
            int[][] dp = new int[word1.length][word2.length];
            for (int i = 0; i < word2.length; i++) {
                if (word2[i] == word1[0]) {
                    dp[0][i] = 1;
                } else {
                    if (i == 0) {
                        dp[0][i] = 0;
                    } else {
                        dp[0][i] = dp[0][i - 1];
                    }
                }
            }
            for (int i = 0; i < word1.length; i++) {
                if (word1[i] == word2[0]) {
                    dp[i][0] = 1;
                } else {
                    if (i == 0) {
                        dp[i][0] = 0;
                    } else {
                        dp[i][0] = dp[i - 1][0];
                    }
                }
            }
            // 开始处理余下的,第一列第一行不处理
            for (int i = 1; i < word1.length; i++) {
                for (int j = 1; j < word2.length; j++) {
                    if (word1[i] == word2[j]) {
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i][j - 1], dp[i - 1][j]));
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i][j - 1], dp[i - 1][j]));
                    }
                }
            }
            printMatrix(dp);
            int maxStr = dp[word1.length - 1][word2.length - 1];
            return word1.length - maxStr + word2.length - maxStr;
        }


        public int recursion(char[] word1, char[] word2, int i, int j) {
            // aac
            // aab
            // aaec
            // aaee
            if (i >= word1.length || j >= word2.length) {
                return word1.length - i + word2.length - j;
            }
            if (word1[i] == word2[j]) {
                return recursion(word1, word2, i + 1, j + 1);
            }
            return Math.min(recursion(word1, word2, i, j + 1) + 1, recursion(word1, word2, i + 1, j) + 1);
        }

        int minEdit = Integer.MAX_VALUE;

        public void recursion(char[] word1, char[] word2, int i, int j, int edit) {
            if (i >= word1.length || j >= word2.length) {
                edit += word1.length - i + word2.length - j;
                minEdit = Math.min(edit, minEdit);
                return;
            }
            if (word1[i] == word2[j]) {
                recursion(word1, word2, i + 1, j + 1, edit);
                return;
            }
            recursion(word1, word2, i, j + 1, edit + 1);
            recursion(word1, word2, i + 1, j, edit + 1);
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
