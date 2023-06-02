package vt.leetcode.leetcode.editor.cn;

class AddTwoNumbersIi {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbersIi().new Solution();
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
            ListNode reversedL1 = reverseList(l1);
            ListNode reversedL2 = reverseList(l2);
            //            printListNode(reversedL1);
            ListNode resultNode = new ListNode(-1);
            addTwoNumbersRecursion(reversedL1, reversedL2, resultNode, false);
            return reverseList(resultNode.next);
        }

        public void addTwoNumbersRecursion(ListNode l1, ListNode l2, ListNode result, boolean carry) {
            if (l1 != null || l2 != null) {
                int sum = nullOrZero(l1) + nullOrZero(l2) + (carry ? 1 : 0);
                if (sum >= 10) {
                    result.next = new ListNode(sum - 10);
                    addTwoNumbersRecursion(nullOrNext(l1), nullOrNext(l2), result.next, true);
                } else {
                    result.next = new ListNode(sum);
                    addTwoNumbersRecursion(nullOrNext(l1), nullOrNext(l2), result.next, false);
                }
            } else if (carry) {
                // 如果节点均为Null，但是有进位，那就加上进位
                result.next = new ListNode(1);
            }
        }

        public int nullOrZero(ListNode node) {
            return node == null ? 0 : node.val;
        }

        public ListNode nullOrNext(ListNode node) {
            return node == null ? null : node.next;
        }

        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode newHead = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }

        public void printListNode(ListNode head) {
            if (head == null) return;
            System.out.println(head.val);
            printListNode(head.next);
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {this.val = val;}

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
