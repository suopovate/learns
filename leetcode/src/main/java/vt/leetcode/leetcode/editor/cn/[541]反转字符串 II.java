package vt.leetcode.leetcode.editor.cn;

class ReverseStringIi {
    public static void main(String[] args) {
        Solution solution = new ReverseStringIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String reverseStr(String s, int k) {
            char[] charArray = s.toCharArray();
            int curStart = -1, curEnd = -1;
            for (int i = 0; i < s.length(); i++) {
                if (curStart == -1){
                    curEnd = curStart = i;
                } else {
                    curEnd = i;
                }
                if (curEnd - curStart + 1 >= 2 * k) {
                    reverseString(charArray, curStart, curStart + k - 1);
                    curStart = -1;
                    curEnd = -1;
                }
            }
            if (curEnd - curStart + 1 <= k) {
                reverseString(charArray, curStart, curEnd);
            } else {
                reverseString(charArray, curStart, curStart + k - 1);
            }
            return new String(charArray);
        }

        public void reverseString(char[] s, int start, int end) {
            if (s == null || s.length == 0) {
                return;
            }
            while (start < end) {
                char temp = s[start];
                s[start++] = s[end];
                s[end--] = temp;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
