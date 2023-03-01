package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

import java.util.HashMap;

class LinkedListCycleIi {
    public static void main(String[] args) {
        Solution solution = new LinkedListCycleIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        return hasCycleHash(head);
    }

    public ListNode hasCycleHash(ListNode head) {
        HashMap<ListNode, Integer> existsNode = new HashMap<>();
        ListNode p = head;
        int index = 0;
        while (p != null) {
            if (existsNode.containsKey(p)) {
                return p;
            }
            existsNode.put(p, index++);
            p = p.next;
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
