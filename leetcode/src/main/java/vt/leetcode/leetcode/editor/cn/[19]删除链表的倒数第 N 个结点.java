package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

import java.util.Stack;

class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        solution.removeNthFromEnd(head, 2);
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

        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head.next == null) {
                return null;
            }
            // 元素必须是大于等于两个，不然这里会出错
            //            return (ListNode) removeNthFromEnd(null, head, n)[0];
            return removeNthFromEndByStack(head, n);
        }

        private ListNode removeNthFromEndByStack(ListNode head, int n) {
            Stack<ListNode> stack = new Stack();
            ListNode p = head, deletedP = null, deletedPre = null;
            while (p != null) {
                stack.push(p);
                p = p.next;
            }
            for (int i = 1; i <= n; i++) {
                deletedP = stack.pop();
            }
            if (head == deletedP) {
                return head.next;
            }
            deletedPre = stack.pop();
            deletedPre.next = deletedP.next;
            return head;
        }

        /**
         * 利用递归的方式，首先进栈到最后一个帧，然后出栈时，返回前一个元素的倒数序号和前置节点，以此来算出当前元素的倒数序号，如果符号要删的，就删除，
         * 针对头结点需要做特殊处理，如果前置节点不存在，那就是头结点。
         *
         * @param pre
         * @param cur
         * @param deleteN
         * @return 0 当前节点的前置节点，如果是头结点，就返回头结点，如果头结点被删除
         */
        public Object[] removeNthFromEnd(ListNode pre, ListNode cur, int deleteN) {
            if (cur.next == null) {
                if (deleteN == 1) {
                    pre.next = null;
                }
                return new Object[]{ pre, 1 };
            }
            // nn 是 后一个节点的倒数序号，n是当前的
            Object[] nextResult = removeNthFromEnd(cur, cur.next, deleteN);
            int nn = (int) nextResult[1], n = nn + 1;
            ListNode ret = pre;
            if (n == deleteN) {
                if (pre != null) {
                    pre.next = cur.next;
                } else {
                    // 头结点，且头结点需删除
                    ret = cur.next;
                }
            } else {
                // pre == null,cur是头结点
                ret = pre == null ? cur : pre;
            }
            return new Object[]{ ret, n };
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
