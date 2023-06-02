package vt.leetcode.leetcode.editor.cn;

import java.util.Arrays;

class MinimumMovesToEqualArrayElements {
    public static void main(String[] args) {
        Solution solution = new MinimumMovesToEqualArrayElements().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minMoves(int[] nums) {
            // 把n-1自增，变为1自减
            // 然后从第2个开始，都将自身减 nums[i] - nums[0] 次，最后就平了
            int count = 0;
            // 排个序
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                count += nums[i] - nums[0];
            }
            return count;
        }

        /**
         * 暴力破解
         */
        public int minMovesViolence(int[] nums) {
            int count = 0;
            while (true) {
                // 排个序
                Arrays.sort(nums);
                if (nums[0] == nums[nums.length - 1]) {
                    return count;
                }
                // 把前N-1个数+1
                for (int i = 0; i < nums.length - 1; i++) {
                    nums[i]++;
                }
                count++;
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
