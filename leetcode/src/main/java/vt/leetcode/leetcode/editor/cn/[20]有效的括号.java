package vt.leetcode.leetcode.editor.cn;

import java.util.Objects;
import java.util.Stack;

class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public final Character lmin = '(';
        public final Character rmin = ')';
        public final Character lmiddle = '{';
        public final Character rmiddle = '}';
        public final Character lmax = '[';
        public final Character rmax = ']';

        public boolean isValid(String s) {
            // 左括号 入栈，右括号 出栈，然后看能不能抵消，不能就直接返回false
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                if (c == lmax || c == lmin || c == lmiddle) {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) return false;
                    if (c == rmin) {
                        if (!Objects.equals(lmin, stack.pop())) {
                            return false;
                        }
                    }
                    if (c == rmiddle) {
                        if (!Objects.equals(lmiddle, stack.pop())) {
                            return false;
                        }
                    }
                    if (c == rmax) {
                        if (!Objects.equals(lmax, stack.pop())) {
                            return false;
                        }
                    }
                }
            }
            return stack.isEmpty();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
