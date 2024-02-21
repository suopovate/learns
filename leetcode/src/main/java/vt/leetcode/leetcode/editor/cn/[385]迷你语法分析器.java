package vt.leetcode.leetcode.editor.cn;

import vt.leetcode.NestedInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class MiniParser {
    public static void main(String[] args) {
        Solution solution = new MiniParser().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     * // Constructor initializes an empty nested list.
     * public NestedInteger();
     * <p>
     * // Constructor initializes a single integer.
     * public NestedInteger(int value);
     * <p>
     * // @return true if this NestedInteger holds a single integer, rather than a nested list.
     * public boolean isInteger();
     * <p>
     * // @return the single integer that this NestedInteger holds, if it holds a single integer
     * // Return null if this NestedInteger holds a nested list
     * public Integer getInteger();
     * <p>
     * // Set this NestedInteger to hold a single integer.
     * public void setInteger(int value);
     * <p>
     * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
     * public void add(NestedInteger ni);
     * <p>
     * // @return the nested list that this NestedInteger holds, if it holds a nested list
     * // Return empty list if this NestedInteger holds a single integer
     * public List<NestedInteger> getList();
     * }
     */
    class Solution {
        NestedInteger identity = new NestedInteger();

        public NestedInteger deserialize(String s) {
            Stack<NestedInteger> stack = new Stack<>();
            StringBuilder curNumber = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (c == '[') {
                    stack.push(new NestedInteger());
                    stack.push(identity);
                    continue;
                }
                if (c == ']') {
                    Stack<NestedInteger> childs = new Stack<>();
                    NestedInteger nestedInteger = stack.pop();
                    while (nestedInteger != identity) {
                        childs.push(nestedInteger);
                        nestedInteger = stack.pop();
                    }
                    // 跳过标识对象
                    nestedInteger = stack.pop();
                    while (!childs.isEmpty()) {
                        nestedInteger.add(childs.pop());
                    }
                    if (curNumber.length() > 0) {
                        nestedInteger.add(new NestedInteger(Integer.parseInt(curNumber.toString())));
                        curNumber = new StringBuilder();
                    }
                    stack.push(nestedInteger);
                    continue;
                }
                if (isNumber(c)) {
                    curNumber.append(c);
                }
                // 碰到逗号，可能是数字，或者前面是数组，数字这里需要处理
                if (c == ',') {
                    if (curNumber.length() > 0) {
                        stack.push(new NestedInteger(Integer.parseInt(curNumber.toString())));
                        curNumber = new StringBuilder();
                    }
                }
            }
            NestedInteger nestedInteger;
            if (stack.isEmpty()) {
                nestedInteger = new NestedInteger();
                if (curNumber.length() > 0) {
                    nestedInteger.setInteger(Integer.parseInt(curNumber.toString()));
                }
            } else {
                nestedInteger = stack.pop();
            }
            return nestedInteger;
        }

        public boolean isNumber(char c) {
            return c >= '0' && c <= '9' || c == '-';
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
