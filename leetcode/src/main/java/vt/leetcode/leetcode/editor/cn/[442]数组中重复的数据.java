package vt.leetcode.leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class FindAllDuplicatesInAnArray {
    public static void main(String[] args) {
        Solution solution = new FindAllDuplicatesInAnArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findDuplicates(int[] nums) {
            return findDuplicates3(nums);
        }

        public List<Integer> findDuplicates1(int[] nums) {
            Map<Integer, Integer> map = new HashMap();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            return map.entrySet().stream().filter(kv -> kv.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        }

        public List<Integer> findDuplicates2(int[] nums) {
            Map<Integer, Integer> map = new HashMap();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            return map.entrySet().stream().filter(kv -> kv.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        }

        public List<Integer> findDuplicates3(int[] nums) {
            int ri = 0;
            int[] temp = new int[nums.length];
            for (int num : nums) {
                temp[num - 1] += 1;
            }
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] > 1) {
                    temp[ri++] = i + 1;
                }
            }
            // collect the result
            int[] result = new int[ri];
            for (int i = 0; i < ri; i++) {
                result[i] = temp[i];
            }
            return Arrays.stream(result).boxed().collect(Collectors.toList());
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
