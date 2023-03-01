package vt.leetcode;

import cn.hutool.core.lang.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n数之和
 * <a href="https://www.notion.so/vate/N-7e3aaf72f26b4c0bb7635b046ed71043">链接<a/>
 *
 * @author vate
 * @date 2022/06/24 23:45:20
 */
public class NnumSum {

    public static void main(String[] args) {
        //        Console.log(nNumSum(3, new int[]{ 1, 1, 1, 2, 3, 3, 3, 3, 4, 5, 6 }, 7));
        //        Console.log(nNumSum(3, new int[]{ -1,0,1,2,-1,-4 }, 0));
        //        Console.log(nNumSum(3, new int[]{0,0,0 }, 0));
        //解答失败: 测试用例:[-1,0,1,2,-1,-4,-2,-3,3,0,4] 测试结果:[[-4,0,4],[-4,1,3],[-3,1,2],[-2,0,2],[-1,0,1]] 期望结果:[[-4,0,4],[-4,1,3],[-3,-1,4],[-3,0,3],[-3,1,2],[-2,-1,3],[-2,0,2],[-1,-1,2],[-1,0,1]] stdout:
        Console.log(nNumSum(3, new int[]{ -1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4 }, 0));
    }

    public static List<List<Integer>> nNumSum(int n, int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        int lo = 0, hi = nums.length - 1;
        if (n < 2) {
            return results;
        }
        Arrays.sort(nums);
        if (n == 2) {
            while (lo < hi) {
                int temSum = nums[lo] + nums[hi];
                if (temSum < target) {
                    // 略过相等的值
                    while (lo < hi && nums[lo] == nums[++lo]) {}
                } else if (temSum > target) {
                    while (lo < hi && nums[hi] == nums[--hi]) {}
                } else {
                    results.add(Arrays.asList(nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[++lo]) {}
                    while (lo < hi && nums[hi] == nums[--hi]) {}
                }
            }
        } else {
            // 外层循环 固定住n-2个数
            int fixedSize = n - 2;
            // 上一次匹配时 固定数的和
            int fixedTemSum = 0;
            // 这个地方需要想一下 为什么是 <= nums.length -n 呢？简单草稿算一下就知道了
            for (int fixedLowIndex = 0; fixedLowIndex <= nums.length - n; fixedLowIndex++) {
                // 固定住那几个数的和
                int curFixedTemSum = 0;
                for (int i = fixedLowIndex; i < fixedLowIndex + fixedSize; i++) {
                    curFixedTemSum += nums[i];
                }
                // 如果往后移动后 固定数的值未变 则略过
                if (fixedLowIndex > 0 && curFixedTemSum == fixedTemSum) {
                    continue;
                }
                fixedTemSum = curFixedTemSum;
                // 剩下两个指针在固定数右端遍历
                lo = fixedLowIndex + fixedSize;
                hi = nums.length - 1;
                while (lo < hi) {
                    int temSum = curFixedTemSum + nums[lo] + nums[hi];
                    if (temSum < target) {
                        // 略过相等的值
                        while (lo < hi && nums[lo] == nums[++lo]) {}
                    } else if (temSum > target) {
                        while (lo < hi && nums[hi] == nums[--hi]) {}
                    } else {
                        results.add(Arrays.asList(curFixedTemSum, nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[++lo]) {}
                        while (lo < hi && nums[hi] == nums[--hi]) {}
                    }
                }
            }
        }
        return results;
    }

}
