package vt.datastruct.dongtaiguihua.example;

import cn.hutool.core.lang.Console;

import java.util.Arrays;

/**
 * 假设有 n 个商品，满减价格为 m
 */
public class ManJianWenTi {

    public static class HuiSuSuanfa {

        /**
         * 商品
         */
        int store[] = {54, 96, 89, 67, 13, 47, 77};
        /**
         * 满减门槛
         */
        int threshold = 200;
        /**
         * 记录最大金额
         */
        int maxMoney = 0;
        /**
         * 记录购买的商品
         */
        int[] validStores = new int[0];

        /**
         * @param i  商品索引
         * @param cm 当前已经累加的金额
         */
        public void compute(int i, int cm, int[] path) {
            // 终止条件
            if (i == store.length || cm >= threshold) {
                // 我们要找到一个最接近200的值,在大于等于200的基础上
                if (cm >= threshold) {
                    if (maxMoney == 0 || maxMoney > cm) {
                        maxMoney = cm;
                        validStores = path;
                    }
                }
                return;
            }
            int[] cPath = Arrays.copyOf(path, path.length + 1);
            cPath[cPath.length - 1] = i;
            // 如果未达到终止条件，我们需要沿着路径寻找
            // 如果当前商品不加入计算
            compute(i + 1, cm, path);
            // 如果当前商品加入计算
            compute(i + 1, store[i] + cm, cPath);
        }
    }

    public static void main(String[] args) {
        HuiSuSuanfa huiSuSuanfa = new HuiSuSuanfa();
        huiSuSuanfa.compute(0, 0, new int[0]);
        Console.log(huiSuSuanfa.maxMoney);
        Console.log(huiSuSuanfa.validStores);
    }

}
