package vt.leetcode.leetcode.editor.cn;

import cn.hutool.core.lang.Console;

class PowxN {
    public static void main(String[] args) {
        Solution solution = new PowxN().new Solution();
        Console.log(solution.myPow(1, -2147483648));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double myPow(double x, int n) {
            boolean negative = n < 0;
            if (n == Integer.MIN_VALUE) {
                return 1 / doMyPow(x, -(n + 1)) * x;
            }
            double pow = doMyPow(x, negative ? -n : n);
            return negative ? 1 / pow : pow;
        }

        /**
         * 二分法，降低计算的次数
         */
        public double doMyPow(double x, int n) {
            if (n == 1) {
                return x;
            }
            if (n == 0) {
                return 1;
            }
            if (n % 2 == 0) {
                // 优化主要体现在这里，减少了一次计算
                double v = doMyPow(x, n / 2);
                return v * v;
            } else {
                // 奇数的话 是 n/2 * n/2 * n
                int m = n / 2;
                double temp = doMyPow(x, m);
                return temp * temp * x;
            }
        }

        public double myPowViolent(double x, int n) {
            boolean negative = n < 0;
            double pow = 1;
            for (int i = 1; i <= (negative ? -n : n); i++) {
                pow *= x;
            }
            return negative ? 1 / pow : pow;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
