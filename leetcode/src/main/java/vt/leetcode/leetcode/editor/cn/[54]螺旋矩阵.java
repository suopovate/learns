package vt.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new SpiralMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            int ur = 0, dr = matrix.length - 1, lc = 0, rc = matrix[0].length - 1;
            List<Integer> result = new ArrayList<>();
            // 从左往右，从上往下，从右往左，从下往上，然后循环
            while (ur < dr && lc < rc) {
                // 第一行 从左往右
                for (int i = lc; i < rc; i++) {
                    result.add(matrix[ur][i]);
                }
                // 最后一列 从上往下
                for (int i = ur; i < dr; i++) {
                    result.add(matrix[i][rc]);
                }
                // 最后一行 从右往左
                for (int i = rc; i > lc; i--) {
                    result.add(matrix[dr][i]);
                }
                // 第一列 从下往上
                for (int i = dr; i > ur; i--) {
                    result.add(matrix[i][lc]);
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
                        result.add(matrix[ur][i]);
                    }
                }
                // 剩一列
                else if (ur != dr) {
                    for (int i = ur; i <= dr; i++) {
                        result.add(matrix[i][lc]);
                    }
                } else {
                    // 走到这里 说明 行列都相等，就剩一个
                    result.add(matrix[ur][lc]);
                }
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
