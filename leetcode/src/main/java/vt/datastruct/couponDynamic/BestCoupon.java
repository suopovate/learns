package vt.datastruct.couponDynamic;

import java.io.Console;
import java.util.Arrays;

/**
 * 动态规划算法之基于商品价格和满减价格，计算最佳的组合，得到一个>=满减价格的，最好组合
 */
public class BestCoupon {

    /**
     * 商品价格
     */
    int[] prices;
    /**
     * 满减价格
     */
    int bestPrice;

    public BestCoupon(int[] prices, int bestPrice) {
        this.prices = prices;
        this.bestPrice = bestPrice;
    }

    /**
     * 基于商品的数量将整个排列分为
     */
    public int[] bestCoupon() {
        // 每个阶段最多具备多少个状态，暂定为满减金额的三倍吧，超过太多的也没意义
        int maxPrice = bestPrice * 3;
        // 每个阶段的状态
        boolean[][] stats = new boolean[prices.length][maxPrice + 1];
        // 每个阶段需要依赖前一个阶段而得到,0号阶段需特殊处理
        // 不放
        stats[0][0] = true;
        // 放
        stats[0][prices[0]] = true;
        // 遍历所有的商品(阶段)，并基于上一阶段得到当前阶段的状态集合
        for (int i = 1; i < prices.length; i++) {
            // 不放，原封不动继承上一阶段的结果即可
            for (int price = 0; price <= maxPrice; price++) {
                stats[i][price] = stats[i - 1][price];
            }
            // 放，则需要基于上一次的结果，进行计算，得到新结果
            // 注意这里是先算得，最大价格 - 当前商品价格，得到差价然后从最大差价开始逐渐缩小
            // 为什么要这样呢？假如我们从 0 -> 最大差价，那可能，上个阶段的0+当前商品价5，得到结果5为true，而原本是false，
            // 会怎样？原本上个阶段没有5，是你本次加得到的结果，你却会把他当成是上个阶段的状态，这不就乱套了么？
            // 所以要从后往前，这样你每次加完上个阶段得到的新状态，肯定是落在后方，而我们的处理顺序是往前，就不会冲突了。
            for (int price = maxPrice - prices[i]; price >= 0; price--) {
                // 如果上一阶段存在则加上
                if (stats[i - 1][price]) {
                    stats[i][price + prices[i]] = true;
                }
            }
        }
        int resultPrice = -1;
        // 得到了最终阶段的状态集合后，我们就知道哪个值是最符合要求的，我们需要拿到 >= bestPrice，最小的那个总价
        for (int price = bestPrice; bestPrice <= maxPrice; price++) {
            if (stats[prices.length - 1][price]) {
                resultPrice = price;
                break;
            }
        }
        if (resultPrice == -1) {
            return new int[]{};
        }
        // 找到了最佳的价格，基于该价格，逆推路径
        // 其实逆推的过程，就是基于 resultPrice从最后一个阶段开始，通过当前阶段的这个状态是否需要加上当前阶段商品价格得出，而可以分出一个分叉，
        // 然后递推到上一阶段，最后就会变成一棵树...然后从根节点到叶子节点的所有路径，就是我们的所有组合...
        // 这里先简单点，我们只有在当前阶段需要加上商品价格的情况，才处理，不考虑不需要的情况...
        int[] resultPrices = new int[prices.length];
        int rpi = 0;
        // 从倒数第一行开始
        int curStatPrice = resultPrice;
        for (int i = prices.length - 1; i >= 1; i--) {
            // 本阶段的状态，是否由上一阶段的有值得出
            int prePriceIfCurExists = curStatPrice - prices[i];
            // 至少意味着本阶段是存在的
            // 上一阶段存不存在就不知道了(我们在当前阶段只知道上一阶段状态的存在与否，不知道上一阶段的状态是如何得来)
            if (prePriceIfCurExists >= 0 && stats[i - 1][prePriceIfCurExists]) {
                resultPrices[rpi++] = prices[i];
                // 更新上一阶段应该具备的值
                curStatPrice = prePriceIfCurExists;
            } else {
                // 到这里本阶段肯定是不存在的，本阶段就不管了，看上一阶段
            }
        }
        // 针对第一个商品，单独处理，如果剩下的价格刚好等于第一个商品价格，那就直接加入，否则就不加（)
        if (curStatPrice > 0 && curStatPrice - prices[0] == 0) {
            resultPrices[rpi] = prices[0];
        }
        return resultPrices;
    }

    public static void main(String[] args) {
        BestCoupon bestCoupon = new BestCoupon(new int[]{ 100, 100, 300, 119, 316, 400, 365 }, 318);
        System.out.println(Arrays.toString(bestCoupon.bestCoupon()));
    }
    
}
