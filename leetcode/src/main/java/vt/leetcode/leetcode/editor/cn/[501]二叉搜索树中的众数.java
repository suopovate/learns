package vt.leetcode.leetcode.editor.cn;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;
import java.util.stream.Collectors;

class FindModeInBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new FindModeInBinarySearchTree().new Solution();
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

        public int[] findMode(TreeNode root) {
            return findModeByHash(root);
        }

        public int[] findModeByHash(TreeNode root) {
            HashMap<Integer, Integer> valAndNodesMap = new HashMap<>();
            lookForNode(root, valAndNodesMap);
            int maxEleSize = 0;
            for (Map.Entry<Integer, Integer> kv : valAndNodesMap.entrySet()) {
                maxEleSize = Math.max(maxEleSize, kv.getValue());
            }
            ArrayList<Integer> results = new ArrayList<>();
            for (Map.Entry<Integer, Integer> kv : valAndNodesMap.entrySet()) {
                if (kv.getValue() == maxEleSize) {
                    results.add(kv.getKey());
                }
            }
            System.out.println(valAndNodesMap);
            System.out.println(results);
            int size = results.size();
            int[] ints = new int[size];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = results.get(i);
            }
            return ints;
        }

        /**
         * 遍历所有树节点
         */
        public void lookForNode(TreeNode root, Map<Integer, Integer> valAndNodes) {
            if (root == null) return;
            if (valAndNodes.containsKey(root.val)) {
                valAndNodes.put(root.val, valAndNodes.get(root.val) + 1);
            } else {
                valAndNodes.put(root.val, 1);
            }
            lookForNode(root.left, valAndNodes);
            lookForNode(root.right, valAndNodes);
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
