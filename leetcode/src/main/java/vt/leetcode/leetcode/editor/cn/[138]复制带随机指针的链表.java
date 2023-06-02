package vt.leetcode.leetcode.editor.cn;

import java.util.HashMap;

class CopyListWithRandomPointer {
    public static void main(String[] args) {
        Solution solution = new CopyListWithRandomPointer().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

    class Solution {
        public Node copyRandomList(Node head) {
            HashMap<Node, Node> oldAndNewedMap = new HashMap<>();
            Node nHead = new Node(-1), np = nHead, p = head;
            while (p != null) {
                np.next = oldAndNewedMap.getOrDefault(p, new Node(p.val));
                oldAndNewedMap.putIfAbsent(p, np.next);
                if (p.random != null) {
                    oldAndNewedMap.putIfAbsent(p.random, new Node(p.random.val));
                    np.next.random = oldAndNewedMap.get(p.random);
                }
                np = np.next;
                p = p.next;
            }
            return nHead.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
