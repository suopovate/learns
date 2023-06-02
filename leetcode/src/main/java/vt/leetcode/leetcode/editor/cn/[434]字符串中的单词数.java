package vt.leetcode.leetcode.editor.cn;

class NumberOfSegmentsInAString {
    public static void main(String[] args) {
        Solution solution = new NumberOfSegmentsInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        /**
         * 两个指针，pre 和 cur
         * 空格 - 非空格 意味着新单词的开始
         * 非空格 - 非空格 同一个单词
         * 非空格 - 空格 当前单词结束
         * 空格 - 空格 空
         */
        public int countSegments(String s) {
            if (s == null || s.isEmpty()) return 0;
            int count = 0;
            char pre = ' ';
            for (int i = 0; i < s.length(); i++) {
                if (pre == ' ' && s.charAt(i) != ' ') {
                    count++;
                }
                pre = s.charAt(i);
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
