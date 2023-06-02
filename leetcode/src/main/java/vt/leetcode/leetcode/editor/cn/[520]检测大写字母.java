package vt.leetcode.leetcode.editor.cn;

class DetectCapital {
    public static void main(String[] args) {
        Solution solution = new DetectCapital().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean detectCapitalUse(String word) {
            if (word == null || word.isEmpty()) {
                return true;
            }
            // 大写在首字母 ok
            // 全大写 ok
            // 全小写 ok
            char[] charArray = word.toCharArray();
            boolean firstUp = false;
            int upSize = 0;
            for (int i = 0; i < charArray.length; i++) {
                boolean curUp = Character.isUpperCase(charArray[i]);
                if (i == 0) {
                    firstUp = curUp;
                }
                if (Character.isUpperCase(charArray[i])) upSize++;
            }
            return firstUp ? (upSize == charArray.length || upSize == 1) : upSize == 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
