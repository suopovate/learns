package vt.leetcode.leetcode.editor.cn;

import java.util.LinkedList;

class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public void flatten(TreeNode root) {
            //            flattenRecursion(root);
            flattenQueue(root);
        }

        public TreeNode flattenRecursion(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode left = root.left, right = root.right;
            TreeNode leftTail = flattenRecursion(left);
            TreeNode rightTail = flattenRecursion(right);
            root.left = null;
            if (leftTail != null) {
                root.right = left;
                leftTail.right = right;
            }
            return rightTail != null ? rightTail : leftTail != null ? leftTail : root;
        }

        public TreeNode flattenQueue(TreeNode root) {
            if (root == null) return null;
            LinkedList<TreeNode> queue = new LinkedList<>();
            flattenQueueRecursion(root, queue);
            TreeNode pre = queue.poll(), p = queue.poll();
            while (p != null) {
                pre.left = null;
                pre.right = p;
                pre = p;
                p = queue.poll();
            }
            return root;
        }

        public void flattenQueueRecursion(TreeNode root, LinkedList<TreeNode> queue) {
            if (root == null) return;
            // 前序遍历收集元素
            queue.offer(root);
            flattenQueueRecursion(root.left, queue);
            flattenQueueRecursion(root.right, queue);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {this.val = val;}

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
