package vt.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class IntegerReplacement {
    public static void main(String[] args) {
        Solution solution = new IntegerReplacement().new Solution();
        System.out.println(solution.integerReplacement(1234));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int min = Integer.MAX_VALUE;

        Map<Long, Integer> pathCount = new HashMap<>();

        public int integerReplacement(int n) {
            if (n == 0) {
                return 0;
            }
            //            integerReplacementRecursion(n, 0);
            //            return min;
            return integerReplacementRecursion3(n);
        }

        /**
         * long 是因为考虑到他可能会提供一个 Integer.MAX_VALUE
         */
        public void integerReplacementRecursion(long n, int count) {
            if (n == 1) {
                min = Math.min(count, min);
                return;
            }
            if (n % 2 == 0) {
                integerReplacementRecursion(n / 2, count + 1);
                return;
            }
            integerReplacementRecursion(n + 1, count + 1);
            integerReplacementRecursion(n - 1, count + 1);
        }

        public int integerReplacementRecursion2(long n, int count) {
            // 剪枝优化

            if (n == 1) {
                return count;
            }
            if (n % 2 == 0) {
                return integerReplacementRecursion2(n / 2, count + 1);
            }
            int plusCount = integerReplacementRecursion2(n + 1, count + 1);
            int minusCount = integerReplacementRecursion2(n - 1, count + 1);
            return Math.min(plusCount, minusCount);
        }

        public int integerReplacementRecursion3(long n) {
            if (pathCount.containsKey(n)) {
                return pathCount.get(n);
            }
            if (n == 1) {
                return 0;
            }
            if (n % 2 == 0) {
                return integerReplacementRecursion3(n / 2) + 1;
            }
            int plus = integerReplacementRecursion3(n + 1);
            int minus = integerReplacementRecursion3(n - 1);
            pathCount.put(n + 1, plus);
            pathCount.put(n - 1, minus);
            return Math.min(plus, minus) + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
