package vt.leetcode.leetcode.editor.cn;

class CanPlaceFlowers {
    public static void main(String[] args) {
        Solution solution = new CanPlaceFlowers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            // 1. 对于每一朵花，检测是否可以插到花坛里去
            // 2. 返回true和当前花的位置，下一朵花，则从当前位置开始寻找
            int start = 0;
            for (int f = 0; f < n; f++) {
                start = canPlaceFlowersRecursion(flowerbed, start);
                if (start < 0) {
                    return false;
                }
            }
            return true;
        }

        public int canPlaceFlowersRecursion(int[] flowerbed, int start) {
            for (int i = start; i < flowerbed.length; i++) {
                // 检测 左右 是否为0就可以了
                int pre = i - 1;
                int after = i + 1;
                if ((pre < 0 || flowerbed[pre] == 0) && (after >= flowerbed.length || flowerbed[after] == 0) && flowerbed[i] == 0) {
                    flowerbed[i] = 1;
                    // 返回下一个位置
                    return i + 2;
                }
            }
            return -1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
