package vt.leetcode.leetcode.editor.cn;

import lombok.CustomLog;

import java.util.*;

class ConstructStringFromBinaryTree {
    public static void main(String[] args) {
        Solution solution = new ConstructStringFromBinaryTree().new Solution();
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
        public String tree2str(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            Set<TreeNode> vis = new HashSet<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.empty()) {
                TreeNode cur = stack.pop();
                if (vis.contains(cur)) {
                    sb.append(")");
                } else {
                    // 把当前节点再放回去，是为了等下遍历完子节点后，又能遇到本节点，再次遇到就相当于是结束了本树的处理
                    stack.push(cur);
                    vis.add(cur);
                    // 任何节点的外边都放一个括号
                    sb.append("(");
                    sb.append(cur.val);
                    // 左空 右不空，则左为()
                    // 左不空 右空 则右可为''
                    // 左右均空 则 不需要括号了
                    if (Objects.nonNull(cur.left) || Objects.nonNull(cur.right)) {
                        if (Objects.nonNull(cur.left)) {
                            if (Objects.nonNull(cur.right)) {
                                stack.push(cur.right);
                            }
                            stack.push(cur.left);
                        } else {
                            // 走到这里 说明左为空，那右肯定不为空
                            sb.append("()");
                            stack.push(cur.right);
                        }
                    }
                }
            }
            return sb.substring(1, sb.length() - 1);
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
