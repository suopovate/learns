package vt.leetcode.leetcode.editor.cn;

class GasStation {
    public static void main(String[] args) {
        Solution solution = new GasStation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 这道理，利用贪心算法来解
         * 核心思路，当然是剪枝，降低时间复杂度。
         * 1. 寻找暴力破解的思路
         * 2. 观察规律，寻找冗余的计算，并优化方案，减少冗余计算，达到提升效率的目的。
         * <p>
         * 这里的关键点在于：
         * 1. 对于某个站i,gas[i] 是指，去下一个站的消耗，cost[i]，是这个站的加油数。
         * 2. 所以 gc = gas - cost = 经过这个站到下一个站，能剩下的油。
         * 3. 对于 i - j ，如果i 到 j的时候，油是负数，即i 可以 到 j-1，但是不能到j，那 i - j 的任意一个点，肯定也不能到j，
         * 既然i不能到j，就意味着i不可能是起点，那i-j的任意点，也不可能是起点，当然j可能是起点。
         * 剪枝：
         * 首先 针对 不可能达到的情况，有一个办法 O(N) 直接判断，不管我们从哪个点开始，我们都是要走一圈的
         * 那我们可以知道，我们的总消耗，肯定是 全部gas加起来，我们也知道我们肯定会把cost也全加一遍，
         * 所以 总 加油数 肯定得 大于 总 消耗，不然你没法走一圈，这里就能得出结论，是否可以走一圈。
         * 然后，不可以走一圈的，直接返回false，不然就肯定是能走一圈。
         * 接下来寻找 那个 可以走完一圈的起点。
         * 非优化情况：从所有的点开始，都尝试走一圈，直到某一个起点，能走完一圈，O(n*n)。
         * 优化的情况：利用我们观察到的，i不能到j，那么i到j-1都不可能是起点，那我们就可以直接从j开始计算，省掉了j-1 - i + 1个元素的计算。
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int sum = 0;
            for (int i = 0; i < gas.length; i++) {
                sum += gas[i] - cost[i];
            }
            if (sum < 0) {
                return -1;
            }
            // 走到这里，说明肯定是能走完一圈的。
            for (int i = 0; i < gas.length;) {
                int cur = i;
                int start = i;
                sum = 0;
                while (true) {
                    sum += gas[cur] - cost[cur];
                    // 说明cur无法往下走了，说明 i - cur 是可以到的，i - cur + 1 到不了。
                    if (sum < 0) {
                        // 那就以cur为下一个i好了
                        // 这里考虑边界情况，假如i本身就无法走到下一个的时候 cur == i
                        i = cur + 1;
                        break;
                    }
                    // 走到下一个站
                    cur = (cur + 1) % gas.length;
                    if (cur == start) return start;
                }
            }
            return -1;
        }

        public int canCompleteCircuitViolent(int[] gas, int[] cost) {
            int liters = 0;
            int cur = 0;
            // 从每一个站点开始
            for (int i = 0; i < gas.length; i++) {
                // 从当前位置开始
                cur = i;
                // 初始化邮箱
                liters = gas[cur];
                // 尝试走一圈
                while (liters >= cost[cur]) {
                    // 剩下的油
                    liters = liters - cost[cur];
                    // 更新位置
                    cur = (cur + 1) % gas.length;
                    if (cur == i) {
                        return i;
                    }
                    // 加油
                    liters += gas[cur];
                }
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
