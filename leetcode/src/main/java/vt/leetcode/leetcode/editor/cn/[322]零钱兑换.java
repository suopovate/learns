package vt.leetcode.leetcode.editor.cn;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.Console;

import java.util.*;
import java.util.stream.Collectors;

class CoinChange {

    public static void main(String[] args) {
        int[] ints = { 3, 7, 405, 436 };
        int amount = 8839;
        StopWatch stopWatch = new StopWatch();
        Solution solution = new CoinChange().new Solution();
        stopWatch.start("1");
        Console.log(solution.coinChange1(
            ints,
            amount
        ));
        stopWatch.stop();
        stopWatch.start("2");
        Console.log(solution.coinChange2(
            ints,
            amount
        ));
        stopWatch.stop();
        stopWatch.start("3");
        Console.log(solution.coinChangeRecursion(
            ints,
            amount
        ));
        stopWatch.stop();

        Console.log(stopWatch.prettyPrint());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        /**
         * 思路关键是
         * 认为每个阶段是每种硬币只能选一个得情况下,求最小值
         * 然后 执行 amount/min(coins) + 1 个阶段(即所有阶段取最小币值时需要耗费的阶段)
         */
        public int coinChange(int[] coins, int amount) {
            return coinChangeRecursion(coins, amount);
        }

        public int coinChange1(int[] coins, int amount) {
            // 写出状态转移方程
            // F(i,j) = min(F(i,j),F(i,j-val)+1)
            // i = amount / min(coins) + 1
            // j <= amount
            if (coins == null || coins.length == 0) return -1;
            if (amount <= 0) return 0;
            Arrays.sort(coins);
            int phase = amount / coins[0] + 1;
            int[][] matrix = new int[phase][amount + 1];
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] <= amount) {
                    matrix[0][coins[i]] = 1;
                }
            }
            for (int i = 1; i < phase; i++) {
                for (int j = 0; j <= amount; j++) {
                    matrix[i][j] = matrix[i - 1][j];
                    for (int coin : coins) {
                        if (j > coin && matrix[i - 1][j - coin] != 0) {
                            matrix[i][j] = matrix[i][j] == 0 ? matrix[i - 1][j - coin] + 1 : Math.min(
                                matrix[i][j],
                                matrix[i - 1][j - coin] + 1
                            );
                        }
                    }
                }
            }
            printMatrix(matrix);
            return matrix[phase - 1][amount] == 0 ? -1 : matrix[phase - 1][amount];
        }

        /**
         * 把阶段分为 硬币的种类
         * 每个阶段,把当前硬币选 0-k个,k<=amount/coins[i]+1
         * 和 1 的区别 其实主要是在于 阶段的定义
         * <p>
         * 1 认为 每个阶段是 每种硬币选一个,然后求结果,最终经过k个阶段,得出结果
         * 2 认为 每个阶段是 每种硬币选0-k个,然后求结果,最终经过coins.length个阶段,得出结果
         */
        public int coinChange2(int[] coins, int amount) {
            // 写出状态转移方程
            // F(i,j) = min(F(i,j),F(i,j-val)+1)
            // i = amount / min(coins) + 1
            // j <= amount
            if (coins == null || coins.length == 0) return -1;
            if (amount <= 0) return 0;
            Arrays.sort(coins);
            int[][] matrix = new int[coins.length][amount + 1];

            // 初始化第一种硬币
            for (int k = 0; k <= amount / coins[0]; k++) {
                matrix[0][k * coins[0]] = k;
            }

            // 每一种硬币
            for (int i = 1; i < coins.length; i++) {
                // 每一种累积金额
                for (int j = 0; j <= amount; j++) {
                    // 同硬币的不同数额 + 旧的累积金额 是否能组成新的累计金额
                    for (int k = 0; k <= j / coins[i]; k++) {
                        // 这里要考虑一下 如果当前这个 k * coins[i] 刚好等于 j ,就等于 咱是用上一个阶段的 0 + k * coin,这种情况也是有效的k
                        if ((j - k * coins[i] == 0) || matrix[i - 1][j - k * coins[i]] > 0) {
                            matrix[i][j] = matrix[i][j] == 0 ? matrix[i - 1][j - k * coins[i]] + k : Math.min(
                                matrix[i][j],
                                matrix[i - 1][j - k * coins[i]] + k
                            );
                        }
                    }
                }
            }
            printMatrix(matrix);
            return matrix[coins.length - 1][amount] == 0 ? -1 : matrix[coins.length - 1][amount];
        }

        /**
         * 优化过的 完美背包 dp 状态转移方程
         * F(i,j) = min(F(i-1,j),F(i,j-val)+1)
         */
        public int coinChange3(int[] coins, int amount) {
            // 写出状态转移方程
            if (coins == null || coins.length == 0) return -1;
            if (amount <= 0) return 0;
            Arrays.sort(coins);
            int[][] matrix = new int[coins.length + 1][amount + 1];
            // 第一列全设为 最大值避免影响我们正常阶段决策.
            Arrays.fill(matrix[0], Integer.MAX_VALUE);
            matrix[0][0] = 0;
            // 每一种硬币
            for (int i = 1; i < coins.length + 1; i++) {
                // 每一种累积金额
                for (int j = 0; j <= amount; j++) {
                    // 这个很容易被遗忘,如果硬币不参与决策,就应该继承上一步
                    if (j < coins[i - 1]) {
                        matrix[i][j] = matrix[i - 1][j];
                    } else {
                        // 如果 j - coin 的值是 max_Val,说明这个j - coin的结果是未知的,那就不应该参与决策,直接继承.
                        matrix[i][j] = matrix[i][j - coins[i - 1]] == Integer.MAX_VALUE
                                       ? matrix[i - 1][j]
                                       : Math.min(
                                           matrix[i - 1][j],
                                           matrix[i][j - coins[i - 1]] + 1
                                       );
                    }
                }
            }
            printMatrix(matrix);
            return matrix[coins.length][amount] == 0 || matrix[coins.length][amount] == Integer.MAX_VALUE ? -1 : matrix[coins.length][amount];
        }

        int count = Integer.MAX_VALUE;
        int amount = 0;

        /**
         * 回溯+备忘录
         */
        public int coinChangeRecursion(int[] coins, int amount) {
            // 写出状态转移方程
            if (coins == null || coins.length == 0) return -1;
            if (amount <= 0) return 0;
            this.amount = amount;
            List<Integer> coinList = Arrays.stream(coins).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            //            doCoinChangeRecursion(coinList, 0, 0, 0);
            //            doCoinChangeRecursion2(coinList, 0, 0, amount);
            //            return this.count == Integer.MAX_VALUE ? -1 : this.count;
            return doCoinChangeRecursion3(coinList, amount);
        }

        public void doCoinChangeRecursion(List<Integer> coins, int accumulateCount, int index, int accumulateAmount) {
            if (accumulateAmount == amount) {
                this.count = Math.min(this.count, accumulateCount);
                return;
            }
            if (index == coins.size()) {
                return;
            }
            int coinNumber = (amount - accumulateAmount) / coins.get(index);
            for (int j = coinNumber; j >= 0; j--) {
                // 贪婪算法
                doCoinChangeRecursion(coins, accumulateCount + j, index + 1, accumulateAmount + j * coins.get(index));
            }
        }

        /**
         * 这个不是回溯算法,
         * 针对每种硬币 - 硬币数,只能调用一次.
         * 所以也没办法做备忘录,因为整个执行路径都不会重复.
         */
        public void doCoinChangeRecursion2(List<Integer> coins, int accumulateCount, int index, int remain) {
            if (remain == 0) {
                this.count = Math.min(this.count, accumulateCount);
                return;
            }
            if (index == coins.size()) {
                return;
            }
            int coinNumber = remain / coins.get(index);
            for (int j = coinNumber; j >= 0; j--) {
                // 贪婪算法
                doCoinChangeRecursion2(coins, accumulateCount + j, index + 1, remain - j * coins.get(index));
            }
        }

        /**
         * 应该记录一个结果的
         */
        Map<Integer, Integer> mem = new HashMap<>();

        /**
         * 求amount最少需要多少个硬币
         */
        public Integer doCoinChangeRecursion3(List<Integer> coins, int amount) {
            if (amount == 0) {
                // 不用组
                return 0;
            }
            if (mem.containsKey(amount)) return mem.get(amount);
            int min = Integer.MAX_VALUE;
            for (Integer coin : coins) {
                // 这里1个就够了,后面的也没必要去看了
                if (amount == coin) return 1;
                // 还要其他硬币参与
                if (amount > coin) {
                    Integer res = doCoinChangeRecursion3(coins, amount - coin);
                    if (res > 0) {
                        // res + 1 是子节点的 加上当前的硬币
                        min = Math.min(min, res + 1);
                    }
                }
            }
            min = min == Integer.MAX_VALUE ? -1 : min;
            mem.put(amount, min);
            return min;
        }

        public void printMatrix(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
