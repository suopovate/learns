package vt.leetcode.leetcode.editor.cn;

import java.util.LinkedList;

class BaseballGame {
    public static void main(String[] args) {
        Solution solution = new BaseballGame().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        String plus = "+";
        String C = "C";
        String D = "D";

        public int calPoints(String[] operations) {
            LinkedList<Integer> stack = new LinkedList<>();
            for (String operation : operations) {
                if (operation.equals(plus)) {
                    Integer a = stack.pop();
                    Integer b = stack.pop();
                    stack.push(b);
                    stack.push(a);
                    // 出栈两次
                    stack.push(a + b);
                } else if (operation.equals(C)) {
                    // 出栈一次
                    stack.pop();
                } else if (operation.equals(D)) {
                    // 出栈一次
                    Integer a = stack.pop();
                    stack.push(a);
                    stack.push(a * 2);
                } else {
                    stack.push(Integer.valueOf(operation));
                }
            }
            return stack.stream().reduce(0, Integer::sum);
        }

        public int calPoints2(String[] operations) {
            LinkedList<Integer> stack = new LinkedList<>();
            for (String operation : operations) {
                if (operation.equals(plus)) {
                    // 出栈两次
                    stack.push(popValidNumber(stack, 2).stream().reduce(0, Integer::sum));
                } else if (operation.equals(C)) {
                    // 出栈一次
                    stack.pop();
                } else if (operation.equals(D)) {
                    // 出栈一次
                    stack.push(popValidNumber(stack, 1).getFirst() * 2);
                } else {
                    stack.push(Integer.valueOf(operation));
                }
            }
            return stack.stream().reduce(0, Integer::sum);
        }

        public LinkedList<Integer> popValidNumber(LinkedList<Integer> stack, int count) {
            LinkedList<Integer> result = new LinkedList<>();
            LinkedList<Integer> temp = new LinkedList<>();
            while (result.size() < count) {
                Integer v = stack.pop();
                temp.push(v);
                result.add(v);
            }
            while (temp.size() > 0) {
                stack.push(temp.pop());
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
