package vt.leetcode.leetcode.editor.cn;

class ArrayNesting {
    public static void main(String[] args) {
        Solution solution = new ArrayNesting().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int arrayNesting(int[] nums) {
            // 0 1 2 3 4
            // 2 3 0 1 4
            // 核心规律就是 它的环是不会重复的 同时可能有多个环
            // 每个元素都属于某一个唯一的环
            // 那我们就只计算第一次碰到这个环的时，环的长度就行了，
            // 所以把碰到过的元素设置为-1，下次再碰到，就说明当前这个元素已经是算过了，不用再算，走下一个环。
            // 有点剪枝的感觉
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                int count = 0;
                int cur = i;
                while (nums[cur] != -1) {
                    count++;
                    int temp = cur;
                    cur = nums[cur];
                    nums[temp] = -1;
                }
                ans = Math.max(ans, count);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
