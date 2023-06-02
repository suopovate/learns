package vt.leetcode.leetcode.editor.cn;

import java.util.*;

class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
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
            return lowestCommonAncestorDeepFirst(root, p, q);
        }

        /**
         * 每次调用返回root或者root的子节点
         */
        public TreeNode lowestCommonAncestorDeepFirst(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestorDeepFirst(root.left, p, q);
            TreeNode right = lowestCommonAncestorDeepFirst(root.right, p, q);
            if (left == null && right == null) {
                return null;
            }
            // 分属两侧，那当前root就是最终结果了
            if (left != null && right != null) {
                return root;
            }
            // 都在左侧 或者 都在右侧
            return right == null ? left : right;
        }


        public TreeNode lowestCommonAncestorAi(TreeNode root, TreeNode p, TreeNode q) {
            Map<Integer, TreeNode> ancestorMap = new HashMap<>();
            buildAncestorAi(root, ancestorMap);
            Set<Integer> pAncestors = new HashSet<>();
            while (p != null) {
                pAncestors.add(p.val);
                p = ancestorMap.get(p.val);
            }
            while (q != null) {
                if (pAncestors.contains(q.val)) {
                    return q;
                }
                q = ancestorMap.get(q.val);
            }
            return root;
        }

        public void buildAncestorAi(TreeNode root, Map<Integer, TreeNode> ancestorMap) {
            if (root == null) {
                return;
            }
            buildAncestorAi(root.left, ancestorMap);
            buildAncestorAi(root.right, ancestorMap);
            if (root.left != null) {
                ancestorMap.put(root.left.val, root);
            }
            if (root.right != null) {
                ancestorMap.put(root.right.val, root);
            }
        }

        // k:p,q v:p或者q的祖先节点，按从下到上的顺序排列
        Map<Integer, LinkedHashMap<Integer, TreeNode>> ancestorMap = new HashMap<>();

        public TreeNode lowestCommonAncestorByRecursionVate(
            TreeNode root,
            TreeNode p,
            TreeNode q
        ) {
            ancestorMap.clear();
            ancestorMap.put(
                p.val,
                new LinkedHashMap<Integer, TreeNode>() {{put(p.val, p);}}
            );
            ancestorMap.put(
                q.val,
                new LinkedHashMap<Integer, TreeNode>() {{put(q.val, q);}}
            );
            buildAncestorVate(root);
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

        public void buildAncestorVate(TreeNode root) {
            if (root == null) {
                return;
            }
            buildAncestorVate(root.left);
            buildAncestorVate(root.right);
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
