package vt.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class NAryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new NAryTreePreorderTraversal().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

    class Solution {
        List<Integer> result = new ArrayList<>();

        public List<Integer> preorder(Node root) {
            preorderRecursion(root);
            return result;
        }

        public void preorderRecursion(Node root) {
            if (root == null){
                return;
            }
            result.add(root.val);
            if (root.children != null) {
                for (Node child : root.children) {
                    preorderRecursion(child);
                }
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
