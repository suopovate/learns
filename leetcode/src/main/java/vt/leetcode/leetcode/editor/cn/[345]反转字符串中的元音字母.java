package vt.leetcode.leetcode.editor.cn;

class ReverseVowelsOfAString {
    public static void main(String[] args) {
        Solution solution = new ReverseVowelsOfAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private char[] alphas = new char[]{ 'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U' };

        public String reverseVowels(String s) {
            char[] charArray = s.toCharArray();
            int st = 0, e = charArray.length - 1;
            while (st < e) {
                while (!isYuanYin(charArray[st]) && st < charArray.length - 1) {
                    st++;
                }
                while (!isYuanYin(charArray[e]) && e > 0) {
                    e--;
                }
                if (st >= e) {
                    break;
                }
                // swap
                char t = charArray[st];
                charArray[st] = charArray[e];
                charArray[e] = t;
                st++;
                e--;
            }
            return new String(charArray);
        }

        public boolean isYuanYin(char c) {
            for (char alpha : alphas) {
                if (alpha == c) return true;
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
