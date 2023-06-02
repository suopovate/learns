package vt.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Stack;

class NextGreaterElementI {
    public static void main(String[] args) {
        Solution solution = new NextGreaterElementI().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            HashMap<Integer, Integer> nums1Map = new HashMap<>();
            int[] ans = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                nums1Map.put(nums1[i], i);
            }
            // 基于Nums2构建 单调递减栈
            // 这里的一个很重要的特点，就是，直接在nums2的基础上，倒序遍历，然后算出nums2每个值的最近最大右边值就行，然后再看Nums1存不存在nums2对应这个值，存在就设置结果就行
            Stack<Integer> stack = new Stack<>();
            // 时间复杂度，每一个元素被遍历一次 = n ，每一个元素最多进出栈各一次，2 * m
            // O(n+2m)
            for (int i = nums2.length - 1; i >= 0; i--) {
                // 将所有小于当前值的出栈
                while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                    stack.pop();
                }
                // 空了？那说明从后面到本值，都比本值小
                int an = stack.isEmpty() ? -1 : stack.peek();
                // 本值是当前最小的(如果右边有值的话)
                stack.push(nums2[i]);
                if (nums1Map.containsKey(nums2[i])) {
                    ans[nums1Map.get(nums2[i])] = an;
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
