package vt.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/*z
 *
 * @author vate
 */
public class BackTracingPalindromeAlgorithm {

    public static void main(String[] args) {
        List<List<String>> ret = new ArrayList<>();
        dfs("abb", 0, new ArrayList<>(), ret);
        System.out.println(ret);
    }

    public static void dfs(String s, int start, ArrayList<String> path, List<List<String>> ret) {
        if (start == s.length()) {
            ret.add(new ArrayList(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String left = s.substring(start, i + 1);
            if (isPalindrome(left)) { // 如果左串为回文，则继续递归查找右串的所有子串
                path.add(left);
                dfs(s, i + 1, path, ret);
                // 在下层 dfs 中的结果在最底层已经保存，所以在这里需要清空下层的结果，换一条其他路径
                path.remove(path.size() - 1);
            } else {
                continue;
            }
        }
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
                    head++;
                    tail--;
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
