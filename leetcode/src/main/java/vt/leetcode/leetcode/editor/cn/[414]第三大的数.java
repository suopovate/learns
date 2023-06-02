package vt.leetcode.leetcode.editor.cn;

import java.util.Stack;

class ThirdMaximumNumber {

    public static void main(String[] args) {
        Solution solution = new ThirdMaximumNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int thirdMax(int[] nums) {
            // 第一、第二、第三大
            // 不包括重复的 比如 3 2 2 1 ，最终是 3 2 1 而不是 3 2 2
            // 跟628题目有相关
            Integer a = null, b = null, c = null;
            for (int num : nums) {
                if (a == null || num > a) {
                    c = b;
                    b = a;
                    a = num;
                } else if (num < a) {
                    if (b == null || num > b) {
                        c = b;
                        b = num;
                    } else if (num < b) {
                        if (c == null || c < num) {
                            c = num;
                        }
                    }
                }
            }
            return c == null ? a : c;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
