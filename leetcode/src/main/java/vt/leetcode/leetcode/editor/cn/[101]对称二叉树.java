package vt.leetcode.leetcode.editor.cn;

import java.util.Objects;

class SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new SymmetricTree().new Solution();
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

        public boolean isSymmetric(TreeNode root) {
            //            return isSymmetric(new TreeNode[]{ root.left, root.right });
            return isSymmetricRecursion(root.left, root.right);
        }

        public boolean isSymmetricRecursion(TreeNode left, TreeNode right) {
            if (left == null || right == null) {
                return left == null && right == null;
            }
            return left.val == right.val
                && isSymmetricRecursion(left.left, right.right)
                && isSymmetricRecursion(left.right, right.left);
        }

        public boolean isSymmetric(TreeNode[] roots) {
            if (roots.length % 2 != 0) {
                return false;
            }
            int hi = 0, ti = roots.length - 1, ci = 0;
            TreeNode[] childs = new TreeNode[roots.length * 2];
            while (ti > hi) {
                if (roots[hi] == null || roots[ti] == null) {
                    if (!(roots[hi] == null && roots[ti] == null)) {
                        return false;
                    }
                    hi++;
                    ti--;
                    continue;
                }
                if (roots[hi++].val != roots[ti--].val) return false;
            }
            for (TreeNode root : roots) {
                if (root == null) {
                    childs[ci++] = null;
                    childs[ci++] = null;
                } else {
                    childs[ci++] = root.left;
                    childs[ci++] = root.right;
                }
            }
            return isAllNull(childs) || isSymmetric(childs);
        }

        public boolean isAllNull(TreeNode[] nodes) {
            for (TreeNode node : nodes) {
                if (node != null) {
                    return false;
                }
            }
            return true;
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
