package vt.leetcode.leetcode.editor.cn;

import java.util.LinkedHashMap;
import java.util.Map;

class FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        Solution solution = new FirstUniqueCharacterInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstUniqChar(String s) {
            LinkedHashMap<Character, Integer> counts = new LinkedHashMap<>();
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (counts.containsKey(chars[i])) {
                    counts.put(chars[i], -1);
                } else {
                    counts.put(chars[i], i);
                }
            }
            for (Character character : counts.keySet()) {
                if (counts.get(character) != -1){
                    return counts.get(character);
                }
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
