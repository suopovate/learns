package vt.leetcode.leetcode.editor.cn;

class MaxConsecutiveOnes {
    public static void main(String[] args) {
        Solution solution = new MaxConsecutiveOnes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int count = 0, maxCount = count;
            for (int num : nums) {
                if (num == 1) {
                    count++;
                    maxCount = Math.max(count, maxCount);
                } else {
                    count = 0;
                }
            }
            return maxCount;
        }

        public int findMaxConsecutiveOnes2(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0] == 1 ? 1 : 0;
            }
            int count = 1, val = -1, maxCount = count, maxOneCount = nums[0] == 1 ? 1 : 0;
            for (int i = 1; i < nums.length; i++) {
                val = nums[i];
                if (nums[i] == nums[i - 1]) {
                    count++;
                    maxCount = Math.max(count, maxCount);
                } else {
                    count = 1;
                }
                if (nums[i] == 1) {
                    maxOneCount = Math.max(count, maxOneCount);
                }
            }
            return maxOneCount;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
