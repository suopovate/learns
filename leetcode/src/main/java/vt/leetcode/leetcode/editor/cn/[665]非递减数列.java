package vt.leetcode.leetcode.editor.cn;

class NonDecreasingArray {
    public static void main(String[] args) {
        Solution solution = new NonDecreasingArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkPossibility(int[] nums) {
            // 双指针,判断
            // 我觉得核心就三点
            // 1. 判断是否有递减的情况,有就计数
            // 2. 检测到有递减时,需要将其数据处理一下,再往后,这样能检测处理后是否有效
            // 3. 判断最后用了几次
            // 那如何处理比较好?
            // 1. 如果改变i-1,则尽量维持前面的递减状态: i-1 不能小于 i - 1 - 1
            // 2. 改变i,则尽量维持后面的递减状态: 那 i 要尽量小一点,那选谁比较好? i-1
            // 因为此时 i 肯定是 小于 i-1
            // 我们的原则又是希望数尽可能的小
            // 结论:
            // 所以 首选 让 i-1 变成 i
            // 然后 才是 让 i 变成 i-1
            int p = 0, q = p + 1;
            int count = 0;
            while (q < nums.length) {
                if (nums[p] > nums[q]) {
                    count++;
                    // 做出调整
                    if (p == 0) {
                        nums[p] = nums[q];
                    } else {
                        if (nums[q] >= nums[p - 1]) {
                            // 把 i -1 降为 i
                            nums[p] = nums[q];
                        } else {
                            // 把 i 升为 i - 1
                            nums[q] = nums[p];
                        }
                    }
                }
                if (count > 1) {
                    return false;
                }
                p++;
                q++;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
