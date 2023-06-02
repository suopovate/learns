package vt.leetcode.leetcode.editor.cn;

import java.util.*;

class LowestCommonAncestorOfABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinarySearchTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    class Solution {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return lowestCommonAncestorBySearch(root, p, q);
        }

        public TreeNode lowestCommonAncestorBySearch(TreeNode root, TreeNode p, TreeNode q) {
            if (p.val > root.val && q.val > root.val) {
                // 右子树
                return lowestCommonAncestorBySearch(root.right, p, q);
            }
            if (p.val < root.val && q.val < root.val) {
                // 左子树
                return lowestCommonAncestorBySearch(root.left, p, q);
            }
            return root;
        }

        // k:p,q v:p或者q的祖先节点，按从下到上的顺序排列
        Map<Integer, LinkedHashMap<Integer, TreeNode>> ancestorMap = new HashMap<>();

        public TreeNode lowestCommonAncestorByRecursion(TreeNode root, TreeNode p, TreeNode q) {
            ancestorMap.clear();
            ancestorMap.put(p.val, new LinkedHashMap<Integer, TreeNode>() {{put(p.val, p);}});
            ancestorMap.put(q.val, new LinkedHashMap<Integer, TreeNode>() {{put(q.val, q);}});
            buildAncestor(root);
            System.out.println(ancestorMap.toString());
            for (TreeNode pAncestor : ancestorMap.get(p.val).values()) {
                for (TreeNode qAncestor : ancestorMap.get(q.val).values()) {
                    if (pAncestor.val == qAncestor.val) {
                        return pAncestor;
                    }
                }
            }
            return root;
        }

        public void buildAncestor(TreeNode root) {
            if (root == null) {
                return;
            }
            buildAncestor(root.left);
            buildAncestor(root.right);
            // 遍历所有节点的祖父列表
            ancestorMap.values().forEach(ancestors -> {
                // 如果当前节点的左右子节点 存在于祖先节点中 那本节点也是其祖先节点
                if (root.left != null && ancestors.containsKey(root.left.val)) {
                    ancestors.put(root.val, root);
                    return;
                }
                if (root.right != null && ancestors.containsKey(root.right.val)) {
                    ancestors.put(root.val, root);
                }
            });
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {val = x;}
    }
}
