package vt.leetcode.leetcode.editor.cn;

class LengthOfLastWord {
    public static void main(String[] args) {
        Solution solution = new LengthOfLastWord().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLastWord(String s) {
            if (s == null || s.length() == 0) return 0;
            // 从后往前遍历，找到第一个单词就行
            int curStart = -1, curEnd = -1;
            char pre = ' ';
            for (int i = s.length() - 1; i >= 0; i--) {
                if (pre == ' ' && s.charAt(i) != ' ') {
                    if (curEnd != -1) break;
                    curStart = i;
                    curEnd = i;
                }
                if (pre != ' ' && s.charAt(i) != ' ') {
                    curStart = i;
                }
                pre = s.charAt(i);
            }
            return curEnd >= 0 ? curEnd - curStart + 1 : 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
