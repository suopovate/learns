package vt.leetcode.leetcode.editor.cn;

class RangeSumQuery2dImmutable {
    public static void main(String[] args) {
        int[][] matrixPrefixSum = new int[0][0];
        System.out.println(matrixPrefixSum[0].length);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumMatrix {

        int[][] matrixPrefixSum;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            matrixPrefixSum = new int[matrix.length][matrix[0].length];
            // 构建以0,0为左上角，元素本身为右下角的所有的小矩阵的和
            // 每一个小矩阵的和 = 左边元素的和 + 上边元素的和 - 左上角矩阵的和
            // 遍历方向 从上往下 - 从左往右
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    // 左边元素,上边元素，左上元素
                    int lms = 0, ums = 0, lums = 0;
                    // 左边
                    if (j - 1 >= 0) {
                        lms = matrixPrefixSum[i][j - 1];
                    }
                    // 上边
                    if (i - 1 >= 0) {
                        ums = matrixPrefixSum[i - 1][j];
                    }
                    // 左上角
                    if (j - 1 >= 0 && i - 1 >= 0) {
                        lums = matrixPrefixSum[i - 1][j - 1];
                    }
                    matrixPrefixSum[i][j] = matrix[i][j] + lms + ums - lums;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // 每一个小矩阵本身都会被包含在一个大矩阵内
            // 我们可以用大矩阵 减去 小矩阵的 左边 和 上边部分的和，就能得到小矩阵的和
            // 但是在减的时候 会导致 重复对 左上角的 小矩阵 重复减，所以再加上这个小矩阵的和 就行了
            if (matrixPrefixSum == null) return 0;
            // 这里省略对 行列 边界值的限制，因为题目本身已经限定
            // 左边元素,上边元素，左上元素
            int lms = 0, ums = 0, lums = 0;
            // 左边
            if (col1 - 1 >= 0) {
                lms = matrixPrefixSum[row2][col1 - 1];
            }
            // 上边
            if (row1 - 1 >= 0) {
                ums = matrixPrefixSum[row1 - 1][col2];
            }
            // 左上角
            if (row1 - 1 >= 0 && col1 - 1 >= 0) {
                lums = matrixPrefixSum[row1 - 1][col1 - 1];
            }
            return matrixPrefixSum[row2][col2] - lms - ums + lums;
        }
    }

    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
