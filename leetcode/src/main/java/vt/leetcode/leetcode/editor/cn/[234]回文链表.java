package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

class PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedList().new Solution();
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

        ListNode frontNode;

        public boolean isPalindrome(ListNode head) {
            if (head == null) return false;
            frontNode = head;
            return isPalindromeRecursion(head);
        }

        public boolean isPalindromeRecursion(ListNode p) {
            if (p == null) return true;
            boolean palindrome = isPalindromeRecursion(p.next);
            palindrome = palindrome && frontNode.val == p.val;
            frontNode = frontNode.next;
            return palindrome;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
