package vt.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class NAryTreePostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new NAryTreePostorderTraversal().new Solution();
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

        public List<Integer> postorder(Node root) {
            preorderRecursion(root);
            return result;
        }

        public void preorderRecursion(Node root) {
            if (root == null) {
                return;
            }
            if (root.children != null) {
                for (Node child : root.children) {
                    preorderRecursion(child);
                }
            }
            result.add(root.val);
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
