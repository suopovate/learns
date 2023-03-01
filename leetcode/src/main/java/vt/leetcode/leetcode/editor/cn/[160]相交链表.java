package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

import java.util.HashMap;

class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        Solution solution = new IntersectionOfTwoLinkedLists().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            return getIntersectionNodeByHash(headA, headB);
        }

        /**
         * 双指针算法
         * 如果两个链表相交，那
         * <pre>
         * 1 2 3 4 5 6 7
         *     8 4
         * </pre>
         * 由于 5 6 7 这三步是固定的，那么 最终 两个链表 都走 1 2 3 8 步后，就可以遇到一块
         * <p>
         * 这么理解吧，假设有两条路，一条路a，3km，一条路b，5km，我不管是先走a再走b，还是先走b再走a，最终我都是走完8km，重点就在于，都是走了8km，这是终止条件。
         */
        public ListNode getIntersectionNodeTwoPoint(ListNode headA, ListNode headB) {
            ListNode ap = headA, bp = headB;
            // 这里的代码算是比较巧妙，有一个关键点，就是，如果长短相同的，不重合，那么就各自遍历完自己的链表 就同时为null，
            // 如果长短不同，不重合，那么肯定要两方都遍历完，才会同时为null，这样就会终止
            // 如果重合呢？
            // 下面的长度，是指相交点之前的节点数。
            // 如果 长短不一，那就两边都 遍历完  多出来的长 + 多出来的短 + 公共部分长 的距离后，两边就会相遇
            // 如果 长度相同，那就两边都 遍历完  多长来的相同长 就可以了

            // 不管 有没有相交，这个循环次数都是，a 长度 + b 长度，最终 一定是 a = b 的。
            // 要么两个同时为null，要么两个同时为相交点。
            while (ap != bp) {
                ap = ap != null ? ap.next : headB;
                bp = bp != null ? bp.next : headA;
            }
            return ap;
        }

        public ListNode getIntersectionNodeByHash(ListNode headA, ListNode headB) {
            HashMap<ListNode, Integer> aMap = new HashMap<>();
            HashMap<ListNode, Integer> bMap = new HashMap<>();
            ListNode aP = headA, bP = headB;
            if (aP == null || bP == null) {
                return null;
            }
            while (true) {
                if (aP == bP) {
                    return aP;
                }
                if (aMap.containsKey(bP)) {
                    return bP;
                }
                if (bMap.containsKey(aP)) {
                    return aP;
                }
                // 这个终止条件的位置很重要
                if (aP.next == null && bP.next == null) {
                    return null;
                }
                aMap.putIfAbsent(aP, 0);
                bMap.putIfAbsent(bP, 0);
                // 如果走到了尾，就停止
                aP = aP.next != null ? aP.next : aP;
                bP = bP.next != null ? bP.next : bP;
            }
        }

        // 双指针解法
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
