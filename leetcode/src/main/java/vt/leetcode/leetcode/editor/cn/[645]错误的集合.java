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

        public int[] findErrorNums(int[] nums) {
            return findErrorNums2(nums);
        }

        /**
         * <pre>
         *  测试用例:[3,2,3,4,6,5]
         * 	测试结果:[2,1]
         * 	期望结果:[3,1]
         * </pre>
         */
        public int[] findErrorNums1(int[] nums) {
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
                if (!counts.containsKey(i)) {
                    missed = i;
                }
            }
            return new int[]{ duplicated, missed };
        }

        /**
         * 只适用于,nums是 1-n 自然顺序排列,所以要主动排序....
         */
        public int[] findErrorNums2(int[] nums) {
            // 边界问题
            if (nums == null || nums.length <= 1) {
                return new int[0];
            }
            Arrays.sort(nums);
            int repeat = 0;
            int lose = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == nums[i + 1]) {
                    repeat = nums[i];
                }
                if (nums[i + 1] - nums[i] > 1) {
                    lose = nums[i] + 1;
                }
            }
            // 1 2 3 4 5
            // 1 2 2 3 4 少5
            // 2 2 3 4 5 少1
            // 1 2 2 4 5 少3
            // 1 2 2 3 5 少4
            // 总结 如果是 少的 首尾,就只能通过 看下标是否是对应值.
            // 如果是 少的中间,那就可以通过 对比 两个数字之间的间隔是否 > 2,找到那个缺失值.
            // 比如 3 5 , 1 3, n n+2(少了n+1)
            if (nums[0] != 1) return new int[]{ repeat, 1 };
            if (nums[nums.length - 1] != nums.length) return new int[]{ repeat, nums.length };
            return new int[]{ repeat, lose };
        }

        public int[] findErrorNums3(int[] nums) {
            // 边界问题
            if (nums == null || nums.length <= 1) {
                return new int[0];
            }
            Arrays.sort(nums);
            int repeat = 0;
            int lose = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == nums[i + 1]) {
                    repeat = nums[i];
                }
                if (nums[i + 1] - nums[i] > 1) {
                    lose = nums[i] + 1;
                }
            }
            // 1 2 3 4 5
            // 直接看,如果 1 少了,前面没有 0,没法算,你没法找出一个 前后相差为 2 的
            // 如果 5 少了,也是一样,只有 4 和 6 才能凑出差为 2,你没有 6.
            // 所以 首 尾,必须特殊处理,你找不到.
            // 但是中间的数字,你少了的话,可以发现,前后一定会出现 差为 2 的情况.
            // 1 2 2 3 4 少5
            // 2 2 3 4 5 少1
            // 1 2 2 4 5 少3
            // 1 2 2 3 5 少4
            // 总结 如果是 少的 首尾,就只能通过 看下标是否是对应值.
            // 如果是 少的中间,那就可以通过 对比 两个数字之间的间隔是否 > 2,找到那个缺失值.
            // 比如 3 5 , 1 3, n n+2(少了n+1)
            if (nums[0] != 1) return new int[]{ repeat, 1 };
            if (nums[nums.length - 1] != nums.length) return new int[]{ repeat, nums.length };
            return new int[]{ repeat, lose };
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
