package vt.leetcode.leetcode.editor.cn;

import jdk.nashorn.internal.ir.IfNode;

import java.util.ArrayList;
import java.util.List;

class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
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

        List<List<Integer>> results = new ArrayList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            return levelOrder2(root);
        }

        public List<List<Integer>> levelOrder2(TreeNode root) {
            levelOrderRecursion(0, root);
            return results;
        }

        public List<List<Integer>> levelOrder1(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            results.add(new ArrayList<Integer>() {{
                add(root.val);
            }});
            levelOrder(new ArrayList<TreeNode>() {{
                add(root);
            }});
            return results;
        }

        public void levelOrder(ArrayList<TreeNode> roots) {
            ArrayList<Integer> childVals = new ArrayList<>();
            ArrayList<TreeNode> childs = new ArrayList<>();
            for (TreeNode root : roots) {
                if (root != null) {
                    if (root.left != null) {
                        childs.add(root.left);
                        childVals.add(root.left.val);
                    }
                    if (root.right != null) {
                        childs.add(root.right);
                        childVals.add(root.right.val);
                    }
                }
            }
            if (!childs.isEmpty()) {
                results.add(childVals);
                levelOrder(childs);
            }
        }

        public void levelOrderRecursion(int index, TreeNode root) {
            if (root == null) {
                return;
            }
            // 其实这里 应该搞一个map来存 index - 当层元素会更加的好理解...
            // 现在的就有点 拗口...且不好维护，因为过后，可能自己都无法想象出这两行代码了
            if (results.size() - 1 < index) {
                results.add(new ArrayList<>());
            }
            results.get(index).add(root.val);
            levelOrderRecursion(index + 1, root.left);
            levelOrderRecursion(index + 1, root.right);
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
