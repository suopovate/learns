package vt.leetcode.leetcode.editor.cn;

import sun.text.resources.cldr.ii.FormatData_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class RotateFunction {
    public static void main(String[] args) {
        Solution solution = new RotateFunction().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 📢：滑动窗口解决旋转数组的问题的话，滑动的过程中得到的窗口，
         * 代表的是任意一个旋转的结果，并不是说，往前滑动一下就是旋转一次！！！
         */
        public int maxRotateFunction(int[] nums) {
            // 整体思路是 想象 把数组 复制一份 左右拼接在一起
            // 12345 12345 然后 按照nums.length为窗口，从做往右滑动，就相当于是所有的 “旋转” 的组合了
            int n = nums.length;
            int[] sum = new int[n * 2];
            // 构建两个数组的前缀和，i是数字在数组中的索引
            // 前缀和是为了降低时间复杂度，优化计算
            sum[0] = nums[0];
            for (int i = 1; i < n * 2; i++) {
                sum[i] = sum[i - 1] + nums[i % n];
            }
            // 从第一个窗口的值开始
            int ans = 0;
            for (int i = 0; i < n; i++) ans += nums[i] * (i);
            // 从第二个窗口开始滑动，i是当前窗口的尾元素的位置，这个i是相对于 两个 nums 拼起来的
            for (int i = n, cur = ans; i < n * 2; i++) {
                System.out.println(cur);
                // 当前窗口的值 = 前一个窗口 - 旧的首元素(都是0) + 新的尾元素
                // 12 25 + 12 37 - 11
                cur += nums[i % n] * (n - 1);
                // 减去中间部分的差额，这里就要涉及到 前缀和的应用了
                // 11
                cur -= (sum[i - 1] - sum[i - n]);
                ans = Math.max(ans, cur);
            }
            System.out.println("---");
            //            for (int i = 0; i < sum.length; i++) {
            //                System.out.println(sum[i]);
            //            }
            return ans;
        }


        // 4326 4326
        public int maxRotateFunctionOri(int[] nums) {
            int n = nums.length;
            int[] sum = new int[n * 2 + 10];
            for (int i = 1; i <= 2 * n; i++) sum[i] = sum[i - 1] + nums[(i - 1) % n];
            int ans = 0;
            for (int i = 1; i <= n; i++) ans += nums[i - 1] * (i - 1);
            // 5 6 7
            for (int i = n + 1, cur = ans; i < 2 * n; i++) {
                // 12
                cur += nums[(i - 1) % n] * (n - 1);
                // 19 - 7
                cur -= sum[i - 1] - sum[i - n];
                if (cur > ans) ans = cur;
            }
            return ans;
        }

        /**
         * 时间复杂度为O(n^2)，会超时
         */
        public int maxRotateFunction2(int[] nums) {
            // k < length，k = length时就又转回去了
            int k = nums.length - 1;
            Integer maxSum = null;
            for (int i = 0; i <= k; i++) {
                int sum = 0;
                // 转i次时，首尾数字所在的位置
                int startIndex = 0;
                startIndex = (-i % nums.length) + nums.length;
                startIndex = startIndex == nums.length ? 0 : startIndex;
                int index = startIndex;
                // 遍历Nums，累加 每个数 * 转i次时对应的索引
                for (int num : nums) {
                    sum += num * index;
                    index = index + 1 == nums.length ? 0 : index + 1;
                }
                if (maxSum == null || sum > maxSum) {
                    maxSum = sum;
                }
            }
            return maxSum == null ? 0 : maxSum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
