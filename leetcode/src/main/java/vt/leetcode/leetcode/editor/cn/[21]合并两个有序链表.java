package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
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
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            return mergeTwoListsRecursion(list1, list2);
        }

        public ListNode mergeTwoListsIterator(ListNode list1, ListNode list2) {
            ListNode result = new ListNode(-1), resultEnd = result;
            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    resultEnd.next = list1;
                    resultEnd = resultEnd.next;
                    list1 = list1.next;
                } else {
                    resultEnd.next = list2;
                    resultEnd = resultEnd.next;
                    list2 = list2.next;
                }
            }
            if (list1 != null) {
                resultEnd.next = list1;
            }
            if (list2 != null) {
                resultEnd.next = list2;
            }
            return result.next;
        }

        public ListNode mergeTwoListsRecursion(ListNode list1, ListNode list2) {
            if (list1 == null || list2 == null) {
                return list1 == null ? list2 : list1;
            }
            // 这里可能是一个，可能是多个，我们的目的是 这里最终返回的是1个
            if (list1.val <= list2.val) {
                list1.next = mergeTwoListsRecursion(list2, list1.next);
                return list1;
            } else {
                list2.next = mergeTwoListsRecursion(list1, list2.next);
                return list2;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
