package vt.leetcode.leetcode.editor.cn;

class DesignLinkedList {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new DesignLinkedList().new MyLinkedList();
        //        myLinkedList.addAtHead(7);
        //        myLinkedList.addAtHead(2);
        //        myLinkedList.addAtHead(1);
        //        myLinkedList.deleteAtIndex(2);
        //        myLinkedList.addAtHead(6);
        //        myLinkedList.addAtTail(4);
        myLinkedList.get(4);
        myLinkedList.addAtIndex(0, 1);
        myLinkedList.addAtIndex(1, 1);
        myLinkedList.addAtIndex(2, 1);
        myLinkedList.addAtIndex(3, 1);
        myLinkedList.addAtIndex(0, 1);
        myLinkedList.addAtIndex(0, 1);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class MyLinkedList {

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            Node p = head.next;
            while (p != null) {
                stringBuilder.append(p.val).append(",");
                p = p.next;
            }
            return stringBuilder.toString();
        }

        /**
         * head是一个虚拟节点
         */
        Node head, tail;
        int count;

        class Node {

            int val;
            Node next;
            Node pre;

            public Node(int val) {
                this.val = val;
            }

        }

        public MyLinkedList() {
            head = new Node(-1);
            count = 0;
        }

        public int get(int index) {
            if (index >= count) {
                return -1;
            }
            Node p = head;
            for (int i = 0; i < count; i++) {
                if (p != null) {
                    p = p.next;
                }
                if (p == null) {
                    return -1;
                }
                if (i == index) {
                    return p.val;
                }
            }
            return -1;
        }

        public void addAtHead(int val) {
            Node node = new Node(val);
            if (tail == null) {
                tail = node;
                head.next = node;
                node.pre = head;
            } else {
                head.next.pre = node;
                node.next = head.next;
                head.next = node;
                node.pre = head;
            }
            count++;
        }

        public void addAtTail(int val) {
            Node node = new Node(val);
            if (tail == null) {
                tail = node;
                head.next = node;
                node.pre = head;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
            count++;
        }

        public void addAtIndex(int index, int val) {
            if (index > count) {
                return;
            }
            if (index < 0) {
                addAtHead(val);
                return;
            }
            Node p = head;
            for (int i = 0; i <= count; i++) {
                if (p != null) {
                    p = p.next;
                }
                // 走到这里 意味着i == count
                if (p == null) {
                    // 走到这里，说明遍历到了尾部，肯定是 i == count的
                    addAtTail(val);
                    return;
                }
                if (i == index) {
                    Node node = new Node(val);
                    node.pre = p.pre;
                    node.next = p;
                    p.pre.next = node;
                    p.pre = node;
                    count++;
                    return;
                }
            }
        }

        public void deleteAtIndex(int index) {
            Node p = head;
            for (int i = 0; i <= index; i++) {
                if (p != null) {
                    p = p.next;
                }
                if (p == null) {
                    return;
                }
                if (i == index) {
                    count--;
                    p.pre.next = p.next;
                    if (p.next != null) {
                        p.next.pre = p.pre;
                    }
                    if (p == tail) {
                        tail = p.pre;
                    }
                    p.pre = null;
                    p.next = null;
                }
            }
        }

    }

    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}
