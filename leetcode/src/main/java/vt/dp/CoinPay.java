package vt.dp;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.Console;

import java.util.*;

class CoinPay {

  int coinNum = Integer.MAX_VALUE;

  public int pay(List<Integer> coins, int amount) {
    // 贪心算法,每次选最大的,然后减去,直到为0
    // 但是可能减不完,那就得回退,那就等于是回溯算法了,所以贪心算法在这里行不通,而是用的一种回溯算法
    // 同时每次都是减最大的,也能提升效率,可以考虑做剪枝
    coins.sort(Comparator.reverseOrder());
    payCircular(coins, amount, 0);
    return coinNum;
  }

  public int payOptimized(List<Integer> coins, int amount) {
    // 贪心算法,每次选最大的,然后减去,直到为0
    // 但是可能减不完,那就得回退,那就等于是回溯算法了,所以贪心算法在这里行不通,而是用的一种回溯算法
    // 同时每次都是减最大的,也能提升效率,可以考虑做剪枝
    coins.sort(Comparator.reverseOrder());
    payCircularOptmize(coins, amount, 0);
    return coinNum;
  }

  public int payDp(List<Integer> coins, int amount) {
    // 动态规划
    coins.sort(Comparator.reverseOrder());
    // 迭代公式
    // 假设一个矩阵,i 代表阶段,val 代表当前阶段的coin币值,j 代表积累的金额
    // 针对每个阶段的-每一枚硬币: F(i,j) = min(F(i-1,j-val) + 1, F(i,j))

    // 最小币值决定阶段的下限
    int phases = amount / coins.get(0) + 1;
    int[][] matrix = new int[phases][amount + 1];
    // 第一决策初始化
    for (Integer coin : coins) {
      if (coin <= amount) {
        matrix[0][coin] = 1;
      }
    }
    // 剩下阶段决策基于第一阶段
    for (int i = 1; i < phases; i++) {
      for (int j = 0; j < amount + 1; j++) {
        // 每个阶段都有多枚硬币
        for (Integer coin : coins) {
          if (coin <= j && matrix[i - 1][j - coin] != 0) {
            matrix[i][j] = matrix[i][j] == 0 ? matrix[i - 1][j - coin] + 1 : Math.min(
                matrix[i][j],
                matrix[i - 1][j - coin] + 1
            );
          }
        }
      }
    }
    // amount / minUnit * amount m* m/n
    return matrix[phases - 1][amount];
  }

  public void payCircular(List<Integer> coins, int amount, int coinCount) {
    if (amount == 0) {
      coinNum = Math.min(coinNum, coinCount);
      return;
    }
    // 从大往小减
    for (Integer coin : coins) {
      if (amount >= coin) {
        amount -= coin;
        payCircular(coins, amount, coinCount + 1);
        amount += coin;
      }
    }
  }

  /**
   * 备忘录
   */
  Set<Integer> mem = new HashSet<>();

  public void payCircularOptmize(List<Integer> coins, int amount, int coinCount) {
    if (amount == 0) {
      coinNum = Math.min(coinNum, coinCount);
      return;
    }
    if (mem.contains(amount)) {
      return;
    } else {
      mem.add(amount);
    }
    // 从大往小减
    for (Integer coin : coins) {
      if (amount >= coin) {
        amount -= coin;
        payCircularOptmize(coins, amount, coinCount + 1);
        amount += coin;
      }
    }
  }


  public static void main(String[] args) {
    //    System.out.println(new CoinPay().pay(Arrays.asList(3, 4, 7), 100));
    //    System.out.println(new CoinPay().pay(Arrays.asList(3, 4, 7), 100));
    //    System.out.println(new CoinPay().payOptimized(Arrays.asList(3, 4, 7), 100));
    //    System.out.println(new CoinPay().payOptimized(Arrays.asList(3, 4, 7), 100));

    StopWatch sw = new StopWatch();
    sw.start("original");
    System.out.println(new CoinPay().pay(Arrays.asList(3, 4, 7), 80));
    sw.stop();
    sw.start("optimized");
    System.out.println(new CoinPay().payOptimized(Arrays.asList(27, 398, 90, 323, 454, 413, 70, 315), 6131));
    sw.stop();
    sw.start("dp");
    System.out.println(new CoinPay().payDp(Arrays.asList(27, 398, 90, 323, 454, 413, 70, 315), 6131));
    sw.stop();
    Console.log(sw.prettyPrint());
  }

}
