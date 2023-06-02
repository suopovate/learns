package vt.leetcode.leetcode.editor.cn;

import java.util.Arrays;

class RotateArray {
    public static void main(String[] args) {
        Solution solution = new RotateArray().new Solution();
        System.out.println(-0 % 10);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[] nums, int k) {
            // 把数组的首尾相连成环就可以了
            // 想象数组是一个环，用两个指针指向它的首和尾
            int startIndex = 0, endIndex = nums.length - 1;
            // 这里的轮转就可以理解成环是顺时针方向旋转
            // 但是我们数字不动，我们想象两个指针是两个圆盘外的箭头，内层不转，外层箭头转
            // 内部既然是顺时针，那相对来说，外部应该做逆时针旋转 所以是 -k
            int[] temp = new int[nums.length];
            startIndex = (-k % nums.length) + nums.length;
            endIndex = startIndex > 0 ? startIndex - 1 : nums.length;
            if (startIndex == 0) {
                return;
            }
            System.arraycopy(nums, startIndex, temp, 0, nums.length - startIndex);
            System.arraycopy(nums, 0, temp, nums.length - startIndex, endIndex + 1);
            System.arraycopy(temp, 0, nums, 0, nums.length);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
