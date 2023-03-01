<p>You are given two <strong>non-empty</strong> linked lists representing two non-negative integers. The digits are stored in <strong>reverse order</strong>, and each of their nodes contains a single digit. Add the two numbers and return the sum&nbsp;as a linked list.</p>

<p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/02/addtwonumber1.jpg" style="width: 483px; height: 342px;" /> 
<pre>
<strong>Input:</strong> l1 = [2,4,3], l2 = [5,6,4]
<strong>Output:</strong> [7,0,8]
<strong>Explanation:</strong> 342 + 465 = 807.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> l1 = [0], l2 = [0]
<strong>Output:</strong> [0]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
<strong>Output:</strong> [8,9,9,9,0,0,0,1]
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li>The number of nodes in each linked list is in the range <code>[1, 100]</code>.</li> 
 <li><code>0 &lt;= Node.val &lt;= 9</code></li> 
 <li>It is guaranteed that the list represents a number that does not have leading zeros.</li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>递归 | 链表 | 数学</details><br>

<div>👍 9241, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://mp.weixin.qq.com/s/NF8mmVyXVfC1ehdMOsO7Cw' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.github.io/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 已更新到 V2.1，[手把手刷二叉树系列课程](https://aep.xet.tech/s/3YGcq3) 上线，[第 17 期刷题打卡挑战](https://aep.xet.tech/s/2jPp5X) 下周开始，报名从速！。**

<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

逆序存储很友好了，直接遍历链表就是从个位开始的，符合我们计算加法的习惯顺序。如果是正序存储，那倒要费点脑筋了，可能需要 [翻转链表](https://labuladong.github.io/article/fname.html?fname=递归反转链表的一部分) 或者使用栈来辅助。

这道题主要考察 [链表双指针技巧](https://labuladong.github.io/article/fname.html?fname=链表技巧) 和加法运算过程中对进位的处理。注意这个 `carry` 变量的处理，在我们手动模拟加法过程的时候会经常用到。

**代码中还用到一个链表的算法题中是很常见的「虚拟头结点」技巧，也就是 `dummy` 节点**。你可以试试，如果不使用 `dummy` 虚拟节点，代码会稍显复杂，而有了 `dummy` 节点这个占位符，可以避免处理初始的空指针情况，降低代码的复杂性。

**标签：[数据结构](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=1318892385270808576)，[链表双指针](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120596033251475465)**

## 解法代码

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 在两条链表上的指针
        ListNode p1 = l1, p2 = l2;
        // 虚拟头结点（构建新链表时的常用技巧）
        ListNode dummy = new ListNode(-1);
        // 指针 p 负责构建新链表
        ListNode p = dummy;
        // 记录进位
        int carry = 0;
        // 开始执行加法，两条链表走完且没有进位时才能结束循环
        while (p1 != null || p2 != null || carry > 0) {
            // 先加上上次的进位
            int val = carry;
            if (p1 != null) {
                val += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                val += p2.val;
                p2 = p2.next;
            }
            // 处理进位情况
            carry = val / 10;
            val = val % 10;
            // 构建新节点
            p.next = new ListNode(val);
            p = p.next;
        }
        // 返回结果链表的头结点（去除虚拟头结点）
        return dummy.next;
    }
}
```

**类似题目**：
  - [445. 两数相加 II 🟠](/problems/add-two-numbers-ii)
  - [67. 二进制求和 🟢](/problems/add-binary)
  - [剑指 Offer II 025. 链表中的两数相加 🟠](/problems/lMSNwu)

</details>
</div>



