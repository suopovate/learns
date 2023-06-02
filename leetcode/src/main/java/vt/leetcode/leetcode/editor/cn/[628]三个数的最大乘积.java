package vt.leetcode.leetcode.editor.cn;

class MaximumProductOfThreeNumbers {

    public static void main(String[] args) {
        Solution solution = new MaximumProductOfThreeNumbers().new Solution();
        solution.maximumProduct(new int[]{ 0, 0, 0 });
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximumProduct(int[] nums) {
            // 叼 不加负数的话，直接找出三个最大值就好了，有负数就难搞了
            // 看图说话
            // -5 -4 -3 -2 -1 0 1 2 3 4 5
            // 最大是 -5 * -4 * 5
            // 最小是 -5 * 5
            // 规律是
            // 全正数 取最大三个数
            // 全负数 取最大三个数
            // 有正有负 最小两个负数 * 最大正数 或者 最大三个正数相乘 取两者大值
            // max123:从大到小
            // min12:从小到大
            // min1 min2 max3 max2 max1
            // 📢：这里跟第三大的数[414] 不一样的地方在于 需要保存重复值
            // 3 2 2 1 就是 3(max1) 2(max2) 2(max3) 2(min2) 2(min1)
            Integer max1 = null, max2 = null, max3 = null, min1 = null, min2 = null;
            for (int num : nums) {
                // 分别收集最大三个值，和最小两个值
                if (min1 == null || num < min1) {
                    min2 = min1;
                    min1 = num;
                } else {
                    if (min2 == null || num < min2) {
                        min2 = num;
                    }
                }

                if (max1 == null || num > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = num;
                } else {
                    if (max2 == null || num > max2) {
                        max3 = max2;
                        max2 = num;
                    } else {
                        if (max3 == null || max3 < num) {
                            max3 = num;
                        }
                    }
                }
            }
            System.out.println(max1);
            System.out.println(max2);
            System.out.println(max3);
            System.out.println(min1);
            System.out.println(min2);
            return Math.max(max1 * max2 * max3, min1 * min2 * max1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
