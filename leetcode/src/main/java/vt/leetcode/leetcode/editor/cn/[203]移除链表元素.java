package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

class RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new RemoveLinkedListElements().new Solution();
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

        public ListNode removeElements(ListNode head, int val) {
            if (head == null) return null;
            return removeElementRecursion(head, val);
        }

        public ListNode removeElementsByVate(ListNode head, int val) {
            ListNode p = head, pr = null, newHead = head;
            if (p == null) {
                return null;
            }
            while (p != null) {
                if (p.val == val) {
                    if (newHead == p) {
                        p = p.next;
                        newHead = p;
                        continue;
                    }
                    if (p.next != null) {
                        pr.next = p.next;
                        p.next = null;
                        p = pr.next;
                        continue;
                    } else {
                        pr.next = null;
                        break;
                    }
                }
                pr = p;
                p = p.next;
            }
            return newHead;
        }

        /**
         * 构建一个虚拟头结点 指向真正的头结点
         */
        public ListNode removeElementsVirtual(ListNode head, int val) {
            if (head == null) {
                return null;
            }
            ListNode virtualHead = new ListNode(-1), p = virtualHead;
            virtualHead.next = head;
            while (p.next != null) {
                if (p.next.val == val) {
                    p.next = p.next.next;
                } else {
                    p = p.next;
                }
            }
            return virtualHead.next;
        }

        /**
         * 递归删除
         */
        public ListNode removeElementRecursion(ListNode head, int val) {
            if (head == null) {
                return null;
            }
            head.next = removeElementRecursion(head.next, val);
            return head.val == val ? head.next : head;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
