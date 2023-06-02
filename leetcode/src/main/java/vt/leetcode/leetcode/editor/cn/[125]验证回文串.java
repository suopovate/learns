package vt.leetcode.leetcode.editor.cn;

class ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
        solution.isPalindrome("0P");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPalindrome(String s) {
            if (s == null || s.isEmpty()) {
                return true;
            }
            // 消除噪音
            char[] charArray = s.toCharArray();
            char[] newCharArray = new char[s.length()];
            int caseCount = 0;
            for (int i = 0; i < charArray.length; i++) {
                if (isLetterOrDigit(charArray[i])) {
                    newCharArray[caseCount] = Character.toLowerCase(charArray[i]);
                    caseCount++;
                }
            }
            System.out.println(newCharArray);
            // 验证回文
            int start = 0, end = caseCount - 1;
            while (start <= end) {
                if (newCharArray[start] != newCharArray[end]) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }

        public boolean isLetterOrDigit(char temp) {
            if (temp >= '0' && temp <= '9') {
                return true;
            } else if (temp >= 'a' && temp <= 'z') {return true;} else if (temp >= 'A' && temp <= 'Z') {return true;} else {
                return false;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
