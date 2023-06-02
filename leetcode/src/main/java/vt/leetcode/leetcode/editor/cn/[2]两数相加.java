package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode p1 = l1, p2 = l2, result = new ListNode(-1), rp = result;
            boolean over = false;
            while (p1 != null || p2 != null) {
                int v1 = p1 != null ? p1.val : 0;
                int v2 = p2 != null ? p2.val : 0;
                int sum = v1 + v2;
                sum = over ? sum + 1 : sum;
                // 清除进位标志
                over = false;
                if (sum >= 10) {
                    rp.next = new ListNode(sum - 10);
                    over = true;
                } else {
                    rp.next = new ListNode(sum);
                }
                rp = rp.next;
                p1 = p1 != null ? p1.next : null;
                p2 = p2 != null ? p2.next : null;
            }
            if (over){
                rp.next = new ListNode(1);
            }
            return result.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
