package vt.leetcode.leetcode.editor.cn;

import java.util.LinkedList;

class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        Solution solution = new EvaluateReversePolishNotation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private String plus = "+";
        private String minus = "-";
        private String multi = "*";
        private String div = "/";

        public int evalRPN(String[] tokens) {
            LinkedList<Integer> stack = new LinkedList<>();
            for (int i = 0; i < tokens.length; i++) {
                switch (tokens[i]) {
                    case "+": {
                        Integer a = stack.pop();
                        Integer b = stack.pop();
                        stack.push(a + b);
                        break;
                    }
                    case "-": {
                        Integer a = stack.pop();
                        Integer b = stack.pop();
                        stack.push(b - a);
                        break;
                    }
                    case "*": {
                        Integer a = stack.pop();
                        Integer b = stack.pop();
                        stack.push(a * b);
                        break;
                    }
                    case "/": {
                        Integer a = stack.pop();
                        Integer b = stack.pop();
                        stack.push(b / a);
                        break;
                    }
                    default:{
                        stack.push(Integer.valueOf(tokens[i]));
                    }
                }
            }
            return stack.pop();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
