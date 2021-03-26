package str;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 基于散列表的方式减少子串字符比较逻辑的RK匹配算法
 * @author vate
 */
public class RabinKarpMatch {

    static int match(CharSequence nStr, CharSequence mStr) {
        assert mStr.length() <= nStr.length();
        // 模式串的长度
        int mSize = mStr.length();
        int nSize = nStr.length();
        // 主串中 待匹配的子串个数
        int nSubNumber = nSize - mSize + 1;
        // 模式串的哈希值
        int mHash = hash(mStr);
        // 下标代表主串中子串的首字符索引 值代表该子串的哈希值
        int[] hashArr = new int[nSubNumber];
        hashArr[0] = hash(nStr.subSequence(0, mSize));
        // 最大遍历的次数
        for (int i = 0; i < nSubNumber; i++) {
            // 子串匹配
            if (mHash == fastHash(nStr, hashArr, i, mSize) && isBfMatch(nStr.subSequence(i, i + mSize), mStr)) {
                // 哈希匹配的时候 再进行一次BF匹配
                return i;
            }
        }
        return -1;
    }

    static boolean isBfMatch(CharSequence nStr, CharSequence mStr) {
        for (int i = 0; i < nStr.length(); i++) {
            if (nStr.charAt(i) != mStr.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    static int hash(CharSequence chars) {
        int hash = 0;
        for (int i = 0; i < chars.length(); i++) {
            hash += chars.charAt(i);
        }
        return hash;
    }

    /**
     * @param nStr     主串
     * @param hashArr  subStart必须 < hashArr.length
     * @param subIndex 子串的首字符索引值
     * @param mSize    模式串的长度
     * @return
     */
    static int fastHash(CharSequence nStr, int[] hashArr, int subIndex, int mSize) {
        if (hashArr[subIndex] != 0) {
            return hashArr[subIndex];
        }
        // 通过前一个计算得出当前的哈希值
        return hashArr[subIndex] = hashArr[subIndex - 1] - nStr.charAt(subIndex - 1) + nStr.charAt(subIndex + mSize - 1);
    }

    public static void main(String[] args) {
        String nStr = FileUtil.readString(new File("/Users/vate/soft/xag/weather/cal_real/ob.csv"), StandardCharsets.UTF_8);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("jdk匹配");
        System.out.println(
                nStr
                        .indexOf("rfgb")
        );
        stopWatch.stop();
        stopWatch.start("BF匹配");
        System.out.println(
                BruteForceMatch.match(
                        nStr,
                        "rfgb"
                )
        );
        stopWatch.stop();
        stopWatch.start("RK匹配");
        System.out.println(
                RabinKarpMatch.match(
                        nStr,
                        "rfgb"
                )
        );
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
