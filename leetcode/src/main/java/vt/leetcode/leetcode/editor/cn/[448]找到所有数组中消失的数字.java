package vt.leetcode.leetcode.editor.cn;

import java.util.*;

class FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        Solution solution = new FindAllNumbersDisappearedInAnArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            return findDisappearedNumbers4(nums);
        }

        public List<Integer> findDisappearedNumbers1(int[] nums) {
            int n = nums.length;
            List<Integer> res = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }
            for (int i = 1; i <= n; i++) {
                if (!set.contains(i)) res.add(i);
            }
            return res;
        }

        public List<Integer> findDisappearedNumbers2(int[] nums) {
            int n = nums.length;
            // 每个数都把当前的数放回正常位置,最终所有位置都有自己正常的数.
            // 如果某个位置没有正常的数,说明这个数缺失了
            List<Integer> res = new ArrayList<>();
            // 4,3,2,7,8,2,3,1
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != nums[nums[i] - 1]) {
                    // switch the i and t-1
                    int t = nums[i];
                    nums[i] = nums[t - 1];
                    nums[t - 1] = t;
                }
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) {
                    res.add(i + 1);
                }
            }
            return res;
        }

        public List<Integer> findDisappearedNumbers3(int[] nums) {
            int n = nums.length;
            List<Integer> res = new ArrayList<>();
            // 如果这个数存在,就让它对应下标的值 + n
            for (int num : nums) {
                // 这里为什么取余?因为可能当前下标对应的数已经被 + n 了
                int index = (num - 1) % n;
                nums[index] = nums[index] + n;
            }
            // 不存在的数,它对应下标的值,肯定不会 + n
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= n) {
                    res.add(i + 1);
                }
            }
            return res;
        }

        public List<Integer> findDisappearedNumbers4(int[] nums) {
            int n = nums.length;
            List<Integer> res = new ArrayList<>();
            // 如果这个数存在,就让它对应下标的值 + n
            for (int num : nums) {
                // 这里为什么取余?因为可能当前下标对应的数已经被 + n 了
                int index = Math.abs(num) - 1;
                if (nums[index] > 0) {
                    nums[index] = -nums[index];
                }
            }
            // 不存在的数,它对应下标的值,肯定不会 + n
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    res.add(i + 1);
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
