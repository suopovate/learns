package vt.leetcode.leetcode.editor.cn;

class LicenseKeyFormatting {

    public static void main(String[] args) {
        Solution solution = new LicenseKeyFormatting().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String licenseKeyFormatting(String s, int k) {
            int validCharCount = 0;
            char[] sCharArray = s.toCharArray();
            char[] validCharArray = new char[sCharArray.length];
            for (char c : sCharArray) {
                if (c != '-') {
                    validCharArray[validCharCount] = c;
                    validCharCount++;
                }
            }
            StringBuilder sb = new StringBuilder();
            // 处理第一组不为k的情况
            int firstCount = validCharCount % k;
            if (firstCount > 0) {
                for (int i = 0; i < firstCount; i++) {
                    sb.append(Character.toUpperCase(validCharArray[i]));
                }
                if (firstCount != validCharCount) {
                    sb.append("-");
                }
            }
            // 处理剩余每组都等于k的情况
            int curCount = 0;
            for (int i = firstCount; i < validCharCount; i++) {
                sb.append(Character.toUpperCase(validCharArray[i]));
                curCount++;
                // 考虑边界情况，最后一组不加'-'
                if (curCount == k && i < validCharCount - 1) {
                    sb.append("-");
                    curCount = 0;
                }
            }
            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
