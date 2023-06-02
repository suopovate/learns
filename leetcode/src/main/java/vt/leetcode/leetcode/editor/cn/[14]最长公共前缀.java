package vt.leetcode.leetcode.editor.cn;

class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            int minLength = strs[0].length();
            for (int i = 0; i < strs.length; i++) {
                minLength = Math.min(strs[i].length(), minLength);
            }
            StringBuilder publicPrefix = new StringBuilder(minLength);
            for (int i = 0; i < minLength; i++) {
                char curChar = strs[0].charAt(i);
                for (int j = 0; j < strs.length; j++) {
                    if (strs[j].charAt(i) != curChar){
                        return publicPrefix.toString();
                    }
                }
                publicPrefix.append(curChar);
            }
            return publicPrefix.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
