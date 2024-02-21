package vt.leetcode.leetcode.editor.cn;

class RemoveElement {
    public static void main(String[] args) {
        Solution solution = new RemoveElement().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int removeElement(int[] nums, int val) {
            // 1 2 3 2 5 6
            // 最笨的方法 是每次碰到 就把后面元素都往前移动
            // 复杂度为 n * n
            // 怎么优化呢？
            // 注意 他这里是无所谓元素的顺序，那就可以使用快慢指针，慢指针永远指向
            // slow 指向最后一个有效的值
            int fast = 0, slow = 0;
            while (fast < nums.length) {
                if (nums[fast] != val) {
                    nums[slow] = nums[fast];
                    slow++;
                }
                fast++;
            }
            return slow;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
