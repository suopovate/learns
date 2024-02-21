package vt.leetcode.leetcode.editor.cn;

class MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            // 滑动窗口算法
            int windowSum = 0, maxValue = Integer.MIN_VALUE;
            int left = 0, right = 0;
            while (right < nums.length) {
                // 向右滑动
                windowSum += nums[right];
                // 最大值保存
                maxValue = Math.max(windowSum, maxValue);
                // 如果当前窗口小于0，那么后面加的值如果是负数，那对于后面的数不管是什么，都会导致后面数变小，
                // 所以这时候，窗口就应该收缩回>=0的状态
                while (windowSum < 0) {
                    windowSum -= nums[left];
                    left++;
                }
                right++;
            }
            return maxValue;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
