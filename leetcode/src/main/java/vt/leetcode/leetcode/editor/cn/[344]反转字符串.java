package vt.leetcode.leetcode.editor.cn;

class ReverseString {
    public static void main(String[] args) {
        Solution solution = new ReverseString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void reverseString(char[] s) {
            if (s == null || s.length == 0) {
                return;
            }
            int start = 0, end = s.length - 1;
            while (start < end) {
                char temp = s[start];
                s[start++] = s[end];
                s[end--] = temp;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
