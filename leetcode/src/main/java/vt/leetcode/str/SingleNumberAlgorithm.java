package vt.leetcode.str;

/**
 * @author vate
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 牛逼 使用亦或来做这个事情，因为亦或的特性 0 ^ 0 = 0, 0 ^ 1 = 1
 *
 * 3 ^ 3 = 0 3 ^ 1
 *
 * 1 ^ 0 ^ 1 ^ 0 = 0
 * 1 ^ 1 ^ 0 ^ 0 = 0
 * 上面这两条公式 就是原因 就只要你是成对出现的 你落单那个 就必定会得到答案
 */
public class SingleNumberAlgorithm {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(3 ^ 1 ^ 3 ^ 1);
    }
}
