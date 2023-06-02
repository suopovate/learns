package vt.leetcode.leetcode.editor.cn;

class ReshapeTheMatrix {
    public static void main(String[] args) {
        Solution solution = new ReshapeTheMatrix().new Solution();
        int[][] mat = new int[][]{ { 1, 2 }, { 3, 4 } };
        solution.matrixReshape(mat, 2, 4);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        // 这是我的解法，直接遍历一遍原矩阵，然后遍历设置新矩阵，其本质其实也是通用解法那种
        public int[][] matrixReshape(int[][] mat, int r, int c) {
            if (mat == null) return mat;
            int m = mat.length;
            int n = mat[0].length;
            if (r * c != m * n) {
                return mat;
            }
            int[][] result = new int[r][c];
            int curR = 0, curC = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < mat[i].length; j++) {
                    result[curR][curC] = mat[i][j];
                    if (++curC >= c) {
                        curR++;
                        curC = 0;
                    }
                }
            }
            return result;
        }

        // 二维数组 映射回一维数组 再映射回二维数组 通用解法
        //        public int[][] matrixReshape(int[][] mat, int r, int c) {
        //            int m = mat.length, n = mat[0].length;
        //            // 如果想成功 reshape，元素个数应该相同
        //            if (r * c != m * n) {
        //                return mat;
        //            }
        //
        //            int[][] res = new int[r][c];
        //            for (int i = 0; i < m * n; i++) {
        //                set(res, i, get(mat, i));
        //            }
        //            return res;
        //        }
        //
        //        // 通过一维坐标访问二维数组中的元素
        //        int get(int[][] matrix, int index) {
        //            int m = matrix.length, n = matrix[0].length;
        //            // 计算二维中的横纵坐标
        //            int i = index / n, j = index % n;
        //            return matrix[i][j];
        //        }
        //
        //        // 通过一维坐标设置二维数组中的元素
        //        void set(int[][] matrix, int index, int value) {
        //            int m = matrix.length, n = matrix[0].length;
        //            // 计算二维中的横纵坐标
        //            int i = index / n, j = index % n;
        //            matrix[i][j] = value;
        //        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
