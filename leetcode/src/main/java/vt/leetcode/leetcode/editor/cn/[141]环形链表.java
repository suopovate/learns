package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

import java.util.HashMap;

class LinkedListCycle {
    public static void main(String[] args) {
        Solution solution = new LinkedListCycle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {

        public boolean hasCycle(ListNode head) {
            return hasCycleHash(head);
        }

        public boolean hasCycleTwoPoint(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            // 快慢指针
            ListNode q = head, s = head;
            while (true) {
                // 快指针跨步
                ListNode qNext = q.next;
                if (qNext != null && qNext.next != null) {
                    q = qNext.next;
                } else {
                    // 你都能走完...那肯定是没环
                    return false;
                }
                // 慢指针跨步
                ListNode sNext = s.next;
                if (sNext != null) {
                    s = sNext;
                } else {
                    return false;
                }
                // 跨完如果相遇
                if (s == q) {
                    return true;
                }
            }
        }

        public boolean hasCycleHash(ListNode head) {
            HashMap<ListNode, Integer> existsNode = new HashMap<>();
            ListNode p = head;
            int index = 0;
            while (p != null) {
                if (existsNode.containsKey(p)) {
                    return true;
                }
                existsNode.put(p, index++);
                p = p.next;
            }
            return false;
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)

}
