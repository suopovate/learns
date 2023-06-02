package vt.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class SpiralMatrixIi {
    public static void main(String[] args) {
        Solution solution = new SpiralMatrixIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] generateMatrix(int n) {
            // n * n
            int[][] matrix = new int[n][n];
            int ur = 0, dr = matrix.length - 1, lc = 0, rc = matrix[0].length - 1;
            int num = 0;
            // 从左往右，从上往下，从右往左，从下往上，然后循环
            while (ur < dr && lc < rc) {
                // 第一行 从左往右
                for (int i = lc; i < rc; i++) {
                    matrix[ur][i] = ++num;
                }
                // 最后一列 从上往下
                for (int i = ur; i < dr; i++) {
                    matrix[i][rc] = ++num;
                }
                // 最后一行 从右往左
                for (int i = rc; i > lc; i--) {
                    matrix[dr][i] = ++num;
                }
                // 第一列 从下往上
                for (int i = dr; i > ur; i--) {
                    matrix[i][lc] = ++num;
                }
                ur++;
                dr--;
                lc++;
                rc--;
            }
            // 剩下一行，或者，剩下一列
            if (ur == dr || lc == rc) {
                // 剩一行
                if (lc != rc) {
                    for (int i = lc; i <= rc; i++) {
                        matrix[ur][i] = ++num;
                    }
                }
                // 剩一列
                else if (ur != dr) {
                    for (int i = ur; i <= dr; i++) {
                        matrix[i][lc] = ++num;
                    }
                } else {
                    // 走到这里 说明 行列都相等，就剩一个
                    matrix[ur][lc] = ++num;
                }
            }
            return matrix;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
