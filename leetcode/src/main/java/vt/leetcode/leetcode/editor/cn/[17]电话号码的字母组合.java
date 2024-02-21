package vt.leetcode.leetcode.editor.cn;

import cn.hutool.core.util.StrUtil;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;

class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        Map<Integer, List<String>> data = new HashMap<Integer, List<String>>() {{
            put(2, Arrays.asList("a", "b", "c"));
            put(3, Arrays.asList("d", "e", "f"));
            put(4, Arrays.asList("g", "h", "i"));
            put(5, Arrays.asList("j", "k", "l"));
            put(6, Arrays.asList("m", "n", "o"));
            put(7, Arrays.asList("p", "q", "r", "s"));
            put(8, Arrays.asList("t", "u", "v"));
            put(9, Arrays.asList("w", "x", "y", "z"));
        }};

        public List<String> letterCombinations(String digits) {
            if (digits.isEmpty()) {
                return new ArrayList<>();
            }
            List<String> combinations = new ArrayList<>();
            findLetterCombinations(combinations, "", digits);
            return combinations;
        }

        public void findLetterCombinations(List<String> combinations, String prefix, String digits) {
            if (digits.isEmpty()) {
                combinations.add(prefix);
                return;
            }
            Integer number = Integer.valueOf(digits.substring(0, 1));
            List<String> letters = data.get(number);
            for (String letter : letters) {
                findLetterCombinations(combinations, prefix + letter, digits.substring(1));
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
