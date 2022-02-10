package vt.leetcode.str;
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学 
// 👍 4834 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class AddTwoNumberList {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cl1 = l1;
        ListNode cl2 = l2;
        ListNode crl = null;
        ListNode rootCrl = null;
        int preInt = 0;

        while (cl1 != null) {
            if (cl2 != null) {
                int currVal = cl1.val + cl2.val + preInt;
                // 进位
                preInt = computePreInt(currVal);
                // 添加当前节点
                if (crl != null) {
                    crl = crl.next = new ListNode(computeCurrVal(currVal));;
                } else {
                    rootCrl = crl = new ListNode(computeCurrVal(currVal));
                }
                cl2 = cl2.next;
            } else {
                int currVal = cl1.val + preInt;
                preInt = computePreInt(currVal);
                crl.next = new ListNode(computeCurrVal(currVal));
                crl = crl.next;
            }
            cl1 = cl1.next;
        }

        // 说明 l2 比较长,此时 l1 已经到底
        if (cl1 == null && cl2 != null) {
            while (cl2 != null) {
                int currVal = cl2.val + preInt;
                preInt = computePreInt(currVal);
                if (crl != null) {
                    crl.next = new ListNode(computeCurrVal(currVal));
                    crl = crl.next;
                } else {
                    crl = crl.next = new ListNode(computeCurrVal(currVal));;
                }
                cl2 = cl2.next;
            }
        }

        if (preInt != 0){
            if (crl != null) {
                crl.next = new ListNode(1);;
            } else {
                rootCrl = new ListNode(1);
            }
        }

        return rootCrl;
    }

    private int computeCurrVal(int currVal) {
        return currVal >= 10 ? currVal - 10 : currVal;
    }

    private int computePreInt(int currVal) {
        return currVal >= 10 ? 1 : 0;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);

        ListNode rl = new AddTwoNumberList().addTwoNumbers(l1, l2);
        ListNode crl = rl;

        while (crl != null) {
            System.out.println(crl.val);
            crl = crl.next;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
