package vt.leetcode.str;

import java.io.Console;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author vate
 */
public class WordSplitAlgorithm {
    public static boolean wordBreak(String s, List<String> wordDict) {
        return doWordBreak(s, new HashSet<>(wordDict));
    }

    public static boolean doWordBreak(String s, Set<String> wordDict) {
        if (wordDict.contains(s)){
            return true;
        }
        int indexEnd = 1;
        for (; indexEnd <= s.length(); indexEnd++) {
            if (wordDict.contains(s.substring(0,indexEnd))){
                if (doWordBreak(s.substring(indexEnd),wordDict)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
//        System.out.println(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
                , Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }
}