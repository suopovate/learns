package vt.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new PascalsTriangle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> results = new ArrayList<>();
            // 第一行先怼上
            results.add(new ArrayList<Integer>() {{add(1);}});
            if (numRows == 0) {
                return results;
            }
            if (numRows == 1) {
                return results;
            }
            // 每一行的个数就是当前的行数
            for (int i = 2; i <= numRows; i++) {
                // results.get(i - 1 - 1) 这个跟 generate1 不同
                // i 是行数，从1开始，对应的索引是i-1，上一行索引是i-1-1
                List<Integer> result = new ArrayList<>(i), upRowResult = results.get(i - 1 - 1);
                results.add(result);
                for (int j = 0; j < i; j++) {
                    // 当前值 等于 上一行的 左上(就是当前的索引-1) 和 右上(当前索引)
                    int leftUpVal = j - 1 < 0 ? 0 : upRowResult.get(j - 1);
                    int rightUpVal = j < upRowResult.size() ? upRowResult.get(j) : 0;
                    int curValue = leftUpVal + rightUpVal;
                    result.add(curValue);
                }
            }
            return results;
        }

        public List<List<Integer>> generate1(int numRows) {
            List<List<Integer>> results = new ArrayList<>();
            // 初始化给 一个虚拟行 设一个1
            results.add(new ArrayList<Integer>() {{add(1);}});
            // 每一行的个数就是当前的行数
            for (int i = 1; i <= numRows; i++) {
                List<Integer> result = new ArrayList<>(i), upRowResult = results.get(i - 1);
                results.add(result);
                for (int j = 0; j < i; j++) {
                    // 当前值 等于 上一行的 左上(就是当前的索引-1) 和 右上(当前索引)
                    int leftUpVal = j - 1 < 0 ? 0 : upRowResult.get(j - 1);
                    int rightUpVal = j < upRowResult.size() ? upRowResult.get(j) : 0;
                    int curValue = leftUpVal + rightUpVal;
                    result.add(curValue);
                }
            }
            results.remove(0);
            return results;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
