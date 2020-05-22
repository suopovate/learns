package vt.leetcode.str;

/**
 * huiwen=3
 *
 * @author vate
 */
public class PalindromeAlgorithm {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if ("".equals(s.trim())) {
            return true;
        }
        s = s.toUpperCase();
        char[] chars = s.toCharArray();
        // 定义两个index 从前后一起扫描
        int head = 0, tail = chars.length - 1, validCount = 0;
        while (true) {
            char headC = chars[head], tailC = chars[tail];
            if (isAlphaOrNumber(headC)) {
                if (isAlphaOrNumber(tailC)) {
                    validCount += head == tail ? 1 : 2;
                    head++;tail--;
                    if (headC != tailC) {
                        return false;
                    }
                } else {
                    tail--;
                }
            } else {
                head++;
            }
            if (head >= tail) {
                return true;
            }
        }
    }

    public static boolean isAlphaOrNumber(char c) {
        return isAlpha(c) || isNumber(c);
    }

    public static boolean isAlpha(char c) {
        return 65 <= c && c <= 90 || 97 <= c && c <= 122;
    }

    public static boolean isAlpha(int c) {
        return 65 <= c && c <= 90 || 97 <= c && c <= 122;
    }

    public static boolean isNumber(char c) {
        return 48 <= c && c <= 57;
    }

    public static boolean isNumber(int c) {
        return 48 <= c && c <= 57;
    }

    public static boolean isUpperChar(char c) {
        return 65 <= c && c <= 90;
    }

    public static boolean isLowerChar(char c) {
        return 97 <= c && c <= 122;
    }

    public static char convertUpperOrLower(char c) {
        return (char) (65 <= c && c <= 90 ? c + 32 : 97 <= c && c <= 122 ? c - 32 : c);
    }

}
