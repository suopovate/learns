package vt.leetcode.leetcode.editor.cn;

class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
        System.out.println(solution.reverse(2076543211));
        ;
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 作弊玩法，不提倡，不优雅
         */
        public int reverse(int x) {
            // 取余
            int rx = 0;
            for (int i; ; ) {
                i = x % 10;
                // 这里的 -8 对应的就是不能小于 整数最小值：min / 10 * 10 + -8
                // 7 就是不能超过对应的整数最大值：max / 10 * 10 + 7
                if (rx > Integer.MAX_VALUE / 10 || (rx == Integer.MAX_VALUE / 10 && i > 7) || rx < Integer.MIN_VALUE / 10 || (rx == Integer.MIN_VALUE / 10 && i < -8)) {
                    return 0;
                }
                rx = rx * 10 + i;
                x = x / 10;
                if (x == 0) {
                    break;
                }
            }
            return (int) rx;
        }

        /**
         * 作弊玩法，不提倡，不优雅
         */
        public int reverseLow(int x) {
            long lx = x;
            if (lx == 0) {
                return 0;
            }
            // 清除多余的0
            while (lx % 10 == 0) {
                lx = lx / 10;
            }
            boolean negative = false;
            if (lx < 0) {
                negative = true;
                lx = -lx;
            }
            System.out.println(lx);
            // 转为字符
            char[] charArray = String.valueOf(lx).toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                int tail = charArray.length - 1 - i;
                if (tail <= i) {
                    break;
                }
                char temp = charArray[i];
                charArray[i] = charArray[tail];
                charArray[tail] = temp;
            }
            long l = Long.parseLong(String.valueOf(charArray)) * (negative ? -1 : 1);
            if ((negative && l < Integer.MIN_VALUE) || (!negative && l > Integer.MAX_VALUE)) {
                return 0;
            }
            return (int) l;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
