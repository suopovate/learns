package vt.dp;

import java.util.*;

class QskMpc202311 {

  /**
   * 错误解法 前缀和(只适用于 找一个 数组中的 子数组(即元素不能重复的))
   */
  public int maxDollCountPresum(List<Integer> dollList, int luckyNum) {
    int maxNumber = -1;
    // 前缀和数组: 0 1 3 5 7 9
    // 原数组:    1 2 2 2 2
    List<Integer> preSums = new ArrayList<>(dollList.size() + 1);
    // 前缀和和对应的下标
    Map<Integer, Integer> preSumAndNumber = new HashMap<>();
    preSumAndNumber.put(0, 0);
    preSums.add(0);
    for (int i = 0; i < dollList.size(); i++) {
      int preSum = dollList.get(i) + preSums.get(i);
      preSums.add(preSum);
      // 这里我们只保存最前面的那个就可以了(路径最长),注意,sum0固定为0,从sum1开始.
      int preSunNum = i + 1;
      preSumAndNumber.putIfAbsent(preSum, preSunNum);
      if (preSumAndNumber.containsKey(preSum - luckyNum)) {
        maxNumber = Math.max(preSunNum - preSumAndNumber.get(preSum - luckyNum), maxNumber);
      }
    }
    return maxNumber == 0 ? -1 : maxNumber;
  }

