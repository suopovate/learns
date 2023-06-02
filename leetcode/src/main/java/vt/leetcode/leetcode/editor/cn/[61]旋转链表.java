package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.str.ListNode;

import java.util.LinkedList;
import java.util.Queue;

class RotateList {
    public static void main(String[] args) {
        Solution solution = new RotateList().new Solution();
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

        public ListNode rotateRight(ListNode head, int k) {
            //            return rotateRightByCircle(head, k);
            return rotateRightByQueue(head, k);
        }

        private ListNode rotateRightByQueue(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            if (head.next == null) {
                return head;
            }
            // 构造队列
            LinkedList<ListNode> queue = new LinkedList<>();
            ListNode p = head;
            while (p != null) {
                queue.add(p);
                p = p.next;
            }
            int size = queue.size();
            int step = k % size;
            for (int i = 0; i < step; i++) {
                queue.offerFirst(queue.pollLast());
            }
            queue.forEach(node -> System.out.println(node.val));
            ListNode rh = new ListNode();
            p = rh;
            while (p != null) {
                p.next = queue.poll();
                p = p.next;
            }
            return rh.next;
        }

        /**
         * 1 2 3 4 5
         * <p>
         * a. 想象成是一个环，然后每个节点，他走 size 步，就会走到原点
         * b. 所以，step = k % size，就是最终有效的步数
         * c. 我们要找到现在整体都走了 step 步后，谁是头节点？简单，那我们要反推出，哪个节点，走 step 后，会走到头结点的位置？
         * d. 那当然就是 size - step了
         * e. 要理解这个，就想象一下，我们找到一个节点n，从1走到n，和从n走到1加起来的值肯定是size，从n走到1我们是知道了，是step，我们要求的就是从1走到n是多少了。
         * 公式：1n(1->n) + n1(n->1) = size
         * n1 = step，n1 用我们最终要旋转的步数来替换
         * 1n = size - step，那么1n就是我们要找的那个节点的位置
         */
        public ListNode rotateRightByCircle(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            if (head.next == null) {
                return head;
            }
            // build a circle
            ListNode hp = head, tp = null, resultHp = null, resultHpp = null;
            int size = getLength(hp);
            // 相对于原来的位置，最终所有节点要走几步
            int step = k % size;
            // 头结点走几步能到达最终的头节点?
            int index = step == 0 ? step : size - step;
            resultHp = getNode(index, head);
            if (index == 0) {
                return resultHp;
            }
            resultHpp = getNode(index - 1, head);
            tp = getNode(size - 1, head);
            System.out.println(resultHp.val);
            System.out.println(resultHpp.val);
            System.out.println(tp.val);
            tp.next = head;
            resultHpp.next = null;
            return resultHp;
        }

        private ListNode getNode(int index, ListNode head) {
            ListNode p = null;
            for (int i = 0; i <= index; i++) {
                if (i == 0) {
                    p = head;
                    continue;
                }
                if (p != null) {
                    p = p.next;
                } else {
                    break;
                }
            }
            return p;
        }

        private int getLength(ListNode p) {
            int length = 0;
            while (p != null) {
                length++;
                p = p.next;
            }
            return length;
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
