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
            return hasCycleTwoPoint2(head);
        }

        /**
         * 这个方法的缺点是，在跨完一步时，他没有检测夸这一步的时候 快慢相等
         * 而只检测了第二步...所以会慢很多
         */
        public boolean hasCycleTwoPoint1(ListNode head) {
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

        /**
         * 快指针先走一步，然后跟
         */
        public boolean hasCycleTwoPoint2(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            // 快慢指针
            ListNode q = head, s = head;
            while (true) {
                // 快指针跨步
                ListNode qNext = q.next;
                if (qNext == s) {
                    // 跨第一步就相遇了
                    return true;
                }
                if (qNext != null && qNext.next != null) {
                    // 跨第二步
                    q = qNext.next;
                } else {
                    // 你都能走完...那肯定是没环
                    return false;
                }
                // 跨完检测一下
                if (q == s) {
                    return true;
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

        /**
         * 可以用来获取环点
         */
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
