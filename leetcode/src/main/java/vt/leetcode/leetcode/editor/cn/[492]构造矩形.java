package vt.leetcode.leetcode.editor.cn;

import cn.hutool.core.lang.Console;

import java.math.BigDecimal;

import static java.lang.Double.NaN;

class ConstructTheRectangle {
    public static void main(String[] args) {
        Console.log(Math.sqrt(10));
        Solution solution = new ConstructTheRectangle().new Solution();
    }

    public static String formatDoubleBinaryString(String s) {
        return s.substring(0, 1) + "-" + s.substring(1, 12) + "-" + s.substring(12, 62);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] constructRectangle(int area) {
            Double sqrt = Double.valueOf(Math.sqrt(area));
            if (sqrt.intValue() == sqrt)
                return new int[]{ sqrt.intValue(), sqrt.intValue() };
            int sqrtInt = sqrt.intValue();
            while (sqrtInt > 0) {
                if (area % sqrtInt == 0) {
                    return new int[]{ area / sqrtInt, sqrtInt };
                }
                sqrtInt--;
            }
            return new int[0];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