  public int maxDollCountJp(List<Integer> dollList, int luckyNum) {
    int n = dollList.size();
    //dp[i]表示前i个填满j的最大数字个数
    int[][] dp = new int[n + 1][luckyNum + 1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(dp[i], -1);
      dp[i][0] = 0;
    }
    for (int i = 1; i <= n; i++) {
      int val = dollList.get(i - 1);
      for (int j = 1; j <= luckyNum; j++) {
        dp[i][j] = dp[i - 1][j]; //默认第i个数字不要
        if (val <= j && dp[i - 1][j - val] != -1) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - val] + 1);
        }
      }
    }
    return dp[n][luckyNum];
  }

  /**
   * 我这个核心的区别在于,我在做状态推导时
   * 假如j是: 0 1 2 3 4
   * 假如val: 1
   * luckNum: 4
   * 假设j对应位置的值都是有效的(即0,1,2,3,4路径都存在)
   * <p>
   * 我是按照
   * j + val <= luckNum 的思路来推导的
   * 然后设置 j + val 的路径长度, 对比 max(j + val)
   * 而答案的推导是:
   * 对于当前j,是否存在一个j1: j1 + val = j
   * 然后设置 j 的路径长度, 然后对比 max(j1(上个状态集的路径长度) + 1 和 j(上个状态集的路径长度)),看是不是现在有个路径 大于原来j的路径
   * 的确 答案的 更干净点,直接设置当前j的长度,而不像我的思路,是设置 j + val 的长度...
   */
  public int maxDollCount(List<Integer> dollList, int luckyNum) {
    // 构建矩阵,行为每个数(阶段),列则是状态集,行+列的值则是 x 这个阶段,决策后,为了达到这个状态的路径(在这里是 经过的最多的数)
    // 这里有个问题,就是状态值必须限制范围,否则会很大,所以,我们限制为luckyNum,决策过程中,超出这个状态范围的结果,就丢掉
    int[][] matrix = new int[dollList.size()][luckyNum + 1];
    // 第一行需要预初始化
    if (dollList.get(0) < luckyNum) {
      matrix[0][dollList.get(0)] = 1;
    }
    for (int i = 1; i < matrix.length; i++) {
      Integer curVal = dollList.get(i);
      // 遍历所有状态,基于前一状态集,推导当前状态集
      for (int j = 0; j <= luckyNum; j++) {
        // 不放的情况
        matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j]);
        // 放的情况
        // 第一行 则直接累加当前数
        // 其他行 则需要
        // 1. 上一行的对应这个结果有效(值>0,意味着路径有数字累加)
        // 2. 跟当前 数字(curVal) 累加后的结果 不超出范围(luckyNum)
        if ((j == 0 || matrix[i - 1][j] > 0) && j + curVal <= luckyNum) {
          // 这里max是因为,可能别的路径,在 j + curVal 上的值 会更大
          matrix[i][j + curVal] = Math.max(matrix[i][j + curVal], matrix[i - 1][j] + 1);
        }
      }
    }
    return matrix[dollList.size() - 1][luckyNum] == 0 ? -1 : matrix[dollList.size() - 1][luckyNum];
  }

  public int maxDollCount2(List<Integer> dollList, int luckyNum) {
    // 构建矩阵,行为每个数(阶段),列则是状态集,行+列的值则是 x 这个阶段,决策后,为了达到这个状态的路径(在这里是 经过的最多的数)
    // 这里有个问题,就是状态值必须限制范围,否则会很大,所以,我们限制为luckyNum,决策过程中,超出这个状态范围的结果,就丢掉
    int[][] matrix = new int[dollList.size()][luckyNum + 1];
    // 第一行需要预初始化
    if (dollList.get(0) < luckyNum) {
      matrix[0][dollList.get(0)] = 1;
    }
    for (int i = 1; i < matrix.length; i++) {
      Integer curVal = dollList.get(i);
      // 遍历所有状态,基于前一状态集,推导当前状态集
      for (int j = 0; j <= luckyNum; j++) {
        // 不放的情况
        matrix[i][j] = matrix[i - 1][j];
        // 放的情况,寻找另一个 j1 = j-curVal
        if (j > curVal && matrix[i - 1][j - curVal] > 0) {
          matrix[i][j] = Math.max(matrix[i][j], matrix[i - 1][j - curVal] + 1);
        }
      }
    }
    return matrix[dollList.size() - 1][luckyNum] == 0 ? -1 : matrix[dollList.size() - 1][luckyNum];
  }

  public int maxDollCount2SpaceOptmized(List<Integer> dollList, int luckyNum) {
    // 构建矩阵,行为每个数(阶段),列则是状态集,行+列的值则是 x 这个阶段,决策后,为了达到这个状态的路径(在这里是 经过的最多的数)
    // 这里有个问题,就是状态值必须限制范围,否则会很大,所以,我们限制为luckyNum,决策过程中,超出这个状态范围的结果,就丢掉
    int[] matrix = new int[luckyNum + 1];
    if (dollList.get(0) < luckyNum) matrix[dollList.get(0)] = 1;
    for (int i = 1; i < dollList.size(); i++) {
      Integer curVal = dollList.get(i);
      // 空间优化版,从后往前,可以确保处理过程中数据的正确性
      // 遍历所有状态,基于前一状态集,推导当前状态集
      for (int j = luckyNum; j >= 0; j--) {
        // 放的情况,寻找另一个 j1 = j-curVal
        if (j > curVal && matrix[j - curVal] > 0) {
          matrix[j] = Math.max(matrix[j], matrix[j - curVal] + 1);
        }
      }
    }
    return matrix[luckyNum] == 0 ? -1 : matrix[luckyNum];
  }

  int maxCount = 0;
  List<Integer> dollList = new ArrayList<>();
  int luckyNum = 0;

  /**
   * 判断每个元素是否需要加入,构成一整棵树,然后递归往下直到叶子,什么时候算叶子呢?当累加的值 >= luckyNum 时.
   */
  public int maxDollCountViolent(List<Integer> dollList, int luckyNum) {
    this.dollList = dollList;
    this.luckyNum = luckyNum;
    doMaxDollCount(0, 0, 0);
    return maxCount == 0 ? -1 : maxCount;
  }

  public void doMaxDollCount(int index, int num, int count) {
    if (index >= dollList.size() || num >= luckyNum) {
      if (num == luckyNum) {
        maxCount = Math.max(maxCount, count);
      }
      return;
    }
    // 不加上当前
    doMaxDollCount(index + 1, num, count);
    // 加上当前的
    doMaxDollCount(index + 1, num + dollList.get(index), count + 1);
  }

  public static void main(String[] args) {
    System.out.println(new QskMpc202311().maxDollCount(Arrays.asList(4, 3, 7), 7));
    System.out.println(new QskMpc202311().maxDollCount2(Arrays.asList(4, 3, 7), 7));
    System.out.println(new QskMpc202311().maxDollCount2SpaceOptmized(Arrays.asList(4, 3, 7), 7));
    System.out.println(new QskMpc202311().maxDollCountJp(Arrays.asList(4, 3, 7), 7));
    System.out.println(new QskMpc202311().maxDollCount(Arrays.asList(1, 1, 2, 2, 3, 3), 6));
    System.out.println(new QskMpc202311().maxDollCount2(Arrays.asList(1, 1, 2, 2, 3, 3), 6));
    System.out.println(new QskMpc202311().maxDollCount2SpaceOptmized(Arrays.asList(1, 1, 2, 2, 3, 3), 6));
    System.out.println(new QskMpc202311().maxDollCountJp(Arrays.asList(1, 1, 2, 2, 3, 3), 6));
    System.out.println(new QskMpc202311().maxDollCount(Arrays.asList(2, 2, 3, 3), 1));
    System.out.println(new QskMpc202311().maxDollCount2(Arrays.asList(2, 2, 3, 3), 1));
    System.out.println(new QskMpc202311().maxDollCount2SpaceOptmized(Arrays.asList(2, 2, 3, 3), 1));
    System.out.println(new QskMpc202311().maxDollCountJp(Arrays.asList(2, 2, 3, 3), 1));
    System.out.println(new QskMpc202311().maxDollCount(Arrays.asList(6, 5, 4, 3, 2, 1), 9));
    System.out.println(new QskMpc202311().maxDollCount2(Arrays.asList(6, 5, 4, 3, 2, 1), 9));
    System.out.println(new QskMpc202311().maxDollCount2SpaceOptmized(Arrays.asList(6, 5, 4, 3, 2, 1), 9));
    System.out.println(new QskMpc202311().maxDollCountJp(Arrays.asList(6, 5, 4, 3, 2, 1), 9));
  }

}
