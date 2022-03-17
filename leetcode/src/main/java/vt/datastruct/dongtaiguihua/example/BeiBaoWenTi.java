package vt.datastruct.dongtaiguihua.example;

/**
 * 背包问题
 * https://time.geekbang.org/column/article/74788
 * 对于一组不同重量、不可分割的物品,我们需要选择一些装入背包,在满足背包最大重量限制的前提下,背包中物品总重量的最大值是多少呢？
 * 物品：1 2 3 4 5 背包限重：9
 * 那答案肯定就是 9 可能的放法就有很多种了  234 45 135 等
 */
public class BeiBaoWenTi {


}

/**
 * 回溯算法
 */
class HuiSuSuanFa {

    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
    private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    public void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了,i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }

}

class HuiSuSuanFaShengJi {

    private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量
    private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值false

    public void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return; // 重复状态
        mem[i][cw] = true; // 记录(i, cw)这个状态
        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }

}

class DongTaiGuiHua {

    // weight:物品重量，n:物品个数，w:背包可承载重量
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1]; // 默认值false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) {//把第i个物品放入背包
                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n - 1][i] == true) return i;
        }
        return 0;
    }

}

/**
 * 实际上，我们只需要一个大小为 w+1 的一维数组就可以解决这个问题。动态规划状态转移的过程，都可以基于这个一维数组来操作
 */
class DongTaiGuiHuaShengJi {

    // weight:物品重量，n:物品个数，w:背包可承载重量
    public static int knapsack2(int[] weight, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - weight[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j] == true) states[j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }

}

