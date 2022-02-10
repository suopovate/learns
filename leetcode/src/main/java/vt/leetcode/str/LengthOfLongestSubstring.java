package vt.leetcode.str;

import java.util.HashSet;

/**
 * @author vate
 */
public class LengthOfLongestSubstring {

    /**
     * 直接使用字符串本身来判断存在
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_1(String s) {

        if (null == s || "".equals(s)) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int head, tail, maxSubLength = 1;

        for (head = 0; head < s.length(); head++) {
            // 当前 head 的首字母
            String sub = s.substring(head, head + 1);
            for (tail = head + 1; tail < s.length(); tail++) {
                // 子串
                if (sub.indexOf(s.charAt(tail)) < 0) {
                    // 子串添加
                    sub += s.charAt(tail);
                    maxSubLength = Math.max(maxSubLength, sub.length());
                } else {
                    // 跳出本次循环，进入下一个 head 的循环
                    break;
                }
            }
        }

        return maxSubLength;
    }

    /**
     * @param s 使用 hash 来判断字符重复的问题
     * @return
     */
    public int lengthOfLongestSubstring_2(String s) {

        if (null == s || "".equals(s)) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int head, tail, maxSubLength = 1;
        HashSet<Character> characterSet = new HashSet<>();

        for (head = 0; head < s.length(); head++) {
            // 当前 head 的首字母
            characterSet.clear();
            characterSet.add(s.charAt(head));
            for (tail = head + 1; tail < s.length(); tail++) {
                // 子串
                if (!characterSet.contains(s.charAt(tail))) {
                    // 子串添加
                    characterSet.add(s.charAt(tail));
                    maxSubLength = Math.max(maxSubLength, characterSet.size());
                } else {
                    // 跳出本次循环，进入下一个 head 的循环
                    break;
                }
            }
        }

        return maxSubLength;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring_2(""));
    }
}
