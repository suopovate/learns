package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

class OddEvenLinkedList {
    public static void main(String[] args) {
        Solution solution = new OddEvenLinkedList().new Solution();
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
//        public ListNode oddEvenList(ListNode head) {
//            ListNode vh1 = new ListNode(-1), vh2 = new ListNode(-1);
//            ListNode p = head, pv1 = vh1, pv2 = vh2;
//            if (p == null) {
//                return null;
//            }
//            int index = 0;
//            while (p != null) {
//                index += 1;
//                // 偶数
//                if (index % 2 == 0) {
//                    pv1.next = p;
//                    pv1 = p;
//                } else {
//                    // 奇数
//                    pv2.next = p;
//                    pv2 = p;
//                }
//                p = p.next;
//            }
//            // 整个链表倒数第二个元素，会有一个指针指向另一个链表的最后一个元素，这里就是防止这种情况出现
//            pv1.next = null;
//            pv2.next = vh1.next;
//            return vh2.next;
//        }

        public ListNode oddEvenList(ListNode head) {
            if (head == null) return null;
            ListNode oddHead = head,evenHead = head.next;
            ListNode oddp = oddHead, evenp = evenHead;
            while (evenp != null && evenp.next !=null){
                oddp.next = evenp.next;
                oddp = oddp.next;
                evenp.next = oddp.next;
                evenp = evenp.next;
            }
            oddp.next = evenHead;
            return oddHead;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
