package vt.leetcode.leetcode.editor.cn;

import org.omg.IOP.IOR;

import java.util.HashMap;

class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder[0] == -1) {
                return new TreeNode(-1);
            }
            return buildTreeRecursion(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        /**
         * @param preorder
         * @param inorder
         * @param pis      前序 开始
         * @param pie      前序 结束
         * @param iis      中序 开始
         * @param iie      中序 结束
         * @return
         */
        public TreeNode buildTreeRecursion(
            int[] preorder, int[] inorder,
            int pis, int pie, int iis, int iie
        ) {
            if (pis > pie && iis > iie) return null;
            if (pis == pie && iis == iie) return new TreeNode(preorder[pis]);
            HashMap<Integer, Integer> pmap = new HashMap<>();
            HashMap<Integer, Integer> imap = new HashMap<>();
            for (int i = pis; i <= pie; i++) {
                pmap.put(preorder[i], i);
            }
            for (int i = iis; i <= iie; i++) {
                imap.put(inorder[i], i);
            }
            TreeNode root = new TreeNode();
            // 前序遍历 根 左 右
            // 找到根
            root.val = preorder[pis];
            // 中序遍历 左 根 右
            // 中序遍历 根节点索引
            int iri = imap.get(root.val);
            // 找左子树在前序列表中的最后一个位置 cLpie，小于等于它的部分自然就是左子树的所有节点，大于它的部分就是右子树的节点
            int cLpie = pis;
            for (int i = iis; i < iri; i++) {
                // 在前序列表中，找到最末尾的那一个左子树的节点所在的位置
                cLpie = Math.max(cLpie, pmap.get(inorder[i]));
            }
            // 左子树在前序中的尾位 == pis 就意味着只有右子树
            if (cLpie != pis) {
                root.left = buildTreeRecursion(preorder, inorder, pis + 1, cLpie, iis, iri - 1);
            }
            // 左子树在前序中的尾位 == pie 就意味着只有右子树
            if (cLpie != pie) {
                // 找右子树的根
                root.right = buildTreeRecursion(preorder, inorder, cLpie + 1, pie, iri + 1, iie);
            }
            return root;
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
