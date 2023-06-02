package vt.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;

class BullsAndCows {
    public static void main(String[] args) {
        Solution solution = new BullsAndCows().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String getHint(String secret, String guess) {
            // 已经匹配过的秘密数字
            // k 索引 v 值
            HashMap<Integer, Integer> matched = new HashMap<>();
            // 公牛，奶牛数
            int bulls = 0, cows = 0;
            char[] secretCharArray = secret.toCharArray();
            char[] guessCharArray = guess.toCharArray();
            // 0-9，分别存储，当 secret 和 guess 同一个位置的数字不同时，总共出现的次数
            // 1234
            // 1323
            // cs[2] = 1 cs[3] = 1
            // cg[2] = 1 cg[3] = 2
            // 然后 对于 数字3 由于 cs的值是1 cg多了个1 那就猜对了 2-1= 1 咯，多出的不算
            int[] countSecret = new int[10], countGuess = new int[10];
            for (int i = 0; i < secretCharArray.length; i++) {
                int sv = secretCharArray[i] - '0';
                int gv = guessCharArray[i] - '0';
                if (sv == gv) {
                    bulls++;
                } else {
                    countSecret[sv]++;
                    countGuess[gv]++;
                }
            }

            for (int i = 0; i < 10; i++) {
                cows += Math.min(countSecret[i], countGuess[i]);
            }

            return bulls + "A" + cows + "B";
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
