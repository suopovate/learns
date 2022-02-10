package vt.leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vate
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class MajorityElementAlgorithm {
    /**
     * 哈希
     */
    public static Integer majorityElement(int[] nums) {
            Map<Integer, Integer> map = new HashMap();
            int threshold = nums.length / 2;
            for (int num : nums) {
                map.computeIfPresent(Integer.valueOf(num), (key, count) -> ++count);
                map.computeIfAbsent(Integer.valueOf(num), key -> 1);
                if (map.get(num) > threshold) {
                    return num;
                }
            }
            return 0;
    }

    /**
     * 摩尔投票
     */
    public static Integer majorityElementMoer(int[] nums) {
        int res = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (res != nums[i]) {
                --count;
            } else {
                count++;
            }
            if (count == 0) {
                res = nums[i + 1];
            }
        }
        if (count > 0) {
            return res;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        majorityElement(new int[]{2,2,1,1,1,2,2});
    }
}
