package vt.leetcode.leetcode.editor.cn;

class FlattenAMultilevelDoublyLinkedList {

    public static void main(String[] args) {
        Solution solution = new FlattenAMultilevelDoublyLinkedList().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
    // Definition for a Node.
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
    */

    class Solution {

        public Node flatten(Node head) {
            Node result = new Node();
            flattenRecursion(result, head);
            Node next = result.next;
            if (next != null) {
                next.prev = null;
            }
            return next;
        }

        /**
         * 深度优先算法
         */
        public Node flattenRecursion(Node result, Node p) {
            if (p == null) {
                return result;
            }

            // 关键点就在于这里，记录下 右节点和子节点，然后再将当前节点，完全抽离
            Node child = p.child;
            Node next = p.next;

            result.next = p;
            p.prev = result;
            p.child = null;

            Node resultEnd = result.next;
            resultEnd = flattenRecursion(resultEnd, child);
            resultEnd = flattenRecursion(resultEnd, next);
            return resultEnd;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    ;
}
