package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

class ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
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

        public ListNode reverseList(ListNode head) {
            return reverseList2(head);
        }

        /**
         * 尾递归调用法，每次调用，都处理一部分结果，将处理后的结果，传给下层调用，调到最后一层时，结果就直接出来了，
         * 就不需要往上传递中间结果计算了。
         * <p>
         * 想象这个场景：
         * <p>
         * 链表1：a -> b -> c -> d -> e
         * 链表2：null
         * head = a，newHead = null
         * 然后..
         * 链表1：b -> c -> d -> e
         * 链表2：null <- a
         * head = b，newHead = a
         * ...
         * 链表1：e
         * 链表2：null <- a <- b <- c <- d
         * head = e，newHead = d
         * <p>
         * head指向的是旧的链表的头节点，newHead指向新链表头结点
         */
        public ListNode reverseList1(ListNode head, ListNode newHead) {
            if (head == null) {
                return newHead;
            }
            ListNode next = head.next;
            head.next = newHead;
            return reverseList1(next, head);
        }

        /**
         * @param head 代表当前待反转的子链表
         * @return 反转后的链表的头结点(其实就是我们传进去的头结点)
         */
        public ListNode reverseList2(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = head.next;
            ListNode newHead = reverseList2(next);
            next.next = head;
            head.next = null;
            return newHead;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
