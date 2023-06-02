package vt.leetcode.leetcode.editor.cn;

class RangeSumQueryImmutable {
    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {

        int[] prefixSums;
        int[] nums;

        public NumArray(int[] nums) {
            prefixSums = new int[nums.length];
            prefixSums[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                prefixSums[i] = nums[i] + prefixSums[i - 1];
            }
            this.nums = nums;
        }

        public int sumRange(int left, int right) {
            return prefixSums[right] - prefixSums[left] + nums[left];
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
