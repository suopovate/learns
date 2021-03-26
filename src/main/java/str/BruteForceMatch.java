package str;

/**
 * BF字符串暴力匹配算法
 *
 * @author vate
 */
public class BruteForceMatch {

    static int match(CharSequence nStr, CharSequence mStr) {
        assert mStr.length() <= nStr.length();
        // 模式串的长度
        int nSize = nStr.length();
        int mSize = mStr.length();
        // 需要遍历的次数
        for (int i = 0; i < nSize - mSize + 1; i++) {
            // 子串匹配
            if (isMatch(nStr.subSequence(i, i + mSize), mStr)) {
                return i;
            }
        }
        return -1;
    }

    static boolean isMatch(CharSequence nStr, CharSequence mStr) {
        for (int i = 0; i < nStr.length(); i++) {
            if (nStr.charAt(i) != mStr.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String nStr = "asdfghjkl";
        String mStr = "fghjkl";
        System.out.println(match(nStr, mStr));
    }
}
