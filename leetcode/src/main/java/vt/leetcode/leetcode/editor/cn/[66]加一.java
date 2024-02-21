package vt.leetcode.leetcode.editor.cn;

class PlusOne {
    public static void main(String[] args) {
        Solution solution = new PlusOne().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] plusOne(int[] digits) {
            int[] result = new int[digits.length];
            int carryOver = 0;
            for (int i = digits.length - 1; i >= 0; i--) {
                int plusResult;
                if (i == digits.length - 1) {
                    plusResult = digits[i] + 1;
                } else {
                    plusResult = digits[i] + carryOver;
                }
                if (plusResult > 9) {
                    result[i] = 0;
                    carryOver = 1;
                } else {
                    result[i] = plusResult;
                    carryOver = 0;
                }
            }
            if (carryOver > 0) {
                int[] finalResult = new int[digits.length + 1];
                finalResult[0] = 1;
                System.arraycopy(result, 0, finalResult, 1, result.length);
                return finalResult;
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
