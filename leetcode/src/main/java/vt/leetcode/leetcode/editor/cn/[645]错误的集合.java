package vt.leetcode.leetcode.editor.cn;

import cn.hutool.core.lang.Console;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class SetMismatch {
    public static void main(String[] args) {
        Solution solution = new SetMismatch().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * <pre>
         *  测试用例:[3,2,3,4,6,5]
         * 	测试结果:[2,1]
         * 	期望结果:[3,1]
         * </pre>
         */
        public int[] findErrorNums(int[] nums) {
            // 边界问题
            if (nums == null || nums.length <= 1) {
                return new int[0];
            }
            // 找出 重复值和缺失值
            Map<Integer, Integer> counts = new HashMap<>();
            int duplicated = -1, missed = -1;
            // 记录重复次数
            for (int i = 0; i < nums.length; i++) {
                Integer count = counts.getOrDefault(nums[i], 0);
                if (count > 0) {
                    duplicated = nums[i];
                }
                counts.put(nums[i], count + 1);
            }
            for (int i = 1; i <= nums.length; i++) {
                if (!counts.containsKey(i)){
                    missed = i;
                }
            }
            return new int[]{ duplicated, missed };
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
