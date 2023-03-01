package vt.leetcode;

import cn.hutool.core.lang.Console;

/**
 * 前缀和系列问题
 * <a href="https://www.notion.so/vate/e6622491f080403da75c9bf752107c28">链接<a/>
 *
 * @author vate
 * @date 2022/06/24 23:45:20
 */
public class PrefixSum {

    public static void main(String[] args) {
        //["NumMatrix","sumRegion","sumRegion","sumRegion"] [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
        Console.log(sumRegionOptmize(
            new int[][]{ { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 }, { 1, 0, 3, 0, 5 } },
            2, 1,
            4, 3
        ));
    }

    public static int sumRange(int[] nums, int left, int right) {
        int[] preSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                preSum[i] = nums[i];
            } else {
                preSum[i] = preSum[i - 1] + nums[i];
            }
        }
        return preSum[right] - preSum[left - 1];
    }

    public static int sumRangeOffice(int[] nums, int left, int right) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        return preSum[right + 1] - preSum[left];
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     */
    public static int subarraySum(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        // 遍历所有的子数组
        for (int i = 1; i < preSum.length; i++) {
            for (int j = 0; j < i; j++) {
                if (k == preSum[i] - preSum[j]) {
                    result++;
                }
            }
        }
        return result;
    }

    // 进阶-矩阵和问题

    /**
     * 矩阵和
     */
    public int sumRegion(int[][] matrix, int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    /**
     * 矩阵和 前缀和 优化版
     * 看这源码 需要结合这个图
     * <p>
     * <img
     * src="https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F552999e5-b7f8-4d69-b9a6-73f584f47210%2Fec87ffc4-037b-41ea-aae7-af0605da7a49.jpeg?table=block&id=4b954dab-d248-48f4-9801-7178b63f9d24&spaceId=75ab23a3-ef5d-42f7-808c-9d908a4bbf63&width=2000&userId=cebcce6e-e621-439e-83ec-3f65f617bb14&cache=v2"/>
     */
    public static int sumRegionOptmize(int[][] matrix, int row1, int col1, int row2, int col2) {
        // todo vate: 未完成调试一下 2022-07-05 16:19:25
        int[][] preSumMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (int i1 = 0; i1 <= i; i1++) {
                    for (int j1 = 0; j1 <= j; j1++) {
                        // 保存在 i j 位置，从原点到该点的矩阵和
                        preSumMatrix[i][j] += matrix[i1][j1];
                    }
                }
            }
        }
        return preSumMatrix[row2][col2] - preSumMatrix[row2][col1] - preSumMatrix[row1][col2] + preSumMatrix[row1][col1];
    }

}
