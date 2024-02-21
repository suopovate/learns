package vt.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FirstMissingPositive {
    public static void main(String[] args) {
        Solution solution = new FirstMissingPositive().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstMissingPositive(int[] nums) {
            // the first we should know is the nums is a array that data from 1 - nums.length-1.
            int[] temp = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0 && nums[i] <= nums.length) {
                    temp[nums[i] - 1] = 1;
                }
            }
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] == 0) {
                    return i + 1;
                }
            }
            return temp.length + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
